package corona.simulator;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class People extends Item{
	
	/***********************************
	 * Created at 11/6/2020
	 * -This is the people class
	 * -It will have a String[][] event that will determine the movement of the people(not done)
	 * -It will get the Place that have the same name as in the event and get the place coordinate and and went to that place(need String[][] event)
	 * -Have 3 states, Suspected, Infected, Removed
	 * -Suspected will become Infected if collides with Infected INSIDE the place
	 * -Infected becomes Removed after 14 days
	 * 
	 */
	
	private Handler handler;
	private Place target;
	private int count;
	private Place[] place;
	private Random r = new Random();
	private int n = 0;
	private int timeTaken = 0;
	private int timeTotal = 0;
	private int occurence = 0;
	private int avgTime;
	private boolean counting = false; 
	private int chance = 5;
	private HEALTH health;
	private HUD hud;
	private int dayInfected;
	
	public People(int x, int y, TYPE id, Handler handler,Place[] place,HEALTH health,HUD hud) {
		super(x, y, id);		
		this.handler = handler;
		this.place = place;
		count = place.length;
		target = place[r.nextInt(count)];
		this.health = health;
		this.hud = hud;

//		for(int i = 0;i<place.length;i++) {
//			if(place[i].getName().equals("Hospital")) {
//				target = place[i];
//				break;
//			}
//				
//		}
		

	}
	
	public void setHealth(HEALTH health) {
		this.health = health;
	}
	
	public HEALTH getHealth() {
		return health;
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 10, 10);
	}



	public void tick() {
		
		
		if(collision()) {			
			counting=false;
		}else { 				//this is just to get the avg time of the people's movement from one place to another, can ignore this thing
			if(counting) {
				timeTaken++;
			}else {
				timeTotal+=timeTaken;
				timeTaken = 0;
				occurence++;
				counting = true;
				if(occurence>0)
					System.out.println("Average: "+(timeTotal/occurence));
			}
			
		}
	}


	public void render(Graphics g) {
		if(health==HEALTH.Suspected)
			g.setColor(Color.green);
		else if(health==HEALTH.Infected)
			g.setColor(Color.red);
		else if(health==HEALTH.Removed)
			g.setColor(Color.gray);
		g.fillRect((int)x , (int)y, 5, 5);
	}
	
	private boolean collision() {
		
		//Person is inside the Place
		if(getBounds().intersects(target.getBounds())) {
			if(n<190) {
				x += velX;
				y += velY;
				n++;
				int k = 50;
				int l = 50;
				if(target.getName().equals("Hospital")) {
					k = 165;
					l=165;
				}
				else if(target.getName().equals("Park")) {
					k=165;
					l=220;
				}
				else if(target.getName().equals("Police Station")) {
					k = 110;
					l=165;
				}
					
					
				if(n>30) {
					if(y <= target.getBounds().y || y >= target.getBounds().y+l) 
						velY *= -1;
					if(x <= target.getBounds().x || x >= target.getBounds().x+k) 
						velX *= -1;
				}
												
				if(health==HEALTH.Suspected) { //where the infection happen
					for(int i = 0;i<handler.object.size();i++) {
						Item temp = handler.object.get(i);
						if(temp.getId()==TYPE.People) {
							People tempP = (People)temp;
							if(tempP.getHealth()==HEALTH.Infected) {
								if(getBounds().intersects(tempP.getBounds())) {
									if(r.nextInt(600)<chance) {    //temporary, will get the person's immunity later
										setHealth(HEALTH.Infected);
										dayInfected = hud.getDay();
										hud.setInfected(hud.getInfected()+1);
										hud.setSuspected(hud.getSuspected()-1);
									}
							}
							}
						}
					}
				}
				
			}
			else {
				target = place[r.nextInt(count)];
				
			}
			return true;
		}
		//Outside place
		else { 
			n=0;
		//so that there will be no confusion in collision
			x += velX;
			y += velY;
			
			float diffX = x - target.getX() - 10;
			float diffY = y - target.getY() - 10;
			/*
			 * The equation to get the place coordinate
			 */
			float distance = (float) Math.sqrt((x- target.getX())*(x-target.getX()) + (y - target.getY())*(y-target.getY()));
			
			/*
			 * The person wil chase the place according to coordinate
			 */			
			velX = (float)((-1.0/distance)* diffX);
			velY =  (float)((-1.0/distance)* diffY);
			
			if(y <= 0 || y >= InfectionSimulator.HEIGHT - 64 + 16) velY *= -1;
			if(x <= 0 || x >= InfectionSimulator.WIDTH - 32) velX *= -1;
		}
		
		//Turns infected into Removed after 14 days
		if(health==HEALTH.Infected) {
			if(hud.getDay()-dayInfected>=14) {
				health=HEALTH.Removed;
				hud.setRemoved(hud.getRemoved()+1);
			}
		}
		return false;
	}

}
