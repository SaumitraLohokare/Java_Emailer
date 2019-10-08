package main.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import main.Main;

public class HUD extends JFrame{

	private JPanel panel;
	private JScrollPane messagePane;
	private JLabel sMailLabel, sMailPass, rMailLabel, messageBodyLabel, subjectLabel;
	private JPasswordField sPassField;
	private JTextField sMail, rMail, subject;
	private JTextArea message;
	private JCheckBox isHtml;
	private JButton send;
	
	public HUD(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 10, 10));
		
		sMailLabel = new JLabel("Your email ID : ");
		sMailPass = new JLabel("Your email password : ");
		rMailLabel = new JLabel("Recepient email ID : ");
		messageBodyLabel = new JLabel("Message : ");
		subjectLabel = new JLabel("Subject : ");
		
		sPassField = new JPasswordField(18);
		
		sMail = new JTextField(18);
		rMail = new JTextField(18);
		subject = new JTextField(30);
		
		message = new JTextArea(10, 20);
		
		isHtml = new JCheckBox("Set as HTML Content.");
		
		send = new JButton("Send");
		
		panel.add(sMailLabel);
		panel.add(sMail);
		
		panel.add(sMailPass);
		panel.add(sPassField);
		
		panel.add(rMailLabel);
		panel.add(rMail);
		
		panel.add(subjectLabel);
		panel.add(subject);
		
		panel.add(messageBodyLabel);		
		messagePane = new JScrollPane(message);
		messagePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(message);
		
		panel.add(isHtml);
		
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.sendMail(sMail.getText(), sPassField.getText(), rMail.getText(), subject.getText(), message.getText(), isHtml.isSelected());
				
			}
			
		});
		
		panel.add(send);
		
		//p.add(panel);
		
		this.add(panel);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
