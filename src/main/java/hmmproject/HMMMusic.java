package hmmproject;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.OpdfIntegerFactory;
import be.ac.ulg.montefiore.run.jahmm.draw.HmmIntegerDrawer;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;
import be.ac.ulg.montefiore.run.jahmm.toolbox.MarkovGenerator;
import jm.JMC;
import jm.music.data.Note;
import jm.music.data.Phrase;

import java.io.IOException;
import java.util.Vector;

public class HMMMusic implements JMC {

    /**
     * The depth of the markov array (ie The number of prior states to use in
     * this matrix).
     */
    private int states;

    /**
     * markov model for the pitches
     */
    private Hmm pitches;

    private Hmm rhythms;

    private Hmm dynamics;

    private Vector pitchseqSet = new Vector();

    private Vector rhythmseqSet = new Vector();

    private Vector dynamicseqSet = new Vector();

    private RhythmMapper mapper = new RhythmMapper();

    private HMMAlgorithm algorithm;

    public HMMMusic(Vector phrases, int states, HMMAlgorithm alg) {
        this.states = states;
        algorithm = alg;
        pitches = new Hmm(states);
        rhythms = new Hmm(states);
        dynamics = new Hmm(states);
        for (int i = 0; i < phrases.size(); i++) {
            Phrase phr = (Phrase) phrases.elementAt(i);
            addPhrase(phr);
        }
    }

    /**
     * writes a .dot file which describes an HMM
     *
     * @param learntHmm
     * @param filename  a dot file ex: learnt11Hmm.dot
     * @throws IOException
     */
    public void drawHMM(Hmm learntHmm, String filename) throws IOException {
        (new HmmIntegerDrawer()).write(learntHmm, filename);
    }

    /**
     * helper method to adds a phrase to the sequence sets
     */
    private void addPhrase(Phrase phrase) {
        int[] p = phrase.getPitchArray();
        Vector pitchsequences = new Vector();
        for (int i = 0; i < p.length; i++) {
            ObservationInteger oi = new ObservationInteger(p[i]);
            pitchsequences.add(oi);
        }
        pitchseqSet.add(pitchsequences);


        double[] r = phrase.getRhythmArray();
        Vector rhythmsequences = new Vector();
        for (int i = 0; i < r.length; i++) {
            ObservationInteger ov = new ObservationInteger(mapper.getIntValue(r[i]));
            rhythmsequences.add(ov);
        }
        rhythmseqSet.add(rhythmsequences);

        Vector dynamicsequences = new Vector();
        int[] d = phrase.getDynamicArray();
        for (int i = 0; i < d.length; i++) {
            ObservationInteger oi = new ObservationInteger(d[i]);
            dynamicsequences.add(oi);
        }
        dynamicseqSet.add(dynamicsequences);

    }

    private int[] getPitchesArray(int numberOfNotes) {
// try {
// pitches = algorithm.getHMM(pitches, 127, pitchseqSet); //.learn();
// }
// catch (Exception ex1) {
// }

        KMeansLearner learner = new KMeansLearner(states,
                new OpdfIntegerFactory(127),
                pitchseqSet);
        pitches = learner.learn();

        BaumWelchLearner learner2 = new BaumWelchLearner(states,
                new OpdfIntegerFactory(127), pitchseqSet);
        pitches = learner2.learn(pitches);

        try {
            drawHMM(pitches, "baumwelch_pitches.dot");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        MarkovGenerator generator = new MarkovGenerator(pitches);

        Vector vec = null;
        try {
            vec = generator.observationSequence(numberOfNotes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int[] p2 = new int[vec.size()];

        for (int i = 0; i < vec.size(); i++) {
            ObservationInteger obInt = (ObservationInteger) vec.elementAt(i);
            p2[i] = obInt.value;
        }
        return p2;

    }

    /**
     * uses the rhythm mapper
     *
     * @param numberOfNotes
     * @return
     */
    private double[] getRhythmsArray(int numberOfNotes) {

// try {
// rhythms = algorithm.getHMM(rhythms, mapper.greatestInt(), rhythmseqSet);
// //.learn();
// }
// catch (Exception ex1) {
// }

        KMeansLearner learner = new KMeansLearner(states, new OpdfIntegerFactory(mapper.greatestInt()), rhythmseqSet);
        rhythms = learner.learn();

        BaumWelchLearner learner2 = new BaumWelchLearner(states,
                new OpdfIntegerFactory(mapper.greatestInt()), rhythmseqSet);
        rhythms = learner2.learn(rhythms);

        try {
            drawHMM(rhythms, "baumwelch_rhythms.dot");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        MarkovGenerator generator = new MarkovGenerator(rhythms);

        Vector vec = null;
        try {
            vec = generator.observationSequence(numberOfNotes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        double[] r = new double[vec.size()];

        for (int i = 0; i < vec.size(); i++) {
            ObservationInteger obInt = (ObservationInteger) vec.elementAt(i);
            r[i] = mapper.getDoubleValue(obInt.value);
        }
        return r;

    }

    private int[] getDynamicsArray(int numberOfNotes) {

// try {
// dynamics = algorithm.getHMM(dynamics, 120, dynamicseqSet); //.learn();
// }
// catch (Exception ex1) {
// }
//
// if(dynamics == null){
// System.out.println("something is NULL");
// }

        KMeansLearner learner = new KMeansLearner(states, new OpdfIntegerFactory(120),
                dynamicseqSet);
        dynamics = learner.learn();

        BaumWelchLearner learner2 = new BaumWelchLearner(states,
                new OpdfIntegerFactory(120), dynamicseqSet);
        dynamics = learner2.learn(dynamics);

        try {
            drawHMM(dynamics, "baumwelch_dynamics.dot");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        MarkovGenerator generator = new MarkovGenerator(dynamics);


        Vector vec = null;
        try {
            vec = generator.observationSequence(numberOfNotes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int[] d = new int[vec.size()];

        for (int i = 0; i < vec.size(); i++) {
            ObservationInteger obInt = (ObservationInteger) vec.elementAt(i);
            d[i] = obInt.value;
        }
        return d;

    }

    public Phrase generate(int numberOfNotes) {
        int[] d = getDynamicsArray(numberOfNotes);
        double[] r = getRhythmsArray(numberOfNotes);
        int[] p = getPitchesArray(numberOfNotes);

        Phrase phrase = new Phrase();
        phrase.addNoteList(p, r, d);
        return phrase;
    }

    /**
     * returns the phrase in string format like (A4,C)-(D4,H)-(C4,C)..etc.
     *
     * @param phr
     * @return
     */
    public static String phraseString(Phrase phr) {
        Note[] noteArray = phr.getNoteArray();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < noteArray.length; i++) {
            Note note = noteArray[i];
            // pitch
            String ps = NoteHelper.getPitchString(note.getPitch());
            // duration
            String dur = NoteHelper.getDurationString(note.getRhythmValue());
            buffer.append("(" + ps + "," + dur + ")");
        }
        return buffer.toString();
    }

    public static Phrase getMarysLamb() {
        Phrase marysLamb = new Phrase();
        int[] p = {
                64, 62, 60, 62, 64, 64, 64, 62, 62, 62, 64, 67, 67, 64, 62, 60, 62, 64,
                64, 64, 64, 62, 62, 64, 62, 60};
        double[] r = {
                C, C, C, C, C, C, M, C, C, M, C, C, M, C, C, C, C, C, C, C, C, C, C, C,
                C, M};
        int[] d = {
                80, 70, 60, 70, 80, 80, 80, 70, 70, 70, 80, 100, 100, 80, 70, 60, 70,
                80, 80, 80, 80, 70, 70, 80, 70, 60};
        marysLamb.addNoteList(p, r, d);
        return marysLamb;
    }


    public static void main(String[] argv) throws java.io.IOException {
        Vector v = new Vector();
        HMMMusic hmmMus = new HMMMusic(v, 2, HMMAlgorithm.BAUM_WELCH);
        Phrase p = getMarysLamb();
        System.out.println(hmmMus.phraseString(p));

//  	Vector v = new Vector();
//    v.add(p);
//  	
//    for(int i=1;i<10;i++){
//      System.out.println("Starting new song");
//      // MIDIFileReader reader = new MIDIFileReader("H:/hmmproject/midi");
//      // Vector songs = reader.getPhrases();
//      HMMMusic hmmMus = new HMMMusic(v, i, HMMAlgorithm.BAUM_WELCH);
//      Phrase phr = hmmMus.generate(20);
//    System.out.println(hmmMus.phraseString(phr));
//   // Write.midi(phr,"generated_marylamb_state"+i+"baumwelch.mid");
//
//    }


// System.out.println("STARTING BAUM WELCH NOW!");
//
// HMMMusic hmmMus2 = new HMMMusic(v, 3, HMMAlgorithm.BAUM_WELCH);
// Phrase phr2 = hmmMus.generate(50);
// System.out.println(phr2);
// Play.midi(phr2,false);

    }

}