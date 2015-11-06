/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Segments;
import game.sprites.Sprite;
import java.net.URL;
/**
 *
 * @author Marcin
 */
public class SegmentBlock extends Segment {	
	public SegmentBlock(int x, int y, URL file)	{
		super(x,y,file);
	}
        @Override
	public void collisionV(Sprite sprite)	{
			sprite.stopMoveY();
	}
        @Override
	public void collisionH(Sprite sprite)	{
			sprite.stopMoveX();
	}
}
