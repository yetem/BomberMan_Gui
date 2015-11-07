/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.Segments.BoardDirector;
import game.sprites.Sprite;
import game.Segments.SegmentBlock;
import game.Segments.Segment;
import game.Segments.SegmentAnim;
import game.Segments.SegmentBlockV;
import game.sprites.SpriteController;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import main.KeyListen;
import media.Media;

/**
 *
 * @author Marcin
 */
public class Game extends JPanel {

    
    private JFrame frame;
    private ArrayList<Segment> plansza;
    private Sprite sprite;
    private int numberBoard=1;
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      BoardDirector director = new BoardDirector();

    

    public Game(final JFrame _frame) {

        frame = _frame;
        plansza = director.getBoard(numberBoard);
        sprite = new Sprite(plansza);
        setPreferredSize(new Dimension(800, 600));
        addKeyListener(KeyListen.getInstance(sprite, frame));

        setFocusable(true);
        

        new Thread(new SpriteController(sprite, plansza, this)).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Segment s : plansza) {
            s.draw(g);
        }
        sprite.draw(g);
    }
}
