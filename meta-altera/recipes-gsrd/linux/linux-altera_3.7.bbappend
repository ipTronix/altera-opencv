FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://socfpga.dts"
KERNEL_DEVICETREE += "socfpga"

do_compile_and_copy_dtb() {
	cp ${WORKDIR}/socfpga.dts ${S}/arch/arm/boot/dts
	oe_runmake socfpga.dtb
}

addtask compile_and_copy_dtb before do_compile_dtb after do_compile
