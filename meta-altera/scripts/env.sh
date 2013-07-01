# This script is intended to setup your environment for future Yocto builds

if [ -z "$ZSH_NAME" ] && [ "x$0" = "x./env.sh" ]; then
   echo "Error: This script needs to be sourced. Please run as '. ./env.sh'"
else
    OEROOT=__OEROOT__
    export OEROOT
    . $OEROOT/scripts/oe-buildenv-internal
fi
