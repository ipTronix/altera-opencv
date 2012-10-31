require recipes-kernel/linux/linux-altera.inc

PR = "r1"

SRC_URI = "http://open-source.altera.com/linux-altera-3.6.tgz;__LINUX_CHECKSUMS__"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

S = "${WORKDIR}/${PN}-${PV}"

SRCREV_pn-${PN} = "${AUTOREV}"

