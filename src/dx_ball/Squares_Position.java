package dx_ball;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Squares_Position 
{
    public int position[][];
    
    public int squareWidth=0;
    public int squareHeight=0;
    
    
    public Squares_Position(int row , int col)
    {
        position = new int[row][col] ;
        
        for(int x=0 ; x<position.length ; x++)
        {
            for(int y=0 ; y<position[0].length ; y++)
            {
                position[x][y]=1;
            }
        }
        squareWidth= 825/col;
        squareHeight= 150/row;
    }
    static int col[][][] = new int [100][100][3];
    boolean flag = true;
    
    public void myColour(){
        for(int x=0 ; x<position.length ; x++)
        {
            for(int y=0 ; y<position[0].length ; y++)
            {
                double xx= Math.random()*255;
                double yy= Math.random()*255;
                double zz= Math.random()*255;
                col[x][y][0] = (int)xx;
                col[x][y][1] = (int)yy;
                col[x][y][2] = (int)zz;
                
            }
        }
    }
    
    public void Draw(Graphics2D g)
    {
        if(flag){
            myColour();
            flag = false;
        }
        for(int x=0 ; x<position.length ; x++)
        {
            for(int y=0 ; y<position[0].length ; y++)
            {
                if(position[x][y]>0)
                {
                    g.setColor(new java.awt.Color(col[x][y][0],col[x][y][1],col[x][y][2]));
                    g.fillRect(y*squareWidth +80 , x*squareHeight +50 , squareWidth, squareHeight);
                    
                    
                    g.setStroke(new BasicStroke(5));
                    g.setColor(Color.BLACK);
                    g.drawRect(y*squareWidth +80 , x*squareHeight +50 , squareWidth, squareHeight);
                }
            }
        }
    }
    public void setSquareValue(int value , int row , int col)
    {
        position[row][col]=value;
    }
}
