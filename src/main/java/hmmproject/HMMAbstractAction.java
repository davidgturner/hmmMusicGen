package hmmproject;

import javax.swing.*;

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