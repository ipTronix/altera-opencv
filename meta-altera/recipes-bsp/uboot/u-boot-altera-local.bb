LIC_FILES_CHKSUM = "file://${S}/COPYING;startline=26;md5=1707d6db1d42237583f50183a5651ecb"

PROVIDES="u-boot-altera-local"
#PR = "${INC_PR}.0"

U_BOOT_SRC_DIR ?= "${WORKDIR}"
U_BOOT_DIR_NAME ?= "${@(d.getVar('U_BOOT_SRC_DIR', True).rstrip('/').split('/')[-1])}"

SRC_URI = "file://${U_BOOT_SRC_DIR}"

S="${WORKDIR}/${U_BOOT_DIR_NAME}"

require u-boot-altera.inc
