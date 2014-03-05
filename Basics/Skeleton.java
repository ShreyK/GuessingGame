package Basics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Skeleton extends JFrame implements ActionListener
{	
	
	JPanel glass = new JPanel();
	JButton btn = new JButton("Start");
	TextField tf = new TextField();
	JLabel label = new JLabel("Enter Guess Here: ");
	JLabel answerLabel = new JLabel("Answer:...");
	Board bob =new Board();
	
	public Skeleton()
	{
		setIconImage(new ImageIcon("icon.png").getImage());
		getContentPane().setBackground(Color.darkGray);
		getContentPane().setForeground(Color.black);
		setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		add(label);
		label.setForeground(Color.black);
		add(tf);
		tf.setText("");
		add(btn);
		btn.setBackground(Color.green);
		btn.addActionListener(this);
		add(answerLabel);
		answerLabel.setForeground(Color.black);
		
		
		setTitle("Guessing Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(300,200);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		
		
	}
	public void actionPerformed(ActionEvent e)
	{//GAME FRAME
		JFrame frame = new JFrame();
		int userGuess= Integer.parseInt(tf.getText());
		frame.add(bob);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300,285);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon("icon.png").getImage());
		
		int answer = bob.getFinalHit();
		int count = bob.getCount();
		
		if(answer == userGuess)
			answerLabel.setText("Answer: You were right!");
		else
			answerLabel.setText("Answer: The actual number of Bounces was "+ answer);
		answerLabel.setVisible(true);
	}
	
}