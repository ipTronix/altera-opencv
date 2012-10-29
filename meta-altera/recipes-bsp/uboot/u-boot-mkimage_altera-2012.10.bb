DESCRIPTION = "U-boot bootloader mkimage tool"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${S}/COPYING;startline=26;md5=1707d6db1d42237583f50183a5651ecb"
SECTION = "bootloader"

UBOOT_VERSION = "altera-2012.10"
PV = "${UBOOT_VERSION}"
PR = "r0"

SRC_URI = "http://open-source.altera.com/u-boot-${PV}.tgz;__UBOOT_CHECKSUMS__"

S = "${WORKDIR}/u-boot-${PV}"

BBCLASSEXTEND = "native nativesdk"

EXTRA_OEMAKE = 'HOSTCC="${CC}" HOSTLD="${LD}" HOSTLDFLAGS="${LDFLAGS}" HOSTSTRIP=true'

do_compile () {
  oe_runmake tools
}

do_install () {
  install -d ${D}${bindir}
  install -m 0755 tools/mkimage ${D}${bindir}/uboot-mkimage
  ln -sf uboot-mkimage ${D}${bindir}/mkimage
}

