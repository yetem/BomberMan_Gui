/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

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
import media.Media;

/**
 *
 * @author Marcin
 */
public class Game extends JPanel {

    private final int TILESIZE = 32;
    private JFrame frame;
    private ArrayList<Segment> plansza;
    private Sprite sprite;
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                

    private ArrayList<Segment> stworzPlansze(URL plik) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(plik.getFile()));
            ArrayList<Segment> plansza = new ArrayList<Segment>();
            String linia;
            int x, y = 4, liczba, znaki;
            char znak, cyfra1, cyfra2;
            while ((linia = br.readLine()) != null) {
                x = 4;
                znaki = 0;
                while ((linia.length() - znaki) >= 3) {
                    znak = linia.charAt(znaki++);
                    cyfra1 = linia.charAt(znaki++);
                    cyfra2 = linia.charAt(znaki++);
                    liczba = (cyfra1 - '0') * 10 + (cyfra2 - '0');
                    switch (znak) {
                        case 'X':
                            x += liczba * TILESIZE;
                            break;
                        case 'A':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = new SegmentBlock(x, y, Media.class.getResource("block1.png"));
                                plansza.add(s);
                                x += TILESIZE;
                            }
                            break;
                        case 'B':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = new SegmentBlockV(x, y, Media.class.getResource("block2.png"));
                                plansza.add(s);
                                x += TILESIZE;
                            }
                            break;
                        case 'C':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = new Segment(x, y, Media.class.getResource("block3.png"));
                                plansza.add(s);
                                x += TILESIZE;
                            }
                            break;
                        case 'G':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = new SegmentAnim(x, y, Media.class.getResource("bonus.png"), new int[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 1, 0, 0});
                                plansza.add(s);
                                x += TILESIZE;
                            }
                            break;
                    }
                }
                y += TILESIZE;
            }
            br.close();
            return plansza;
        } catch (IOException e) {
            System.out.println("Blad wczytania planszy");
            e.printStackTrace();
            return null;
        }
    }

    public Game(URL plik, final JFrame _frame) {

        frame = _frame;
        setPreferredSize(new Dimension(800, 600));
        addKeyListener(new KeyAdapter() {
            
            
            @Override
            public void keyReleased(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        sprite.stopMoveY();
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        sprite.stopMoveX();
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        sprite.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        sprite.right();
                        break;

                    case KeyEvent.VK_DOWN:
                        sprite.down();
                        break;
                    case KeyEvent.VK_UP:
                        sprite.up();
                        break;
                    case KeyEvent.VK_F12:
                        gd.setFullScreenWindow(frame);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;                  
                }
            }
        });

        setFocusable(true);
        plansza = stworzPlansze(plik);
        sprite = new Sprite(plansza);

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
