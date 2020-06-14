package corona.simulator;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	/***********************************
	 * Created at 11/6/2020
	 * 
	 * Handler class handles the addition ,deletion and activity of items in the program (Place class and Person class)
	 * 
	 */
	
	Place[] place;
	
	LinkedList<Item> object = new LinkedList<Item>();
	
	public void tick() {
		for(int i=0; i< object.size(); i++){
		Item tempObject = object.get(i);
		
		tempObject.tick();
		}
	}

	public void render(Graphics g)throws Exception {
		for(int i=0; i< object.size(); i++){
			Item tempObject = object.get(i);
			
			tempObject.render(g);
			}
	}
	
	/*
	 * -Called when the back button is clicked
	 */
	public void clear() {
		object.clear();
	}
	
	/*
	 * -Called each time an item is added
	 */	
	public void addObject(Item object) {
		this.object.add(object);
	}
	
	public void removeObject(Item object) {
		this.object.remove(object);
	}
	
	public void setPlace(Place[] place) {
		this.place = place;
	}
	
}
