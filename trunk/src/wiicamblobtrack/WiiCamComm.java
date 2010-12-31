/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wiicamblobtrack;

import java.util.StringTokenizer;
import java.awt.Point;

/**
 *
 * @author mes
 */
public class WiiCamComm {

    public WiiCamComm() {
        // not much to do, here
    }
    
    // We're expecting a comma separated list of 8 tokens, or
    // 4 x,y pairs like: x1,y1,x2,y2,...x4,y4
    // but this could work with any even list of tokens
    //
    public Point[] getCoordinates(String line) {
        Point[] p = null;

        // parse the line into coordinates, store in number array
        StringTokenizer st = new StringTokenizer(line, ",");
        // we need an even list of tokens
        if ((st.countTokens() % 2) == 0 && st.countTokens() >= 8) {
            int max = st.countTokens()/2;
            p = new Point[max];   // then move them into an array of Points
            for (int i=0; i < max; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                p[i] = new Point(x, y);
                //System.out.println("x,y: "+x+","+y);
            }
        }

        // return the coordinates
        return p;
    }

}
