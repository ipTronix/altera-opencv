# Assuming that you have CROSS_COMPILE defined iff you are not building under Yocto
ifdef CROSS_COMPILE
CC = $(CROSS_COMPILE)gcc
endif

CFLAGS = -O0 -fno-omit-frame-pointer

all:
	$(CC) $(CFLAGS) memtool.c -pthread -o memtool
	@file memtool

.PHONY : help
help:
	@echo "usage of this Makefile"
	@echo "make         : builds the application (same as 'make all')"
	@echo "make clean   : clean binaries"
	@echo "make release : makes a tar ball of these sources"
	@echo "make help    : this message"

.PHONY : clean
clean:
	-rm -rf memtool *.o memtool.tar.gz

.PHONY : release
release: clean
	tar czf memtool.tar.gz *.c Makefile
