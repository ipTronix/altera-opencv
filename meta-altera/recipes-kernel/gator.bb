inherit module
DESCRIPTION = "ARM DS5 Gator Debugger"
AUTHOR = "Jim Rucker <jrucker@altera.com>"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://${S}/${PN}/driver/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH="socfpga5xs1"
SRC_URI = "http://open-source.altera.com/${PN}-${PV}.tgz"

S = "${WORKDIR}"

DEPENDS = "virtual/kernel"
PROVIDES = "virtual/gator"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	cd ${S}/${PN}/driver
	CROSS_COMPILE=${TARGET_PREFIX}
	STAGING_KERNEL_DIRECTORY=${TMPDIR}/work/${MULTIMACH_TARGET_SYS}/${PREFERRED_PROVIDER_virtual/kernel}-${PREFERRED_VERSION_linux-altera}-r1/${PREFERRED_PROVIDER_virtual/kernel}-${PREFERRED_VERSION_linux-altera}
	${MAKE} -C ${STAGING_KERNEL_DIRECTORY} M=`pwd` ARCH=${TARGET_ARCH} CROSS_COMPILE=${CROSS_COMPILE} modules
	cd ../daemon
	${MAKE} -j5 ARCH="arm" CROSS_COMPILE=${CROSS_COMPILE}
}

do_install() {
	DEST_DIR=${QPEDIR}/lib/modules
	mkdir -p $DEST_DIR
	cp ${S}/${PN}/driver/gator.ko $DEST_DIR
	cp ${S}/${PN}/daemon/gatord $DEST_DIR
}
