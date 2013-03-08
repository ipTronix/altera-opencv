#!/bin/sh

case "$1" in
  start)
	modprobe gpio-altera
	modprobe gpio-dw
	modprobe led-class
	modprobe leds_gpio
	/www/pages/cgi-bin/scroll_server &

	IP=`ifconfig eth0 | head -n 2 | tail -n 1 | sed s/inet\ addr:// | sed s/\ Bcast.*// | sed s/\ *//g`
	IP_CHECK=`echo $IP | sed 's/\(\([0-9]\{1,3\}\)\.\)\{3\}\([0-9]\{1,3\}\)//g'`
	if [ "$IP_CHECK" != "" ]; then
		IP="No IP obtained"
	fi
	printf '\e[2J' > /dev/ttyLCD0
	printf "$IP" > /dev/ttyLCD0
	printf "\n" > /dev/ttyLCD0
	printf Hello\ Tim! > /dev/ttyLCD0
	;;
  stop)
	ID=`ps | grep scroll_server | awk '{print $1;}'`
	kill $ID
	;;
esac

