package jumpGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Player extends Rectangle {
	
	public int dir = 0;
	public int jump = 0;
	public static int spd = 3;
	public static int yspd = 8;
	public static int jumpTime = 0;
	
	public Player(int x, int y){
		super(x,y,32,32);
	}

	public void buffer() {
		if(dir == 1) {
			x+=spd;
		} if(dir == 2) {
			x-=spd;
		}
		
		if(jump == 1 /*&& !Game.isPlatform(x, y + yspd)*/) {
			jumpTime++;
			y-=yspd;
			if(jumpTime >= 30) {
				y+=yspd*2;
				if(!Game.isPlatform(x, y + yspd)) {
					jumpTime = 0;
				}
			}
			


		}else if(Game.isPlatform(x, y+yspd)) {
			y+=yspd;
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.cyan);
		g.fillRect(x, y, 32, 32);
	}
}
