// $Id: BinaryTest.java,v 1.1 2000/06/12 16:28:01 locher Exp $

package jobmatch.data.test;

import junit.framework.*;
import jobmatch.data.*;

/**
 *  TestCase for Binary Data Storage
 *
 *  @since June 12 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class BinaryTest extends TestCase {

    final static private String testType = "binary/test";

    public BinaryTest(String name) {
	super(name);

    }

    public void setUp(){
    }

    public void testPicture() throws Exception {
	int size = 256;
	int count = 0;
	for (int i=0; i < 7; i++) {
	    this.addTestData(size);
	    size *= 2;
	    count++;
	}
	this.verifyAllTestRecords(count);
    }

    private void addTestData(int testSize) throws Exception{
	final byte[] initialData = this.createTestData(testSize);
	assert(this.verifyTestData(initialData));
	PictureBDO picture = PictureBDO.createVirgin();
	picture.setData(initialData);
	picture.setMimeType(BinaryTest.testType);
	picture.commit();
    }

    private void verifyAllTestRecords(int minRecordsExpected) throws Exception {
	PictureQuery query = new PictureQuery();
	final boolean exact = true;
	query.setQueryMimeType(BinaryTest.testType, exact);
	
	int count = 0;
	PictureBDO item;
	while (null != (item = query.getNextBDO())) {
	    assert(BinaryTest.testType.equals(item.getMimeType()));
	    assert(this.verifyTestData(item.getData()));
	    count++;
	    item.delete();
	}
	assert(count >= minRecordsExpected);
    }

    private byte[] createTestData(int length) {
	byte[] result = new byte[length];
	for (int i=0; i < result.length; i++) {
	    result[i] = (byte) (i % 127);
	}
	return result;
    }

    private boolean verifyTestData(byte[] data) {
	for (int i=0; i < data.length; i++) {
	    if (data[i] != (i % 127)) {
		return false;
	    }
	}
	return true;
    }


    public static Test suite() { 
	TestSuite suite= new TestSuite(BinaryTest.class);
	return suite;
    }

} //class

// Document history
/*
 * $Log: BinaryTest.java,v $
 * Revision 1.1  2000/06/12 16:28:01  locher
 * added new data layer tests
 *
 *
 */
