DESCRIPTION = "tools to manage i2c devices"
SECTION = "dev tools"
LICENSE = "GPLv2"

PV = "3.1.0"
PR = "r53"

SRC_URI = "http://dl.lm-sensors.org/i2c-tools/releases/i2c-tools-${PV}.tar.bz2"
LIC_FILES_CHKSUM="file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI[md5sum] = "f15019e559e378c6e9d5d6299a00df21"
SRC_URI[sha256sum] = "960023f61de292c6dd757fcedec4bffa7dd036e8594e24b26a706094ca4c142a"

S = "${WORKDIR}/${PN}-${PV}"

do_install () {
   make install DESTDIR=${D}
   rm -r ${D}/usr/local/bin 
   rm -r ${D}/usr/local/sbin/i2c-stub-from-dump
}

inherit pkgconfig

FILES_${PN} = "/usr/local/sbin/i2c*"

INSANE_SKIP_i2c-tools += "installed_vs_shipped"
