package hmmproject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class HMMMusicFrame extends JFrame {
    public static final String MIDI_FILE_SRC_FOLDER = "./midi";
    JList jList1 = new JList();
    JButton jButton1 = new JButton();
    JLabel jLabel1 = new JLabel();
    JButton jButton2 = new JButton();
    JRadioButton jRadioButton4 = new JRadioButton();
    JRadioButton jRadioButton5 = new JRadioButton();
    JLabel jLabel2 = new JLabel();
    JRadioButton jRadioButton6 = new JRadioButton();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JButton jButton3 = new JButton();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel8 = new JLabel();
    private ButtonGroup hmmAlgorithms = new ButtonGroup();
    private ButtonGroup compositionOptions = new ButtonGroup();
    private ButtonGroup outputType = new ButtonGroup();
    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButton4 = new JButton();
    JLabel jLabel9 = new JLabel();
    JCheckBox jCheckBox1 = new JCheckBox();
    JCheckBox jCheckBox2 = new JCheckBox();
    JCheckBox jCheckBox3 = new JCheckBox();
    //ImageIcon musicNote = new ImageIcon("musicnote.jpg");
    //JLabel jLabel10 = new JLabel(musicNote);
    JTextField jTextField3 = new JTextField();
    JLabel jLabel11 = new JLabel();
    JLabel jLabel12 = new JLabel();
    private HMMManager manager;
    JLabel jLabel13 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel14 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();
    JLabel jLabel5 = new JLabel();

    //Construct the applet
    public HMMMusicFrame() {
    }

    //Initialize the applet
    public void init() {
        try {
            manager = HMMManager.getInstance();
            jbInit();
            createButtonGroups();
            addActions();
            hideComponents();
            fillComboBoxes();
            initJList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Component initialization
    private void jbInit() throws Exception {
        Container c = this.getContentPane();
        c.setLayout(null);
        jButton1.setBounds(new Rectangle(22, 225, 124, 27));
        jButton1.setText("Add MIDI Files");
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14));
        jLabel1.setText("MIDI File Input");
        jLabel1.setBounds(new Rectangle(22, 67, 197, 13));
        jButton2.setBounds(new Rectangle(12, 397, 159, 24));
        jButton2.setText("Generate Melody");
        jRadioButton4.setText("Viterbi");
        jRadioButton4.setBounds(new Rectangle(185, 294, 87, 23));
        jRadioButton5.setText("Forward-Backward");
        jRadioButton5.setBounds(new Rectangle(185, 314, 137, 23));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14));
        jLabel2.setText("HMM Algorithms");
        jLabel2.setBounds(new Rectangle(186, 260, 143, 35));
        jRadioButton6.setText("Baum-Welch");
        jRadioButton6.setBounds(new Rectangle(185, 335, 100, 23));
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16));
        jLabel3.setForeground(Color.blue);
        jLabel3.setText("MUSIC COMPOSITION SYSTEM: Hidden Markov Model ");
        jLabel3.setBounds(new Rectangle(109, 9, 446, 17));
        jLabel4.setText("# of Notes to Add");
        jLabel4.setBounds(new Rectangle(18, 345, 109, 16));
        jButton3.setBounds(new Rectangle(189, 226, 97, 27));
        jButton3.setText("Train HMM");
        jTextField1.setText("");
        jTextField1.setBounds(new Rectangle(116, 345, 49, 18));
        this.setBackground(UIManager.getColor("scrollbar"));
        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14));
        jLabel8.setText("Music Generation");
        jLabel8.setBounds(new Rectangle(19, 257, 180, 23));
        //jLabel9.setText("");
        jScrollPane1.setBounds(new Rectangle(15, 85, 344, 104));
        jButton4.setBounds(new Rectangle(383, 87, 178, 21));
        jButton4.setText("Play/Show Longest Melody");
        jLabel9.setText("Number of Files:");
        jLabel9.setBounds(new Rectangle(18, 199, 103, 19));
        jCheckBox1.setText("Consider Pitches?");
        jCheckBox1.setBounds(new Rectangle(18, 283, 132, 15));
        jCheckBox2.setBounds(new Rectangle(18, 304, 144, 15));
        jCheckBox2.setText("Consider Rhythyms?");
        jCheckBox3.setText("Consider Dynamics?");
        jCheckBox3.setBounds(new Rectangle(18, 324, 145, 15));
        //jLabel10.setText("jLabel10");
        //jLabel10.setBounds(new Rectangle(543, 14, 114, 107));
        jTextField3.setText("");
        jTextField3.setBounds(new Rectangle(187, 365, 49, 18));
        jLabel11.setText("Number of States / Markov Depth");
        jLabel11.setBounds(new Rectangle(18, 363, 167, 22));
        jLabel12.setText("Training Complete!!!");
        jLabel12.setBounds(new Rectangle(295, 230, 118, 26));
        jLabel13.setText("0");
        jLabel13.setBounds(new Rectangle(133, 198, 57, 19));
        jComboBox1.setBounds(new Rectangle(319, 271, 141, 20));
        jLabel14.setText("Instrument");
        jLabel14.setBounds(new Rectangle(471, 266, 98, 23));
        jComboBox2.setBounds(new Rectangle(329, 313, 140, 25));
        jLabel5.setText("Duration");
        jLabel5.setBounds(new Rectangle(483, 317, 67, 18));
        c.add(jLabel3, null);
        c.add(jLabel1, null);
        c.add(jButton1, null);
        c.add(jLabel8, null);
        c.add(jButton3, null);
        c.add(jScrollPane1, null);
        c.add(jButton4, null);
        c.add(jLabel9, null);
        //this.add(jLabel10, null);
        c.add(jLabel12, null);
        c.add(jCheckBox1, null);
        c.add(jCheckBox2, null);
        c.add(jCheckBox3, null);
        c.add(jLabel4, null);
        c.add(jTextField1, null);
        c.add(jLabel2, null);
        c.add(jRadioButton4, null);
        c.add(jRadioButton5, null);
        c.add(jRadioButton6, null);
        c.add(jLabel11, null);
        c.add(jComboBox1, null);
        c.add(jButton2, null);
        c.add(jLabel13, null);
        c.add(jLabel14, null);
        c.add(jComboBox2, null);
        c.add(jLabel5, null);
        c.add(jTextField3, null);
        jScrollPane1.getViewport().add(jList1, null);
    }

    //Get Applet information
    public String getAppletInfo() {
        return "Applet Information";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        return null;
    }

    private void createButtonGroups() {

        hmmAlgorithms.add(jRadioButton4);
        hmmAlgorithms.add(jRadioButton5);
        hmmAlgorithms.add(jRadioButton6);

        //compositionOptions.add(jRadioButton1);
        //compositionOptions.add(jRadioButton2);

        //outputType.add(jRadioButton7);
        //outputType.add(jRadioButton8);
    }

    private void addActions() {
        jButton1.addActionListener(new AddMIDIFilesAction(this, manager));
        jButton3.addActionListener(new TrainHMMAction(this, manager));
        jButton2.addActionListener(new GenerateMusicAction(this, manager));
        jButton4.addActionListener(new PlaySelectedFileAction(this, manager));
    }

    private void hideComponents() {
        jLabel12.show(false);
    }


    private void initJList() {
        ArrayList ar_list = new ArrayList();
        String[] strArr = manager.getMidiFilesStr(ar_list, MIDI_FILE_SRC_FOLDER);
        jList1.setListData(strArr);
    }

    private void fillComboBoxes() throws IllegalArgumentException,
            IllegalAccessException, ClassNotFoundException {
        //add instruemtns
        HashMap map = InterfaceUtil.getConstantsNamesMapInt("jm.constants.Instruments");
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {
            jComboBox1.addItem(iter.next());
        }

        //add durations
        HashMap map2 = InterfaceUtil.getConstantsNamesMapDouble("jm.constants.Durations");
        Iterator iter2 = map2.keySet().iterator();
        while (iter2.hasNext()) {
            jComboBox2.addItem(iter2.next());
        }
    }

    public JList getJList1() {
        return jList1;
    }

    public void setJList1(JList jList1) {
        this.jList1 = jList1;
    }

    public ButtonGroup getCompositionOptions() {
        return compositionOptions;
    }

    public void setCompositionOptions(ButtonGroup compositionOptions) {
        this.compositionOptions = compositionOptions;
    }

    public void setHmmAlgorithms(ButtonGroup hmmAlgorithms) {
        this.hmmAlgorithms = hmmAlgorithms;
    }

    public ButtonGroup getHmmAlgorithms() {
        return hmmAlgorithms;
    }

    public JButton getJButton1() {
        return jButton1;
    }

    public void setJButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }

    public void setJButton2(JButton jButton2) {
        this.jButton2 = jButton2;
    }

    public JButton getJButton2() {
        return jButton2;
    }

    public JButton getJButton3() {
        return jButton3;
    }

    public void setJButton3(JButton jButton3) {
        this.jButton3 = jButton3;
    }

    //  public JTextArea getJTextArea1() {
//    return jTextArea1;
//  }
//  public void setJTextArea1(JTextArea jTextArea1) {
//    this.jTextArea1 = jTextArea1;
//  }
    public void setJTextField1(JTextField jTextField1) {
        this.jTextField1 = jTextField1;
    }

    public JTextField getJTextField1() {
        return jTextField1;
    }
//  public JTextField getJTextField2() {
//    return jTextField2;
//  }
//  public void setJTextField2(JTextField jTextField2) {
//    this.jTextField2 = jTextField2;
//  }

    public JLabel getJLabel12() {
        return jLabel12;
    }

    public void setJLabel12(JLabel jLabel12) {
        this.jLabel12 = jLabel12;
    }

    public JLabel getJLabel9() {
        return jLabel9;
    }

    public void setJLabel9(JLabel jLabel9) {
        this.jLabel9 = jLabel9;
    }

    public JLabel getJLabel13() {
        return jLabel13;
    }

    public void setJLabel13Text(String jLabel13) {
        this.jLabel13.setText(jLabel13);
    }

    public JCheckBox getJCheckbox1() {
        return jCheckBox1;
    }

    public void setJCheckBox1(JCheckBox jCheckBox1) {
        this.jCheckBox1 = jCheckBox1;
    }

    public JCheckBox getJCheckbox2() {
        return jCheckBox2;
    }

    public void setJCheckBox2(JCheckBox jCheckBox2) {
        this.jCheckBox2 = jCheckBox2;
    }


    public JCheckBox getJCheckbox3() {
        return jCheckBox3;
    }

    public void setJCheckBox3(JCheckBox jCheckBox3) {
        this.jCheckBox3 = jCheckBox3;
    }

    public static void main(String[] args) {
        HMMMusicFrame hmmMusicFrame = new HMMMusicFrame();
        hmmMusicFrame.init();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;

        hmmMusicFrame.setPreferredSize(new Dimension(width, height));

        hmmMusicFrame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 700), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 250));
        hmmMusicFrame.setMaximizedBounds(new Rectangle(0, 0, 500, 500));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        hmmMusicFrame.setLocation(dim.width / 2 - hmmMusicFrame.getSize().width / 2, dim.height / 2 - hmmMusicFrame.getSize().height / 2);
        hmmMusicFrame.setVisible(true);

        hmmMusicFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}