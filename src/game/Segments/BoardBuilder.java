/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Segments;

import media.Media;

/**
 *
 * @author Marcin
 */
public class BoardBuilder {
    int x, y;
    public BoardBuilder(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int x){
        this.x += x;
    }
 
    public void addY(int y){
        this.y += y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    public Segment getSegmentAnim(){
        return new SegmentAnim(x, y, Media.class.getResource("bonus.png"), new int[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 1, 0, 0});
    }
    public Segment getSegmentBlock(){
        return new SegmentBlock(x, y, Media.class.getResource("block1.png"));
    }
    public Segment getSegmentBlockV(){
        return new SegmentBlockV(x, y, Media.class.getResource("block2.png"));
    }
    public Segment getSegment(){
        return new Segment(x, y, Media.class.getResource("block3.png"));
    }
}

