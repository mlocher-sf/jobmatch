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
# $Id: stdrules.mk,v 1.3 2000/05/17 09:55:15 locher Exp $
#-----------------------------------------------------------------------------

#
# stdrules.mk
#
#  Standard rules for use for Enhydra server applications.  It contains rules
#  for compiling generic Java modules and packages.  This requires GNU make,
#  as conditionals and other features are used.  This is normally included
#  from a top-level file, config.mk, which is included by each of the make
#  files.
# 
#  This file includes stdconf.mk, which has the configuration variables for
#  the location of programs, jars, etc needed by make.  It contains the 
#  global defaults an Enhydra installation.  Individual applications can in
#  turn override values with their config.mk file.  See stdconf.mk for
#  the variables it contains.
#
#  This file also includes stdconf.mk, which is the site-wide configuration
#  for an install Enhydra tree.  This file is created by the configure script.
#  If one needs to chance the location of any of the packages Enhydra is
#  dependent on, edit stdconf.mk, not stdrules.mk.  If the Enhydra convention
#  for tree structure is followed, specific application's can override these
#  values in config.mk.
#
#  Individual Makefiles define the following variables to use the targets in
#  this Makefile.
#
#    o ROOT -- The path to the directory containing the config.mk file.  This
#      defines the top of the build tree, with most paths being relative to
#      this value.  It should be defined relative to the directory containing
#      the make file, for example:
#          ROOT= ../..
#
#      If absolute name expansion is needed in path names, it can be set to
#      an absolute path. However this should only be used when required,
#      as it may result in file paths containing spaces, which must be
#      handled carefully where ever referenced.  For example:
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
#         ENHYDRA_CLASSPATH:=${MY_CLASSES}${PS}${ENHYDRA_CLASS_PATH}
#
#      Use of `:=' allows paths to be preappended as needed, in this
#      file and in application config.mk and Makefiles. ENHYDRA_CLASS_PATH
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
#    o POSTSUBDIRS -- Subdirectories, if any, to recursively build after
#      the current directory has been processed.  This is useful for
#      test subdirectories, that want to have JARNAME=tests.  If the
#      test directories are compiled first, they will compile their
#      dependencies into the wrong directory.
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
# Platform-specific separator for paths.
#
ifneq ($(OSTYPE),cygwin32)
    PS=:
else
    PS=\;
endif

#----------------------------------------------------------------------------- 
# Absolute path to tree root
ABS_ROOT = $(shell cd ${ROOT} && pwd)

#----------------------------------------------------------------------------- 
# Include site configuration.  Allow override the locaiton for the Enhydra
# tree.
ifeq (${STDCONF},)
#    STDCONF=${ENHYDRA_DIR}/lib/stdconf.mk
    STDCONF=${ROOT}/../stdconf.mk
endif
include ${STDCONF}

#----------------------------------------------------------------------------- 
# Class path to use for all Java compiles and execution.  This is a simply
# expanded variable so that paths can be added by application config.mk and
# Makefiles.  XTRA_CLASSPATH is obsolete.  Since ENHYDRA_CLASSPATH is simply
# expanded, all defines must be set at the point they are added to
# ENHYDRA_CLASSPATH.  It is seeded with the CLASSPATH environment variable
# and the root of the application/

ENHYDRA_CLASSPATH:=$(XTRA_CLASSPATH)${PS}${ROOT}${PS}${ENHYDRA_CLASSPATH}${PS}${CLASSPATH}

#----------------------------------------------------------------------------- 
# The full path to the Enhydra directory and classes.  ENHYDRA_DIR is
# normally set by the config.mk file that includes this file.
#
ifeq ($(ENHYDRA_DIR),)
    ENHYDRA_DIR = /usr/local/enhydra
endif
ifeq ($(ENHYDRA_CLASSES),)
    ENHYDRA_CLASSES = $(ENHYDRA_DIR)/lib/enhydra.jar
endif
ENHYDRA_CLASSPATH:=${ENHYDRA_CLASSES}${PS}${ENHYDRA_CLASSPATH}

#
# Jddi HTML compiler.
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
#----------------------------------------------------------------------------- 

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
# Where Java Native Interface DLL's are placed.
#
ifeq ($(NATIVE_OUTPUT),)
    NATIVE_OUTPUT = $(OUTPUT)/lib
endif

#
# Where Java Native Interface generated include files go.
#
ifeq ($(INCLUDE_OUTPUT),)
    INCLUDE_OUTPUT = $(OUTPUT)/include
endif

#  
# Where Java Native Interface include files are placed.
#  
ifeq ($(JNI_INCLUDES),)
    JNI_INCLUDES =  -I$(JDKDIR)/include -I$(JDKDIR)/include/$(JDKARCH) \
		    -I$(INCLUDE_OUTPUT)
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
PACKAGE = $(shell echo $(PACKAGEDIR) | tr / .)

#
# Package output directory.
#
PACKAGE_OUTPUT = $(CLASS_OUTPUT)/$(PACKAGEDIR)

#
# File that is generated containing the list of out-of-date Java source
# files for a package.
#
JAVA_OUTDATED_SRCS = ./java-outdated-srcs.tmp

###############################################################################
# Default "all" rule if none is specified in the subdir make file.
###############################################################################

build_all:	echocp write_header check_sanity java_pass

all:		build_all

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


###############################################################################
#				Java Build Rules
###############################################################################

#
# Performs a Java target build pass at the current directory level.
#
java_pass:: do_xmlc_html_targets dojhtml_targets jpass_subdirs dojpass_targets jpass_post_subdirs dormi_targets donative_targets domisc_targets dojar_targets

#
# Default rule to build Java class targets.
#
dojpass_targets:: $(JDDI_CLASSES:%=$(PACKAGE_OUTPUT)/%.class) $(JOLT_CLASSES:%=$(PACKAGE_OUTPUT)/%.class) $(HTML_CLASSES:%=${PACKAGE_OUTPUT}/%.class) $(CLASSES:%=$(PACKAGE_OUTPUT)/%.class)

#
# Default rule to build JOLT source targets.
#
dojhtml_targets:: $(JDDI_CLASSES:%=%.jhtml) $(JOLT_CLASSES:%=%.jhtml)

#
# Default rule to build classes from HTML using XMLC
#
do_xmlc_html_targets:: $(HTML_CLASSES:%=${PACKAGE_OUTPUT}/%.class)

#
# Default rule to build rmi skeleton and stub classes.
#
dormi_targets:: $(RMI_CLASSES:%=$(PACKAGE_OUTPUT)/%.class) $(RMI_CLASSES:%=$(PACKAGE_OUTPUT)/%_Skel.class)

#
# Default rule to build native class targets.
# FIX: this should not loop
ifneq ($(NATIVE),)
donative_targets::
	@mkdir -p $(NATIVE_OUTPUT)
	@for x in $(NATIVE); do \
		$(MAKE) $(NATIVE_OUTPUT)/lib$$x.so || exit 1 ; \
	done
else
donative_targets:
endif

#
# Default rule to install MISC files.
# FIX: this should not loop
#
domisc_targets:: domisc_build domisc_install

domisc_build:: $(MISC_BUILD)

ifneq ($(MISC_INSTALL),)
domisc_install::
	@mkdir -p $(OUTPUT)/$(PACKAGEDIR)
	@for f in $(MISC_INSTALL) ; do \
		ff=`basename $$f`; \
		if cmp $$f $(OUTPUT)/$(PACKAGEDIR)/$$ff > /dev/null 2>&1; \
		then \
			: ; \
		else \
			echo Installing $$f; \
			cp `basename $$f` $(OUTPUT)/$(PACKAGEDIR); \
		fi; \
	done
else
domisc_install::
endif


#
# Copy files into the classes directory, so they will be put in the jar file.
#
dojar_targets:: dojar_install

ifneq ($(JAR_INSTALL),)
dojar_install::
	@mkdir -p $(PACKAGE_OUTPUT)
	@for f in $(JAR_INSTALL) ; do \
		ff=`basename $$f`; \
		if cmp $$f $(PACKAGE_OUTPUT)/$$ff > /dev/null 2>&1; \
		then \
			: ; \
		else \
			echo Adding to JAR classes: $$f; \
			cp $$f $(PACKAGE_OUTPUT); \
		fi; \
	done
else
dojar_install::
endif

#
# Default rule to run the Java target build pass on any subdirectories
# listed in SUBDIRS. The build is performed depth-first, so that
# subdirectories are output before targets at the current level are output.
# NOTE: The funky assignment to $* (with set) is due to the fact
# that Solaris sh can handle `for d in ${SUBDIRS} do if SUBDIRS is
# empty.
#
jpass_subdirs: pre_jpass_subdirs
	@set - $(SUBDIRS) ;\
	for d do \
	    (cd $$d && $(MAKE) java_pass) || exit 1 ;\
	done

#
# Always done before jpass_subdirs. Add dependencies to this target, don't
# make *_subdirs a :: target.
#
pre_jpass_subdirs::

#
# Subdirectories done after Java compile.
#
jpass_post_subdirs: dojpass_targets
	@set - $(POST_SUBDIRS) ;\
	for d do \
	    (cd $$d && $(MAKE) java_pass) || exit 1 ;\
	done



###############################################################################
#			  JavaDoc Documentation Rule
###############################################################################
#
#    At any point in the tree, you can type "make javadoc", and you will
# end up with a directory "jdoc". Normally this is run at the top of the
# tree, but for testing you can run it in a subdirectory. 
#

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
javadoc_pass::	javadoc_subdirs javadoc_targets

#
# Default rule to descend the source tree and build all JavaDoc HTML
# documentation from the source code.
#
javadoc_subdirs: pre_javadoc_subdirs
	@set - $(SUBDIRS) $(POST_SUBDIRS) ;\
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
	@set - $(SUBDIRS) $(POST_SUBDIRS) ;\
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
ifneq ($(JAVA_OUTDATED_SRCS),)
	-rm -f $(JAVA_OUTDATED_SRCS)
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
# Rule to build Java classes.  An intermediate list of files is generated
# so that all of the out-of-date files in the package are compiled in
# one Java command.  This is much faster.  The files that are current must
# be touched after building ${JAVA_OUTDATED_SRCS}, otherwise they are out
# of date relative to the just created ${JAVA_OUTDATED_SRCS}.  The ones to be
# rebuilt are removed so they don't get touched.
#

# FIXME: This currently doesn't work for all cases.  Its breaking XMLC compiles,
# disable it for now.
ifeq (true, false)
ifneq ($(CLASSES),)
${JAVA_OUTDATED_SRCS}: $(CLASSES:%=%.java)
	@mkdir -p $(PACKAGE_OUTPUT)
	@cd $(PACKAGE_OUTPUT) && rm -f ${subst .java,.class,$?}
	@echo $? >${JAVA_OUTDATED_SRCS}
	@cd $(PACKAGE_OUTPUT) && touch -c ${subst .java,.class,$^}
endif

$(PACKAGE_OUTPUT)/%.class: ${JAVA_OUTDATED_SRCS}
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH):$(CLASS_OUTPUT)"; export CLASSPATH  ; \
	javafiles="`cat ${JAVA_OUTDATED_SRCS}`" ;\
	set -x ; \
	$(ICONTRACT) $(ICONTRACTFLAGS) $$javafiles || exit 1 ;\
	mv *.class $(PACKAGE_OUTPUT)
else
# Old, one at a time rule
ifneq ($(USEICONTRACT),)	
$(PACKAGE_OUTPUT)/%.class: %.java
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH):$(CLASS_OUTPUT)" ; export CLASSPATH ; \
	set -x ; \
	$(ICONTRACT) $(ICONTRACTFLAGS) $< || $(JAVAC) $(JCFLAGS) $< || exit 1;\
	mv *.class $(PACKAGE_OUTPUT)
else
$(PACKAGE_OUTPUT)/%.class: %.java
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; \
	set -x ; \
	$(JAVAC) -d $(CLASS_OUTPUT) $(JCFLAGS) $< || exit 1
endif
endif

#
# Rule for building RMI stub and skeleton classes.
#
$(PACKAGE_OUTPUT)/%_Skel.class: $(PACKAGE_OUTPUT)/%.class
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; \
	set -x ; \
	$(RMIC) -d $(CLASS_OUTPUT) $(PACKAGE).$(<F:%.class=%) || exit 1


#
# Pattern-matching rule to build java native class methods.
# to the $(OUTPUT) directory
#
$(NATIVE_OUTPUT)/lib%.so: $(OUTPUT)/$(PACKAGEDIR)/%.class
	@mkdir -p $(INCLUDE_OUTPUT) $(NATIVE_OUTPUT)
	@class=`basename $< .class` ; \
	includes="-I$(JDKDIR)/include -I$(JDKDIR)/include/solaris" ; \
	includes="$$includes -I$(INCLUDE_OUTPUT)" ; \
	CLASSPATH="$(ENHYDRA_CLASSPATH)"; export CLASSPATH ; \
	set -x ; \
	$(JAVAH) -jni -o $(INCLUDE_OUTPUT)/$$class.h $(PACKAGE).$$class || exit 1 ; \
	$(CC) -G -o $@ $$includes $$class.c || exit 1 ; \

#
# Compile JoltHTML.
#
$(PACKAGE_OUTPUT)/%.class: %.jhtml
	@mkdir -p $(PACKAGE_OUTPUT)
	@CLASSPATH="$(ENHYDRA_CLASSPATH)" ; export CLASSPATH ; set -x ; \
	$(JDDIC) $(JOLTCFLAGS) -d $(CLASS_OUTPUT) $< $(PACKAGE)

#
# Support old name HTML_XMLC_OPTS_FILE
#
ifeq (${HTML_XMLC_OPTS_FILE},)
    XMLC_HTML_OPTS_FILE += ${HTML_XMLC_OPTS_FILE}
endif

#
# Support for XMLC recompilation
#
ifeq ($(XMLC_AUTO_COMP),YES)
    XMLC_HTML_OPTS += -for-recomp
endif

#
# Compile HTML with XMLC
#
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
# Check for missing parts of the build environment.  If ENHYDRA_CHECK
# is set to "YES", this checks for things in the enhydra directory that
# are needed to build apps.
#
check_sanity:
	@[ -d $(JDKDIR) -o -h $(JDKDIR) ] || (echo "ERROR: Unable to find JDK ($(JDKDIR)). You must set JDKDIR to point to your installation of Sun's Java Development Kit, in the files config.mk or ${STDCONF}" ; echo)
	@[ -d $(JDKDIR) -o -h $(JDKDIR) ]
	@[ -f $(JAVAC) ] || (echo "ERROR: Unable to find javac in the JDK ($(JAVAC)). You must set JDKDIR to point to your installation of Sun's Java Development Kit, in the files config.mk or ${STDCONF}" ; echo)
	@[ -f $(JAVAC) ]
	@[ -f $(JAVA) ] || (echo "ERROR: Unable to find java interpreter ($(JAVA)). You must set JDKDIR to point to your installation of Sun's Java Development Kit, in the files config.mk or /usr/local/enhydra/lib/stdrule.mk" ; echo)
	@[ -f $(JAVA) ]
ifeq (${JDKVERSION},1.1)
	@[ -d $(SWINGDIR) -o -h $(SWINGDIR) ] || (echo "WARNING: Unable to find SWING (JFC) ($(SWINGDIR)). To fix, set SWINGDIR to point to your installation of swing, in the files config.mk or ${STDCONF}" ; echo)
endif
ifeq ($(ENHYDRA_CHECK),YES)
	@[ -d $(ENHYDRA_DIR) -o -h $(ENHYDRA_DIR) ] || (echo "ERROR: Unable to find the Enhydra directory ($(ENHYDRA_DIR)). You must set ENHYDRA_DIR to point to the Enhydra distribution directory in your file config.mk." ; echo)
	@[ -d $(ENHYDRA_DIR) -o -h $(ENHYDRA_DIR) ]
	@[ -f $(ENHYDRA_CLASSES) -o -d $(ENHYDRA_CLASSES) ] || (echo "ERROR: Unable to find either enhydra.jar or the enhydra classes directory ($(ENHYDRA_CLASSES)). You must set ENHYDRA_DIR to point to the Enhydra distribution directory in your file config.mk." ; echo)
	@[ -f $(ENHYDRA_CLASSES) -o -d $(ENHYDRA_CLASSES) ] 
	@[ -f $(JDDIC) ] || (echo "ERROR: Unable to find jddic ($(JDDIC)). You must set ENHYDRA_DIR to point to the Enhydra distribution directory in your file config.mk." ; echo)
	@[ -f $(JDDIC) ]
endif
ifneq ($(XTRA_CLASSPATH),)
	@echo
	@echo "***"
	@echo "*** Warning: Use of outdated make variable XTRA_CLASSPATH."
	@echo "*** Please convert to prepending to the simple expanded"
	@echo "*** ENHYDRA_CLASSPATH make variable.  See documentation in"
	@echo "*** stdrules.mk."
	@echo "***"
	@echo
endif

write_header:
	@echo ; echo "Build Started `date`"

JAR_FILE = $(shell mkdir -p $(LIB_OUTPUT) && cd $(LIB_OUTPUT) && pwd)/$(JARNAME).jar
jar:
ifneq ($(XMLC_AUTO_COMP),YES)
	mkdir -p $(LIB_OUTPUT)
	(cd $(CLASS_OUTPUT) && $(JAR) -c .) >$(LIB_OUTPUT)/$(JARNAME).jar.tmp
	mv -f $(LIB_OUTPUT)/$(JARNAME).jar.tmp $(LIB_OUTPUT)/$(JARNAME).jar
endif
