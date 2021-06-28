package hmmproject;

import jm.music.data.Note;
import jm.util.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class GenerateMusicAction extends HMMAbstractAction {

    public GenerateMusicAction(HMMMusicFrame applet, HMMManager manager) {
        super(applet, manager);
    }

//  public GenerateMusicAction(HMMMusicFrame frame,HMMManager manager) {
//   super(frame,manager);
// }


    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Playing some new music now!!! Tell me what you think of it. Well did it sound good to you? ");
        JCheckBox pitch = applet.getJCheckbox1();
        JCheckBox rhy = applet.getJCheckbox2();
        JCheckBox dyn = applet.getJCheckbox3();
//System.out.println("pit " + pitch.isSelected());
//    System.out.println("rhy " + rhy.getText());
        boolean considerPitch = pitch.isSelected();
        boolean considerRhy = rhy.isSelected();
        boolean considerDyn = dyn.isSelected();

        int numberOfNotes = Integer.parseInt(applet.jTextField1.getText());
        int markovDepth = Integer.parseInt(applet.jTextField3.getText());
        HashMap map = null;
        HashMap map2 = null;
        try {
            map = InterfaceUtil.getConstantsNamesMapInt("jm.constants.Instruments");
            map2 = InterfaceUtil.getConstantsNamesMapDouble("jm.constants.Durations");
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String selectedItem1 = (String) applet.jComboBox1.getSelectedItem();
        String selectedItem2 = (String) applet.jComboBox2.getSelectedItem();
        System.out.println(selectedItem1);
        System.out.println(selectedItem2);
        Integer instr = (Integer) map.get(selectedItem1);
        Double dur = (Double) map2.get(selectedItem2);
        //ButtonGroup group = applet.getHmmAlgorithms();
        //ButtonModel model = group.getSelection();
        //String actionName = model.getActionCommand();
        //System.out.println("Action Name " + actionName);
        manager.generateNewPhrase(numberOfNotes, markovDepth, instr.intValue(), dur.doubleValue(), HMMAlgorithm.KMEANS);
        StringBuffer buffer = new StringBuffer();
        Note[] noteArr = manager.getGeneratedPhrase().getNoteArray();
        for (int i = 0; i < noteArr.length; i++) {
            buffer.append(noteArr[i].toString() + "\n");
        }
        //applet.getJTextArea1().setText(buffer.toString());

        // Create an instance of the applet class.
        //JApplet applet = new AppletClass();

        // Send the applet an init() message.
        //applet.init();

        // Construct a JFrame.

        //final JFrame frame = new JFrame("FrameTitle");
        // Transfer the applet's context pane to the JFrame.
        //frame.setContentPane(applet.getContentPane());
        //PhraseViewer pv = new PhraseViewer(frame);
        //pv.showPhrase(new GrandStave(),manager.getGeneratedPhrase(),50,50);
        View.notate(manager.getGeneratedPhrase());
        manager.playGeneratedPhrase();
    }
}