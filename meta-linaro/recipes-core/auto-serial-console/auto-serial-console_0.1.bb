SUMMARY = "Auto Serial Console and Auto Login scripts"
DESCRIPTION = "Scripts to auto login with root and call the console tty from the kernel cmd line"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
	file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
	file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
"

PR = "r2"

inherit update-rc.d

SRC_URI = "\
	file://autogetty \
	file://auto-root-login \
	file://auto-getty \
	file://auto-console-login \
	file://auto-serial-console \
"

PACKAGES =+ "auto-console-login"
RDEPENDS_${PN} = "auto-console-login"

FILES_auto-console-login = "\
	${bindir}/auto-root-login \
	${sysconfdir}/init.d/auto-console-login \
"

S = "${WORKDIR}"

INITSCRIPT_PACKAGES = "${PN} auto-console-login"
INITSCRIPT_NAME_auto-serial-console = "auto-serial-console"
INITSCRIPT_NAME_auto-console-login = "auto-console-login"
INITSCRIPT_PARAMS = "start 99 5 ."

do_install () {
	install -m 0755 -d ${D}${sysconfdir}/default
	install -m 0644 ${WORKDIR}/autogetty ${D}${sysconfdir}/default/autogetty

	install -m 0755 -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/auto-console-login ${D}${sysconfdir}/init.d/auto-console-login
	install -m 0755 ${WORKDIR}/auto-serial-console ${D}${sysconfdir}/init.d/auto-serial-console

	install -m 0755 -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/auto-root-login ${D}${bindir}/auto-root-login
	install -m 0755 ${WORKDIR}/auto-getty ${D}${bindir}/auto-getty
}
