require recipes-devtools/gcc/gcc-4.7.inc
require gcc-linaro-common-4.7.inc

PR = "r7"
RELEASE = "2012.11"
BINV = "4.7.3"

FILESPATH = "${@base_set_filespath([ '${FILE_DIRNAME}/gcc-${PV}-${RELEASE}' ], d)}"

SRC_URI = "http://launchpad.net/gcc-linaro/4.7/4.7-2012.11/+download/gcc-linaro-4.7-${RELEASE}.tar.bz2 \
	   file://gcc-4.3.1-ARCH_FLAGS_FOR_TARGET.patch \
	   file://64bithack.patch \
	   file://optional_libstdc.patch \
	   file://use-defaults.h-and-t-oe-in-B.patch \
	   file://fix-g++-sysroot.patch \
	  "

SRC_URI[md5sum] = "590481ff4aff1a9e0f9a206516248877"
SRC_URI[sha256sum] = "31d66226706839c81b8de475c52ecadcafb7766f8253ab443b5815404ed80238"

S = "${TMPDIR}/work-shared/gcc-${PV}-${PR}/gcc-${PV}-${RELEASE}"
B = "${WORKDIR}/gcc-${PV}-${RELEASE}/build.${HOST_SYS}.${TARGET_SYS}"

INSANE_SKIP_${PN} += "installed_vs_shipped"

