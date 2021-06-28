package hmmproject;

import jm.music.data.Phrase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class TrainHMMAction extends HMMAbstractAction {

    public TrainHMMAction(HMMMusicFrame applet, HMMManager manager) {
        super(applet, manager);
        // manager = HMMManager.getInstance();
    }

    public Phrase getLongestPhrase() {
        JList list = applet.getJList1();
        String filepath = (String) list.getSelectedValue();
        Object[] values = (Object[]) list.getSelectedValues();
        int greatestLengthIndex = 0;
        Phrase[] phrases = null;
        Phrase input = null;
        ListModel model = list.getModel();
        // .getElementAt(i)
        Object[] values2 = new Object[model.getSize()];

        for (int i = 0; i < values.length; i++) {
            MIDIFileReader midiFileReader = new MIDIFileReader(
                    (String) values[i]);

            Vector v = midiFileReader.getParts();
            for (int k = 0; k < v.size(); k++) {
                phrases = (Phrase[]) midiFileReader.getPhrases(k);

                for (int j = 0; j < phrases.length; j++) {
                    int phraseSize = phrases[j].size();

                    if (greatestLengthIndex < phrases[j].size()) {
                        greatestLengthIndex = j;
                    }
                    System.out.println("Greatest Length Index "
                            + greatestLengthIndex);
                    input = phrases[greatestLengthIndex];
                }
            }
        }

        System.out.println(input);
        return input;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Phrase input = getLongestPhrase();
        manager.filterAndAddPhrase(input);
        // System.out.println("Mega Phrase Size: " +
        // manager.getMegaPhrase().size());
        applet.getJLabel12().show(true);
        // analyzeInputPhrase(input,2);
        // }
        // }
    }

}