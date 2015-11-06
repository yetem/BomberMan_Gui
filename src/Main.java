
import game.Game;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import media.Media;

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

        frame.getContentPane().add(new Game(Media.class.getResource("plansza01.txt"), frame));

        frame.pack();
        frame.setVisible(true);
    }
}
