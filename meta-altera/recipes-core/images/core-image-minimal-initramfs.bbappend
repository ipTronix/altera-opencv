
#IMAGE_INSTALL = "initramfs-altera busybox base-passwd base-files tinylogin sysvinit initscripts netbase e2fsprogs mtd-utils systemtap gcc gdb bash usb-utils strace openssh openssl elfutils sysfsutils vim dtc gawk ethtool grep lighttpd iptables iputils make net-tools modutils oprofile pciutils portmap sed setserial wget autoconf dhcp diffutils perl sysklogd nfs-utils ltt-ng minicom valgrind"
IMAGE_INSTALL = "initramfs-altera busybox base-passwd base-files tinylogin sysvinit initscripts netbase"

ROOTFS_POSTPROCESS_COMMAND += "clear_root_password ; "

clear_root_password () {
        sed 's%^root:[^:]*:%root::%' < ${IMAGE_ROOTFS}/etc/passwd >${IMAGE_ROOTFS}/etc/passwd.new
        mv ${IMAGE_ROOTFS}/etc/passwd.new ${IMAGE_ROOTFS}/etc/passwd
}

IMAGE_DEVICE_TABLES += " files/socfpga_cyclone5_device_table.txt "

