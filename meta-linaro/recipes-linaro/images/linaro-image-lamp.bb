inherit core-image

PR = "r3"

IMAGE_INSTALL += "\
	apache2 \
	mysql5-server \
	mysql5-client \
	php-fpm \
	php-fpm-apache2 \
	sudo \
	linaro-lava-tests \
	"

IMAGE_FEATURES += "package-management ssh-server-openssh"
