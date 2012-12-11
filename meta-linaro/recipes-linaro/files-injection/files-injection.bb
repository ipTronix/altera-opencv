DESCRIPTION = "Random files to be injected into rootfs"
LICENSE = "CLOSED"

SRC_URI = "file://files-to-inject.tar.xz"

S = "${WORKDIR}/files-to-inject/"

# all files will be put in /opt/ directory with keeping tarball structure

do_install() {
	install -d ${D}/opt/
	cp -a ${S} ${D}/opt/
}

FILES_${PN} = "/opt"

# How to use it?
#
# unpack files/files-to-inject.tar.xz tarball, add own files, compress it.
# add "files-injection" into CORE_IMAGE_EXTRA_INSTALL in your build/conf/local.conf
# build rootfs
#
