/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wiicamblobtrack;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author mes
 */
public class Blob {

    private int x, y;
    private Image image;
    boolean visible;
    private static int count = 0;

    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 2;

    public Blob(int x, int y) {
        if (count < 4) {
            ImageIcon ii =
                new ImageIcon(this.getClass().getResource("resources/blob"+count+".png"));
            image = ii.getImage();
            visible = true;
            this.x = x;
            this.y = y;
            Blob.count++;
        }
        // else destroy this object?
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean v) {
        visible = v;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
