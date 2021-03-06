#
# playground
# Enhydra Application Makefile Configuration
#
#

#
# The directory containing the Enhydra distribution.
#
ENHYDRA_DIR = /home/scg/Software/enhydra/enhydra3.0


#
# You may need to set other values to override the defaults, for example:
#
# JDKDIR = /usr/local/jdk1.2.2
# JSDKDIR = ...
# SWINGDIR = ...  (optional)
#
# See lib/stdrules.mk in the Enhydra installation for more
# configuration settings.
#


#
# What is the name of the jar file to create?
#
JARNAME = playground

#
# Generate interfaces and implementions
#
XMLC_HTML_OPTS += -generate both

#
# Do some sanity checking before building.
#
ENHYDRA_CHECK = YES

include $(ENHYDRA_DIR)/lib/stdrules.mk

#
# Only used if ENHYDRA_DIR is not correct.
#
$(ENHYDRA_DIR)/lib/stdrules.mk:
	@echo
	@echo "Please edit config.mk. ENHYDRA_DIR must point to your installation of Enhydra."
	@echo


XMLC_AUTO_COMP=YES 
XMLC_HTML_OPTS += -for-recomp 
