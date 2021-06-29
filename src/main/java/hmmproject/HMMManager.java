package hmmproject;

import jm.music.data.Note;
import jm.music.data.Phrase;
import jm.util.Play;
import jm.util.Write;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * <p>
 * Title: HMMManager.java
 * </p>
 * <p>
 * Description: a class which does the background control logic for hidden
 * markov processing.
 * </p>
 *
 */

public class HMMManager {

    public static final String MIDI_FILES_SRC_FOLDER = "./midi";
    private HMMMusic hmmMus;

    /**
     * the input phrases...the longest phrase from the set of all MIDI files
     */
    private Vector phrases;

    /**
     * generated phrase
     */
    private Phrase generatedPhrase;

    /**
     * static reference to the lone singleton instance
     */
    private static HMMManager instance = null;

    private HMMManager() {
        phrases = new Vector();
        ArrayList list = new ArrayList();
        String[] allFilesStr = getMidiFilesStr(list, MIDI_FILES_SRC_FOLDER);
        for (int i = 0; i < allFilesStr.length; i++) {
            MIDIFileReader reader = new MIDIFileReader(allFilesStr[i]);
            Phrase phr = reader.getLongestPhrase();
            filterAndAddPhrase(phr);
        }
    }

    public static HMMManager getInstance() {
        if (instance == null) {
            instance = new HMMManager();
        }
        return instance;
    }

    /**
     * adds a phrase to the phrases vector
     *
     * @param phr
     */
    public void filterAndAddPhrase(Phrase phr) {
        Phrase filteredPhrase = new Phrase();
        // Note[] notes = megaPhrase.getNoteArray();
        Note[] notes = phr.getNoteArray();
        notes = filterArray(notes);
        // System.out.println(notes);
        filteredPhrase.addNoteList(notes);
        phrases.add(filteredPhrase);
    }

    /**
     * gets all MIDI files under a directory by scanning the subdirectories as
     * well creates an ArrayList of files
     *
     * @param str
     * @return
     */
    public ArrayList getMidiFilesRecurively(ArrayList list, String str) {
        System.out.println("Input string folder " + str);
        File directory = new File(str);
        // String[] files_str = directory.list();
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                getMidiFilesRecurively(list, file.toString());
            } else {
                list.add(file);
            }
        }
        return list;
    }

    public String[] getMidiFilesStr(ArrayList ar_list, String str) {
        ArrayList list = getMidiFilesRecurively(ar_list, str);
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            File file = (File) list.get(i);
            String fileStr = file.toString();
            // list.add(fileStr);
            strArr[i] = fileStr;
        }
        return strArr;
    }

    public void generateNewPhrase(int numberOfNotes, int markovDepth,
                                  int instrument, double duration, HMMAlgorithm alg) {
        hmmMus = new HMMMusic(phrases, markovDepth, alg);
        generatedPhrase = hmmMus.generate(numberOfNotes);
        generatedPhrase.setInstrument(instrument);
    }

    public void playGeneratedPhrase() {
        Play.midi(generatedPhrase, false);
    }

    /**
     * filters the note array to get rid of negative numbers
     *
     * @param arr
     * @return
     */
    public Note[] filterArray(Note[] arr) {
        Vector vec = new Vector();
        // just set a negative value with the previous index value
        // filters pitches
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getPitch() < 0 || arr[i].getRhythmValue() < 0
                    || arr[i].getDynamic() < 0) {
                // do nothing
            } else {
                vec.add(arr[i]);
            }

        }

        Note[] newnotes = new Note[vec.size()];
        for (int i = 0; i < newnotes.length; i++) {
            newnotes[i] = (Note) vec.elementAt(i);
        }

        return newnotes;
    }

    public Phrase getGeneratedPhrase() {
        return generatedPhrase;
    }

    public void setGeneratedPhrase(Phrase generatedPhrase) {
        this.generatedPhrase = generatedPhrase;
    }

    public static void main(String s[]) throws IOException {
        HMMManager manager = HMMManager.getInstance();
        manager.generateNewPhrase(20, 10, Phrase.SYN_STRINGS, Phrase.CROTCHET,
                HMMAlgorithm.KMEANS);
        System.out.println(HMMMusic.phraseString(manager.getGeneratedPhrase()));
        Write.midi(manager.getGeneratedPhrase(), "mozart_baum_20_10.mid");
        manager.playGeneratedPhrase();

    }

}