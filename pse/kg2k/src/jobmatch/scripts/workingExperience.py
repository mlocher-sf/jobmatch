import jobmatch

accountManager = jobmatch.business.provider.account.AccountManager.getUniqueInstance()




works = [
    ("UBS",  TimeUtil.getDate(1999,5,6), TimeUtil.getDate(2000,4,20), "Ferienjob", "Hans Wurst", "Tellerwaescher", 10),
   
    ]

for (employerName, begin, end, remarks, reference, function, pensum) in formations:
    
    work = WorkingExperience()
    formation.setCandidate(accountManager.getCandidateAccount(username).getCandidateBO())
    formation.setBeginDate(begin)
    formation.setEndDate(end)
    formation.set(Graduation.getGraduation(graduation))
    formation.setRemarks(remarks)
    school = School.getSchool(schoolname)
    school.setType(Schooltype.getSchooltype(schooltype))
    school.commit()
    formation.setSchool(school)
    formation.commit()
    
    
                            
    

    

    
