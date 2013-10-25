FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0001-fbaltera-Add-module.patch"
SRC_URI += "file://0003-altera-Add-V4L2-camera.patch"
SRC_URI += "file://0004-added-support-for-asoc_dsp-driver.patch"

PRINC := "${@int(PRINC) + 1}"

do_configure_append() {
	sed -i 's\# CONFIG_INOTIFY_USER is not set\CONFIG_INOTIFY_USER=y\' .config
	sed -i 's\# CONFIG_FB is not set\CONFIG_FB=y\' .config

	grep CONFIG_FB .config || echo "CONFIG_FB=y" >> .config
	grep CONFIG_FB_ALTERA .config || echo "CONFIG_FB_ALTERA=y" >> .config
	grep CONFIG_FRAMEBUFFER_CONSOLE .config || echo "CONFIG_FRAMEBUFFER_CONSOLE=y" >> .config
	grep CONFIG_FRAMEBUFFER_CONSOLE_DETECT_PRIMARY .config || echo "CONFIG_FRAMEBUFFER_CONSOLE_DETECT_PRIMARY=y" >> .config

	grep CONFIG_FRAMEBUFFER_CONSOLE_ROTATION .config || echo "# CONFIG_FRAMEBUFFER_CONSOLE_ROTATION is not set" >> .config
	grep CONFIG_FONTS .config || echo "# CONFIG_FONTS is not set" >> .config
	grep CONFIG_FONT_8x8 .config || echo "CONFIG_FONT_8x8=y" >> .config
	grep CONFIG_FONT_8x16 .config || echo "CONFIG_FONT_8x16=y" >> .config
	grep CONFIG_LOGO .config || echo "CONFIG_LOGO=y" >> .config
	grep CONFIG_LOGO_LINUX_MONO .config || echo "CONFIG_LOGO_LINUX_MONO=y" >> .config
	grep CONFIG_LOGO_LINUX_VGA16 .config || echo "CONFIG_LOGO_LINUX_VGA16=y" >> .config
	grep CONFIG_LOGO_LINUX_CLUT224 .config || echo "CONFIG_LOGO_LINUX_CLUT224=y" >> .config
	grep CONFIG_SOUND .config || echo "# CONFIG_SOUND is not set" >> .config

	grep CONFIG_FIRMWARE_EDID .config || echo "# CONFIG_FIRMWARE_EDID is not set" >> .config
	grep CONFIG_FB_FOREIGN_ENDIAN .config || echo "# CONFIG_FB_FOREIGN_ENDIAN is not set" >> .config
	grep CONFIG_FB_MODE_HELPERS .config || echo "# CONFIG_FB_MODE_HELPERS is not set" >> .config
	grep CONFIG_FB_TILEBLITTING .config || echo "# CONFIG_FB_TILEBLITTING is not set" >> .config
	grep CONFIG_FB_CFB_FILLRECT .config || echo "CONFIG_FB_CFB_FILLRECT=y" >> .config
	grep CONFIG_FB_CFB_COPYAREA .config || echo "CONFIG_FB_CFB_COPYAREA=y" >> .config
	grep CONFIG_FB_CFB_IMAGEBLIT .config || echo "CONFIG_FB_CFB_IMAGEBLIT=y" >> .config

	grep CONFIG_FB_ARMCLCD .config || echo "# CONFIG_FB_ARMCLCD is not set" >> .config
	grep CONFIG_FB_S1D13XXX .config || echo "# CONFIG_FB_S1D13XXX is not set" >> .config
	grep CONFIG_FB_SMSCUFX .config || echo "# CONFIG_FB_SMSCUFX is not set" >> .config
	grep CONFIG_FB_UDL .config || echo "# CONFIG_FB_UDL is not set" >> .config
	grep CONFIG_FB_VIRTUAL .config || echo "# CONFIG_FB_VIRTUAL is not set" >> .config
	grep CONFIG_FB_METRONOME .config || echo "# CONFIG_FB_METRONOME is not set" >> .config
	grep CONFIG_FB_BROADSHEET .config || echo "# CONFIG_FB_BROADSHEET is not set" >> .config
	grep CONFIG_FB_AUO_K190X .config || echo "# CONFIG_FB_AUO_K190X is not set" >> .config
	grep CONFIG_FB_SSD1307 .config || echo "# CONFIG_FB_SSD1307 is not set" >> .config

	grep CONFIG_EXYNOS_VIDEO .config || echo "# CONFIG_EXYNOS_VIDEO is not set" >> .config
	grep CONFIG_BACKLIGHT_LCD_SUPPORT .config || echo "# CONFIG_BACKLIGHT_LCD_SUPPORT is not set" >> .config

	sed -i 's\# CONFIG_USB_EHCI_HCD is not set\CONFIG_USB_EHCI_HCD=y\' .config
	sed -i 's\# CONFIG_MEDIA_SUPPORT is not set\CONFIG_MEDIA_SUPPORT=y\' .config
	sed -i 's\# CONFIG_MEDIA_CAMERA_SUPPORT is not set\CONFIG_MEDIA_CAMERA_SUPPORT=y\' .config
	sed -i 's\# CONFIG_MEDIA_USB_SUPPORT is not set\CONFIG_MEDIA_USB_SUPPORT=y\' .config
	sed -i 's\# CONFIG_USB_VIDEO_CLASS is not set\CONFIG_USB_VIDEO_CLASS=y\' .config
#	sed -i 's\# CONFIG_USB_GSPCA is not set\CONFIG_USB_GSPCA=y\' .config
	grep CONFIG_USB_EHCI_HCD .config || echo "CONFIG_USB_EHCI_HCD=y" >> .config
	grep CONFIG_MEDIA_SUPPORT .config || echo "CONFIG_MEDIA_SUPPORT=y" >> .config
	grep CONFIG_MEDIA_CAMERA_SUPPORT .config || echo "CONFIG_MEDIA_CAMERA_SUPPORT=y" >> .config
	grep CONFIG_MEDIA_USB_SUPPORT .config || echo "CONFIG_MEDIA_USB_SUPPORT=y" >> .config
	grep CONFIG_USB_VIDEO_CLASS .config || echo "CONFIG_USB_VIDEO_CLASS=y" >> .config
#	grep CONFIG_USB_GSPCA .config || echo "CONFIG_USB_GSPCA=y" >> .config
	grep CONFIG_USB_GSPCA .config || echo "# CONFIG_USB_GSPCA is not set" >> .config
	grep CONFIG_MEDIA_ANALOG_TV_SUPPORT .config || echo "# CONFIG_MEDIA_ANALOG_TV_SUPPORT is not set" >> .config
	grep CONFIG_MEDIA_DIGITAL_TV_SUPPORT .config || echo "# CONFIG_MEDIA_DIGITAL_TV_SUPPORT is not set" >> .config
	grep CONFIG_MEDIA_RADIO_SUPPORT .config || echo "# CONFIG_MEDIA_RADIO_SUPPORT is not set" >> .config
	grep CONFIG_MEDIA_RC_SUPPORT .config || echo "# CONFIG_MEDIA_RC_SUPPORT is not set" >> .config
	grep CONFIG_MEDIA_CONTROLLER .config || echo "# CONFIG_MEDIA_CONTROLLER is not set" >> .config

	grep CONFIG_VIDEO_ADV_DEBUG .config || echo "# CONFIG_VIDEO_ADV_DEBUG is not set" >> .config
	grep CONFIG_VIDEO_FIXED_MINOR_RANGES .config || echo "# CONFIG_VIDEO_FIXED_MINOR_RANGES is not set" >> .config
	grep CONFIG_USB_VIDEO_CLASS_INPUT_EVDEV .config || echo "CONFIG_USB_VIDEO_CLASS_INPUT_EVDEV=y" >> .config

	grep CONFIG_USB_M5602 .config || echo "# CONFIG_USB_M5602 is not set" >> .config
	grep CONFIG_USB_STV06XX .config || echo "# CONFIG_USB_STV06XX is not set" >> .config
	grep CONFIG_USB_GL860 .config || echo "# CONFIG_USB_GL860 is not set" >> .config
	grep CONFIG_USB_GSPCA_BENQ .config || echo "# CONFIG_USB_GSPCA_BENQ is not set" >> .config
	grep CONFIG_USB_GSPCA_CONEX .config || echo "# CONFIG_USB_GSPCA_CONEX is not set" >> .config
	grep CONFIG_USB_GSPCA_CPIA1 .config || echo "# CONFIG_USB_GSPCA_CPIA1 is not set" >> .config
	grep CONFIG_USB_GSPCA_ETOMS .config || echo "# CONFIG_USB_GSPCA_ETOMS is not set" >> .config
	grep CONFIG_USB_GSPCA_FINEPIX .config || echo "# CONFIG_USB_GSPCA_FINEPIX is not set" >> .config
	grep CONFIG_USB_GSPCA_JEILINJ .config || echo "# CONFIG_USB_GSPCA_JEILINJ is not set" >> .config
	grep CONFIG_USB_GSPCA_JL2005BCD .config || echo "# CONFIG_USB_GSPCA_JL2005BCD is not set" >> .config
	grep CONFIG_USB_GSPCA_KINECT .config || echo "# CONFIG_USB_GSPCA_KINECT is not set" >> .config
	grep CONFIG_USB_GSPCA_KONICA .config || echo "# CONFIG_USB_GSPCA_KONICA is not set" >> .config
	grep CONFIG_USB_GSPCA_MARS .config || echo "# CONFIG_USB_GSPCA_MARS is not set" >> .config
	grep CONFIG_USB_GSPCA_MR97310A .config || echo "# CONFIG_USB_GSPCA_MR97310A is not set" >> .config
	grep CONFIG_USB_GSPCA_NW80X .config || echo "# CONFIG_USB_GSPCA_NW80X is not set" >> .config
	grep CONFIG_USB_GSPCA_OV519 .config || echo "# CONFIG_USB_GSPCA_OV519 is not set" >> .config
	grep CONFIG_USB_GSPCA_OV534 .config || echo "# CONFIG_USB_GSPCA_OV534 is not set" >> .config
	grep CONFIG_USB_GSPCA_OV534_9 .config || echo "# CONFIG_USB_GSPCA_OV534_9 is not set" >> .config
	grep CONFIG_USB_GSPCA_PAC207 .config || echo "# CONFIG_USB_GSPCA_PAC207 is not set" >> .config
	grep CONFIG_USB_GSPCA_PAC7302 .config || echo "# CONFIG_USB_GSPCA_PAC7302 is not set" >> .config
	grep CONFIG_USB_GSPCA_PAC7311 .config || echo "# CONFIG_USB_GSPCA_PAC7311 is not set" >> .config
	grep CONFIG_USB_GSPCA_SE401 .config || echo "# CONFIG_USB_GSPCA_SE401 is not set" >> .config
	grep CONFIG_USB_GSPCA_SN9C2028 .config || echo "# CONFIG_USB_GSPCA_SN9C2028 is not set" >> .config
	grep CONFIG_USB_GSPCA_SN9C20X .config || echo "# CONFIG_USB_GSPCA_SN9C20X is not set" >> .config
	grep CONFIG_USB_GSPCA_SONIXB .config || echo "# CONFIG_USB_GSPCA_SONIXB is not set" >> .config
	grep CONFIG_USB_GSPCA_SONIXJ .config || echo "# CONFIG_USB_GSPCA_SONIXJ is not set" >> .config
	grep CONFIG_USB_GSPCA_SPCA500 .config || echo "# CONFIG_USB_GSPCA_SPCA500 is not set" >> .config
	grep CONFIG_USB_GSPCA_SPCA501 .config || echo "# CONFIG_USB_GSPCA_SPCA501 is not set" >> .config
	grep CONFIG_USB_GSPCA_SPCA505 .config || echo "# CONFIG_USB_GSPCA_SPCA505 is not set" >> .config
	grep CONFIG_USB_GSPCA_SPCA506 .config || echo "# CONFIG_USB_GSPCA_SPCA506 is not set" >> .config
	grep CONFIG_USB_GSPCA_SPCA508 .config || echo "# CONFIG_USB_GSPCA_SPCA508 is not set" >> .config
	grep CONFIG_USB_GSPCA_SPCA561 .config || echo "# CONFIG_USB_GSPCA_SPCA561 is not set" >> .config
	grep CONFIG_USB_GSPCA_SPCA1528 .config || echo "# CONFIG_USB_GSPCA_SPCA1528 is not set" >> .config
	grep CONFIG_USB_GSPCA_SQ905 .config || echo "# CONFIG_USB_GSPCA_SQ905 is not set" >> .config
	grep CONFIG_USB_GSPCA_SQ905C .config || echo "# CONFIG_USB_GSPCA_SQ905C is not set" >> .config
	grep CONFIG_USB_GSPCA_SQ930X .config || echo "# CONFIG_USB_GSPCA_SQ930X is not set" >> .config
	grep CONFIG_USB_GSPCA_STK014 .config || echo "# CONFIG_USB_GSPCA_STK014 is not set" >> .config
	grep CONFIG_USB_GSPCA_STV0680 .config || echo "# CONFIG_USB_GSPCA_STV0680 is not set" >> .config
	grep CONFIG_USB_GSPCA_T613         .config || echo "# CONFIG_USB_GSPCA_T613 is not set"        >> .config
	grep CONFIG_USB_GSPCA_TOPRO        .config || echo "# CONFIG_USB_GSPCA_TOPRO is not set"       >> .config
	grep CONFIG_USB_GSPCA_TV8532       .config || echo "# CONFIG_USB_GSPCA_TV8532 is not set"      >> .config
	grep CONFIG_USB_GSPCA_VC032X       .config || echo "# CONFIG_USB_GSPCA_VC032X is not set"      >> .config
	grep CONFIG_USB_GSPCA_VICAM        .config || echo "# CONFIG_USB_GSPCA_VICAM is not set"       >> .config
	grep CONFIG_USB_GSPCA_XIRLINK_CIT  .config || echo "# CONFIG_USB_GSPCA_XIRLINK_CIT is not set" >> .config
	grep CONFIG_USB_GSPCA_ZC3XX        .config || echo "# CONFIG_USB_GSPCA_ZC3XX is not set"       >> .config
	grep CONFIG_USB_GSPCA_SUNPLUS      .config || echo "# CONFIG_USB_GSPCA_SUNPLUS is not set"     >> .config

	grep CONFIG_USB_PWC .config || echo "# CONFIG_USB_PWC is not set" >> .config
	grep CONFIG_VIDEO_CPIA2 .config || echo "# CONFIG_VIDEO_CPIA2 is not set" >> .config
	grep CONFIG_USB_ZR364XX .config || echo "# CONFIG_USB_ZR364XX is not set" >> .config
	grep CONFIG_USB_STKWEBCAM .config || echo "# CONFIG_USB_STKWEBCAM is not set" >> .config
	grep CONFIG_USB_S2255 .config || echo "# CONFIG_USB_S2255 is not set" >> .config
	grep CONFIG_USB_SN9C102 .config || echo "# CONFIG_USB_SN9C102 is not set" >> .config
	grep CONFIG_VIDEO_EM28XX .config || echo "# CONFIG_VIDEO_EM28XX is not set" >> .config

	grep CONFIG_V4L_PLATFORM_DRIVERS .config || echo "# CONFIG_V4L_PLATFORM_DRIVERS is not set" >> .config
	grep CONFIG_V4L_MEM2MEM_DRIVERS  .config || echo "# CONFIG_V4L_MEM2MEM_DRIVERS is not set"  >> .config
	grep CONFIG_V4L_TEST_DRIVERS     .config || echo "# CONFIG_V4L_TEST_DRIVERS is not set"     >> .config
	grep CONFIG_MEDIA_SUBDRV_AUTOSELECT .config || echo "CONFIG_MEDIA_SUBDRV_AUTOSELECT=y" >> .config
	grep CONFIG_USB_G_WEBCAM .config || echo "CONFIG_USB_G_WEBCAM=y" >> .config
  grep CONFIG_VIDEO_V4L2_INT_DEVICE .config || echo "CONFIG_VIDEO_V4L2_INT_DEVICE=n" >> .config
  grep CONFIG_VIDEO_USBTV .config || echo "CONFIG_VIDEO_USBTV=n" >> .config
  grep CONFIG_USB_EHCI_ROOT_HUB_TT .config || echo "CONFIG_USB_EHCI_ROOT_HUB_TT=n" >> .config
  grep CONFIG_USB_EHCI_TT_NEWSCHED .config || echo "CONFIG_USB_EHCI_TT_NEWSCHED=n" >> .config
  grep CONFIG_USB_EHCI_HCD_PLATFORM .config || echo "CONFIG_USB_EHCI_HCD_PLATFORM=n" >> .config
  grep CONFIG_USB_SISUSBVGA .config || echo "CONFIG_USB_SISUSBVGA=n" >> .config

        grep CONFIG_FB_GOLDFISH .config || echo "CONFIG_FB_GOLDFISH=n" >> .config
        grep CONFIG_FB_SIMPLE .config || echo "# CONFIG_FB_SIMPLE is not set" >> .config
        grep CONFIG_CYPRESS_FIRMWARE .config || echo "# CONFIG_CYPRESS_FIRMWARE is not set" >> .config


}
