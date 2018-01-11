import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloFrame extends JFrame {

	 public HelloFrame() 
	    { 
	        setSize(250, 100); 
	        setTitle("Hello World String");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        JLabel myLabel = new JLabel("This is my label");
	        getContentPane().add(myLabel);
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelloFrame frame = new HelloFrame();
        frame.setVisible(true);

	}

}
