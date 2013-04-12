require recipes-kernel/linux/linux-altera.inc

PR = "r1"

SRC_URI = "http://open-source.altera.com/linux-altera-${PV}.tgz"
SRC_URI[md5sum] = "__LINUX_MD5SUM__"
SRC_URI[sha256sum] = "__LINUX_SHA256SUM__"

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

S = "${WORKDIR}/${PN}-${PV}"

SRCREV_pn-${PN} = "${AUTOREV}"

DTB_SUBDIR="dts/"

