
DESCRIPTION = "The set of packages for development and testing provided by Altera"

LICENSE = "MIT"

ALTERA_MINIMAL_IMAGE_INSTALL ?= "util-linux initramfs-altera kernel-modules busybox base-passwd base-files tinylogin sysvinit initscripts"
IMAGE_INSTALL ?= "${ALTERA_MINIMAL_IMAGE_INSTALL}"

inherit core-image

# altera-image.inc must be included after inherit core-image to override functionality
include altera-image.inc

