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
# $Id: stdconf.mk,v 1.1 2000/06/20 13:32:52 pse4 Exp $
#-----------------------------------------------------------------------------

#  This file defines variables that contain the locations of various devsys
#  components.  They default to standard values.  to overide these values, set
#  the macros *BEFORE* this file has been included.  This file explictly
#  checks with conditionals; this works around certain definition order
#  problems. The variables are:
#
#    o JDKDIR -- Location of Sun Java Development Kit (JDK).
#
#    o JDKVERSION -- Major version of the JDK.  Currently this should be
#      either "1.1" or "1.2".  It is normally determined at runtime
#      automatically.
#
#    o JAVACCDIR -- Location of Sun Javacc Kit.
#
#    o SWINGDIR -- Directory containing the JFC (Java Foundation
#		   Classes) otherwise known as "swing".  Only requird for JDK 1.1
#
#    o JSSEDIR -- Directory containing the JSEE classes.
#
#    o KAFFE - If set to contain the command to run the Kaffe virtual
#      machine, this JVM is used instead of the default one.  See
#      www.kaffe.org.
#
#    o JIKES - If set to contain the command to run the IBM Jikes java
#      compiler, Jikes will be used to compile java in place of javac.
#      
#

#----------------------------------------------------------------------------- 
# The directory containing the Sun Java Development Kit (JDK) and
# various components.  JDKARCH is needed only if native methods are being
# compiled.  This is the same name as that used for platform-specific
# directories in the JDK. JDKVERSION is the major version number which is
# currently either 1.1 or 1.2.
#
ifeq ($(JDKDIR),)
    ifneq ($(OSTYPE),cygwin32)
        JDKDIR = /opt/local/jdk1.2.2
    else
        JDKDIR = /opt/local/jdk1.2.2
    endif
endif
ifeq ($(JDKARCH),)
    ifneq ($(OSTYPE),cygwin32)
        JDKARCH = solaris
    else
        JDKARCH = win
    endif
endif
ifeq ($(JDKVERSION),)
    JDKVERSION=${shell if [ -r ${JDKDIR}/lib/dt.jar ] ; then echo 1.2; else echo 1.1; fi}
endif
ifeq ($(JDKVERSION),1.1)
    JDK_CLASSES = ${JDKDIR}/lib/classes.zip
else
    JDK_CLASSES = ${JDKDIR}/jre/lib/rt.jar
endif

#
# Directory that contains the JDK programs.
#
ifeq ($(JDKBIN),)
    JDKBIN = $(JDKDIR)/bin
endif
PATH = $(JDKBIN):$(shell echo $${PATH})

#
# Java compiler command.
#
ifeq ($(JAVAC),)
    JAVAC = $(JDKBIN)/javac
endif

#
# Debug or optimization flag for javac.
#
ifeq ($(JCDEBUG),)
    JCDEBUG = -g
endif

#
# Compile-time flags for the Java compiler.
#
ifeq ($(JCFLAGS),)
JCFLAGS = ${JCDEBUG} -J-Xmx256m -J-Xms32m
endif

#
# iContract Command
#
ifeq ($(ICONTRACT),)
    ICONTRACT = $(JAVA) -cp $(JDKDIR)/lib/tools.jar:$(CLASSPATH) iContract.Tool
endif

#
# Compile-time flags for the Java compiler.
#
ifeq ($(ICONTRACTFLAGS),)
ICONTRACTFLAGS = -vA
endif

#
# Set JAVAC to be the JIKES compiler if it is specified.  Also add JDK classes
# to classpath if jikes is used and adjust other variables.
#
ifneq ($(JIKES),)
    JAVAC = ${JIKES}
    JCFLAGS += +E -nowarn
    ENHYDRA_CLASSPATH:=${JDK_CLASSES}${PS}${ENHYDRA_CLASSPATH}
    XMLC_JAVAC_OPTS = -javac $(JAVAC) -javacflag +E -javacflag -nowarn -javacflag $(JCDEBUG)
endif


#
#  Java native method header generator command.
#
ifeq ($(JAVAH),)
    JAVAH = $(JDKBIN)/javah
endif

#
# JavaDoc builder command.
#
ifeq ($(JAVADOC),)
    JAVADOC = $(JDKBIN)/javadoc
endif

#
# Memory argument to pass to the javadoc command.
#
ifeq ($(JDOCMEM),)
    JDOCMEM=-J-mx32m
endif

#
# The name of the RMI compiler.
#
ifeq ($(RMIC),)
    RMIC = $(JDKBIN)/rmic
endif

#
# jar command.
#
ifeq ($(JAR),)
    JAR = $(JDKBIN)/jar
endif

#
# The name of the java interpreter.
#
ifeq ($(JAVA),)
    JAVA = $(JDKBIN)/java
endif
ifeq ($(JOPTS),)
    JOPTS =
endif

#----------------------------------------------------------------------------- 
# The full path to the JFC library, swing.
#
ifeq ($(SWINGDIR),)
    ifneq ($(OSTYPE),cygwin32)
	SWINGDIR = /usr/local/swing-1.1
    else
	SWINGDIR = /usr/local/swing-1.1
    endif
endif
# We need to set Swing classes even with 1.2, as the debugger servlet
# wants the file.  Just don't add them to the class path for 1.2.
ifeq ($(SWING_CLASSES),)
    SWING_CLASSES = $(SWINGDIR)/swingall.jar
endif
ifeq (${JDKVERSION},1.1)
   ENHYDRA_CLASSPATH:=${SWING_CLASSES}${PS}${ENHYDRA_CLASSPATH}
endif


#----------------------------------------------------------------------------- 
# The directory containing the Sun Javacc kit.
#
ifeq ($(JAVACCDIR),)
    ifneq ($(OSTYPE),cygwin32)
        JAVACCDIR  = /usr/local/javacc1.1
    else
        JAVACCDIR  = /usr/local/javacc1.1
    endif
endif
WINJAVACCDIR = $(shell echo $(JAVACCDIR) | tr '/' '\\' | sed 's/^\\\\\(.\)/\1:/')
ifeq ($(JAVACC_CLASSES),)
    JAVACC_CLASSES = $(JAVACCDIR)/JavaCC.zip
endif
ENHYDRA_CLASSPATH:=${JAVACC_CLASSES}${PS}${ENHYDRA_CLASSPATH}

ifeq ($(JJTREE),)
    JJTREE = $(JAVA) COM.sun.labs.jjtree.Main
endif
ifeq ($(JAVACC),)
    JAVACC = $(JAVA) COM.sun.labs.javacc.Main
endif


#----------------------------------------------------------------------------- 
# The directory containing the Java Secure Socket Extension
#
ifeq ($(JSSEDIR),)
    JSSEDIR = /usr/local/jsse1.0
endif
ifeq ($(JSSE_CLASSES),)
    JSSE_CLASSES = ${JSSEDIR}/lib/jsse.jar${PS}${JSSEDIR}/lib/jcert.jar${PS}${JSSEDIR}/lib/jnet.jar
endif
# JSSE requires 1.2.
ifneq ($(JDKVERSION),1.1)
ifneq ($(wildcard ${JSSEDIR}/lib/jsse.jar),)
    HAVE_JSSE = YES
else
    HAVE_JSSE = NO
endif
endif
ifeq ($(HAVE_JSSE),YES)
    ENHYDRA_CLASSPATH:=${JSSE_CLASSES}${PS}${ENHYDRA_CLASSPATH}
endif

#----------------------------------------------------------------------------- 
# Location of Borland/Inprise JBuilder classes.  These are only required
# if the Kelp JBuilder support is to be built.  They are not required by
# the Enhydra runtime so are not added to the global CLASSPATH.
#
ifeq (${JBUILDER_DIR},)
    JBUILDER_DIR =
endif
ifeq (${JBUILDER_CLASSES},)
    JBUILDER_CLASSES = ${JBUILDER_DIR}/lib/jbuilder.zip
endif

ifeq (${JBFOUNDATION_DIR},)
    JBFOUNDATION_DIR =
endif
ifeq (${JBFOUNDATION_CLASSES},)
    JBFOUNDATION_CLASSES = ${JBFOUNDATION_DIR}/lib/jbuilder.jar${PS}${JBFOUNDATION_DIR}/lib/pthelp3.1.jar
endif

ifneq ($(wildcard ${JBUILDER_DIR}/lib/jbuilder.zip),)
    HAVE_JBUILDER = YES
else
    HAVE_JBUILDER = NO
endif

