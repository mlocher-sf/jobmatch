#
#  playground
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
PACKAGEDIR = playground/presentation

#
# To add new directories, create the directories and add their names
# this list. Be sure to copy a Makefile into each directory. Then add a
# "../" to the ROOT variable, and add the directory name to the end of.
# the PACKAGEDIR variable.
#
SUBDIRS = \
	media

#
# To add more Java classes, create the .java files and add their names to
# this list (without the .java extension).
#
CLASSES = \
	Redirect \
	Welcome \
	Student \
	Picture \
	Upload \
	Sendmail \
	ShowTree

HTML_DIR = ./html
HTML_CLASSES = WelcomeHTML \
	StudentHTML \
	PictureHTML \
	ShowTreeHTML \
	TreeNodeHTML

JAR_INSTALL = \
	./html/Mail.html\
	./html/MailSent.html \
	./html/Static.html \
	./html/style.css \
	./html/tree.css

include $(ROOT)/config.mk

