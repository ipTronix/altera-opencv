UBOOT_VERSION = "2012.10"

LIC_FILES_CHKSUM = "file://${S}/COPYING;startline=26;md5=1707d6db1d42237583f50183a5651ecb"

PR = "${INC_PR}.0"

SRC_URI = "http://open-source.altera.com/${PN}-${PV}.tgz;__UBOOT_CHECKSUMS__"

require u-boot-altera.inc
