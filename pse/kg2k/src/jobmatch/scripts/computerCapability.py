import jobmatch

ComputerCapability = jobmatch.business.entity.ComputerCapability

capability = [
    ("Profi"),
    ("Fortgeschrittener"),
    ("Anfänger")
  
    ]
ordinal = 1
for (skill) in capability:
    lanCap = ComputerCapability.getCapability(skill)
    lanCap.setOrdinal(ordinal)
    lanCap.commit()
    ordinal = ordinal + 1
  
    
    
                            
    

    

    
