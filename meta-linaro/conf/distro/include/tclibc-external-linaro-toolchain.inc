#
# eglibc specific configuration
#

LIBCEXTENSION = "${@['', '-gnu'][(d.getVar('ABIEXTENSION', True) or '') != '']}"

# Add glibc overrides to the overrides for eglibc.
LIBCOVERRIDE = ":libc-glibc"
OVERRIDES .= "${LIBCOVERRIDE}"

PREFERRED_PROVIDER_virtual/libiconv ?= "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/nativesdk-libiconv ?= "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/nativesdk-libintl ?= "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/libintl ?= "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/libc ?= "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/nativesdk-libc ?= "external-linaro-toolchain"
PREFERRED_PROVIDER_virtual/libc-locale ?= "external-linaro-toolchain"

#CXXFLAGS += "-fvisibility-inlines-hidden"

LIBC_DEPENDENCIES = "external-linaro-toolchain"
#libsegfault \
		     eglibc \
		     eglibc-dbg \
		     eglibc-dev \
		     eglibc-utils \
		     eglibc-thread-db \
		     ${@get_libc_locales_dependencies(d)}"

LIBC_LOCALE_DEPENDENCIES = ""
#\
	eglibc-localedata-i18n \
	eglibc-gconv-ibm850 \
	eglibc-gconv-cp1252 \
	eglibc-gconv-iso8859-1 \
	eglibc-gconv-iso8859-15"

def get_libc_locales_dependencies(d):
    if 'libc-locales' in (d.getVar('DISTRO_FEATURES', True) or '').split() :
        return d.getVar('LIBC_LOCALE_DEPENDENCIES', True) or ''
    else:
        return ''
