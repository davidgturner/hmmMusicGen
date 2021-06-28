package hmmproject;

import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public abstract class HMMAbstractAction extends AbstractAction {

    //protected HMMMusicFrame frame;
    protected HMMMusicFrame applet;
    protected HMMManager manager;

    public HMMAbstractAction(HMMMusicFrame applet, HMMManager manager) {
        this.applet = applet;
        this.manager = manager;
    }

//  public HMMAbstractAction(HMMMusicApplet frame, HMMManager manager) {
//   this.frame = frame;
//   this.manager = manager;
// }


    public HMMAbstractAction() {
    }


}