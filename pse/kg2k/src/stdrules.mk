#-----------------------------------------------------------------------------
# Enhydra Java Application Server
# Copyright 1997-2000 Lutris Technologies, Inc.
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions
# are met:
# 1. Redistributions of source code must retain the above copyright
#    notice, this list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright
#    notice, this list of conditions and the following disclaimer in
#    the documentation and/or other materials provided with the distribution.
# 3. All advertising materials mentioning features or use of this software
#    must display the following acknowledgement:
#      This product includes Enhydra software developed by Lutris
#      Technologies, Inc. and its contributors.
# 4. Neither the name of Lutris Technologies nor the names of its contributors
#    may be used to endorse or promote products derived from this software
#    without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY LUTRIS TECHNOLOGIES AND CONTRIBUTORS ``AS IS''
# AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
# IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
# ARE DISCLAIMED.  IN NO EVENT SHALL LUTRIS TECHNOLOGIES OR CONTRIBUTORS BE
# LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
# CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
# SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
# INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
# CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
# ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
# POSSIBILITY OF SUCH DAMAGE.
#-----------------------------------------------------------------------------
# $Id: stdrules.mk,v 1.2 2000/05/16 13:40:14 locher Exp $
#-----------------------------------------------------------------------------

#-----------------------------------------------------------------------------
# stdrules.mk
#
#  Standard rules for use for Enhydra server applications.  It contains rules
#  for compiling generic Java modules and packages.  This requires GNU make,
#  as conditionals and other features are used.  This is normally included
#  from a top-level file, config.mk, which is included by each of the make
#  files.
# 
#  This file includes stdconf.mk if it has not already been included.  The
#  stdconf.mk file set the location of programs, jars, etc needed by make.  It
#  contains the global defaults for an Enhydra installation.  Applications can
#  override stdconf.mk values with their config.mk file.  See stdconf.mk for
#  the variables it contains.
#
#  There are standard two ways to organize an applications make system.
#  The first one requires only a single include of an external file, but
#  has less flexibility when using GNU make conditionals, due to include
#  order restrictions.  A summary of a makefile structure under this
#  approach is:
#
#     ROOT = ../..  # Set root directory
#     # Define CLASSES, SUBDIRS, etc
#     include $(ROOT)/config.mk  # includes stdconf.mk, stdrules.mk
#     # Define local rules, like clean::
#
#  The more flexable way, similar to that used by the Enhydra Makefiles,
#  requires two includes
#
#     ROOT = ../..  # Set root directory
#     include $(ROOT)/config.mk  # includes stdconf.mk
#     # Define CLASSES, SUBDIRS, etc
#     include $(STDRULES)
#     # Define local rules, like clean::
#
#  This second approach is more flexable in that values set in config.mk
#  can affect the values of makefile-specific defines which then affect
#  stdrules.mk definitions.
#
#  This file also includes stdconf.mk, which is the site-wide configuration
#  for an install Enhydra tree.  This file is created by the configure script.
#  If one needs to chance the location of any of the packages Enhydra is
#  dependent on, edit stdconf.mk, not stdrules.mk.  If the Enhydra convention
#  for tree structure is followed, specific application's can override these
#  values in config.mk.
#
#  The make is run in multiple passes.  For pass xxxx, the following make
#  targets are defined:
#     o xxxx_pass: - Target to run the pass in the current makefile.  This
#       target invokes the other targets.
#     o xxxx_pass_pre_targets:: - Target to build in current directory before
#       subdirectories are built.  This is a double-colon rule and makefiles
#       may add to this rule to perform tasks.
#     o xxxx_pass_subdirs: - Rule to recursively run the pass on the subdirectories
#       specified in SUBDIRS.
#     o xxxx_pass_post_targets:: - Target to build in current directory after
#       subdirectories are built.  This is a double-colon rule and makefiles
#       may add to this rule to perform tasks.
#
#  The following passes are currently defined:
#     o setup_pass: - This pass is used to do any initial setup and to
#       build files required by the Java compile pass, but are not java
#       sources themselves.  XMLC and JDDI files are compiled in the
#       post-phase of this pass.
#
#     o java_pass: - Pass to compile all Java class files specified in the
#       CLASSES variable.  They are compiled during the post-phase.  The
#       MISC_BUILD targets and MISC_INSTALL files are also handled in the
#       post-phase.
#
#  Individual Makefiles define the following variables:
#
#    o ROOT -- The path to the directory containing the config.mk file.  This
#      defines the top of the build tree, with most paths being relative to
#      this value.  It should be defined relative to the directory containing
#      the make file, for example:
#          ROOT= ../..
#
#      If absolute name expansion is needed in path names, it can be set to
#      an absolute path. For example:
#      
#          ROOT = ${shell ../..;pwd}
#
#    o CLASSES -- The classes, if any, to compile for the current subdirectory.
#      Do not use the .java or .class extension.
#
#    o ENHYDRA_CLASSPATH -- Use to set the Java CLASSPATH variable.  This
#      is a GNU make `simply expanded' variable.  It must be set using `:='
#      rather than `='.   This value of the ENHYDRA_CLASS_PATH variable is
#      built up using this style of assignment:
#
#         ENHYDRA_CLASSPATH:=${MY_CLASSES}${PS}${ENHYDRA_CLASSPATH}
#
#      Use of `:=' allows paths to be preappended as needed, in this
#      file and in application config.mk and Makefiles. ENHYDRA_CLASSPATH
#      must be set after including stdrules.mk if ${PS} is to be used.
#      Any existing CLASSPATH environment variable is appended to the
#      end of ENHYDRA_CLASSPATH.
#      The outdate variable, XTRA_CLASSPATH is still added, but a warning is
#      generated.  Please remove use of this variable.
#
#    o JDDI_CLASSES -- The JDDI POs, if any, to compile for the current
#      subdirectory. Do not include the .jhtml extension.
#      NOTE:  make clean will remove any generate .java files with the same
#      base name.  This is necessary to clean up any files left around
#      after running jddic -k or killing jddic before it completes.  
#
#    o RMI_CLASSES -- The remote object implementation classes, if any, to run
#      the RMI compiler on for the current subdirectory.  Do not use the .java
#      or .class extension.
#
#    o SUBDIRS -- Subdirectories, if any, to recursively build.
#
#    o PACKAGEDIR -- If CLASSES is not empty, the directory, relative to the
#      install root, where the resulting class files will be written by javac.
#      This is needed in order to allow "make" to check dependencies.
#
#    o HTML_CLASSES - List of classes to be generated from HTML using XMLC.
#      These classes *must* be named in the form xxxxHTML and be generated
#      from a HTML file named xxxx.html in HTML_DIR.  The names in this 
#      variable must not include the .class extension.
#
#    o HTML_DIR - Directory containing HTML files to compile with XMLC.  It
#      should be relative to the current directory or $(ROOT).  A rule also
#      exists for compiling HTML files in the current directory as well as
#      this directory.
#
#    o XMLC_HTML_OPTS_FILE - Name of .xmlc options file to use when compiling
#      HTML files with XMLC.  May be left unspecified or empty.
#
#    o XMLC_HTML_OPTS - Options to pass to XMLC when compiling HTML files.
#      May be left unspecified or empty.
#
#    o XMLC_xxxx_OPTS - Additional options to apply only to the compiling
#      xxxx.html.
#
#    o XMLC_xxxx_OPTS_FILE - Name of .xmlc options file to use when compiling
#      xxxx.html.
#
#    o WML_CLASSES - List of classes to be generated from WML using XMLC.
#      These classes *must* be named in the form xxxxWML and be generated
#      from a WML file named xxxx.wml in WML_DIR.  The names in this 
#      variable must not include the .class extension.
#
#    o WML_DIR - Directory containing WML files to compile with XMLC.  It
#      should be relative to the current directory or $(ROOT).  A rule also
#      exists for compiling WML files in the current directory as well as
#      this directory.
#
#    o XMLC_WML_OPTS - Options to pass to XMLC when compiling WML files.
#      May be left unspecified or empty.
#
#    o XMLC_JAVAC_OPTS - Options use to specify the the java compile that
#      XMLC will use.
#
#    o XMLC_AUTO_COMP - If YES, then XMLC files get compiled to support
#      automatic recompilation.  A jar file is not created if this is enabled.
#      The classes directory is set to point to output/lib/classes and a
#
#    o MISC_BUILD - add additional build target
#
#    o MISC_INSTALL - files to be installed into the output directory.
#
#    o JAR_INSTALL - files to be installed into the classes directory for
#      inclusion in the jar file.
#

#----------------------------------------------------------------------------- 
# General make setup
.SUFFIXES: .java .class _Skel.class _Stub.class .sh .jpp .xml .jar .html


#----------------------------------------------------------------------------- 
# Include site configuration if it hasn't already been done.  Allow override
# the location for the Enhydra tree.
ifeq (${STDCONF},)
#    STDCONF=${ENHYDRA_DIR}/lib/stdconf.mk
    STDCONF=${ROOT}/../stdconf.mk
endif
ifeq (${STDCONF_INCLUDED},)
    include ${STDCONF}
endif

#----------------------------------------------------------------------------- 
# Class path to use for all Java compiles and execution.  This is a simply
# expanded variable so that paths can be added by application config.mk and
# Makefiles.  Since ENHYDRA_CLASSPATH is simply expanded, all defines must be
# set at the point they are added to ENHYDRA_CLASSPATH.  It is seeded with the
# CLASSPATH environment variable and the root of the application.

ENHYDRA_CLASSPATH:=${ROOT}${PS}${ENHYDRA_CLASSPATH}${PS}${CLASSPATH}

#----------------------------------------------------------------------------- 
# The full path to the Enhydra directory, classes and programs.  ENHYDRA_DIR is
# normally set by the config.mk file that includes this file.
#
ENHYDRA_LIB_DIR = $(ENHYDRA_DIR)/lib
ifeq ($(ENHYDRA_CLASSES),)
    ENHYDRA_CLASSES = $(ENHYDRA_DIR)/lib/enhydra.jar
endif
ENHYDRA_CLASSPATH:=${ENHYDRA_CLASSES}${PS}${ENHYDRA_CLASSPATH}

#
# JDDI HTML compiler.
#
ifeq ($(JDDIC),)
    JDDIC = $(ENHYDRA_DIR)/bin/jddic 
endif
ifeq ($(JOLTC),)
    JOLTC = $(ENHYDRA_DIR)/bin/joltc 
endif

#
# XMLC compiler.
#
ifeq ($(XMLC_DIR),)
    XMLC_DIR = $(ENHYDRA_DIR)
endif
ifeq ($(XMLC),)
    XMLC = $(XMLC_DIR)/bin/xmlc 
endif

#
# Flags to specify java compiler that XMLC will use.
#
ifeq ($(XMLC_JAVAC_OPTS),)
    XMLC_JAVAC_OPTS =
endif

#
# Command to run XMLC.  Need to append options and input file.
#
ifeq ($(XMLC_CMD),)
   XMLC_CMD = $(XMLC) -d $(CLASS_OUTPUT)
endif

#-----------------------------------------------------------------------------
# Definitions of components of the source tree and destination tree.

#
# The directory that contains all generated files.  This is an image of
# what gets installed on a production system.
#
ifeq ($(OUTPUT),)
    OUTPUT = $(ROOT)/output
endif

#
# The directory into which compiled Java classes are to be placed.
# The java compiler automatically arranges the classes correctly
# below this directory according to package name.
#
ifeq ($(CLASS_OUTPUT),)
    ifeq ($(XMLC_AUTO_COMP),YES)
	CLASS_OUTPUT = $(OUTPUT)/lib/classes
    else
	CLASS_OUTPUT = $(ROOT)/classes
    endif
endif
ENHYDRA_CLASSPATH:=${CLASS_OUTPUT}${PS}${ENHYDRA_CLASSPATH}

#
# Include Java extension jars.
#
ENHYDRA_CLASSPATH:=${JNDI_CLASSES}${PS}${ENHYDRA_CLASSPATH}
ENHYDRA_CLASSPATH:=${JDBC_STDEXT_CLASSES}${PS}${ENHYDRA_CLASSPATH}
ENHYDRA_CLASSPATH:=${JTA_CLASSES}${PS}${ENHYDRA_CLASSPATH}

#
# Other packages include with Enhydra
#
ENHYDRA_CLASSPATH:=${ENHYDRA_LIB_DIR}/castor.jar${PS}${ENHYDRA_CLASSPATH}

#
# J2EE for Enhydra Enterprise.  This must follow enhydra.jar or we get
# the wrong DOM classes.
#
ifneq ($(J2EE_DIR),)
    J2EE_CLASSES = ${J2EE_DIR}/lib/j2ee.jar
    ENHYDRA_CLASSPATH:=${ENHYDRA_CLASSPATH}${PS}${J2EE_CLASSES}
endif


#
# Where script command files go.
#
ifeq ($(BIN_OUTPUT),)
    BIN_OUTPUT = $(OUTPUT)/bin
endif

#
# Where jar/zip files go.
#
ifeq ($(LIB_OUTPUT),)
    LIB_OUTPUT = $(OUTPUT)/lib
endif

#  
# Where javadoc is written.
#
ifeq ($(JAVADOCDIR),)
    JAVADOCDIR = $(ROOT)/jdoc
endif

#  
# Default name of jar file without the .jar extension.
#
ifeq ($(JARNAME),)
    JARNAME = enhydraApp
endif

#
# Temporary directory to use for some intermediate files.
#
BUILD_TMP = ${ROOT}/tmp

#
# The '.' seperated package name derived from PACKAGEDIR
#
PACKAGE = $(subst /,.,$(PACKAGEDIR))

#
# Package output directory.
#
PACKAGE_OUTPUT = $(CLASS_OUTPUT)/$(PACKAGEDIR)

#
# Package-specific make tmp directory
#
PACKAGE_TMP = ${BUILD_TMP}/pkg-tmp/${PACKAGEDIR}

#-----------------------------------------------------------------------------
# Default "all" rule if none is specified on the command line.

build_all: echocp check_sanity setup_pass java_pass

all: build_all

echocp:
	@echo CLASSPATH='${ENHYDRA_CLASSPATH}'

rules:
	@echo ""
	@echo "public make rules"
	@echo "================="
	@echo "all	-	Build all source."
	@echo "build_all -	Same as \"all\" rule"
	@echo "javadoc	-	Build all the javadoc in the source files."
	@echo "jar	-	Build the jar file."
	@echo "world	-	Build all source. Can be called from anywhere"
	@echo "			within the source tree."
	@echo "clean	-	Clean the source tree."
	@echo "clean_world -	Clean the source tree. Can be called from"
	@echo "			anywhere within the source tree."
	@echo ""


#-----------------------------------------------------------------------------
# Set pass, add to setup_pass_pre_targets or setup_pass_post_targets to
# include tasks.  The funky assignment to $* (with set) is due to the fact
# that Solaris sh cann't handle `for d in ${SUBDIRS} do if SUBDIRS is
# empty.

setup_pass:: setup_pass_pre_targets \
	     setup_pass_subdirs \
	     setup_pass_post_targets

setup_pass_pre_targets::

setup_pass_subdirs:
	@set - $(SUBDIRS) ;\
	for d do \
	    (cd $$d && $(MAKE) setup_pass) || exit 1 ; \
	done

setup_pass_post_targets:: xmlc_targets jddi_targets

#-----------------------------------------------------------------------------
# Java pass, add to java_pass_pre_targets or java_pass_post_targets to include
# tasks.   See comment in setup_pass section on SUBDIRS loops.

java_pass:: java_pass_pre_targets java_pass_subdirs java_pass_post_targets

java_pass_pre_targets::

java_pass_subdirs:
	@set - $(SUBDIRS) ;\
	for d do \
	    (cd $$d && $(MAKE) java_pass) || exit 1 ; \
	done

java_pass_post_targets:: misc_build_targets java_targets rmi_targets \
			 misc_install_targets jar_install_targets

#-----------------------------------------------------------------------------
# Standard targets referenced in xxxx_pass_targets

# Java classes
java_targets:: $(CLASSES:%=$(PACKAGE_OUTPUT)/%.class)

# JDDI classes
jddi_targets:: $(JDDI_CLASSES:%=$(PACKAGE_OUTPUT)/%.class) \
	       $(JOLT_CLASSES:%=$(PACKAGE_OUTPUT)/%.class)

# XMLC classes from HTML
xmlc_targets:: $(HTML_CLASSES:%=${PACKAGE_OUTPUT}/%.class)

# RMI stub and Skel
rmi_targets:: $(RMI_CLASSES:%=$(PACKAGE_OUTPUT)/%.class) \
	      $(RMI_CLASSES:%=$(PACKAGE_OUTPUT)/%_Skel.class) \
	      $(RMI_CLASSES:%=$(PACKAGE_OUTPUT)/%_Stub.class)

# Misc target to build
misc_build_targets:: $(MISC_BUILD) domisc_targets

# Misc targets to install in output.  FIXME: drop.
misc_install_targets:: $(MISC_INSTALL:%=$(OUTPUT)/$(PACKAGEDIR)/%)

$(OUTPUT)/$(PACKAGEDIR)/%: %
	@mkdir -p `dirname $@`
	cp -f $< $@

# Misc targets to install in package class directory
jar_install_targets:: $(JAR_INSTALL:%=$(PACKAGE_OUTPUT)/%) dojar_targets

$(PACKAGE_OUTPUT)/%: %
	@mkdir -p `dirname $@`
	cp -f $< $@

# Compatibility with older version of stdrules
domisc_targets:: domisc_build domisc_install
domisc_build:: 
domisc_install::
dojar_targets:: dojar_install
dojar_install::

#-----------------------------------------------------------------------------
# JavaDoc rules
#
# At any point in the tree, you can type "make javadoc", and you will end up
# with a directory "jdoc". Normally this is run at the top of the
# tree, but for testing you can run it in a subdirectory. 


# javadoc 1.1 didn't include runtime classes if user set CLASSPATH
ifeq (${JDKVERSION},1.1)
    JAVADOC_JDK_MISSING_CLASSES=${JDKDIR}/lib/classes.zip
endif

#
# Invoke javadoc with a list of packages that were discovered.
#
javadoc: jdclean jddir jdinit javadoc_pass
	$(JAVADOC) $(JDOCMEM) $(JAVADOCFLAGS) -d $(JAVADOCDIR) \
	    -sourcepath "$(ROOT)${PS}$(ENHYDRA_CLASSPATH)${PS}${JAVADOC_JDK_MISSING_CLASSES}" \
	    `sort -u $(JAVADOCDIR)/package.list`

#
# Get rid of the old doc.
#
jdclean:
	rm -rf $(JAVADOCDIR)

#
# Get the jdoc directory created and initialized.
# In Java 1.2 they removed the images and html file, so if they exist
# in the JDK, then copy them. If the don't that's fine.
#
jddir:
	@mkdir -p $(JAVADOCDIR)
	@[ ! -f $(JDKDIR)/docs/api/API_users_guide.html ]  || \
	    ${MAKE} $(JAVADOCDIR)/API_users_guide.html
	@[ ! -d $(JDKDIR)/docs/api/images ]  || \
	    ${MAKE} $(JAVADOCDIR)/images

#
# Create the javadoc directory, if it doesn't already exist.
#
$(JAVADOCDIR)/API_users_guide.html:
ifeq ($(JDKVERSION),1.1)
	@mkdir -p $(JAVADOCDIR)
	cp -rf $(JDKDIR)/docs/api/API_users_guide.html $(JAVADOCDIR)
	chmod u+w $(JAVADOCDIR)/API_users_guide.html
endif

$(JAVADOCDIR)/images:
ifeq ($(JDKVERSION),1.1)
	@mkdir -p $(JAVADOCDIR)/images
	cp -rf $(JDKDIR)/docs/api/images/ $(JAVADOCDIR)
	chmod -R u+w $(JAVADOCDIR)/images
endif

#
# Builds JavaDoc documentation from the source tree.
#
javadoc_pass:: javadoc_subdirs javadoc_targets

#
# Default rule to descend the source tree and build all JavaDoc HTML
# documentation from the source code.
#
javadoc_subdirs: pre_javadoc_subdirs
	@set - $(SUBDIRS) ;\
	for d do \
	    (cd $$d && $(MAKE) javadoc_pass) || exit 1 ;\
	done

#
# Always done before jpass_subdirs. Add dependencies to this target, don't
# make *_subdirs a :: target.
#
pre_javadoc_subdirs::


#
# Default rule to build JavaDoc targets. Directories containing no
# CLASSES cannot contain javadoc and are thus skipped.
#
javadoc_targets:
	@PACKAGEDIR=$(PACKAGEDIR) ; \
	export PACKAGEDIR ; \
	if [ -n "$$PACKAGEDIR" ] ; then \
	    if [ -n "$(CLASSES)" ] ; then \
		PKGNAME=`echo "$$PACKAGEDIR" | sed 's/\//./g'` ; \
		echo "Including package '$$PKGNAME' in documentation..." ; \
		echo "$$PKGNAME" >> $(JAVADOCDIR)/package.list ; \
	    fi ; \
	fi

#
# Clean the generated javadoc tree.
#
clean_javadoc:
	rm -rf $(JAVADOCDIR)

#
# Initialize the package list prior to building JavaDoc.
#
jdinit:
	rm -f $(JAVADOCDIR)/package.list


###############################################################################
#				 General build rules
###############################################################################

#
# At any directory level, "make world" builds from the root of the tree.
#
world:
	((cd $(ROOT) || exit 1) && ($(MAKE) build_all || exit 1))

#
# Default Clean-up rule to remove compiled classes.
#
clean::	clean_subdirs clean_current

#
# Cleans up any subdirectories listed in SUBDIRS.
#
clean_subdirs:
	@set - $(SUBDIRS) ;\
	for d do \
	    (cd $$d && $(MAKE) clean) || exit 1 ;\
	done

#
# Cleans the current directory level.  Conditionals are because some rm
# commands complain if there are no arguments.
#
clean_current:
ifneq ($(PACKAGEDIR),)
	-rm -rf $(PACKAGE_OUTPUT)/*.class
endif
ifneq ($(JDDI_CLASSES),)
	-rm -rf $(JDDI_CLASSES:%=./%.java)
endif
ifneq ($(JOLT_CLASSES),)
	-rm -rf $(JOLT_CLASSES:%=./%.java)
endif
ifneq ($(HTML_CLASSES),)
	-rm -rf $(HTML_CLASSES:%=./%.java)
	-rm -rf $(HTML_CLASSES:%=./%Impl.java)
endif
ifneq ($(MISC_INSTALL),)
	-rm -rf $(MISC_INSTALL:%=$(OUTPUT)/$(PACKAGEDIR)/%)
endif

#
# Does a global cleanup -- removes the entire "output" tree and
# generated javadoc leaving the tree in pristine condition.
#
clean_world:	clean_javadoc
	-rm -rf $(OUTPUT) $(CLASS_OUTPUT)

###############################################################################
#			 Pattern-matching rules
###############################################################################

#
# Rule for building RMI stub and skeleton classes.
#
$(PACKAGE_OUTPUT)/%_Skel.class: $(PACKAGE_OUTPUT)/%.class
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; \
	set -x ; \
	$(RMIC) -d $(CLASS_OUTPUT) $(PACKAGE).$(<F:%.class=%) || exit 1


#
# Rule to build Java classes.  A list of the java sources to all outdated
# files is generated by a recursive call to a special target.  This is then
# used to compile all out-of-date java files in one execution of the
# compiler, which is significantly faster.  These rules have been problematic
# to get working, if a make file has trouble, set JAVAC_SIMPLE_MODE=YES
#

ifeq (${GET_OUTDATED},YES)
# Recursively called to output list of out-of-date files.
get-outdated: $(CLASSES:%=$(PACKAGE_OUTPUT)/%.class)

$(PACKAGE_OUTPUT)/%.class: %.java
	@echo $<

else
# Compile all at once.  Recursively calls make to get files.
$(PACKAGE_OUTPUT)/%.class: %.java
	@mkdir -p $(PACKAGE_OUTPUT)
	@javafiles="`${MAKE} --no-print-directory --quiet get-outdated GET_OUTDATED=YES MAKEFLAGS=`" ; \
	if [ "X$$javafiles" != "X" ] ; then \
	(CLASSPATH="$(ENHYDRA_CLASSPATH)"; export CLASSPATH; set -x ; \
	$(JAVAC) -d $(CLASS_OUTPUT) $(JCFLAGS) $$javafiles || exit 1) ;fi
endif
ifneq (${JAVAC_SIMPLE_MODE},YES)

else

# One at a time rule
$(PACKAGE_OUTPUT)/%.class: %.java
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; \
	set -x ; \
	$(JAVAC) -d $(CLASS_OUTPUT) $(JCFLAGS) $< || exit 1
endif

#
# Rule for building RMI stub and skeleton classes. Note, both the Stub abnd
# works around a very problem with this rule which appears to be a bug in
# make.  If the $(PACKAGE_OUTPUT) directory didn't exists yet, the rule would
# not be defined by make.  So the first time a make was done in a directory,
# it couldn't find the rule to run make.  Wasted a lot of time trying to
# figure out why. This recurses to run make a second time with a different
# rule.
#
$(PACKAGE_OUTPUT)/%_Skel.class $(PACKAGE_OUTPUT)/%_Stub.class: $(PACKAGE_OUTPUT)/%.class
ifeq (${RMI_RECURSE},YES)
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; set -x ; \
	$(RMIC) -d $(CLASS_OUTPUT) $(PACKAGE).$* || exit 1
else
$(PACKAGE_OUTPUT)/%_Skel.class $(PACKAGE_OUTPUT)/%_Stub.class: %.java
	${MAKE} RMI_RECURSE=YES
endif

#
# Compile JDDI files
#
$(PACKAGE_OUTPUT)/%.class: %.jhtml
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; set -x ; \
	$(JDDIC) $(JOLTCFLAGS) -d $(CLASS_OUTPUT) $< $(PACKAGE)

#
# Support old name HTML_XMLC_OPTS_FILE
#
ifeq (${HTML_XMLC_OPTS_FILE},)
    error: HTML_XMLC_OPTS_FILE is no longer valied, switch to XMLC_HTML_OPTS_FILE
endif

#
# HTML build rules
#
ifeq ($(XMLC_AUTO_COMP),YES)
    XMLC_HTML_OPTS += -for-recomp
endif

$(PACKAGE_OUTPUT)/%HTML.class: $(HTML_DIR)/%.html $(XMLC_HTML_OPTS_FILE) $(XMLC_%_OPTS_FILE)
	@mkdir -p $(PACKAGE_OUTPUT)
ifeq ($(XMLC_AUTO_COMP),YES)
	cp -f $(HTML_DIR)/$*.html $(PACKAGE_OUTPUT)
endif
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; \
	set -x ; \
	$(XMLC_CMD) -class $(PACKAGE).$*HTML $(XMLC_HTML_OPTS) $(XMLC_$*_OPTS) $(XMLC_JAVAC) $(XMLC_HTML_OPTS_FILE) $(XMLC_$*_OPTS_FILE) $(HTML_DIR)/$*.html

$(PACKAGE_OUTPUT)/%HTML.class: %.html $(XMLC_HTML_OPTS_FILE)
	@mkdir -p $(PACKAGE_OUTPUT)
ifeq ($(XMLC_AUTO_COMP),YES)
	cp -f $*.html $(PACKAGE_OUTPUT)
endif
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; \
	set -x ; \
	$(XMLC_CMD) -class $(PACKAGE).$*HTML $(XMLC_HTML_OPTS) $(XMLC_$*_OPTS) $(XMLC_JAVAC) $(XMLC_HTML_OPTS_FILE)  $*.html

#
# WML build rules
#
XMLC_WML_OPTS += -domfactory org.enhydra.wireless.wml.WMLDomFactory -xcatalog ${ENHYDRA_DIR}/xml/wml/wml.xcat
ifeq ($(XMLC_AUTO_COMP),YES)
    XMLC_WML_OPTS += -for-recomp
endif

$(PACKAGE_OUTPUT)/%WML.class: $(WML_DIR)/%.wml $(XMLC_WML_OPTS_FILE) $(XMLC_%_OPTS_FILE)
	@mkdir -p $(PACKAGE_OUTPUT)
ifeq ($(XMLC_AUTO_COMP),YES)
	cp -f $(WML_DIR)/$*.wml $(PACKAGE_OUTPUT)
endif
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; set -x ; \
	$(XMLC_CMD) -class $(PACKAGE).$*WML $(XMLC_WML_OPTS) $(XMLC_$*_OPTS) \
	    $(XMLC_JAVAC) $(XMLC_WML_OPTS_FILE) $(XMLC_$*_OPTS_FILE) $(WML_DIR)/$*.wml

$(PACKAGE_OUTPUT)/%WML.class: %.wml $(XMLC_WML_OPTS_FILE) $(XMLC_%_OPTS_FILE)
	@mkdir -p $(PACKAGE_OUTPUT)
ifeq ($(XMLC_AUTO_COMP),YES)
	cp -f $*.wml $(PACKAGE_OUTPUT)
endif
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; set -x ; \
	$(XMLC_CMD) -class $(PACKAGE).$*WML $(XMLC_WML_OPTS) $(XMLC_$*_OPTS) \
	    $(XMLC_JAVAC) $(XMLC_WML_OPTS_FILE) $(XMLC_$*_OPTS_FILE) $*.wml

do_xmlc_html_targets:: $(WML_CLASSES:%=${PACKAGE_OUTPUT}/%.class)

#
# Check for missing parts of the build environment.  If ENHYDRA_CHECK
# is set to "YES", this checks for things in the enhydra directory that
# are needed to build apps.
#
check_sanity:
	@[ -d $(JDKDIR) -o -h $(JDKDIR) ] || (echo "ERROR: Unable to find JDK ($(JDKDIR)). You must set JDKDIR to point to your installation of Sun's Java Development Kit, in the files config.mk or ${STDCONF}" ; echo; exit 1)
	@[ -f $(JAVAC) ] || (echo "ERROR: Unable to find javac in the JDK ($(JAVAC)). You must set JDKDIR to point to your installation of Sun's Java Development Kit, in the files config.mk or ${STDCONF}" ; echo; exit 1)
	@[ -f $(JAVA) ] || (echo "ERROR: Unable to find java interpreter ($(JAVA)). You must set JDKDIR to point to your installation of Sun's Java Development Kit, in the files config.mk or /usr/local/enhydra/lib/stdrule.mk" ; echo; exit 1)
ifeq (${JDKVERSION},1.1)
	@[ -d $(SWINGDIR) -o -h $(SWINGDIR) ] || (echo "WARNING: Unable to find SWING (JFC) ($(SWINGDIR)). To fix, set SWINGDIR to point to your installation of swing, in the files config.mk or ${STDCONF}" ; echo)
endif
ifeq ($(ENHYDRA_CHECK),YES)
	@[ -d $(ENHYDRA_DIR) -o -h $(ENHYDRA_DIR) ] || (echo "ERROR: Unable to find the Enhydra directory ($(ENHYDRA_DIR)). You must set ENHYDRA_DIR to point to the Enhydra distribution directory in your file config.mk." ; echo; exit 1)
	@[ -f $(ENHYDRA_CLASSES) -o -d $(ENHYDRA_CLASSES) ] || (echo "ERROR: Unable to find either enhydra.jar or the enhydra classes directory ($(ENHYDRA_CLASSES)). You must set ENHYDRA_DIR to point to the Enhydra distribution directory in your file config.mk." ; echo; exit 1)
	@[ -f $(JDDIC) ] || (echo "ERROR: Unable to find jddic ($(JDDIC)). You must set ENHYDRA_DIR to point to the Enhydra distribution directory in your file config.mk." ; echo; exit 1)
endif
ifneq ($(XTRA_CLASSPATH),)
	@echo "***"
	@echo "*** Error: Use of outdated make variable XTRA_CLASSPATH."
	@echo "*** Convert to prepending to the simple expanded ENHYDRA_CLASSPATH"
	@echo "*** make variable.  See documentation in *** stdrules.mk."
	@echo "***"
	exit 1
endif

# FIXME: Setting XMLC_AUTO_COMP should only affect the XMLC generate objects
# not the whole jar.  This will be easier when we go to building WARs for
# EnhydraApps
jar:
ifneq ($(XMLC_AUTO_COMP),YES)
	mkdir -p $(LIB_OUTPUT)
	(cd $(CLASS_OUTPUT) && $(JAR) -c .) >$(LIB_OUTPUT)/$(JARNAME).jar.tmp
	mv -f $(LIB_OUTPUT)/$(JARNAME).jar.tmp $(LIB_OUTPUT)/$(JARNAME).jar
endif

#
# Create a jar for the current JARNAME
#
build-jar:
	mkdir -p $(LIB_OUTPUT)
	(cd $(CLASS_OUTPUT) && $(JAR) -c .) >$(LIB_OUTPUT)/$(JARNAME).jar.tmp
	mv -f $(LIB_OUTPUT)/$(JARNAME).jar.tmp $(LIB_OUTPUT)/$(JARNAME).jar
