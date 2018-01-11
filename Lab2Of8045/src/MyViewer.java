
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
public class MyViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewFrame frame1 = new NewFrame();
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//一定要设置关闭
		frame1.setVisible(true);
	}

}

class NewFrame extends JFrame{
    private JLabel label1 = new JLabel();
    private JButton button1;
    //private JTextField text1;
    private JTextArea text1;
    
    private JPanel panel;
    
    
    private JRadioButton radioOne = new JRadioButton();
    private JRadioButton radioTwo = new JRadioButton();
    private JRadioButton radioThree = new JRadioButton();
    private JRadioButton radioFour = new JRadioButton();
    
    
    public NewFrame(){

        super();
        
        this.setSize(700,210);
        this.setTitle("Season Viewer");
        this.getContentPane().setLayout(null);
        
        this.add(this.getTextField(),null);
        this.add(this.getButton(),null);
        
        //panel.add(radioOne);
        //this.setRadio();
        this.add(this.getPanel(),null);
        
        JRadioButton jb1 = new JRadioButton("spring");
        JRadioButton jb2 = new JRadioButton("summer");
        JRadioButton jb3 = new JRadioButton("autumn");
        JRadioButton jb4 = new JRadioButton("winter");

        jb1.setBounds(100,5,100,30);
        jb2.setBounds(600,35,100,30);
        jb3.setBounds(600,65,100,30);
        jb4.setBounds(600,95,100,30);
        
        label1.setText("haha");
        label1.setBounds(600,5,100,30);
        this.add(label1);

        ButtonGroup bg = new ButtonGroup();
        
        bg.add(jb1);
        bg.add(jb2);
        bg.add(jb3);
        bg.add(jb4);
        
        this.add(jb1);
        this.add(jb2);
        this.add(jb3);
        this.add(jb4);
        
        jb1.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
           {
              System.out.println("Button spring was clicked!");
              text1.setText("Everything turns green and the flowers bloom out");
           }
         }
        );
        
        jb2.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
           {
              System.out.println("Button spring was clicked!");
              text1.setText("The trees are very shady.");
           }
         }
        );
        
        jb3.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
           {
              System.out.println("Button spring was clicked!");
              text1.setText("The season of harvest");
           }
         }
        );
       
        jb4.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
           {
              System.out.println("Button winter was clicked!");
              text1.setText("white snow make the world pure and neat");
           }
         }
        );
        
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
             {
                System.out.println("Button ok was clicked!");
                text1.setText("Hello,java");
             }
           }
          );
    }
    
    private JPanel getPanel(){
    	if(panel==null){
    		panel = new JPanel();
    		panel.setBounds(300,10,100,100);
    		
    		 //panel.setBounds(10, 10, 300, 200);
    	        panel.setBackground(Color.BLACK);
    	        panel.setVisible(true);
    	        panel.setEnabled(true);
    	        panel.setLayout(new GridLayout(1,4));
    	//setContentPane(panel)；
    	        /*radioOne.setText("spring");
    	        radioOne.setEnabled(true);
    	        radioTwo.setText("summer");
    	        radioTwo.setEnabled(true);
    	        radioThree.setText("autumn");
    	        radioThree.setEnabled(true);
    	        radioFour.setText("winter");
    	        radioFour.setEnabled(true);
    	        //this.setRadio();
    	        panel.add(radioOne);
    	        panel.add(radioTwo);
    	        panel.add(radioThree);
    	        panel.add(radioFour);
    		*/
    		}
    	return panel;
    }

    private void setRadio(){
    	radioOne.setText("春季");
        radioOne.setEnabled(true);
        radioTwo.setText("夏季");
        radioTwo.setEnabled(true);
        radioThree.setText("秋季");
        radioThree.setEnabled(true);
        radioFour.setText("冬季");
        radioFour.setEnabled(true);
  
    }
    
    private JButton getButton(){
    	if(button1==null){
    	button1 = new JButton();
    	button1.setBounds(600,135,71,27);
    	button1.setText("OK");
    	button1.setToolTipText("OK");
    	//button1.addActionListener(new HelloButton());//添加监听器类，其主要的响应都由监听器类的方法实现
    	}
    	return button1;
    }

    
    private JTextArea getTextField(){
        if(text1==null){
    	    text1 = new JTextArea();
    	    text1.setBounds(0,5,600,160);
    	    //text1.setWrapStyleWord(true);

    	    text1.setText("hehe");
    	}
    	return text1;
    }


}

