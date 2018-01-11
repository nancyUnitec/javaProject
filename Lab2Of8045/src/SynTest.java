
public class SynTest {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		SynTest synTest = new SynTest();
		synTest.test();
	}
	
	public void test() throws InterruptedException{
		new SynThread1().start();
		new SynThread1().start();
		}

}

class SynThread1 extends Thread {
	public void run()
	{
		    try {
				syn("Nancy");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

   }
	
	public void syn(String userName) throws Exception {
		synchronized(userName) {
			System.out.println("entered synchronized block，userName=" + userName);
			Thread.sleep(5000);
			System.out.println("exit synchronized block，userName=" + userName);
		}
	}
}
