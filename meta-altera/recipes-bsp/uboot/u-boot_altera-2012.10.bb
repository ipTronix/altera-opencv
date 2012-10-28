UBOOT_VERSION = "2012.10"

UBOOT_MACHINE = "socfpga_cyclone5_config"
LIC_FILES_CHKSUM = "file://${S}/COPYING;startline=26;md5=1707d6db1d42237583f50183a5651ecb"

PR = "${INC_PR}.0"

SRC_URI = "http://open-source.altera.com/${PN}-${PV}.tgz;md5sum=37e7d456addb239363ba5c91264b33ca;sha256sum=80fb55ede09580c2ec3e491a6fb7ad58dabf468a7635bb6f1c620f7301b9a14c"

require u-boot-altera.inc
