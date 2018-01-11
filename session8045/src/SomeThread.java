
public class SomeThread extends Thread {

	SomeThread() {
	  }
	
	public void run() {
	     // do something
		System.out.println("test java thread");
	  }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SomeThread thread = new SomeThread ();
	    thread.start();

	}

}
