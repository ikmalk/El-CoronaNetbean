package corona;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import corona.Movement.Adult;
import corona.Movement.Children;
import corona.Movement.MovementSimulatorAlgorithm;
import corona.Movement.Senior;
import corona.map.Map;

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
	private JButton showMap;
		
	private Font font1;
	private Font font2;
	private Font font3;
	private Font font4;
	
	private JPanel topPanel;
	private JPanel bottomRightPanel;
	private JScrollPane midLeftPanel;
	private JScrollPane midRightPanel;
	
	private InfectionSimulatorFrame infectSim;
	private MovementSimulatorAlgorithm moveAlgo;
	
	private DoubleLinkedList<Children> childList;
	private DoubleLinkedList<Adult> adultList;
	private DoubleLinkedList<Senior> seniorList;
	
	private ListIterator<Children> childIterator;
	private ListIterator<Adult> adultIterator;
	private ListIterator<Senior> seniorIterator;
	
	private Map map;
	
	
	public MovementSimulatorFrame() {
		super("Movement");
		requestFocus();
		
		
		font1 = new Font("SansSerif", Font.BOLD, 30);
		font2 = new Font("Arial", Font.BOLD, 20);
		font3 = new Font("Arial", Font.PLAIN, 18);
		font4 = new Font("Arial",Font.PLAIN,10);
		
		/*
		 * JButton
		 */
		
		topPanel = new JPanel();
		
		midLeftPanel = new JScrollPane();
		
		midRightPanel = new JScrollPane();
		
		bottomRightPanel = new JPanel();
		SpringLayout sl_bottomRightPanel = new SpringLayout();
		bottomRightPanel.setLayout(sl_bottomRightPanel);
		
		//Title for enter simulation
		enter = new JTextField("Simulation");
		bottomRightPanel.add(enter);
		enter.setHorizontalAlignment(SwingConstants.CENTER);
		enter.setEditable(false);
		enter.setForeground(Color.DARK_GRAY);
		enter.setFont(font1);
		
		//Name sim indicator
		nameT = new JTextField("Name");
		sl_bottomRightPanel.putConstraint(SpringLayout.SOUTH, enter, -29, SpringLayout.NORTH, nameT);
		sl_bottomRightPanel.putConstraint(SpringLayout.WEST, nameT, 25, SpringLayout.WEST, bottomRightPanel);
		sl_bottomRightPanel.putConstraint(SpringLayout.WEST, enter, 0, SpringLayout.WEST, nameT);
		bottomRightPanel.add(nameT);
		nameT.setHorizontalAlignment(SwingConstants.CENTER);
		nameT.setEditable(false);
		nameT.setFont(new Font("Arial", Font.BOLD, 18));
		
		//Day sim indicator
		dayT = new JTextField("Day");
		sl_bottomRightPanel.putConstraint(SpringLayout.SOUTH, nameT, -6, SpringLayout.NORTH, dayT);
		sl_bottomRightPanel.putConstraint(SpringLayout.NORTH, dayT, 118, SpringLayout.NORTH, bottomRightPanel);
		sl_bottomRightPanel.putConstraint(SpringLayout.WEST, dayT, 25, SpringLayout.WEST, bottomRightPanel);
		sl_bottomRightPanel.putConstraint(SpringLayout.EAST, dayT, 0, SpringLayout.EAST, nameT);
		bottomRightPanel.add(dayT);
		dayT.setHorizontalAlignment(SwingConstants.CENTER);
		dayT.setEditable(false);
		dayT.setFont(new Font("Arial", Font.BOLD, 18));
		
		//Name sim input
		nameI = new JTextField();
		sl_bottomRightPanel.putConstraint(SpringLayout.NORTH, nameI, 0, SpringLayout.NORTH, nameT);
		sl_bottomRightPanel.putConstraint(SpringLayout.WEST, nameI, 18, SpringLayout.EAST, nameT);
		sl_bottomRightPanel.putConstraint(SpringLayout.EAST, nameI, 0, SpringLayout.EAST, enter);
		bottomRightPanel.add(nameI);
		nameI.setFont(new Font("Arial", Font.BOLD, 18));
		
		//Day sim input
		dayI = new JTextField();
		sl_bottomRightPanel.putConstraint(SpringLayout.NORTH, dayI, 6, SpringLayout.SOUTH, nameI);
		sl_bottomRightPanel.putConstraint(SpringLayout.WEST, dayI, 18, SpringLayout.EAST, dayT);
		sl_bottomRightPanel.putConstraint(SpringLayout.EAST, dayI, -25, SpringLayout.EAST, bottomRightPanel);
		bottomRightPanel.add(dayI);
		dayI.setFont(new Font("Arial", Font.BOLD, 18));

		SpringLayout sl_topPanel = new SpringLayout();
		topPanel.setLayout(sl_topPanel);
		
		//Start Button
		start = new JButton();
		sl_topPanel.putConstraint(SpringLayout.NORTH, start, 28, SpringLayout.NORTH, topPanel);
		sl_topPanel.putConstraint(SpringLayout.WEST, start, 304, SpringLayout.WEST, topPanel);
		sl_topPanel.putConstraint(SpringLayout.SOUTH, start, 64, SpringLayout.NORTH, topPanel);
		sl_topPanel.putConstraint(SpringLayout.EAST, start, 388, SpringLayout.WEST, topPanel);
		start.setFont(new Font("Tahoma", Font.BOLD, 14));
		start.setText("Start");
		topPanel.add(start);
	
		//Previous Button
		prev = new JButton();
		sl_topPanel.putConstraint(SpringLayout.NORTH, prev, 11, SpringLayout.SOUTH, start);
		sl_topPanel.putConstraint(SpringLayout.WEST, prev, -52, SpringLayout.EAST, start);
		prev.setFont(new Font("Tahoma", Font.BOLD, 12));
		prev.setText("Prev");
		topPanel.add(prev);
		
		//Next Button
		next = new JButton();
		sl_topPanel.putConstraint(SpringLayout.SOUTH, prev, 0, SpringLayout.SOUTH, next);
		sl_topPanel.putConstraint(SpringLayout.EAST, prev, -6, SpringLayout.WEST, next);
		sl_topPanel.putConstraint(SpringLayout.WEST, next, 415, SpringLayout.WEST, topPanel);
		next.setFont(new Font("Tahoma", Font.BOLD, 12));
		next.setText("Next");
		topPanel.add(next);
		
		showMap = new JButton();
		sl_topPanel.putConstraint(SpringLayout.NORTH, next, 0, SpringLayout.NORTH, showMap);
		sl_topPanel.putConstraint(SpringLayout.SOUTH, next, 0, SpringLayout.SOUTH, showMap);
		sl_topPanel.putConstraint(SpringLayout.EAST, next, -6, SpringLayout.WEST, showMap);
		sl_topPanel.putConstraint(SpringLayout.NORTH, showMap, 75, SpringLayout.NORTH, topPanel);
		sl_topPanel.putConstraint(SpringLayout.WEST, showMap, 494, SpringLayout.WEST, topPanel);
		sl_topPanel.putConstraint(SpringLayout.SOUTH, showMap, 106, SpringLayout.NORTH, topPanel);
		sl_topPanel.putConstraint(SpringLayout.EAST, showMap, 598, SpringLayout.WEST, topPanel);
		showMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		showMap.setText("Show Map");
		topPanel.add(showMap);
		

		
		//Will show the data of person
		result = new JTextArea("",5,5);
		result.setLocation(37, 128);
		result.setSize(393, 490);
		result.setWrapStyleWord(true);
		result.setFont(font3);
//		result.setLineWrap(true);
		result.setEditable(false);
		midLeftPanel.setViewportView(result);
	
		
		//show number of child, adult and senior
		data = new JTextArea("",5,5);
		data.setLocation(450, 128);
		data.setSize(169, 260);
		data.setWrapStyleWord(true);
		data.setFont(font3);
		data.setEditable(false);
		midRightPanel.setViewportView(data);
		
		/*
		 * JTextField
		 */
		
		//That Movement text
		title = new JTextField("Movement");
		sl_topPanel.putConstraint(SpringLayout.NORTH, title, 12, SpringLayout.NORTH, topPanel);
		sl_topPanel.putConstraint(SpringLayout.WEST, title, 100, SpringLayout.WEST, topPanel);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setEditable(false);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font1);
		topPanel.add(title);
		
		//name button
		name = new JTextField("Name");
		sl_topPanel.putConstraint(SpringLayout.NORTH, name, 76, SpringLayout.NORTH, topPanel);
		sl_topPanel.putConstraint(SpringLayout.WEST, name, 48, SpringLayout.WEST, topPanel);
		sl_topPanel.putConstraint(SpringLayout.EAST, name, 121, SpringLayout.WEST, topPanel);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setEditable(false);
		name.setFont(font2);
		topPanel.add(name);
		
		//Will search name in text file folder and show it on result
		search = new JTextField(20);
		sl_topPanel.putConstraint(SpringLayout.NORTH, search, 76, SpringLayout.NORTH, topPanel);
		sl_topPanel.putConstraint(SpringLayout.WEST, search, 131, SpringLayout.WEST, topPanel);
		sl_topPanel.putConstraint(SpringLayout.EAST, search, 312, SpringLayout.WEST, topPanel);
		search.setFont(font2);
		topPanel.add(search);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(midLeftPanel, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(7)
									.addComponent(midRightPanel, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
								.addComponent(bottomRightPanel, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 641, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(midLeftPanel, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(midRightPanel, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(bottomRightPanel, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))))
		);
		getContentPane().setLayout(groupLayout);
		

		
//		sc = new JScrollPane(result,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		getContentPane().add(sc);
//		sc.setVisible(true);
//		
//		result.setEditable(true);
		
		
		//Call to set everything but Start button to invisible
		visible(false);
		

		
		//Will handle all action on item it set
		Handler handler = new Handler();
		start.addActionListener(handler);
		dayI.addActionListener(handler);
		nameI.addActionListener(handler);
		showMap.addActionListener(handler);
		next.addActionListener(handler);
		prev.addActionListener(handler);
	}
	
	//only called once
	private void visible(boolean b) { 

		search.setVisible(b);
		name.setVisible(b);
		data.setVisible(b);
		prev.setVisible(b);
		next.setVisible(b);
		enter.setVisible(b);
		nameT.setVisible(b);
		nameI.setVisible(b);
		dayT.setVisible(b);
		dayI.setVisible(b);
		showMap.setVisible(b);
		midRightPanel.setVisible(b);
		

		
		
	}
	
	/*
	 *This to start the movement simulation algorithm that will be conducted in another class
	 *For now it is just a dummy 
	 */
	private void startSimulation() {
		start.setEnabled(false);
		map = new Map();
		try {
			moveAlgo = new MovementSimulatorAlgorithm(this,map);
			childList = moveAlgo.getChildList();
			adultList = moveAlgo.getAdultList();
			seniorList = moveAlgo.getSeniorList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		start.setVisible(false);
		visible(true);
	}
	
	private void setNext() {
		if(adultIterator.hasNext())
			result.setText(adultIterator.next().toString());
	}
	
	private void setPrev() {
		if(adultIterator.hasPrevious())
			result.setText(adultIterator.previous().toString());
	}
	
	private void randomize() {
		Children tempC;
    	Adult tempA;
    	Senior tempS;
		
		childIterator = childList.getListIterator();
		adultIterator = adultList.getListIterator();
		seniorIterator = seniorList.getListIterator();
		
		tempA = adultIterator.next();
		result.setText(tempA.toString());
	}
	
	public void setLoad(String str) {
		result.setText(str);
	}

	
	/**
	 * 
	 *All action for the item in constructor will be handled here
	 *	
	 *
	 */
private class Handler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource()==start) {
				startSimulation();
				randomize();
			}
			
			if(event.getSource()==dayI||event.getSource()==nameI) {
				if(!dayI.getText().isEmpty()&&!nameI.getText().isEmpty()) {
					infectSim = new InfectionSimulatorFrame(Integer.parseInt(nameI.getText()),Integer.parseInt(dayI.getText()));
					infectSim.setSize(596,504);
					infectSim.setVisible(true);
					infectSim.setLocationRelativeTo(null);
				}
			}
			if(event.getSource()==next) {
				setNext();
			}
			if(event.getSource()==prev) {
				setPrev();
			}
			
			
//			if(event.getSource()==showMap) {
////				map = new Map();
////				result.setText(map.toString());
//				MapFrame mapframe = new MapFrame();
//				mapframe.setSize(500,500);
//				mapframe.setVisible(true);
//				mapframe.setLocationRelativeTo(null);
//				
//			}
//			
		}
}
}
