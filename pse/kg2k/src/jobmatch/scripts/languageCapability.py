import jobmatch

LanguageCapability = jobmatch.business.entity.LanguageCapability

capability = [
    ("Muttersprache"),
    ("Sehr gut"),
    ("durchschnittlich"),
    ("minimal")
  
    ]
ordinal = 1
for (skill) in capability:
    lanCap = LanguageCapability.getCapability(skill)
    lanCap.setOrdinal(ordinal)
    lanCap.commit()
    ordinal = ordinal + 1
  
    
    
                            
    

    

    
