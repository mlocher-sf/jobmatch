package playground.business;
//import com.lutris.appserver.server.sql.DatabaseManagerException;
//import com.lutris.appserver.server.sql.ObjectIdException;
import com.lutris.dods.builder.generator.query.DataObjectException;
import playground.data.*;

public class InsertData{
    public static StudentsDO studentsDO = null;
    
    public static void insert() throws DataObjectException{
        try {
            studentsDO = StudentsDO.createVirgin();  
	    studentsDO.setName("Marco");
	    studentsDO.setLname("Studer");
	    studentsDO.commit();
        } catch(Exception ex) {
	    String message = "Insertion into DB failed: "+ ex.getMessage(); 
            throw new DataObjectException(message);
		}
     }
 }



