PRINC = "1"

do_install_append () {
	echo "127.0.0.1 ${MACHINE}" >>  ${D}${sysconfdir}/hosts
}

PACKAGE_ARCH = "${MACHINE}"
