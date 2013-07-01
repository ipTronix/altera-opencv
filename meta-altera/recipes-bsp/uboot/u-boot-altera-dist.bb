require u-boot-altera.inc

SRC_URI = "http://www.rocketboards.org/uboot-socfpga.tgz;md5sum=${ALTERA_UBOOT_MD5SUM};sha256sum=${ALTERA_UBOOT_SHA256SUM}"

S = "${WORKDIR}/uboot-socfpga"

