
DESCRIPTION = "The set of packages for development and testing provided by Altera"

LICENSE = "MIT"

COMMERCIAL_VIDEO_PLUGINS ?= "gst-ffmpeg"
IPTRONIX_IMAGE_INSTALL ?= "util-linux initramfs-altera kernel-modules netbase \
                         init-ifupdown busybox base-passwd base-files tinylogin sysvinit \
                         initscripts e2fsprogs mtd-utils gdb gdbserver bash \
                         strace openssh openssl elfutils sysfsutils usbutils \
                         dtc gawk ethtool grep lighttpd iputils make pciutils \
                         portmap sed setserial wget autoconf diffutils perl \
                         minicom valgrind i2c-tools lttng-tools \
                         iptables oprofile openssh-sftp-server memtool \
                         nfs-utils-client gcc eglibc-dev libgcc-dev \
                         xserver-xorg xf86-input-evdev xf86-input-keyboard \
                         xf86-input-mouse xf86-video-fbdev packagegroup-core-x11-base \
                         encodings font-alias font-util xorg-minimal-fonts \
                         xev xinit xinput xprop xrandr xvinfo xset x11perf xhost xeyes \
                         opencv opencv-samples nfs-utils-client mplayer2"
# net-tools
IMAGE_INSTALL ?= "${IPTRONIX_IMAGE_INSTALL}"

inherit core-image

# altera-image.inc must be included after inherit core-image to override functionality

include iptronix-image.inc
