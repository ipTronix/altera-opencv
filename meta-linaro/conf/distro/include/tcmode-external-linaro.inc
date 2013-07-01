#
# Configuration to use an external Linaro binary toolchain
#

EXTERNAL_TOOLCHAIN ?= "/usr/local/linaro-binary-toolchain/${TARGET_ARCH}"

TOOLCHAIN_PATH_ADD = "${EXTERNAL_TOOLCHAIN}/bin:"
PATH =. "${TOOLCHAIN_PATH_ADD}"

ELT_TARGET_SYS = "arm-linux-gnueabihf"
TARGET_PREFIX = "${ELT_TARGET_SYS}-"

PREFERRED_PROVIDER_linux-libc-headers = "external-linaro-toolchain"
PREFERRED_PROVIDER_linux-libc-headers-dev = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}gcc = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}gcc-initial = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}g++ = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}binutils = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}libc-for-gcc = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}libc-initial = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}compilerlibs = "external-linaro-toolchain"
PREFERRED_PROVIDER_libgcc = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/libc = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/libintl = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/libiconv = "external-linaro-toolchain"
PREFERRED_PROVIDER_glibc-thread-db = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/linux-libc-headers = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/${BUILD_ARCH}-pokysdk-linux-gcc-crosssdk = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/nativesdk-${BUILD_ARCH}-pokysdk-linux-libc-for-gcc = "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/nativesdk-libintl = "nativesdk-gettext"

TARGET_CPPFLAGS_prepend = " -isystem${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/include "
TARGET_LDFLAGS_prepend = " -L${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/lib -Wl,-rpath-link,${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/lib "

TOOLCHAIN_OPTIONS = " --sysroot=${STAGING_DIR_HOST}"

DISTRO_FEATURES_LIBC = "ipv4 ipv6 libc-backtrace libc-big-macros libc-bsd libc-cxx-tests libc-catgets libc-crypt \
			libc-crypt-ufc libc-db-aliases libc-envz libc-fcvt libc-fmtmsg libc-fstab libc-ftraverse \
			libc-getlogin libc-idn libc-inet-anl libc-libm libc-libm-big \
			libc-memusage libc-nis libc-nsswitch libc-rcmd libc-rtld-debug libc-spawn libc-streams libc-sunrpc \
			libc-utmp libc-utmpx libc-wordexp libc-posix-clang-wchar libc-posix-regexp libc-posix-regexp-glibc \
			libc-posix-wchar-io"

ENABLE_BINARY_LOCALE_GENERATION = "0"
GLIBC_INTERNAL_USE_BINARY_LOCALE = "precompiled"

def populate_toolchain_links(d):
    import errno
    import os
    from glob import glob

    d = d.createCopy()
    d.finalize()

    pattern = bb.data.expand('${EXTERNAL_TOOLCHAIN}/bin/${TARGET_PREFIX}*', d)
    files = glob(pattern)
    if not files:
        bb.fatal("Unable to populate toolchain binary symlinks")

    bindir = d.getVar('STAGING_BINDIR_TOOLCHAIN', True)
    bb.mkdirhier(bindir)
    for f in files:
        base = os.path.basename(f)
        newpath = os.path.join(bindir, base)
        try:
            os.symlink(f, newpath)
        except OSError as exc:
            if exc.errno == errno.EEXIST:
                break
            bb.fatal("Unable to populate toolchain binary symlink for %s: %s" % (newpath, exc))

require conf/distro/include/external-linaro-toolchain-versions.inc
