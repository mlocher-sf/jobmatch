import jobmatch

accountManager = jobmatch.business.provider.account.AccountManager.getUniqueInstance()

Formation = jobmatch.business.candidate.cv.Formation 
Graduation = jobmatch.business.entity.Graduation
TimeUtil = jobmatch.business.util.TimeUtil
Schooltype = jobmatch.business.entity.Schooltype
School = jobmatch.business.entity.School
SchoolDO = jobmatch.data.SchoolDO


formations = [
    ("Momsen Gymnasium", "Gymnasium", "pater" , TimeUtil.getDate(1999,5,6), TimeUtil.getDate(2000,4,20), "Matur", "Dummy"),
    ("Sekundarschule Engelhalde", "Sekundarschule", "jackson" , TimeUtil.getDate(1999,5,6), TimeUtil.getDate(2000,4,20), "Matur", "Dummy"),
     ("Primarschule Liebefeld", "Primarschule", "bruhin" , TimeUtil.getDate(1999,5,6), TimeUtil.getDate(2000,4,20), "Matur", "Dummy")
    ]

for (schoolname, schooltype, username, begin, end, graduation, remarks) in formations:
    
    formation = Formation()
    formation.setCandidate(accountManager.getCandidateAccount(username).getCandidateBO())
    formation.setBeginDate(begin)
    formation.setEndDate(end)
    formation.setDiploma(Graduation.getGraduation(graduation))
    formation.setRemarks(remarks)
    school = School.getSchool(schoolname)
    school.setType(Schooltype.getSchooltype(schooltype))
    school.commit()
    formation.setSchool(school)
    formation.commit()
    
    
                            
    

    

    
