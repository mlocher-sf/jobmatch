import jobmatch

accountManager = jobmatch.business.provider.account.AccountManager.getUniqueInstance()

companies = [
    ("nutella", "nutella", "jobs@nutella.org", "Nutella"),
    ("tuxmaster", "igloo", "master@tuxgarage.com", "Tux Garage")
    ]

for (login, passwd, email, name) in companies:
    try:
        accountManager.createCompanyAccount(login, passwd, email)
    except:
        print 'account %s exists already' % login
        account = accountManager.getCompanyAccount(login)
        account.setPassphrase(passwd)
        account.setEmail(email)
        account.commit()

    account = accountManager.getCompanyAccount(login)
        
    company = account.getCompanyBO()
    company.setName(name)
    company.commit()

    print '-' * 50
    print str(account)

    
