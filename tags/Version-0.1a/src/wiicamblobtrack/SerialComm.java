////////////////////////////////////////////////////////////////////////
// SERIAL COMMUNICATIONS
////////////////////////////////////////////////////////////////////////

package wiicamblobtrack;

import gnu.io.*;
import java.io.*;

/**
 *
 * @author Michael Shimniok
 */
public class SerialComm {

    private SerialPort port = null;
    private InputStream in = null;
    private OutputStream out = null;
    private BufferedReader reader = null;
    private String serialPortName = null;

    public SerialComm(String serialPortName) throws IOException {
        try {
            CommPortIdentifier cpi = CommPortIdentifier.getPortIdentifier(serialPortName);

            port = (SerialPort) cpi.open("Serial Port", 3000);
            port.setSerialPortParams(115200, SerialPort.DATABITS_8,
                                            SerialPort.STOPBITS_1,
                                            SerialPort.PARITY_NONE);
            port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            port.disableReceiveThreshold();
            port.disableReceiveTimeout();
            in = port.getInputStream();
            out = port.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            this.serialPortName = serialPortName;
        } catch (Exception e) {
            throw(new IOException(e.getMessage()));
        }
    }

    public String getLine() throws IOException {
        String line;
        line = reader.readLine();
        //if (line != null)
          //  System.out.println("line: "+line);
        return line;
    }

/*
    public static int getByte(InputStream in, int timeout)
        throws IOException {
        int c = -1;
        int times;
        // If nothing available delay until timeout and...
        try {
            for (times = 0; times < 100; times++) {
            if (in.available() > 0) break;
                Thread.sleep(50);
            }
        }
        catch (Exception e) {
            throw new IOException("Internal error with thread sleep() delay");
        }

        // ...try again (or, if we had data already)
        if (in.available() > 0) {
            c = in.read();
        }
        else {
            throw new IOException("Communication timeout");
        }
        return c;
    }*/

}
