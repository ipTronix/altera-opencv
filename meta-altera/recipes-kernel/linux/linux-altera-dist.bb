require recipes-kernel/linux/linux-altera.inc

SRC_URI = "http://www.rocketboards.org/linux-socfpga.tgz;md5sum=${ALTERA_LINUX_MD5SUM};sha256sum=${ALTERA_LINUX_SHA256SUM}"

SRCREV_pn-${PN} = "${AUTOREV}"

S = "${WORKDIR}/linux-socfpga"

DTB_SUBDIR="dts/"

