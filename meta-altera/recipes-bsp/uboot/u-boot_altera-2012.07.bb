LIC_FILES_CHKSUM = "file://${S}/COPYING;startline=26;md5=1707d6db1d42237583f50183a5651ecb"

PR = "${INC_PR}.0"

SRC_URI = "http://open-source.altera.com/${PN}-${PV}.tgz"
SRC_URI[md5sum] = "__UBOOT_MD5SUM__"
SRC_URI[sha256sum] = "__UBOOT_SHA256SUM__"

require u-boot-altera.inc
