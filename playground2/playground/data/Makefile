
ROOT =		../..

PACKAGEDIR = 	playground/data

CLASSES = 	StudentsBDO StudentsDO StudentsDataStruct StudentsQuery

SUBDIRS = 	

include $(ROOT)/config.mk

ENHYDRA_CLASSPATH:=:${PS}${ENHYDRA_CLASSPATH}

delete_source:
	@rm StudentsBDO.java StudentsDO.java StudentsDataStruct.java StudentsQuery.java ; \
        subs="$(SUBDIRS)" ; \
        if [ -n "$$MAKE_PATH" ] ; then \
            PARENT_PATH="$$MAKE_PATH" ; \
        else  \
            PARENT_PATH="." ; \
        fi ; \
        export PARENT_PATH ; \
        for d in $$subs ; \
            do \
                ( cd $$d || exit 1 ; \
                MAKE_PATH="$$PARENT_PATH/$$d" ; \
                export MAKE_PATH ; \
                echo "<< Start  CLEANUP pass in: $$MAKE_PATH >>" >> /dev/null ; \
                $(MAKE) delete_source || exit 0 ; \
                echo "<< Finish CLEANUP pass in: $$MAKE_PATH >>" >> /dev/null ; \
                ) ; \
            done

