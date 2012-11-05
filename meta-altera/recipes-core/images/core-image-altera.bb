
DESCRIPTION = "The set of packages for development and testing provided by Altera"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "

IMAGE_INSTALL = "initramfs-altera busybox base-passwd base-files tinylogin sysvinit initscripts netbase e2fsprogs mtd-utils gcc gdb bash strace openssh openssl elfutils sysfsutils usbutils dtc gawk ethtool grep lighttpd iputils make modutils-initscripts pciutils portmap sed setserial wget autoconf diffutils perl sysklogd minicom valgrind"
#nfs-utils lttng-modules lttng-tools iptables oprofile net-tools python systemtap 

ROOTFS_POSTPROCESS_COMMAND += "clear_root_password ; "

clear_root_password () {
        sed 's%^root:[^:]*:%root::%' < ${IMAGE_ROOTFS}/etc/passwd >${IMAGE_ROOTFS}/etc/passwd.new
        mv ${IMAGE_ROOTFS}/etc/passwd.new ${IMAGE_ROOTFS}/etc/passwd
}

