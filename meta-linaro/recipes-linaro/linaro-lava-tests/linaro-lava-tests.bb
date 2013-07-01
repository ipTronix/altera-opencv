DESCRIPTION = "Scripts and files for LAVA tests"
LICENSE = "Expat"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3725c2bb543d06ff759f9db9b5d986ac"
PR = "r2"

SRC_URI = "\
        file://hello.c \
        file://hello.cc \
        file://info.php \
        file://mysql.php \
        file://LICENSE \
        "

S = "${WORKDIR}"

do_install() {
    install -d ${D}/home/root ${D}/usr/share/apache2/htdocs/
    install -m 0644 hello.*  ${D}/home/root/
    install -m 0644 *.php ${D}/usr/share/apache2/htdocs/
}

FILES_${PN} = "/"
