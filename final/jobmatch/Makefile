#
#  jobmatch
#  Enhydra Application Makefile
#
#

#
#  Where is the root of the source tree?
#
ROOT =	.

#
# To add new directories, create the directories and add their names
# this list. Be sure to copy a Makefile into each directory. Then add a
# "../" to the ROOT variable, and add the directory name to the end of.
# the PACKAGEDIR variable.
#
SUBDIRS = \
	jobmatch


#
# build_all is normally the top-level-rule. This makes it so the jar file
# will be created after the build is all done.
#
top_build_all: build_all jar



include $(ROOT)/config.mk

clean::
	rm -rf $(CLASS_OUTPUT) $(OUTPUT)

