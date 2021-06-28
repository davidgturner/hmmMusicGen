package hmmproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class AddMIDIFilesAction extends HMMAbstractAction {

    public AddMIDIFilesAction(HMMMusicFrame applet, HMMManager manager) {
        super(applet, manager);
    }

    /**
     * adds MIDI files to the JList
     *
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        JList list = applet.getJList1();
        //list.setListData(new String[]{"Song 1","Song 2"});
        JDirectoryChooser chooser = new JDirectoryChooser();
        int value = chooser.showDialog(applet);
        String str = "";
        if (chooser.getSelectedFile() != null)
            str = chooser.getSelectedFile().toString();
        //get all the files recursively under a given directory

        System.out.println(str);
        ArrayList ar_list = new ArrayList();
        String[] strArr = manager.getMidiFilesStr(ar_list, str);
        list.setListData(strArr);
        this.applet.setJLabel13Text(strArr.length + "");
    }


}