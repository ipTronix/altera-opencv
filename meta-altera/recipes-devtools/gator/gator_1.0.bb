inherit module

DESCRIPTION = "ARM DS5 Gator Debugger"
AUTHOR = "Jim Rucker <jrucker@altera.com>"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://driver/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

BP="${BPN}"
SRC_URI = "http://open-source.altera.com/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "3b7242837dbae214421bb5f34b0849f2"
SRC_URI[sha256sum] = "2a94f662a10eb1e4f161207d259795c33c3ca3f5e4f9ae55f6b7e30164e06b7e"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	echo "Using Custom compile step"
	cd ${S}/daemon
	${MAKE} -j5 ARCH="arm" CROSS_COMPILE=${TARGET_PREFIX}
	cd ${S}/driver
	${MAKE} -C ${STAGING_KERNEL_DIR} M=`pwd` ARCH=${TARGET_ARCH} CROSS_COMPILE=${TARGET_PREFIX} modules
}

do_install() {
	DEST_DIR=${D}/${libdir}/modules
	install -d ${DEST_DIR}
	cp driver/gator.ko ${DEST_DIR}
	cp daemon/gatord ${DEST_DIR}
}
