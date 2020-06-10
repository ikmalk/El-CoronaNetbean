package corona;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class InfectionSimulatorFrame extends JFrame{

	/*
	 ********************************************
	 ***Created at 31/5/2020
	 *
	 *This the GUI for the Infection Simulator that will pop up after user input in Movement GUI
	 *
	 ********************************************
	 ***Write all your changes here
	 *
	 *Ikmal 31/5/2020
	 *Set up all the basic GUI stuff
	 *
	 ********************************************
	 ***To do list
	 *-implement an iterator points at original infector from a double-linked list for prev and next button(not done)
	 *-the frame open with result text set at original infector tree(not done)
	 *-implement all handler class action(not done)
	 *-add Scroll for all JTextArea if possible(not done)
	 *
	 ********************************************
	 ***Handler class action
	 **search(not done)
	 *find the persons tree in iterator(while loop) and set it to text
	 *
	 **prev & next(not done)
	 *-find the previous or next person from the iterator and set it to result
	 *
	 **contact(not done)
	 *-set result to all name that have contacted in simulation
	 *
	 **infected(not done)
	 *--set result to all name that have infected in simulation
	 *
	 ********************************************
	 */
	private JTextField title;
	private JTextField search;
	private JTextField tree;
	
	private JTextArea result;
	private JTextArea data;
	
	private JButton prev;
	private JButton next;
	private JButton infected;

	private JButton contact;
	
	private Font font1;
	private Font font2;
	private Font font3;
	
	private JScrollPane midLeftPanel;
	private JScrollPane midRightPanel;
	
	private int name;
	private int day;
	
	public InfectionSimulatorFrame(int name, int day) {
		super("Infection");
		
		this.name = name;
		this.day = day;
		
		requestFocus();
		
		font1 = new Font("SansSerif", Font.BOLD, 30);
		font2 = new Font("Arial", Font.BOLD, 20);
		font3 = new Font("Arial", Font.PLAIN, 18);
		getContentPane().setLayout(null);
		
		midLeftPanel = new JScrollPane();
		midLeftPanel.setLocation(10, 128);
		midLeftPanel.setSize(406, 326);
		getContentPane().add(midLeftPanel);
		
		midRightPanel = new JScrollPane();
		midRightPanel.setLocation(426, 128);
		midRightPanel.setSize(135, 214);
		getContentPane().add(midRightPanel);
		
		
		//That Infected Simulation text
		title = new JTextField("Infected Simulation");
		title.setBounds(23, 11, 393, 50);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setEditable(false);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font1);
		getContentPane().add(title);
				
		
		//Previous Button
		prev = new JButton();
		prev.setBounds(266, 72, 69, 31);
		prev.setFont(new Font("Tahoma", Font.BOLD, 12));
		prev.setText("Prev");
		getContentPane().add(prev);
		
		//Next Button
		next = new JButton();
		next.setBounds(345, 72, 69, 31);
		next.setFont(new Font("Tahoma", Font.BOLD, 12));
		next.setText("Next");
		getContentPane().add(next);
		
		//Infected Button
		infected = new JButton();
		infected.setBounds(427, 353, 134, 31);
		infected.setFont(new Font("Tahoma", Font.BOLD, 12));
		infected.setText("Infected List");
		getContentPane().add(infected);
		
		//Contact button
		contact = new JButton();
		contact.setBounds(427, 395, 134, 31);
		contact.setFont(new Font("Tahoma", Font.BOLD, 12));
		contact.setText("Contacted List");
		getContentPane().add(contact);
		
		//Tree Button
		tree = new JTextField();
		tree.setHorizontalAlignment(SwingConstants.CENTER);
		tree.setBounds(10, 73, 102, 31);
		tree.setFont(new Font("Tahoma", Font.BOLD, 14));
		tree.setEditable(false);
		tree.setText("Person tree");
		getContentPane().add(tree);		
		
		//Will search for person's tree
		search = new JTextField(20);
		search.setBounds(122, 72, 134, 31);
		search.setFont(font2);
		getContentPane().add(search);
		//title
		
		//shows tree, contact count and infected count
		result = new JTextArea("",5,5);
		result.setBounds(23, 128, 393, 300);
		result.setWrapStyleWord(true);
		result.setFont(font3);
		result.setEditable(false);
		midLeftPanel.setViewportView(result);
		
		//show number of infected and contacted
		data = new JTextArea("",5,5);
		data.setLocation(427, 128);
		data.setSize(134, 218);
		data.setWrapStyleWord(true);
		data.setFont(font3);
		data.setLineWrap(true);
		data.setEditable(false);
		midRightPanel.setViewportView(data);

		
		Handler handler = new Handler();
		infected.addActionListener(handler);
		contact.addActionListener(handler);
		search.addActionListener(handler);
		
		setField();
	}
	
	private void setField() {
		String treeGraph = "Person Name\nTree Day 1\n Tree Day 2\n  Tree Day 3\n   Tree Day 4\n     Tree day 5";
		String stat = "Contact Count:\nInfected Count:";
		result.setText(treeGraph);
		data.setText(stat);
	}
	
	/*
	 * 
	 *All action for the item in constructor will be handled here
	 *	
	 *
	 */
	
	private class Handler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==infected) {
				String infectedCount = "123\n456\n678\n912\n999";
				result.setText(infectedCount);
			}
			if(event.getSource()==contact) {
				String contactCount = "123\n234\n356\n456\n678\n768\n912\n999";
				result.setText(contactCount);
			}
			if(event.getSource()==search) {
				setField();
			}
			
		}
	}
}
