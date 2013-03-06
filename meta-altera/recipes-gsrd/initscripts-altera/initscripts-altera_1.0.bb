DESCRIPTION = "Altera GSRD init script"
SECTION = "gsrd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"
PR = "r0"
inherit update-rc.d
SRC_URI = "file://gsrd_init.sh"
FILES_${PN} += "${sysconfdir}"
CONFFILES_${PN} = "${sysconfdir}/gsrd_init.sh"
INITSCRIPT_NAME = "gsrd_init.sh"
INITSCRIPT_PARAMS = "defaults 90"

S = "${WORKDIR}"

do_configure() {
}

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/gsrd_init.sh ${D}${sysconfdir}/init.d
}
