#
#  playground
#  Enhydra Application Makefile
#
#

#
#  Where is the root of the source tree?
#
ROOT =	..

#
#  What package does this directory represent? (use / instead of .)
#
PACKAGEDIR = playground

#
# To add new directories, create the directories and add their names
# this list. Be sure to copy a Makefile into each directory. Then add a
# "../" to the ROOT variable, and add the directory name to the end of.
# the PACKAGEDIR variable.
#
SUBDIRS = \
	data \
	business \
	presentation

#
# To add more Java classes, create the .java files and add their names to
# this list (without the .java extension).
#
CLASSES = \
	Playground

MISC_BUILD = conf_file ${OUTPUT}/multiserver.conf ${OUTPUT}/start

include $(ROOT)/config.mk

conf_file:
	@[ -d ${OUTPUT} ] || mkdir -p ${OUTPUT}
	cp playground.conf ${OUTPUT}/playground.conf

${OUTPUT}/multiserver.conf: multiserver.conf.in
	@[ -d ${OUTPUT} ] || mkdir -p ${OUTPUT}
	rm -rf ${OUTPUT}/multiserver.conf
	sed -e s%@OUTPUT@%`cd $(OUTPUT);pwd`%g \
	    < multiserver.conf.in > ${OUTPUT}/multiserver.conf

${OUTPUT}/start: start
	@[ -d ${OUTPUT} ] || mkdir -p ${OUTPUT}
	rm -f ${OUTPUT}/start
	cp start ${OUTPUT}/start
	chmod +x ${OUTPUT}/start

clean::
	rm -f ${OUTPUT}/playground.conf


