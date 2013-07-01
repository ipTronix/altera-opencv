# Download update-rc.d via http instead of git which might be block by firewall

SRC_URI = "git://github.com/philb/update-rc.d.git;protocol=http \
           file://add-verbose.patch;"

