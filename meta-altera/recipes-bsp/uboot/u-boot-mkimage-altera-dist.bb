require u-boot-mkimage-altera.inc

SRC_URI = "http://www.rocketboards.org/uboot-socfpga.tgz;md5sum=${ALTERA_LINUX_MD5SUM};sha256sum=${ALTERA_LINUX_SHA256SUM}"

S = "${WORKDIR}/uboot-socfpga"

