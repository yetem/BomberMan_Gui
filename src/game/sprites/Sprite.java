/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.sprites;

import game.Segments.Segment;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import media.Media;

/**
 *
 * @author Marcin
 */
public class Sprite {
	private static final Image img = new ImageIcon(Media.class.getResource("Mario.png")).getImage();
		
	private int[] anim = {0, 1, 2, 1};
	private int frame = 2;		// klatka animacji
	private boolean mirror = false; // postac patrzy w lewo/ prawo
	private int movingX = 0;		// ruch w poziomie
	private int movingY = 0; 	// ruch w pionie
	private final ArrayList<Segment> plansza;

	private int x=40, y=40; 	// pozycja na ekranie
	private final int W=16, H=27;// wysokosc i szerokosc sprite'a
	
	public Sprite(ArrayList<Segment> pl) { plansza=pl; }

	public int getX() { return x; }
	public int getY() { return y; }
	public int getBottomY() { return y+H; }

	public void up() {		// poruszanie postacia
		if(movingY == 0) movingY = 3;
	}
        public void down() {		// poruszanie postacia
		if(movingY == 0) movingY = -3;
	}
	public boolean isJumping() { return movingY>0; }
	public boolean jumpingDown() { return movingY<0; }
	public void stopMoveY() { movingY=0; }
	public void stopMoveX() { movingX=0; }

	public void left() {
		movingX = -3;
		mirror = false;
	}
	public void right() {
		movingX = 3;
		mirror = true;
	}
	public void stop() {
                movingY = 0;
		movingX = 0;
		frame = 2;
	}
		
	private boolean canGo(int dx, int dy)	{
		for(Segment s:plansza)
			if(s.getBounds().intersects(x+dx, y+dy, W, H))	{
				return false;
			}
		return true;
	}
	private void collide(int dx, int dy)	{
		for(Segment s:plansza)
			if(s.getBounds().intersects(x+dx, y+dy, W, H))	{
				if(dx != 0)
					s.collisionH(this);
				if(dy != 0)
					s.collisionV(this);
			}
	}
	public void tick() {
		if(movingX != 0 || movingY !=0) {// animacja ruchu
			frame++;
			while (frame >= anim.length)
				frame -= anim.length;
		}
		// przesuniecie w poziomie
		for(int i = 0; i < Math.abs(movingX); ++i) {
			collide((int)Math.signum(movingX), 0);
			x += (int)Math.signum(movingX);
		}
			
		// przesuniecie w pionie
		for(int i = 0; i < Math.abs(movingY); ++i) {
                    collide(0, -(int)Math.signum(movingY));
			y -= (int)Math.signum(movingY);
                    
//			collide(0, -(int)Math.signum(jumping));
//			y -= (int)Math.signum(jumping);
		}
		// czy mamy grunt pod nogami?
//		jumping--;
		//collide(0,1);
//		if(jumping != 0)	frame = 0;
//		if(jumping == 0 && moving == 0)	frame = 2;
	}
	public void draw(Graphics g) {
		g.drawImage(img, x + (mirror?W:0),y,x + (mirror?0:W),y + H,
			anim[frame]*W,0,anim[frame]*W + W,H,null);
	}
}
