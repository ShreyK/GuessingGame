package Basics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Board extends JPanel implements Runnable {

    private Image img;
    private Thread animator;
    private int x, y;
    private final int DELAY = 50;
    private boolean boolx = true;
    private boolean booly = true;
    private int count = 0;
    private int finalHit = (int) (Math.random()*10+1);
    
    public Board() {
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        ImageIcon ii = new ImageIcon(this.getClass().getResource("icon.png"));
        img = ii.getImage();

        x = y = 10;
    }

    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawString("Bounces: " + count, 10, 250);
        g2d.drawImage(img, x, y, this);
        if(getCount()==getFinalHit())
        	g2d.drawString("END!", 250, 250);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void cycle() {
    	if(x==280)
    		{boolx = false;
    		count++;}
    	if(y==240)
    		{booly = false;
    		count++;}
    	if(x==0)
    		{boolx = true;
    		count++;}
    	if(y==0)
    		{booly = true;
    		count++;}
    if(boolx && booly){
       x+=2;
       y+=2;
    }	
    if(!boolx && booly)
    {
       x-=2;
       y+=2;
    }
    if(!booly && boolx)
    {
       y-=2;
       x+=2;
    }
    if(!boolx && !booly)
    {
       x-=2;
       y-=2;
    }
    if(count == finalHit)
    	{x=-260;
    	y=-260;
    	}
   }

    public int getFinalHit()
    {
    	return finalHit;
    }
    
    public int getCount()
    {
    	return count;
    }
    
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}