package playground.business;
import com.lutris.appserver.server.sql.DatabaseManagerException;
import com.lutris.appserver.server.sql.ObjectIdException;
//import com.lutris.dods.builder.generator.query.DataObjectException;
import playground.data.*;

public class InsertData{
    public static StudentsDO studentsDO = null;
    
    public static void insert(){
        try {
            studentsDO = StudentsDO.createVirgin();  
	    studentsDO.setName("mario");
	    studentsDO.setLname("loeffel");
	    studentsDO.commit();
        } catch(Exception ex) {ex.printStackTrace();}
     }
 }



