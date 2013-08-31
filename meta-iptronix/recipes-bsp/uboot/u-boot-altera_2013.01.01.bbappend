FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0001-added-coherent_pool-on-linux-command-line-to-support.patch"

PRINC := "${@int(PRINC) + 1}"
