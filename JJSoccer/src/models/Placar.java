/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import models.interfaces.Renderable;

/**
 *
 * @author Andre Chateaubriand
 */
public class Placar implements Renderable {

    private final int x;
    private final int y;
    private int golsTime1;
    private int golsTime2;
    private String nomeTime1;
    private String nomeTime2;
    private int ImageWidth;
    private BufferedImage image;
    private Font font;

    public Placar(int x, int y, String nomeTime1, String nomeTime2) {
        this.y = y;
        golsTime1 = 0;
        golsTime2 = 0;
        this.nomeTime1 = nomeTime1;
        this.nomeTime2 = nomeTime2;
        int fontSize = 32;
        font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        ImageWidth = (fontSize/2) * (nomeTime1.length() + nomeTime2.length() + 10);
        image = new BufferedImage(ImageWidth, 80, BufferedImage.TYPE_INT_ARGB_PRE);
        updateImage();
        this.x = x - (ImageWidth / 2);
    }

    private void updateImage() {
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(new Color(0, true));
        graphics.fillRect(0, 0, ImageWidth, 80);
        graphics.setColor(Color.DARK_GRAY);
        graphics.setFont(font);
        graphics.drawString(nomeTime1 + " " + golsTime1 + " x " + golsTime2 + " " + nomeTime2, 10, 50);

        graphics.dispose();

    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}
