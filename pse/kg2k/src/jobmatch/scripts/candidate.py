import jobmatch

accountManager = jobmatch.business.provider.account.AccountManager.getUniqueInstance()

candidates = [
    ("muster", "muster", "muster@mueller.ch", "Muster", "Hans"),
    ("bruhin", "bruhin", "bruhin@iamexwi.unibe.ch", "Bruhin", "Michel"),
    ("pater", "noster", "pater@divine.ch", "Pope", "Sepp")
    ]

for (login, passwd, email, lastname, firstname) in candidates:
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
    candidate.commit()

    print '-' * 50
    print str(account)

    
