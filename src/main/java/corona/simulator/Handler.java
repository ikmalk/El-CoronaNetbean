package corona.simulator;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
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
	
	public void clear() {
		object.clear();
	}
	
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
