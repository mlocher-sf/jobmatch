import jobmatch

accountManager = jobmatch.business.provider.account.AccountManager.getUniqueInstance()

Country = jobmatch.business.entity.Country


candidates = [
    ("muster", "muster", "muster@mueller.ch", "Muster", "Hans", "Schweiz"),
    ("bruhin", "bruhin", "bruhin@iamexwi.unibe.ch", "Bruhin", "Michel" , "Deutschland"),
    ("pater", "noster", "pater@divine.ch", "Pope", "Sepp", "Vatikan")
    ]

for (login, passwd, email, lastname, firstname, nationality) in candidates:
    try:
        accountManager.createCandidateAccount(login, passwd, email)
    except:
        print 'account %s exists already' % login
        account = accountManager.getCandidateAccount(login)
        account.setPassphrase(passwd)
        account.setEmail(email)
        account.commit()
        
    account = accountManager.getCandidateAccount(login)
    
    candidate = account.getCandidateBO()
    candidate.setLname(lastname)
    candidate.setFname(firstname)
    candidate.setStatus(1)
    candidate.setNationality(Country.getCountry(nationality))
    candidate.commit()

    print '-' * 50
    print str(account)

    
