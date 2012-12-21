DESCRIPTION = "U-boot bootloader for Altera socfpga_cyclone5"
HOMEPAGE = "http://www.altera.com/"
SECTION = "bootloaders"
PROVIDES = "virtual/bootloader"

inherit deploy

PARALLEL_MAKE=""

# GCC 4.5.1 builds unusable binaries using -Os, remove it from OPTFLAGS
EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

python () {
	if not d.getVar("UBOOT_MACHINE", True):
		PN = d.getVar("PN", True)
		FILE = os.path.basename(d.getVar("FILE", True))
		bb.debug(1, "To build %s, see %s for instructions on \
			     setting up your machine config" % (PN, FILE))
		raise bb.parse.SkipPackage("because UBOOT_MACHINE is not set")
}

UBOOT_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.bin"
UBOOT_IMAGE_UIMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.img"
UBOOT_IMAGE_SPL ?= "u-boot-spl-${MACHINE}-${PV}-${PR}.bin"
UBOOT_BINARY ?= "u-boot.bin"
UBOOT_SYMLINK ?= "u-boot-${MACHINE}.bin"
UBOOT_SYMLINK_UIMAGE ?= "u-boot-${MACHINE}.img"
UBOOT_SYMLINK_SPL ?= "u-boot-spl-${MACHINE}.bin"
UBOOT_MAKE_TARGET ?= "all"
INC_PR = "r0"

LICENSE = "GPLv2+"

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake ${UBOOT_MACHINE}
	oe_runmake ${UBOOT_MAKE_TARGET}
}

do_install () {
    install -d ${D}/boot
    install ${S}/${UBOOT_BINARY} ${D}/boot/${UBOOT_IMAGE}
    ln -sf ${UBOOT_IMAGE} ${D}/boot/${UBOOT_BINARY}
}

FILES_${PN} = "/boot"

do_deploy () {
	install ${S}/u-boot.bin ${DEPLOYDIR}/${UBOOT_IMAGE}
	install ${S}/u-boot.img ${DEPLOYDIR}/${UBOOT_IMAGE_UIMAGE}
	install ${S}/spl/u-boot-spl.bin ${DEPLOYDIR}/${UBOOT_IMAGE_SPL}

	cd ${DEPLOYDIR}
	rm -f ${UBOOT_SYMLINK}
	ln -sf ${UBOOT_IMAGE} ${UBOOT_SYMLINK}
	rm -f ${UBOOT_SYMLINK_UIMAGE}
	ln -sf ${UBOOT_IMAGE_UIMAGE} ${UBOOT_SYMLINK_UIMAGE}
	rm -f ${UBOOT_SYMLINK_SPL}
	ln -sf ${UBOOT_IMAGE_SPL} ${UBOOT_SYMLINK_SPL}

}
addtask deploy before do_build after do_compile
