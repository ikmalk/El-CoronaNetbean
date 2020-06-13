package corona.simulator;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;



@SuppressWarnings("serial")
public class InfectionSimulator extends Canvas implements Runnable {
	
	public static final int WIDTH = 900, HEIGHT = 690; 
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	private Random g;
	
	
	

	
	public static STATE State = STATE.Menu;
	
	public InfectionSimulator() {
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "Simulator", this);
		
		
		
		
			
			
			
			
//			handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
//			handler.addObject(new BasicEnemy(g.nextInt(Game.WIDTH - 50), g.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
		/*  //this is for menu particle which brings more problem than good
		else {
			for(int i = 0; i<10; i++) {
				handler.addObject(new MenuParticle(g.nextInt(WIDTH), g.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
		*/
		
	}

	public void spawn(){

		Place[] place = new MapPlace().getPlace();
		
		spawner = new Spawn(handler, hud, place);
		
		
		g = new Random();
		
		handler.setPlace(place);
		State = STATE.Simulation;
		if(State == STATE.Simulation) 
		{
			for(int i = 0;i<place.length;i++) { 
				handler.addObject(place[i]);
			}
		}
			
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//the simulation loop (clock)
	public void run(){
		  this.requestFocus();
		  long lastTime = System.nanoTime();				 // get current time to the nanosecond
		  double amountOfTicks = 60.0;						 // set the number of ticks 
		  double ns = 1000000000 / amountOfTicks; 			// this determines how many times we can divide 60 into 1e9 of nano seconds or about 1 second
		  double delta = 0;									 // change in time (delta always means a change like delta v is change in velocity)
		  long timer = System.currentTimeMillis(); 			// get current time
		  int frames = 0; 									// set frame variable
		  while(running){ 
		   long now = System.nanoTime(); 					// get current time in nanoseconds during current loop
		   delta += (now - lastTime) / ns; 					// add the amount of change since the last loop
		   lastTime = now;  								// set lastTime to now to prepare for next loop
		   while(delta >= 1){
														    // one tick of time has passed in the game this 
														    //ensures that we have a steady pause in our game loop 
														    //so we don't have a game that runs way too fast 
														    //basically we are waiting for  enough time to pass so we 
														    // have about 60 frames per one second interval determined to the nanosecond (accuracy)
														    // once this pause is done we render the next frame
		    tick();  
		    delta--;  										// lower our delta back to 0 to start our next frame wait
		   }
		   if(running){
		    render(); 										// render the visuals of the game
		   }
		   frames++; 										// note that a frame has passed
		   if(System.currentTimeMillis() - timer > 1000 ){ // if one second has passed
		    timer+= 1000; 									// add a thousand to our timer for next time
		    //System.out.println("FPS: " + frames); 			// print out how many frames have happened in the last second
		    frames = 0;						 				// reset the frame count for the next second
		   }
		  }
		  stop();										 	// no longer running stop the thread
		 }
	
	private void tick(){
		handler.tick();
		if(State == STATE.Simulation) {
//			hud.tick();
			try {
			spawner.tick();
			}catch(NullPointerException e) {
				
			}
			hud.tick();
		}
		else if(State == STATE.Menu) {
			menu.tick();
			
		}
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
						
		try {
			handler.render(g);
		} catch (Exception e) {
		}
		
		if(State == STATE.Simulation) {
			hud.render(g);
			menu.render(g);
		}
		else if(State == STATE.Menu) {
			menu.render(g);
		}
		
		
		
		
		g.dispose();
		bs.show();
		
		
	}
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min) 
			return min;
		else 
			return var;
		
	}
	
	public static void main(String[] args) {		
		new InfectionSimulator();
	}
}
