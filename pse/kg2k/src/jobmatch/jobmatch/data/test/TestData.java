// $Id: TestData.java,v 1.1 2000/06/12 21:52:30 locher Exp $

package jobmatch.data.test;

/**
 *  Provides binary test data for the tests
 *
 *  @since June 12 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
class TestData {
        
    static synchronized byte[] createTestData(int length) {
	byte[] result = new byte[length];
	for (int i=0; i < result.length; i++) {
	    result[i] = (byte) (i % 127);
	}
	return result;
    }

    static synchronized boolean verifyTestData(byte[] data) {
	for (int i=0; i < data.length; i++) {
	    if (data[i] != (i % 127)) {
		return false;
	    }
	}
	return true;
    }
	
} //class

// Document history
/*
 * $Log: TestData.java,v $
 * Revision 1.1  2000/06/12 21:52:30  locher
 * added DB Stress test
 *
 *
 */
