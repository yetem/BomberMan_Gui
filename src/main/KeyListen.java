/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import game.sprites.Sprite;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Marcin
 */
public class KeyListen extends KeyAdapter{
    private static KeyListen instance = new KeyListen();
    private Sprite sprite;
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    JFrame frame;
    
    private KeyListen(){}
    public static KeyListen getInstance(Sprite sprite, JFrame frame){     
        instance.frame = frame;
        instance.sprite = sprite;
        return instance;
    }

    

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
}
