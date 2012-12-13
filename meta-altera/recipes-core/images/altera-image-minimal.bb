
DESCRIPTION = "The set of packages for development and testing provided by Altera"

LICENSE = "MIT"

ALTERA_MINIMAL_IMAGE_INSTALL ?= "initramfs-altera busybox base-passwd base-files tinylogin sysvinit initscripts"
IMAGE_INSTALL ?= "${ALTERA_MINIMAL_IMAGE_INSTALL}"

include altera-image.inc

inherit core-image

