/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Segments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import media.Media;

/**
 *
 * @author Marcin
 */
public class BoardDirector {
    private final int TILESIZE = 32;
    private BoardBuilder builder;
        
    
    
    public BoardDirector(){
        builder = new BoardBuilder(4,4);
    }
    
    public ArrayList<Segment> getBoard(int number){
        return createBoard(BoardDirector.class.getResource("plansza" + String.format("%02d", number) + ".txt"));
    }
    private ArrayList<Segment> createBoard(URL plik) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(plik.getFile()));
            ArrayList<Segment> plansza = new ArrayList<Segment>();
            String linia;
            int liczba, znaki;
            char znak, cyfra1, cyfra2;
            while ((linia = br.readLine()) != null) {
                builder.setX(4);
                znaki = 0;
                while ((linia.length() - znaki) >= 3) {
                    znak = linia.charAt(znaki++);
                    cyfra1 = linia.charAt(znaki++);
                    cyfra2 = linia.charAt(znaki++);
                    liczba = (cyfra1 - '0') * 10 + (cyfra2 - '0');
                    switch (znak) {
                        case 'X':
                            builder.addX(liczba * TILESIZE);
                            break;
                        case 'A':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = builder.getSegmentBlock();
                                plansza.add(s);
                                builder.addX(TILESIZE);
                            }
                            break;
                        case 'B':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = builder.getSegmentBlockV();
                                plansza.add(s);
                                builder.addX(TILESIZE);
                            }
                            break;
                        case 'C':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = builder.getSegment();
                                plansza.add(s);
                                builder.addX(TILESIZE);
                            }
                            break;
                        case 'G':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = builder.getSegmentAnim();
                                plansza.add(s);
                                builder.addX(TILESIZE);
                            }
                            break;
                    }
                }
                builder.addY(TILESIZE);
            }
            br.close();
            return plansza;
        } catch (IOException e) {
            System.out.println("Blad wczytania planszy");
            e.printStackTrace();
            return null;
        }
    }
}
