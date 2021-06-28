package hmmproject;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlaySelectedFileAction extends HMMAbstractAction {

    public PlaySelectedFileAction(HMMMusicFrame applet, HMMManager manager) {
        super(applet, manager);
    }

    /**
     * assumes only one file is selected
     *
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        JList list = applet.getJList1();
        String filepath = (String) list.getSelectedValue();
        MIDIFileReader midiFileReader = new MIDIFileReader(filepath);
        midiFileReader.play();
    }

}