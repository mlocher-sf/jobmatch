import jobmatch

Schooltype = jobmatch.business.entity.Schooltype
SchooltypeDO = jobmatch.data.SchooltypeDO

schools = [
    ("Gymnasium"),
    ("Sekundarschule"),
    ("Primarschule")
  
    ]

for (schooltype) in schools:
    
    school = SchooltypeDO.createVirgin()
    school.setDescription(schooltype)
    school.commit()
  
    
    
                            
    

    

    
