import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIHello extends JFrame {
	public GUIHello() 
    { 
        setSize(200, 200); 
        setTitle("Hello World String");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        JLabel myLabel = new JLabel("This is my label");
        JButton myButton = new JButton ("Click me!");
        
        myButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
           {
              System.out.println("Button was clicked!");
           }
         }
        );
        
        JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(myLabel, BorderLayout.CENTER);
		content.add(myButton, BorderLayout.SOUTH);

        getContentPane().add(content);
        
       
    }
    public static void main(String[] args) {
        GUIHello frame = new GUIHello();
        frame.setVisible(true);
    }

	
}
