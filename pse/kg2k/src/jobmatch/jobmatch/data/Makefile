
ROOT =		../..

PACKAGEDIR = 	jobmatch/data

CLASSES = 	AccountDO AccountDataStruct AreaBDO AreaDO AreaDataStruct AreaQuery CandidateAccountBDO CandidateAccountDO CandidateAccountDataStruct CandidateAccountQuery CandidateBDO CandidateDO CandidateDataStruct CandidateQuery CompanyAccountBDO CompanyAccountDO CompanyAccountDataStruct CompanyAccountQuery CompanyBDO CompanyDO CompanyDataStruct CompanyQuery ContactpersonBDO ContactpersonDO ContactpersonDataStruct ContactpersonQuery CountryBDO CountryDO CountryDataStruct CountryQuery GraduationBDO GraduationDO GraduationDataStruct GraduationQuery IndustryBDO IndustryDO IndustryDataStruct IndustryQuery JobwishBDO JobwishDO JobwishDataStruct JobwishQuery

SUBDIRS = 	

include $(ROOT)/config.mk

ENHYDRA_CLASSPATH:=:${PS}${ENHYDRA_CLASSPATH}

delete_source:
	@rm AccountDO.java AccountDataStruct.java AreaBDO.java AreaDO.java AreaDataStruct.java AreaQuery.java CandidateAccountBDO.java CandidateAccountDO.java CandidateAccountDataStruct.java CandidateAccountQuery.java CandidateBDO.java CandidateDO.java CandidateDataStruct.java CandidateQuery.java CompanyAccountBDO.java CompanyAccountDO.java CompanyAccountDataStruct.java CompanyAccountQuery.java CompanyBDO.java CompanyDO.java CompanyDataStruct.java CompanyQuery.java ContactpersonBDO.java ContactpersonDO.java ContactpersonDataStruct.java ContactpersonQuery.java CountryBDO.java CountryDO.java CountryDataStruct.java CountryQuery.java GraduationBDO.java GraduationDO.java GraduationDataStruct.java GraduationQuery.java IndustryBDO.java IndustryDO.java IndustryDataStruct.java IndustryQuery.java JobwishBDO.java JobwishDO.java JobwishDataStruct.java JobwishQuery.java ; \
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

