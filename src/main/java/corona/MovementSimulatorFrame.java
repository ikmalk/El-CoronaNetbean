package corona;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MovementSimulatorFrame extends JFrame{

	/*
	 * *************************************
	 ***Created at 31/5/2020
	 *
	 *This the GUI for the Movement Simulator
	 *
	 *
	 ***************************************
	 ***Write all your changes here
	 *
	 *Ikmal 31/5/2020
	 *Set up all the basic GUI stuff
	 *
	 **************************************
	 ***To do list
	 *-implement an iterator from a double-linked list for prev and next button(not done)
	 *-implement all handler class action(not done)
	 *-add Scroll for all JTextArea if possible(not done)
	 *-add show adult, children and senior button(not done)
	 *
	 **************************************
	 ***Handler class action
	 *
	 **start button(not done)
	 *-it will start the simulation
	 *-while the simulation is loading, set result to visible and set text to "Loading"
	 *-after the simulation is complete, call visible method to true
	 *-random a number(for person name), find said person and set the text to result
	 *-set number of children, adult and senior to data
	 *-set iterator for prev and next
	 *
	 **search(not done)
	 *-find said person and set the text to result
	 *-reset the iterator pointing at said person
	 *
	 **prev & next (not done)
	 *-find the previous or next person from the iterator and set it to result
	 *
	 **NameI & DayI
	 *-Open the simulation frame with the name and day parameter
	 *
	 **************************************
	 */
	
	private JTextField title;
	private JTextField search;
	private JTextField name;
	private JTextField enter;
	private JTextField nameT; //T for title, I for input
	private JTextField nameI;
	private JTextField dayT;
	private JTextField dayI;
	
	private JTextArea result;
	private JTextArea data;
	
	private JButton start;
	private JButton prev;
	private JButton next;
	
	private Font font1;
	private Font font2;
	private Font font3;
	
	private InfectionSimulatorFrame infectSim;
	
	
	public MovementSimulatorFrame() {
		super("Movement");
		requestFocus();
		
		font1 = new Font("SansSerif", Font.BOLD, 30);
		font2 = new Font("Arial", Font.BOLD, 20);
		font3 = new Font("Arial", Font.PLAIN, 18);
		getContentPane().setLayout(null);
		
		//Start Button
		start = new JButton();
		start.setFont(new Font("Tahoma", Font.BOLD, 14));
		start.setText("Start");
		start.setBounds(255, 69, 99, 38);
		getContentPane().add(start);
	
		//Previous Button
		prev = new JButton();
		prev.setFont(new Font("Tahoma", Font.BOLD, 12));
		prev.setText("Prev");
		prev.setBounds(281, 74, 69, 31);
		getContentPane().add(prev);
		
		//Next Button
		next = new JButton();
		next.setFont(new Font("Tahoma", Font.BOLD, 12));
		next.setText("Next");
		next.setBounds(364, 74, 69, 31);
		getContentPane().add(next);
		
		//That Movement text
		title = new JTextField("Movement");
		title.setLocation(122, 11);
		title.setSize(170, 50);		
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setEditable(false);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font1);
		getContentPane().add(title);
		
		//Will show the data of person
		result = new JTextArea("",5,5);
		result.setLocation(37, 128);
		result.setSize(393, 490);
		result.setWrapStyleWord(true);
		result.setFont(font3);
		result.setLineWrap(true);
		result.setEditable(false);
		getContentPane().add(result);
		
		//show number of child, adult and senior
		data = new JTextArea("",5,5);
		data.setLocation(450, 128);
		data.setSize(169, 260);
		data.setWrapStyleWord(true);
		data.setFont(font3);
		data.setLineWrap(true);
		data.setEditable(false);
		getContentPane().add(data);
		
		//name button
		name = new JTextField("Name");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setEditable(false);
		name.setLocation(37, 72);
		name.setSize(70, 31);
		name.setFont(font2);
		getContentPane().add(name);
		
		//Will search name in text file folder and show it on result
		search = new JTextField(20);
		search.setBounds(122, 72, 134, 31);
		search.setFont(font2);
		getContentPane().add(search);
		
		//Title for enter simulation
		enter = new JTextField("Simulation");
		enter.setLocation(460, 399);
		enter.setSize(170, 50);		
		enter.setHorizontalAlignment(SwingConstants.CENTER);
		enter.setEditable(false);
		enter.setForeground(Color.DARK_GRAY);
		enter.setFont(font1);
		getContentPane().add(enter);
		
		//Name sim indicator
		nameT = new JTextField("Name");
		nameT.setHorizontalAlignment(SwingConstants.CENTER);
		nameT.setEditable(false);
		nameT.setLocation(450, 460);
		nameT.setSize(70, 31);
		nameT.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(nameT);
		
		//Name sim input
		nameI = new JTextField();
		nameI.setLocation(530, 460);
		nameI.setSize(100, 31);
		nameI.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(nameI);
		
		//Day sim indicator
		dayT = new JTextField("Day");
		dayT.setHorizontalAlignment(SwingConstants.CENTER);
		dayT.setEditable(false);
		dayT.setLocation(450, 502);
		dayT.setSize(70, 31);
		dayT.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(dayT);
		
		//Day sim input
		dayI = new JTextField();
		dayI.setLocation(530, 502);
		dayI.setSize(100, 31);
		dayI.setFont(new Font("Arial", Font.BOLD, 18));
		getContentPane().add(dayI);
		
		//Call to set everything but Start button to invisible
		visible(false);
		
		//Will handle all action on item it set
		Handler handler = new Handler();
		start.addActionListener(handler);
		nameI.addActionListener(handler);
		dayI.addActionListener(handler);
		
	}
	
	//only called once
	private void visible(boolean b) { 

		search.setVisible(b);
		name.setVisible(b);
		result.setVisible(b);
		data.setVisible(b);
		prev.setVisible(b);
		next.setVisible(b);
		enter.setVisible(b);
		nameT.setVisible(b);
		nameI.setVisible(b);
		dayT.setVisible(b);
		dayI.setVisible(b);
		
	}
	
	/*
	 *This to start the movement simulation algorithm that will be conducted in another class
	 *For now it is just a dummy 
	 */
	private void startSimulation() {
		String profile = "Name:\nAge:\nRelated with:\nAwareness and Education Score:\nSchedule\nDay 1\n\nDay2";
		String stat = "Children:\nAdult:\nSenior:\n";
		result.setText(profile);
		data.setText(stat);
	}
	
	int x = 0; //just temporary 
	
	
	/**
	 * 
	 *All action for the item in constructor will be handled here
	 *	
	 *
	 */
private class Handler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource()==start) {
				if(x==0) {
					result.setVisible(true);
					result.setText("Loading...");

					x++;
				}else {
					start.setVisible(false);
					startSimulation();
					visible(true);
				}
			}
			
			if(event.getSource()==dayI||event.getSource()==nameI) {
				if(!dayI.getText().isEmpty()&&!nameI.getText().isEmpty()) {
					infectSim = new InfectionSimulatorFrame(Integer.parseInt(nameI.getText()),Integer.parseInt(dayI.getText()));
					infectSim.setSize(596,504);
					infectSim.setVisible(true);
					infectSim.setLocationRelativeTo(null);
				}
			}
			
		}
}

	
}
