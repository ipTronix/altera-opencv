
IMAGE_INSTALL = "initramfs-altera busybox base-passwd base-files tinylogin sysvinit initscripts netbase"

ROOTFS_POSTPROCESS_COMMAND += "clear_root_password ; "

clear_root_password () {
        sed 's%^root:[^:]*:%root::%' < ${IMAGE_ROOTFS}/etc/passwd >${IMAGE_ROOTFS}/etc/passwd.new
        mv ${IMAGE_ROOTFS}/etc/passwd.new ${IMAGE_ROOTFS}/etc/passwd
}

IMAGE_DEVICE_TABLES += " files/socfpga5xs1_device_table.txt "

