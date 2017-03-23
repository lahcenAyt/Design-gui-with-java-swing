/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author lahcen
 */
public class CustomPanel extends JPanel{
    int progress =0;
    int nbFont=30;
    int nbr =120;
    int moin=10;
    int boucle=100;
    Color colorCercle=Color.white;
    Color colorLine=Color.orange;
    Color colorNot = Color.gray;

    public CustomPanel(){
        
    }
    public CustomPanel(int nbFont_val,int nbr_val,Color colorCercle_val,Color colorLine_val,Color colorNot_val,int moin_val,int boucle_val){
        nbFont = nbFont_val;
        nbr = nbr_val;
        colorCercle = colorCercle_val;
        colorLine = colorLine_val;
        colorNot = colorNot_val;
        moin = moin_val;
        boucle = boucle_val;
        setBackground(colorCercle_val);
    }

    public Color getColorNot() {
        return colorNot;
    }

    public void setColorNot(Color colorNot) {
        this.colorNot = colorNot;
    }

    public int getMoin() {
        return moin;
    }

    public void setMoin(int moin) {
        this.moin = moin;
    }

    public int getBoucle() {
        return boucle;
    }

    public void setBoucle(int boucle) {
        this.boucle = boucle;
    }
    
    public Color getColorCercle() {
        return colorCercle;
    }

    public void setColorCercle(Color colorCercle) {
        this.colorCercle = colorCercle;
    }

    public Color getColorLine() {
        return colorLine;
    }

    public void setColorLine(Color colorLine) {
        this.colorLine = colorLine;
    }

    public int getNbFont() {
        return nbFont;
    }

    public void setNbFont(int nbFont) {
        this.nbFont = nbFont;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.translate(this.getWidth()/2, this.getHeight()/2);
        g2.rotate(Math.toRadians(270));
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        Ellipse2D circle = new Ellipse2D.Float(0, 0, getNbr()-getMoin(), getNbr()-getMoin());
        Ellipse2D circle2 = new Ellipse2D.Float(0, 0, getNbr(), getNbr());
        arc.setFrameFromCenter(new Point(0,0),new Point(getNbr(),getNbr()));
        circle.setFrameFromCenter(new Point(0,0),new Point(getNbr()-getMoin(),getNbr()-getMoin()));
        circle2.setFrameFromCenter(new Point(0,0),new Point(getNbr(),getNbr()));
        arc.setAngleStart(1);
        arc.setAngleExtent(-getProgress()*3.6);
        g2.setColor(getColorNot());
        g2.draw(circle2);
        g2.fill(circle2);
        g2.setColor(getColorLine());
        g2.draw(arc);
        g2.fill(arc);
        g2.setColor(getColorCercle());
        g2.draw(circle);
        g2.fill(circle);
        g2.setColor(getColorLine());
        g2.rotate(Math.toRadians(90));
        g2.setFont(new Font("Tahoma", Font.PLAIN, getNbFont()));
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(getProgress()+"", g);
        int x =(0-(int)r.getWidth())/2;
        int y =(0-(int)r.getHeight())/2+fm.getAscent();
        g2.drawString(getProgress()+"", x, y);
        
    }
    public void runCircle(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= getBoucle(); i++) {
                        setProgress(i);
                        repaint();
                        Thread.sleep(50);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }).start();
    }
}
