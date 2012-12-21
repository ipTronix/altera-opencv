require recipes-kernel/linux/linux-altera.inc

PR = "r1"
PV = "0.0"

LINUX_SRC_DIR ?= "${WORKDIR}"
SRC_URI = "file://${LINUX_SRC_DIR}"

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

LINUX_DIR_NAME ?= "${@(d.getVar('LINUX_SRC_DIR', True).rstrip('/').split('/')[-1])}"

S = "${WORKDIR}/${LINUX_DIR_NAME}"

SRCREV_pn-${PN} = "${AUTOREV}"

