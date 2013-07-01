require u-boot-altera.inc

U_BOOT_SRC_DIR ?= "${WORKDIR}"
U_BOOT_DIR_NAME ?= "${@(d.getVar('U_BOOT_SRC_DIR', True).rstrip('/').split('/')[-1])}"

SRC_URI = "file://${U_BOOT_SRC_DIR}"

S="${WORKDIR}/${U_BOOT_DIR_NAME}"

