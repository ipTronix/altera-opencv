require recipes-devtools/gcc/gcc-4.6.inc
require gcc-linaro-common-4.6.inc

MMYY = "13.05"
RELEASE = "20${MMYY}"
PR = "r${RELEASE}"
BINV = "4.6.4"

FILESPATH = "${@base_set_filespath([ '${FILE_DIRNAME}/gcc-${PV}' ], d)}"

SRC_URI = "http://releases.linaro.org/${MMYY}/components/toolchain/gcc-linaro/${BASEPV}/gcc-${PV}-${RELEASE}.tar.bz2 \
	   file://gcc-4.3.1-ARCH_FLAGS_FOR_TARGET.patch \
	   file://64bithack.patch \
	   file://optional_libstdc.patch \
	   file://use-defaults.h-and-t-oe-in-B.patch \
	  "

SRC_URI[md5sum] = "26b48802ae1203cd99415026fbf56ed7"
SRC_URI[sha256sum] = "4b6cad20684ba06da766d523c38abcee22d69020e0b9213cf6f580920c91e18e"

S = "${TMPDIR}/work-shared/gcc-${PV}-${PR}/gcc-${PV}-${RELEASE}"
B = "${WORKDIR}/gcc-${PV}-${RELEASE}/build.${HOST_SYS}.${TARGET_SYS}"
