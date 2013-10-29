DESCRIPTION = "Sanitized set of kernel headers for the C library's use."
SECTION = "devel"
LICENSE = "GPLv2"

PROVIDES += " linux-libc-headers linux-libc-headers-dev"

PR = "r1"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

SRC_URI = "git://git.rocketboards.org/linux-socfpga-next.git;protocol=http;branch=socfpga-3.10_dwc2" 

S = "${WORKDIR}/git"
SRCREV_pn-${PN} = "${AUTOREV}"
