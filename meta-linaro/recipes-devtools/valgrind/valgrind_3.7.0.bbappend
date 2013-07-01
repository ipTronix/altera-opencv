META_LINARO_VALGRIND = "${@d.getVar('FILE_DIRNAME', True).replace('meta','meta-linaro',1)}"
SRC_URI_append = " file://${META_LINARO_VALGRIND}/valgrind-3.7.0/valgrind-linaro.patch"
