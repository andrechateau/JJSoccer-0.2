/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.menuModels;

import controller.managers.ImageManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Actor;
import models.Sprite;
import models.interfaces.Action;

/**
 *
 * @author Andre Chateaubriand
 */
public class ButtonActor extends Actor {

    private List<ActionListener> listeners;
    private String text;
    private boolean focused;
    private boolean onPosition;
    private int speed;
    private int expectedX;
    private BufferedImage image;
    private boolean ballroll;
    private final int DELAY = 250;
    private long previuousMillis;

    public ButtonActor(int x, int y, int speed, String text) {
        super(-300, y);
        setSpr(new Sprite("soccerball.png"));
        onPosition = false;
        this.speed = speed;
        expectedX = x;
        focused = false;
        ballroll = false;
        this.text = text;
        listeners = new LinkedList<>();
        image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_ARGB_PRE);
        updateImage();
        previuousMillis = System.currentTimeMillis();
    }

    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        if (!isOnPosition()) {
            setX(speed + getX());
            if (expectedX <= getX()) {
                onPosition = true;
            }
        }
        if (System.currentTimeMillis() - previuousMillis >= DELAY) {
            updateImage();
            previuousMillis=System.currentTimeMillis();
        }
    }

    /**
     * @return the onPosition
     */
    public boolean isOnPosition() {
        return onPosition;
    }

    /**
     * @return the focused
     */
    public boolean isFocused() {
        return focused;
    }

    /**
     * @param focused the focused to set
     */
    public void setFocused(boolean focused) {
        this.focused = focused;
        image=new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        updateImage();
    }

    public void add(ActionListener listener) {
        listeners.add(listener);
    }

    public void ActionPerformed() {
        ActionEvent e = new ActionEvent(this, 0, text);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(e);
        }
    }

    @Override
    public Image getImage() {
        return image;
    }

    private void updateImage() {
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(new Color(10, 10, 10));
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        graphics.setColor(new Color(60, 100, 60));
        graphics.drawString(text, 38, 43);
        graphics.setColor(new Color(10, 10, 10));
        graphics.drawString(text, 35, 40);
        if (isFocused()) {
            if (ballroll) {
                ballroll = false;
                graphics.drawImage(ImageManager.getInstance().loadImage("selectionball1.png"), 0, 15, null);
            } else {
                ballroll = true;
                graphics.drawImage(ImageManager.getInstance().loadImage("selectionball2.png"), 0, 15, null);
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}