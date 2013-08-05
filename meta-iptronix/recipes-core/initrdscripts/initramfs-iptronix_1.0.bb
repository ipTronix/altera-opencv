DESCRIPTION = "Iptronix image init script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

do_install() {
	ln -fsn sbin/init ${D}/init 
	ln -fsn bin/busybox ${D}/linuxrc	
}

inherit allarch

FILES_${PN} += " /init "
FILES_${PN} += " /linuxrc "
