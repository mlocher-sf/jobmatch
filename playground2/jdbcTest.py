# little script to check the connection to the JDBC database

import java

Driver = 'org.gjt.mm.mysql.Driver'
url = 'jdbc:mysql://leeta.unibe.ch:3306/Kontaktboerse4'
pwd = 'bllns2k'
login = 'pse4'

java.lang.Class.forName(Driver)
con = java.sql.DriverManager.getConnection(url, login, pwd)
stmt = con.createStatement()

def query(query):
    try:
        return stmt.executeQuery(query)
    except:
        return None


######################################################################

rs = query('select * from Students')

print con
print stmt
print rs




