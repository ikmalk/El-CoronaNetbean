package corona.simulator;

import java.util.ArrayList;
import java.util.Random;

public class Spawn {
	
	/*
	 * Will handle all the spawning of people
	 */
	
	private Handler handler;
	private HUD hud;
	private Random g = new Random();	
	private int n;
	private Place[] place;
	
	public Spawn() {
		
	}
	
	public Spawn(Handler handler, HUD hud,Place[] place) {
		this.handler = handler;
		this.hud = hud;
		this.place = place;
		n=0;
	}
	
	public void tick() throws NullPointerException{
		
		if(n<200) {
			handler.addObject(new People(g.nextInt(600),g.nextInt(600),TYPE.People,handler,place,HEALTH.Suspected,hud));
			hud.setSuspected(hud.getSuspected()+1);
			n++;
		}
		else if(n==200) {
			handler.addObject(new People(g.nextInt(600),g.nextInt(600),TYPE.People,handler,place,HEALTH.Infected,hud));
			hud.setInfected(hud.getInfected()+1);
			n++;
		}

	}
		
}
	

