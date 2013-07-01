require linux-libc-headers-altera.inc

LINUX_SRC_DIR ?= "${WORKDIR}"
SRC_URI = "file://${LINUX_SRC_DIR}"

SRCREV_pn-${PN} = "${AUTOREV}"

LINUX_DIR_NAME ?= "${@(d.getVar('LINUX_SRC_DIR', True).rstrip('/').split('/')[-1])}"
S = "${WORKDIR}/${LINUX_DIR_NAME}"

