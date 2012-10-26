require recipes-kernel/linux/linux-altera.inc

LINUX_VERSION ?= "3.4"

PR = "r1"
PV = "${LINUX_VERSION}"

SRC_URI = "http://open-source.altera.com/${PN}-${PV}.tgz"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

S = "${WORKDIR}/${PN}-${PV}"

SRCREV_pn-${PN} = "${AUTOREV}"

