// $Id: StressTest.java,v 1.1 2000/06/12 21:52:29 locher Exp $

package jobmatch.data.test;

import junit.framework.*;
import jobmatch.data.*;

/**
 *  Database/Memory Stress Test.
 *  approx. 8MB are inserted, queried and removed from the db.
 *
 *  @since June 12 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class StressTest extends TestCase {

    private static final String testType = "stress/test";
    private static final int NUMBER_OF_WRITES = 16;
    private static final int SIZE_OF_WRITE = 512*1024;

    public StressTest(String name) {
	super(name);
    }

    public void setUp() {
    }

    public void testHeavyUsage() throws Exception {
	this.writes();
	this.hugeQuery();
	this.removeTestData();
    }

    private void writes() throws Exception {
	final byte[] testData = TestData.createTestData(StressTest.SIZE_OF_WRITE);
	assert(testData.length == StressTest.SIZE_OF_WRITE);
	for (int i=0; i < StressTest.NUMBER_OF_WRITES; i++) {
	    PictureBDO item = PictureBDO.createVirgin();
	    item.setMimeType(StressTest.testType);
	    item.setData(testData);
	    item.commit();
	}
    }

    private void hugeQuery() throws Exception {
	PictureQuery query = new PictureQuery();
	final boolean exact = true;
	query.setQueryMimeType(StressTest.testType, exact);

	int count = 0;
	PictureBDO item;
	while (null != (item = query.getNextBDO())) {
	    assert(StressTest.testType.equals(item.getMimeType()));
	    byte[] data = item.getData();
	    assert(data != null && data.length == StressTest.SIZE_OF_WRITE);
	    count++;
	}
	assert(count >= StressTest.NUMBER_OF_WRITES);
    }

    private void removeTestData() throws Exception {
	PictureQuery query = new PictureQuery();
	final boolean exact = true;
	query.setQueryMimeType(StressTest.testType, exact);
	PictureBDO item;
	while (null != (item = query.getNextBDO())) {
	    item.delete();
	}
    }

    public static Test suite() { 
	TestSuite suite= new TestSuite(StressTest.class);
	return suite;
    }

} //class

// Document history
/*
 * $Log: StressTest.java,v $
 * Revision 1.1  2000/06/12 21:52:29  locher
 * added DB Stress test
 *
 *
 */
