/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller.managers;

import com.sun.glass.events.KeyEvent;
import java.applet.AudioClip;
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
import models.interfaces.Renderable;
import models.interfaces.changeSceneListener;
import models.menuModels.ButtonActor;
import models.menuModels.LogoActor;
import models.menuModels.PaneRenderable;
import view.Frame;

/**
 *
 * @author Andre Chateaubriand
 */
public class MenuScene extends GameScene {

    private final changeSceneListener parent;
    private AudioClip BGM;
    private LogoActor logo;
    private PaneRenderable pane;
    private final Frame tela;
    private List<ButtonActor> buttons;

    public MenuScene(Frame tela, changeSceneListener parent) {
        this.tela = tela;
        this.parent = parent;
        logo = new LogoActor(100, 50, 4);
        pane = new PaneRenderable(tela.getWidth(), tela.getHeight());

        long previousMillis = System.currentTimeMillis();
        try {
            BGM = AudioManager.getInstance().loadAudio("Theme.wav");

            int delay = 1000;
            while (System.currentTimeMillis() - previousMillis < delay) {
            }
            BGM.play();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        buttons = new LinkedList<>();

        // BT JOGAR
        ButtonActor btJogar = new ButtonActor(300, 300, 5, "Jogar");
        btJogar.setFocused(true);
        btJogar.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogar();
            }
        });
        buttons.add(btJogar);

        // BT SAIR
        ButtonActor btSair = new ButtonActor(300, 400, 6, "Sair");
        btSair.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
        buttons.add(btSair);
    }

    @Override
    public void update() {
        logo.act(null, null);
        for (ButtonActor button : buttons) {
            button.act(null, null);
        }
        if (InputManager.getInstance().isJustPressed(KeyEvent.VK_DOWN)) {
            downButton();
        } else if (InputManager.getInstance().isJustPressed(KeyEvent.VK_UP)) {
            upButton();
        } else if (InputManager.getInstance().isJustPressed(KeyEvent.VK_ENTER)) {
            enterButton();
        }
    }

    private void downButton() {
        boolean found = false;
        boolean set = false;
        for (ButtonActor button : buttons) {
            if (found) {
                button.setFocused(true);
                set = true;
                break;
            } else if (button.isFocused()) {
                button.setFocused(false);
                found = true;
            }
        }
        if (!set && buttons.size() >= 1) {
            buttons.get(0).setFocused(true);
        }
    }

    private void upButton() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isFocused()) {
                buttons.get(i).setFocused(false);
                if (i > 0) {
                    buttons.get(i - 1).setFocused(true);
                } else {
                    buttons.get(buttons.size() - 1).setFocused(true);
                }
            }
        }

    }

    private void enterButton() {
        for (ButtonActor button : buttons) {
            if (button.isFocused()) {
                button.ActionPerformed();
                break;
            }
        }
    }

    public void jogar() {
        //BGM.stop();
        parent.changeScene(new MatchScene(tela));
    }

    public void sair() {
        System.exit(0);
    }

    @Override
    public void render() {
        LinkedList<Renderable> sprites = new LinkedList<Renderable>();
        sprites.add(pane);
        sprites.add(logo);
        for (ButtonActor button : buttons) {
            sprites.add(button);
        }
        tela.render(sprites);

    }

}