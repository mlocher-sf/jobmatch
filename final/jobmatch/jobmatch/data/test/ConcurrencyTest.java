// $Id: ConcurrencyTest.java,v 1.1 2000/06/20 13:33:26 pse4 Exp $

package jobmatch.data.test;

import junit.framework.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  Database/Memory Stress Test.
 *  64MB are inserted, queried and removed from the db.
 *
 *  @since June 12 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class StressTest extends TestCase {

    private static final String testType = "stress/test";
    private static final int NUMBER_OF_THREADS = 1;
    private static final int WRITES_PER_THREAD = 4;
    private static final int SIZE_OF_WRITE = 4*1024;

    private Collection threadPool;

    public StressTest(String name) {
	super(name);
    }

    public void setUp() {
	this.threadPool = new Vector();
    }

    public void testHeavyUsage() throws Exception {
	this.concurrentWrites(StressTest.NUMBER_OF_THREADS);
	this.hugeQuery();
	this.removeTestData();
    }

    private void concurrentWrites(int numThreads) throws Exception {
	for (int i=0; i < numThreads; i++) {
	    final byte[] testData = TestData.createTestData(StressTest.SIZE_OF_WRITE);
	    assert(testData.length == StressTest.SIZE_OF_WRITE);
	    Writer writer = new Writer(testData);
	    this.threadPool.add(writer);
	    writer.start();
	}
	while (!this.threadPool.isEmpty()) {
	    Iterator it = this.threadPool.iterator();
	    while (it.hasNext()) {
		Thread thread = (Thread) it.next();
		try {
		    if (thread.isAlive()) {
			thread.join();
		    }
		    this.threadPool.remove(thread);
		} catch (Exception e) {
		    System.err.println(e);
		}
	    }
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
	final int minItemsExpected = (StressTest.NUMBER_OF_THREADS * 
				      StressTest.WRITES_PER_THREAD);
	assert(count >= minItemsExpected);
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

    private class Writer extends Thread {
	private byte[] data;

	public Writer(byte[] data) {
	    super();
	    assert(data != null);
	    this.data = data;
	}
	
	public void run() {
	    try {
		for (int i=0; i < StressTest.WRITES_PER_THREAD; i++) {
		    PictureBDO item = PictureBDO.createVirgin();
		    assert(item != null);
// 		    item.setMimeType(StressTest.testType);
// 		    item.setData(this.data);
// 		    item.commit();
		}
	    } catch (Exception e) {
		throw new RuntimeException(e.toString());
	    }
	}

    } // inner-class
    

} //class

// Document history
/*
 * $Log: ConcurrencyTest.java,v $
 * Revision 1.1  2000/06/20 13:33:26  pse4
 * Initial revision
 *
 * Revision 1.1  2000/06/13 12:02:30  locher
 * test concurrent data access
 *
 *
 */
