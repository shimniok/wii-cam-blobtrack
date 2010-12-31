/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wiicamblobtrack;

import java.awt.Point;
import java.io.IOException;

/**
 *
 * @author mes
 */
public class BlobUpdater extends Thread {

    private TrackPanel panel = null;
    private SerialComm serial = null;
    private WiiCamComm wii = null;

    public BlobUpdater(TrackPanel panel, SerialComm serial) {
        this.panel = panel;
        this.serial = serial;
        wii = new WiiCamComm();
    }

    public void run() {
        Point[] p;
        Blob[] b;

        System.out.println("BlobUpdater.run() -- enter");

        while (true) {
            try {
                String line = serial.getLine();

                // if for some reason we get a bogus line, skip it
                if (line == null)
                    continue;

                p = wii.getCoordinates(line);

                // if we get bogus array of points or a short array,
                // then skip this iteration
                if (p == null || p.length < 4)
                    continue;

                b = panel.getBlobs();

                // Expect 4 coordinate pairs
                for (int i=0; i < 4; i++) {
                    if (p[i].x == 1023 && p[i].y == 1023) {
                        b[i].setVisible(false); // set blob invisible
                    } else {
                        b[i].setVisible(true);
                        b[i].setXY(p[i].x, p[i].y);
                    }
                }
                panel.repaint();

            } catch (IOException e) {
                // TODO: BlobUpdater error, how to communicate back?
                // JOptionPane.showMessageDialog(mainFrame, "Error reading from serial port: " + e, "Error",
                // JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
