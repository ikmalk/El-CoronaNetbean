package corona.simulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
		
	private int suspected;
	private int infected;
	private int removed;
	private int day;
	private int count;
	
	public HUD() {
		suspected = 0;
		infected = 0;
		removed = 0;
		day = 1;
		count = 0;
	}
	
	public void tick() {
		count++;
		if(count==200) {
			day++;
			count=0;
		}
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		Font font2 = new Font("arial", 1, 18);
		g.setFont(font2);
		g.drawString("Day: " + day, InfectionSimulator.WIDTH-222, 34);
		g.drawString("Suspected: " + suspected, InfectionSimulator.WIDTH-222, 64);
		g.drawString("Infected: " + getInfected(), InfectionSimulator.WIDTH-222, 94);
		g.drawString("Removed: "+getRemoved(), InfectionSimulator.WIDTH-222, 124);
		
		
	}
	
	public void setSuspected(int suspected) {
		this.suspected = suspected;
	}
	
	public int getSuspected() { 
		return suspected;
	}
	
	
	public int getDay() {
		return day;
	
	}

	public int getInfected() {
		return infected;
	}

	public void setInfected(int infected) {
		this.infected = infected;
	}

	public int getRemoved() {
		return removed;
	}

	public void setRemoved(int removed) {
		this.removed = removed;
	}

}
