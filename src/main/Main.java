package main;


import com.sun.java.accessibility.util.AWTEventMonitor;
import game.Game;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import media.Media;
import menu.Menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcin
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mario");

        //frame.getContentPane().add(new Menu());
        frame.getContentPane().add(new Game(frame));
        frame.pack();
        frame.setVisible(true);
    }
}
