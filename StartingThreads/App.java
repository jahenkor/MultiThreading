/*
 * Implmenetation of multi-threading using thread class.
 *
 */
class Runner extends Thread {
        
	@Override
	public void run() {
	 
		for(int i = 0; i < 10; i++) {
			
			System.out.println("Hello " + i);

			try{
			//Pauses program for x milliseconds
			Thread.sleep(100);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class App {

	public static void main(String[] args) {
		Runner runner1 = new Runner();
		//Runs run() in own thread, don't call runner1.run()
		runner1.start();

		Runner runner2 = new Runner();
		//Runs run() in own thread, don't call runner1.run()
		runner2.start();
	}
}
