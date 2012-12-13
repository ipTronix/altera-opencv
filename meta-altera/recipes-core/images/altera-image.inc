
IMAGE_LINGUAS = " "

IMAGE_ROOTFS_SIZE = "8192"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "

# Set root password to blank
zap_root_password () {
        sed 's%^root:[^:]*:%root::%' < ${IMAGE_ROOTFS}/etc/passwd >${IMAGE_ROOTFS}/etc/passwd.new
        mv ${IMAGE_ROOTFS}/etc/passwd.new ${IMAGE_ROOTFS}/etc/passwd
}

