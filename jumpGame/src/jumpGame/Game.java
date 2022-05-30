package jumpGame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable, KeyListener{
	
	public final int WIDTH = 480;
	public final int HEIGHT = 480;
	public final int SCALE = 3;
	
	public static Player player;
	public static List<Platform> platform = new ArrayList<Platform>();
	
	public Game() {
		this.addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		player = new Player((WIDTH/2)-16,HEIGHT-40);
		platform.add(new Platform(0,480-8,480));
		platform.add(new Platform(60, 240,80));
		platform.add(new Platform(480 - 80 - 60, 120,80));
	}
	
	public static boolean isPlatform(int x, int y) {
		for(int i = 0; i < platform.size(); i++) {
			Platform currentPlat = platform.get(i);
			if(currentPlat.intersects(new Rectangle(x,y+20,32,8))){
				return false;
				}
			}
		return true;
		
	}
	
	public void buffer() {
		player.buffer();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		for(int i = 0; i < platform.size(); i++) {
			platform.get(i).render(g);
		}
		player.render(g);
		bs.show();			
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.setResizable(false);
		
		frame.add(game);
		frame.setTitle("Jumping Game");
		
		
		frame.pack();
		
		
		frame.setLocationRelativeTo(null);
		
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		frame.setVisible(true);
		
		
		new Thread(game).start();
	}

	@Override
	public void run() {
		while(true) {
			buffer();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub	
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.dir = 1;
		}else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			player.dir = 2;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.jump = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.dir = 0;
		}else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT);{
			player.dir = 0;
		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.jump = 0;
		}
	}
}
