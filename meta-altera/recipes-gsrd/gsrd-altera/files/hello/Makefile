#
# Copyright Altera 2013
# All Rights Reserved.
#

C_SRC := hello.c
CFLAGS := -g -O0 -Werror -Wall

CROSS_COMPILE := arm-none-linux-gnueabi-
CC ?= $(CROSS_COMPILE)gcc
NM ?= $(CROSS_COMPILE)nm

ifeq ($(or $(COMSPEC),$(ComSpec)),)
RM := rm -rf
else
RM := cs-rm -rf
endif

ELF ?= $(basename $(firstword $(C_SRC)))
OBJ := $(patsubst %.c,%.o,$(C_SRC))

.PHONY: all
all: $(ELF)

.PHONY:
clean:
	$(RM) $(ELF) $(OBJ) *.objdump *.map

$(OBJ): %.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

$(ELF): $(OBJ)
	$(CC) $(CFLAGS) $(OBJ) -o $@ $(LDFLAGS)
	$(NM) $@ > $@.map
