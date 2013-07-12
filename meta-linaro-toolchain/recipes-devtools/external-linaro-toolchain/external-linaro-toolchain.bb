require recipes-core/eglibc/eglibc-package.inc

INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# License applies to this recipe code, not the toolchain itself
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
	file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
	file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
"

PROVIDES += "\
	linux-libc-headers \
	virtual/${TARGET_PREFIX}gcc \
	virtual/${TARGET_PREFIX}g++ \
	virtual/${TARGET_PREFIX}gcc-initial \
	virtual/${TARGET_PREFIX}binutils \
	virtual/${TARGET_PREFIX}libc-for-gcc \
	virtual/${TARGET_PREFIX}compilerlibs \
	virtual/libc \
	virtual/libintl \
	virtual/libiconv \
	glibc-thread-db \
	eglibc \
	libgcc \
	libg2c \
	libg2c-dev \
	libssp \
	libssp-dev \
	libssp-staticdev \
	libgfortran \
	libgfortran-dev \
	libmudflap \
	libmudflap-dev \
	libgomp \
	libgomp-dev \
	libgomp-staticdev \
	libitm \
	libitm-dev \
	libitm-staticdev \
	virtual/linux-libc-headers \
"

PV = "${ELT_VER_MAIN}"
PR = "r2"

# https://launchpad.net/linaro-toolchain-binaries
SRC_URI = "file://SUPPORTED"

do_install() {
	install -d ${D}${base_libdir}
	install -d ${D}${bindir}
	install -d ${D}${sbindir}
	install -d ${D}${libdir}
	install -d ${D}${libexecdir}
	install -d ${D}${datadir}
	install -d ${D}${includedir}

	cp -a ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/lib/*  ${D}${base_libdir}
	if [ -d ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/lib/${ELT_TARGET_SYS} ]; then
		cp -a ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/lib/${ELT_TARGET_SYS}/*  ${D}${base_libdir}
	else
		cp -a ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/lib/*  ${D}${base_libdir}
	fi
	if [ -d ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/usr/lib/${ELT_TARGET_SYS} ]; then
		cp -a ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/usr/lib/${ELT_TARGET_SYS}/*  ${D}${libdir}
	else
		cp -a ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/usr/lib/*  ${D}${libdir}
	fi
	cp -a ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/usr/share/*  ${D}${datadir}
	cp -a ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/usr/include/*  ${D}${includedir}
	if [ -d ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/usr/include/${ELT_TARGET_SYS} ]; then
		cp -a ${EXTERNAL_TOOLCHAIN}/${ELT_TARGET_SYS}/libc/usr/include/${ELT_TARGET_SYS}/*  ${D}${includedir}

		rm -r ${D}${includedir}/${ELT_TARGET_SYS}
	fi

	# fix up the copied symlinks (they are still pointing to the multiarch directory)
	ln -sf ld-${ELT_VER_LIBC}.so ${D}${base_libdir}/ld-linux.so.3
	ln -sf ../../lib/libnsl.so.1 ${D}${libdir}/libnsl.so
	ln -sf ../../lib/librt.so.1 ${D}${libdir}/librt.so
	ln -sf ../../lib/libcrypt.so.1 ${D}${libdir}/libcrypt.so
	ln -sf ../../lib/libnss_nis.so.2 ${D}${libdir}/libnss_nis.so
	ln -sf ../../lib/libresolv.so.2 ${D}${libdir}/libresolv.so
	ln -sf ../../lib/libnss_dns.so.2 ${D}${libdir}/libnss_dns.so
	ln -sf ../../lib/libnss_hesiod.so.2 ${D}${libdir}/libnss_hesiod.so
	ln -sf ../../lib/libutil.so.1 ${D}${libdir}/libutil.so
	ln -sf ../../lib/libnss_files.so.2 ${D}${libdir}/libnss_files.so
	ln -sf ../../lib/libnss_compat.so.2 ${D}${libdir}/libnss_compat.so
	ln -sf ../../lib/libcidn.so.1 ${D}${libdir}/libcidn.so
	ln -sf ../../lib/libBrokenLocale.so.1 ${D}${libdir}/libBrokenLocale.so
	ln -sf ../../lib/libthread_db.so.1 ${D}${libdir}/libthread_db.so
	ln -sf ../../lib/libanl.so.1 ${D}${libdir}/libanl.so
	ln -sf ../../lib/libdl.so.2 ${D}${libdir}/libdl.so
	ln -sf ../../lib/libnss_nisplus.so.2 ${D}${libdir}/libnss_nisplus.so
	ln -sf ../../lib/libm.so.6 ${D}${libdir}/libm.so

	if [ -d ${D}${base_libdir}/arm-linux-gnueabi ]; then
	   rm -rf ${D}${base_libdir}/arm-linux-gnueabi
	fi

	if [ -d ${D}${base_libdir}/ldscripts ]; then
	   rm -rf ${D}${base_libdir}/ldscripts
	fi

	if [ -f ${D}${libdir}/libc.so ];then
		sed -i -e "s# /lib/${ELT_TARGET_SYS}# ../../lib#g" -e "s# /usr/lib/${ELT_TARGET_SYS}# .#g" ${D}${libdir}/libc.so
	fi
	if [ -f ${D}${base_libdir}/libc.so ];then
		sed -i -e "s# /lib/${ELT_TARGET_SYS}# ../../lib#g" -e "s# /usr/lib/${ELT_TARGET_SYS}# .#g" ${D}${base_libdir}/libc.so
	fi
	if [ -f ${D}${libdir}/libpthread.so ];then
		sed -i -e "s# /lib/${ELT_TARGET_SYS}# ../../lib#g" -e "s# /usr/lib/${ELT_TARGET_SYS}# .#g" ${D}${libdir}/libpthread.so
	fi
	if [ -f ${D}${base_libdir}/libpthread.so ];then
		sed -i -e "s# /lib/${ELT_TARGET_SYS}# ../../lib#g" -e "s# /usr/lib/${ELT_TARGET_SYS}# .#g" ${D}${base_libdir}/libpthread.so
	fi
}

PACKAGES =+ "\
	libgcc \
	libgcc-dev \
	libstdc++ \
	libstdc++-dev \
	libstdc++-staticdev \
	linux-libc-headers \
	linux-libc-headers-dev \
	libssp \
	libssp-dev \
	libssp-staticdev \
	libgfortran \
	libgfortran-dev \
	libmudflap \
	libmudflap-dev \
	libmudflap-staticdev \
	libgomp \
	libgomp-dev \
	libgomp-staticdev \
	libitm \
	libitm-dev \
	libitm-staticdev \
"

INSANE_SKIP_${PN}-dbg = "staticdev"
INSANE_SKIP_${PN}-utils += "ldflags"
INSANE_SKIP_libstdc++ += "ldflags"
INSANE_SKIP_libgcc += "ldflags"
INSANE_SKIP_gdbserver += "ldflags"

PKG_${PN} = "eglibc"
PKG_${PN}-dev = "eglibc-dev"
PKG_${PN}-doc = "eglibc-doc"
PKG_${PN}-dbg = "eglibc-dbg"
PKG_${PN}-pic = "eglibc-pic"
PKG_${PN}-utils = "eglibc-utils"
PKG_${PN}-gconv = "eglibc-gconv"
PKG_${PN}-extra-nss = "eglibc-extra-nss"
PKG_${PN}-thread-db = "eglibc-thread-db"
PKG_${PN}-pcprofile = "eglibc-pcprofile"

PKGV_${PN} = "${ELT_VER_LIBC}"
PKGV_${PN}-dev = "${ELT_VER_LIBC}"
PKGV_${PN}-doc = "${ELT_VER_LIBC}"
PKGV_${PN}-dbg = "${ELT_VER_LIBC}"
PKGV_${PN}-pic = "${ELT_VER_LIBC}"
PKGV_${PN}-utils = "${ELT_VER_LIBC}"
PKGV_${PN}-gconv = "${ELT_VER_LIBC}"
PKGV_${PN}-extra-nss = "${ELT_VER_LIBC}"
PKGV_${PN}-thread-db = "${ELT_VER_LIBC}"
PKGV_${PN}-pcprofile = "${ELT_VER_LIBC}"
PKGV_catchsegv = "${ELT_VER_LIBC}"
PKGV_libsegfault = "${ELT_VER_LIBC}"
PKGV_sln = "${ELT_VER_LIBC}"
PKGV_nscd = "${ELT_VER_LIBC}"
PKGV_ldd = "${ELT_VER_LIBC}"
PKGV_libgcc = "${ELT_VER_GCC}"
PKGV_libgcc-dev = "${ELT_VER_GCC}"
PKGV_libstdc++ = "${ELT_VER_GCC}"
PKGV_libstdc++-dev = "${ELT_VER_GCC}"
PKGV_libstdc++-staticdev = "${ELT_VER_GCC}"
PKGV_linux-libc-headers = "${ELT_VER_KERNEL}"
PKGV_linux-libc-headers-dev = "${ELT_VER_KERNEL}"
PKGV_gdbserver = "${ELT_VER_GDBSERVER}"

FILES_libgcc = "${base_libdir}/libgcc_s.so.1"
FILES_libgcc-dev = "${base_libdir}/libgcc_s.so"
FILES_libstdc++ = "${base_libdir}/libstdc++.so.*"
FILES_libstdc++-dev = "\
	${includedir}/c++/${PV} \
	${base_libdir}/libstdc++.so"
FILES_libstdc++-staticdev = "\
	${base_libdir}/libstdc++.a \
	${base_libdir}/libsupc++.a \
"
FILES_linux-libc-headers = "\
	${includedir}/asm* \
	${includedir}/linux \
	${includedir}/mtd \
	${includedir}/rdma \
	${includedir}/scsi \
	${includedir}/sound \
	${includedir}/video \
"
FILES_${PN} += "\
	${libdir}/bin \
	${libdir}/locale \
	${libdir}/gconv/gconv-modules \
	${datadir}/zoneinfo \
	${base_libdir}/libcrypt*.so.* \
	${base_libdir}/libcrypt-*.so \
	${base_libdir}/libc.so.* \
	${base_libdir}/libc-*.so \
	${base_libdir}/libm.so.* \
	${base_libdir}/libmemusage.so \
	${base_libdir}/libm-*.so \
	${base_libdir}/ld*.so.* \
	${base_libdir}/ld-*.so \
	${base_libdir}/libpthread*.so.* \
	${base_libdir}/libpthread-*.so \
	${base_libdir}/libresolv*.so.* \
	${base_libdir}/libresolv-*.so \
	${base_libdir}/librt*.so.* \
	${base_libdir}/librt-*.so \
	${base_libdir}/libutil*.so.* \
	${base_libdir}/libutil-*.so \
	${base_libdir}/libnsl*.so.* \
	${base_libdir}/libnsl-*.so \
	${base_libdir}/libnss_files*.so.* \
	${base_libdir}/libnss_files-*.so \
	${base_libdir}/libnss_compat*.so.* \
	${base_libdir}/libnss_compat-*.so \
	${base_libdir}/libnss_dns*.so.* \
	${base_libdir}/libnss_dns-*.so \
	${base_libdir}/libnss_nis*.so.* \
	${base_libdir}/libnss_nisplus-*.so \
	${base_libdir}/libnss_nisplus*.so.* \
	${base_libdir}/libnss_nis-*.so \
	${base_libdir}/libnss_hesiod*.so.* \
	${base_libdir}/libnss_hesiod-*.so \
	${base_libdir}/libdl*.so.* \
	${base_libdir}/libdl-*.so \
	${base_libdir}/libanl*.so.* \
	${base_libdir}/libanl-*.so \
	${base_libdir}/libBrokenLocale*.so.* \
	${base_libdir}/libBrokenLocale-*.so \
	${base_libdir}/libcidn*.so.* \
	${base_libdir}/libcidn-*.so \
	${base_libdir}/libthread_db*.so.* \
	${base_libdir}/libthread_db-*.so \
	${base_libdir}/libmemusage.so \
	${base_libdir}/libSegFault.so \
	${base_libdir}/libpcprofile.so \
    "

FILES_libstdc++ = "${base_libdir}/libstdc++.so.*"
FILES_libstdc++-dev = "\
  ${includedir}/c++/ \
  ${base_libdir}/libstdc++.so \
  ${base_libdir}/libstdc++.la \
  ${base_libdir}/libsupc++.la"
FILES_libstdc++-staticdev = "\
  ${base_libdir}/libstdc++.a \
  ${base_libdir}/libsupc++.a"

FILES_libstdc++-precompile-dev = "${includedir}/c++/${TARGET_SYS}/bits/*.gch"

FILES_libssp = "${base_libdir}/libssp.so.*"
FILES_libssp-dev = " \
  ${base_libdir}/libssp*.so \
  ${base_libdir}/libssp*_nonshared.a \
  ${base_libdir}/libssp*.la \
  ${base_libdir}/gcc/${TARGET_SYS}/${BINV}/include/ssp"
FILES_libssp-staticdev = " \
  ${base_libdir}/libssp*.a"

FILES_libgfortran = "${base_libdir}/libgfortran.so.*"
FILES_libgfortran-dev = " \
  ${base_libdir}/libgfortran.a \
  ${base_libdir}/libgfortran.so \
  ${base_libdir}/libgfortranbegin.a"

FILES_libmudflap = "${base_libdir}/libmudflap*.so.*"
FILES_libmudflap-dev = "\
  ${base_libdir}/libmudflap*.so \
  ${base_libdir}/libmudflap*.a \
  ${base_libdir}/libmudflap*.la"

FILES_libitm = "${base_libdir}/libitm*${SOLIBS}"
FILES_libitm-dev = "\
  ${base_libdir}/libitm*${SOLIBSDEV} \
  ${base_libdir}/libitm*.la \
  ${base_libdir}/libitm.spec \
  "
FILES_libitm-staticdev = "\
  ${base_libdir}/libitm*.a \
  "

FILES_libgomp = "${base_libdir}/libgomp*${SOLIBS}"
FILES_libgomp-dev = "\
  ${base_libdir}/libgomp*${SOLIBSDEV} \
  ${base_libdir}/libgomp*.la \
  ${base_libdir}/libgomp.spec \
  ${base_libdir}/gcc/${TARGET_SYS}/${BINV}/include/omp.h \
  "
FILES_libgomp-staticdev = "\
  ${base_libdir}/libgomp*.a \
  "
ELT_VER_MAIN ??= ""

python () {
    if not d.getVar("ELT_VER_MAIN"):
	raise bb.parse.SkipPackage("External Linaro toolchain not configured (ELT_VER_MAIN not set).")
