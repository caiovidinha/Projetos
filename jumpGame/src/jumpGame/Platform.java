package jumpGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Platform extends Rectangle {

	public Platform(int x, int y, int width) {
		super(x,y,width,8);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x,y,width,8);
	}
}
