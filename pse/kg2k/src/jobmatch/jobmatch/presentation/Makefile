#
#  jobmatch
#  Enhydra Application Makefile
#
#

#
#  Where is the root of the source tree?
#
ROOT =	../..

#
#  What package does this directory represent? (use / instead of .)
#
PACKAGEDIR = jobmatch/presentation

#
# To add new directories, create the directories and add their names
# this list. Be sure to copy a Makefile into each directory. Then add a
# "../" to the ROOT variable, and add the directory name to the end of.
# the PACKAGEDIR variable.
#
POST_SUBDIRS = \
	test

#
# To add more Java classes, create the .java files and add their names to
# this list (without the .java extension).
#
CLASSES = \
	Redirect \
	CandidateHome \
	CVPersonal \
	Welcome \
	Login \
	CheckLogin \


XMLC_HTML_OPTS_FILE = options.xmlc

HTML_DIR = ./html
HTML_CLASSES = \
	CandidateHomeHTML \
	CVPersonalHTML \
	WelcomeHTML \
	LoginHTML

JAR_INSTALL = \
	./html/style.css

include $(ROOT)/config.mk

