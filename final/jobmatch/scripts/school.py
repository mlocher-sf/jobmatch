import jobmatch

SchoolBDO = jobmatch.data.SchoolBDO
Schooltype = jobmatch.business.entity.Schooltype
School = jobmatch.business.entity.School
SchooltypeQuery = jobmatch.data.SchooltypeQuery

schools = [
    ("Momsen Gymnasium",),
    ("Sekundarschule Engelhalde",),
    ("Primarschule Liebefeld",)
  
    ]

for (schoolname,) in schools:
    school = SchoolBDO()
    school.setDescription(schoolname)
    school.setLocation("Bern")
    school.setType(Schooltype.getSchooltype("Primarschule"))
    school.commit()
  
    
    
                            
    

    

    
