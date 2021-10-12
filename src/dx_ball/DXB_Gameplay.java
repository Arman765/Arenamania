package dx_ball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.IOException;


public class DXB_Gameplay extends JPanel implements KeyListener,ActionListener
{
    private boolean start=false;
    
    private int score=0;
    
    private int NumberOfSquares = 21;
    
    private Timer game_time;
    
    private int time_delay = 0;
    
    private int user_pos=350;
    
    private int ball_X=350;
    private int ball_Y=400;
    
    private int  ballXmove= -(1);
    private int  ballYmove= -(1);
    
    private Squares_Position sqmap;
    
    
    public DXB_Gameplay() throws IOException
    {
        sqmap = new Squares_Position(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        game_time = new Timer(time_delay , this);
        game_time.start();
    }
    

    
    public void paint(Graphics g)
    {
        //background of the screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 680);
        
        sqmap.Draw((Graphics2D)g);
        
        //borders of the rectangles
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 668);
        g.fillRect(0, 0, 992, 3);
        g.fillRect(991, 0, 3, 668);
        g.fillRect(0, 648, 992, 3);
        
        //scoring
        g.setColor(Color.WHITE);
        g.setFont(new Font("verdana" , Font.BOLD , 25));
        g.drawString(""+score, 935, 30);
        
        
        //the moving plate of the user
        g.setColor(Color.GREEN);
        g.fillRect(user_pos, 635, 130, 8);
        g.setColor(Color.GRAY);
        g.fillRect(user_pos, 632, 130, 6);
        g.setColor(Color.WHITE);
        g.fillOval(user_pos-2, 630, 15, 15);
        g.fillOval(user_pos+128, 630, 15, 15);
        g.setColor(Color.BLUE);
        g.fillOval(user_pos-1, 631, 12, 12);
        g.fillOval(user_pos+129, 631, 12, 12);
        
        //the ball details
        g.setColor(Color.red);
        g.fillOval(ball_X, ball_Y, 16, 16);
        g.setColor(Color.ORANGE);
        g.fillOval(ball_X+1, ball_Y+1, 13, 13);
        g.setColor(Color.yellow);
        g.fillOval(ball_X+1, ball_Y+1, 10, 10);
                
        if(NumberOfSquares <= 0)
        {
            start = false;
            ballXmove=0;
            ballYmove=0;
            
            g.setColor(Color.RED);
            g.setFont(new Font("verdana" , Font.BOLD , 35));
            g.drawString("Game Over! You've Won", 230, 320);
            
            g.setFont(new Font("verdana" , Font.BOLD , 20));
            g.drawString("Press Enter to Restart", 355, 350);
        }
        
        if(ball_Y > 665)
        {
            start = false;
            ballXmove=0;
            ballYmove=0;
            
            g.setColor(Color.yellow);
            g.setFont(new Font("verdana" , Font.BOLD , 35));
            g.drawString("Game Over! Your Score is : "+score, 180, 320);
            
            g.setFont(new Font("verdana" , Font.BOLD , 20));
            g.drawString("Press Enter to Restart", 355, 350);
            
        }
        
        g.dispose();
    }
    
    public void continue_right()
    {
        start = true;
        user_pos = user_pos+40;
    }
    public void continue_left()
    {
        start = true;
        user_pos = user_pos-40;
    }
    

    @Override
    public void keyTyped(KeyEvent ke)
    {
        
    }
    
    @Override
    public void keyReleased(KeyEvent ke)
    {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
       if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
       {
           if(user_pos > 840)
           {
               user_pos=850;
           }
           else
           {
               continue_right();
           }
       }
       if(ke.getKeyCode() == KeyEvent.VK_LEFT)
       {
           if(user_pos < 10)
           {
               user_pos=5;
           }
           else
           {
               continue_left();
           }
       }
       
       if(ke.getKeyCode() == KeyEvent.VK_ENTER)
       {
           if(!start)
           {
               start = true;
               ball_X = 350;
               ball_Y = 400;
               
               ballXmove = -1;
               ballYmove = -1;
               
               user_pos = 350;
               score = 0;
               NumberOfSquares = 21;
               sqmap = new Squares_Position(3,7);
               
               repaint();
               
           }
       }
    }
    

    

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        game_time.start();
        repaint();
                
        if(start)
        {
            if(new Rectangle(ball_X,ball_Y,20,20).intersects(new Rectangle(user_pos , 635 , 130 ,8)))
            {
                ballYmove = - ballYmove;
            }
            
            a: for(int i=0 ; i<sqmap.position.length ; i++)
            {
                for(int j=0 ; j<sqmap.position[0].length ; j++)
                {
                    if(sqmap.position[i][j] > 0)
                    {
                        int square_X = j*sqmap.squareWidth + 80;
                        int square_Y = i*sqmap.squareHeight + 50;
                        int squareWidth = sqmap.squareWidth;
                        int squareHeight = sqmap.squareHeight;
                        
                        Rectangle R1 = new Rectangle(square_X , square_Y , squareWidth , squareHeight);
                        Rectangle ball_rctngl = new Rectangle(ball_X,ball_Y,20,20);
                        Rectangle square_rctngl = R1;
                        
                        if(ball_rctngl.intersects(square_rctngl))
                        {
                            sqmap.setSquareValue(0, i, j);
                            NumberOfSquares --;
                            score +=5;
                            
                            
                            if((square_rctngl.x + square_rctngl.width ) <= ball_X + 1 || (ball_X + 19) <= square_rctngl.x)
                            {
                                ballXmove = -ballXmove;
                            }
                            else
                            {
                                ballYmove = -ballYmove;
                            }    
                            break a;
                        }
                        
                    }
                }
            }
            
            ball_X = ball_X + ballXmove ;
            ball_Y = ball_Y + ballYmove ;
            
            if(ball_X < 0)
            {
                ballXmove = - ballXmove;
            }
            if(ball_Y < 0)
            {
                ballYmove = - ballYmove;
            }
            if(ball_X > 970)
            {
                ballXmove = - ballXmove;
            }
            
        }
    }
    
}
