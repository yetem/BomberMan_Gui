/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.sprites;

import game.Segments.Segment;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Marcin
 */



public class SpriteController implements Runnable {
	private final Sprite sprite;
	private final ArrayList<Segment> plansza;
	private final JPanel panel;
	public SpriteController(Sprite sp, ArrayList<Segment> pl, JPanel pan) {
		sprite=sp;
		plansza=pl;
		panel=pan;
	}
	public void run() {
		while(true) {
			sprite.tick();
			for(Segment s:plansza)
				s.tick();
			panel.repaint();
			Thread.currentThread().yield();
			try {
				Thread.currentThread().sleep(40);
			}catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}