import jobmatch
from jobmatch.business.candidate import Candidate


candidates = [
    ("Muster", "Hans"),
    ("Bruhin", "Michel")]

for (lastname, firstname) in candidates:
    c = Candidate()
    c.setLname(lastname)
    c.setFname(firstname)
    c.commit()
    
