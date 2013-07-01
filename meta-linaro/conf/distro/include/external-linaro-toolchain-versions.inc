def elt_run(d, cmd, *args):
	import bb.process
	import subprocess

	topdir = d.getVar('TOPDIR', True)
	toolchain_path = d.getVar('EXTERNAL_TOOLCHAIN', True)
	if not toolchain_path:
		return 'UNKNOWN', 'UNKNOWN'

	target_prefix = d.getVar('TARGET_PREFIX', True)
	path = os.path.join(toolchain_path, 'bin', target_prefix + cmd)
	args = [path] + list(args)

	return bb.process.run(args, cwd=topdir, stderr=subprocess.PIPE)

def elt_get_version(d):
	try:
		stdout, stderr = elt_run(d, 'gcc', '-v')
	except bb.process.CmdError as exc:
		bb.error('Failed to obtain external Linaro toolchain version: %s' % exc)
		return 'UNKNOWN'
	else:
		last_line = stderr.splitlines()[-1]
		return last_line

def elt_get_main_version(d):
	version = elt_get_version(d)
	if version != 'UNKNOWN':
		return version.split()[-1].rstrip(')')
	else:
		return version

def elt_get_gcc_version(d):
	version = elt_get_version(d)
	if version != 'UNKNOWN':
		return version.split()[2]
	else:
		return version

def elt_get_libc_version(d):
	import os,bb
	syspath = bb.data.expand('${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}', d)
	if not syspath:
		return 'UNKNOWN'

	libpath = syspath + '/libc/lib/${ELT_TARGET_SYS}/'

	if os.path.exists(libpath):
		for file in os.listdir(libpath):
			if file.find('libc-') == 0:
				return file[5:-3]
	return 'UNKNOWN'

def elt_get_kernel_version(d):
	import os,bb
	syspath = bb.data.expand('${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}', d)
	if not syspath:
		return 'UNKNOWN'

	vf = syspath + '/libc/usr/include/linux/version.h'

	try:
		f = open(vf, 'r')
	except (OSError, IOError):
		return 'UNKNOWN'

	l = f.readlines();
	f.close();
	for s in l:
		if s.find('LINUX_VERSION_CODE') > 0:
			ver = int(s.split()[2])
			maj = ver / 65536
			ver = ver % 65536
			min = ver / 256
			ver = ver % 256
			return str(maj)+'.'+str(min)+'.'+str(ver)
	return 'UNKNOWN'

def elt_get_gdb_version(d):
	try:
		stdout, stderr = elt_run(d, 'gdb', '-v')
	except CmdError:
		return 'UNKNOWN'
	else:
		first_line = stdout.splitlines()[0]
		return first_line.split()[-1]

python external_linaro_toolchain_version_handler () {
	if not isinstance(e, bb.event.ConfigParsed):
		return
	d = e.data
	ld = d.createCopy()
	ld.finalize()

	d.setVar('ELT_VER_MAIN', elt_get_main_version(ld))
	d.setVar('ELT_VER_GCC', elt_get_gcc_version(ld))
	d.setVar('ELT_VER_LIBC', elt_get_libc_version(ld))
	d.setVar('ELT_VER_KERNEL', elt_get_kernel_version(ld))
	d.setVar('ELT_VER_GDB', elt_get_gdb_version(ld))
}
addhandler external_linaro_toolchain_version_handler
