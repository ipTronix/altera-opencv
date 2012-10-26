DESCRIPTION = "Sanitized set of kernel headers for the C library's use."
SECTION = "devel"
LICENSE = "GPLv2"

LINUX_VERSION ?= "3.4"

PR = "r1"
PV = "${LINUX_VERSION}"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "http://open-source.altera.com/linux-altera-${PV}.tgz"
S = "${WORKDIR}/linux-altera-${PV}"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

