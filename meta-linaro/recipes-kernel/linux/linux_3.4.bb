KERNEL_ENABLE_CGROUPS = "1"

require recipes-kernel/linux/linux.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git"

S = "${WORKDIR}/git"

# tag: v3.4 76e10d158efb6d4516018846f60c2ab5501900bc
SRCREV="76e10d158efb6d4516018846f60c2ab5501900bc"

PR = "r0"
PV = "3.4+git${SRCPV}"

COMPATIBLE_MACHINE = "qemuarmv7a"

SRC_URI += "file://defconfig"
