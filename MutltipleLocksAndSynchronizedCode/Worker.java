import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * You can create two lock objects, and have two intrisic locks in
 * methods, won't have to wait for a lock when just putting synchronized
 * in the method header.
 */
public class Worker {

	private Random random = new Random();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public void stageOne() {
     
	synchronized(lock1) {     	
     	try{
		Thread.sleep( 1 );
	   }
	   catch (InterruptedException e) {
		   e.printStackTrace();
	   }

	   list1.add( random.nextInt( 100 ) );

	}
	}
	public void stageTwo() {
       
	synchronized(lock2) {	
	try{
		Thread.sleep( 1 );
	   }
	   catch (InterruptedException e) {
		   e.printStackTrace();
	   }

	   list2.add( random.nextInt( 100 ) );
	}
	}

	public void process() {
		for(int i = 0; i < 1000; i++ ) {
			stageOne();
			stageTwo();
		}
	}

	public static void main(String[] args) {
		
		Worker myWorker = new Worker();
		
		System.out.println("Starting....");

		long start = System.currentTimeMillis();
		
		//Anon class: Create Thread using runnable interface
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				myWorker.process();
			}
			});
	        
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				myWorker.process();
			}
			});

	//Starts thread	
		t1.start();
		t2.start();
		
		try{
		t1.join();
		t2.join();
		}
		catch( InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + myWorker.list1.size() + 
				"; List2: " + myWorker.list2.size());
	}
}
