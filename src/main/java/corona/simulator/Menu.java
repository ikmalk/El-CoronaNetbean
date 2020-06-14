package corona.simulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;




public class Menu extends MouseAdapter{
	
	/***********************************
	 * Created at 11/6/2020
	 * 
	 * -Determine the state of the game (the STATE enum)
	 * -Will call the spawn method in the game if the start button is clicked
	 * -will return the variable for the manipulator of the simulation(not done)
	 * 
	 */
	
	private InfectionSimulator simulator;
	private Handler handler;
	private HUD hud;
	
	private Random g = new Random();
	
	
	public Menu(InfectionSimulator simulator, Handler handler, HUD hud) {
		this.simulator = simulator;
		this.hud = hud;
		this.handler = handler;
		


		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		
		
		if(InfectionSimulator.State == STATE.Menu) {																
			//Start button
			if(mouseOver(mx, my, 370, 150, 150, 60)) {
				InfectionSimulator.State = STATE.Simulation;
				simulator.spawn();
				
			}

		}
		if(InfectionSimulator.State==STATE.Simulation) {
			
			if(mouseOver(mx, my, InfectionSimulator.WIDTH-180, InfectionSimulator.HEIGHT-107, 100, 35)) {
				InfectionSimulator.State = STATE.Menu;
				handler.clear();
				
			}
		}						
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void tick() {
	
		
	}
	
	public void render(Graphics g) {
		if(InfectionSimulator.State == STATE.Menu) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			
			g.setFont(font);		
			g.setColor(Color.white);
			
			g.drawString("Infection Simulation", 200, 100);
			
			g.setFont(font2);
			
			g.drawString("Start", 410, 190);
			g.drawRect(370, 150, 150, 60);
			
			
		}
		else if(InfectionSimulator.State==STATE.Simulation) {
			Font font2 = new Font("arial",1, 20);
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Back", InfectionSimulator.WIDTH-150, InfectionSimulator.HEIGHT-85);
			g.drawRect(InfectionSimulator.WIDTH-180, InfectionSimulator.HEIGHT-107, 100, 35);
		}
		
		
	}
}
