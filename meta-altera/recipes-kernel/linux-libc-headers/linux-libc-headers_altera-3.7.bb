DESCRIPTION = "Sanitized set of kernel headers for the C library's use."
SECTION = "devel"
LICENSE = "GPLv2"

PROVIDES += " linux-libc-headers"

PR = "r1"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

SRC_URI = "http://open-source.altera.com/linux-${PV}.tgz"
SRC_URI[md5sum] = "__LINUX_MD5SUM__"
SRC_URI[sha256sum] = "__LINUX_SHA256SUM__"

S = "${WORKDIR}/linux-${PV}"

