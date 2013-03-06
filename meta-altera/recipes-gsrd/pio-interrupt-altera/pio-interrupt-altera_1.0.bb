DESCRIPTION = "Altera GSRD pio interrupt module."
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

inherit module

PR = "r0"
PV = "0.1"

SRC_URI = "file://Makefile \
           file://gpio_interrupt.c \
          "

S = "${WORKDIR}"
