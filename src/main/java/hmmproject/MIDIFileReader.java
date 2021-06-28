package hmmproject;

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.Read;

import java.util.Vector;

/*
 * Reads MIDI files as Score and tells information about it.
 *
 * Ex:
 * -extracts the motifs used in the song
 * -extracts key signature
 * -extract scales used, chords used
 * -number of tracks
 *
 */
public class MIDIFileReader {

        private Score theScore;
        //private Score newScore = new Score("Combination Score");

        public MIDIFileReader(String scoreName, String filepath) {
                theScore = new Score(scoreName);
                Read.midi(theScore, filepath);

        }

        public MIDIFileReader(String filepath) {
                theScore = new Score("temp score");
                Read.midi(theScore, filepath);
                if (theScore == null) {
                        System.out.println("NULL!!!");
                }
                System.out.println(theScore.size());
                // get the part from it
                Part tempPart = theScore.getPart(0);
                System.out.println(tempPart);
                // add it to the new score
                //newScore.addPart(tempPart.copy());

        }

        public int numberOfParts() {
                Part[] parts = theScore.getPartArray();
                return parts.length;
        }

        public Vector getParts() {
                return theScore.getPartList();
        }

        public void play() {
                //System.out.println(newScore);
                Play.midi(theScore);
        }

        public void play(Phrase phr) {
                //System.out.println(newScore);
                Play.midi(phr);
        }

        public void play(Part part) {
                //System.out.println(newScore);
                Play.midi(part);
        }

        /**
         * returns a Vector of Phrase arrays
         *
         * @param partIndex
         * @return
         */
        public Vector getPhrases() {
                Vector phraseArrays = new Vector();
                for (int i = 0; i < this.numberOfParts(); i++) {
                        Phrase[] phrases = getPhrases(i);
                        phraseArrays.add(phrases);
                }
//                Part tempPart = theScore.getPart(partIndex);
//                if(tempPart == null){
//                  tempPart = new Part();
//                }
//
//                Phrase[] phrases = tempPart.getPhraseArray();
                return phraseArrays;
        }

        /**
         * gets phrases from all parts
         *
         * @param partIndex
         * @return
         */
        public Phrase[] getPhrases(int partIndex) {
                Part tempPart = theScore.getPart(partIndex);
                if (tempPart == null) {
                        tempPart = new Part();
                }
                Phrase[] phrases = tempPart.getPhraseArray();
                return phrases;
        }

        public Phrase getLongestPhrase() {
                // JList list = applet.getJList1();
                //String filepath = (String)list.getSelectedValue();
                //Object[] values = (Object[])list.getSelectedValues();
                int greatestLengthIndex = 0;
                Phrase[] phrases = null;
                Phrase input = null;
                //ListModel model = list.getModel();
                // .getElementAt(i)
                //Object[] values2 = new Object[model.getSize()];

                //for(int i=0;i<values.length;i++){
                //MIDIFileReader midiFileReader = new MIDIFileReader( (String) values[i]);
                //midiFileReader.play();
                Vector v = getParts();
                for (int k = 0; k < v.size(); k++) {
                        phrases = (Phrase[]) getPhrases(k);

                        for (int j = 0; j < phrases.length; j++) {
                                int phraseSize = phrases[j].size();

                                if (greatestLengthIndex < phrases[j].size()) {
                                        greatestLengthIndex = j;
                                }
                                System.out.println("Greatest Length Index " + greatestLengthIndex);
                                input = phrases[greatestLengthIndex];
                        }
                }
                // }

                System.out.println(input);
                return input;
        }


        //	public int[] getPitches(int track){
//		theScore.getPart()
//	}
//
//	public ArrayList getPitchesList(int track){
//
//	}
//
        public String getPartString(int track) {
                Vector parts = theScore.getPartList();
                Part part = (Part) parts.get(track);
                StringBuffer buffer = new StringBuffer();
                Vector v = part.getPhraseList();
                for (int i = 0; i < v.size(); i++) {
                        Phrase ph = (Phrase) v.get(i);
                        Vector n = ph.getNoteList();
                        for (int j = 0; j < n.size(); j++) {
                                Note note = (Note) n.get(j);
                                buffer.append(note.getPitch() + "-");
                        }
                }
                return buffer.toString();
        }

        /**
         * the file information represented as a string
         */
        public String toString() {
                StringBuffer buffer = new StringBuffer();
                buffer.append("Score Name: " + theScore.getTitle() + "\n");
                buffer.append("Key Signature: " + theScore.getKeySignature() + "\n");
                buffer.append(
                        "Time Signature: "
                                + theScore.getNumerator()
                                + " / "
                                + theScore.getDenominator()
                                + "\n");
                buffer.append("End Time: " + theScore.getEndTime() + "\n");
                buffer.append("Tempo: " + theScore.getTempo() + "\n");

                //print parts, phrases, and notes
                Part[] parts = theScore.getPartArray();
                buffer.append("Number of Parts: " + parts.length + "\n");
                for (int i = 0; i < parts.length; i++) {
                        buffer.append("Part " + i + ": " + "\n");
                        Part part = parts[i];
                        Phrase[] phrases = part.getPhraseArray();
                        buffer.append(
                                "\t Number of Phrases in Part " + i + ": " + phrases.length + "\n");
                        for (int j = 0; j < phrases.length; j++) {
                                Phrase phr = phrases[j];
                                Note[] notes = phr.getNoteArray();
                                buffer.append(
                                        "\t Number of Notes in Phrase " + j + ": " + notes.length + "\n");
                                for (int k = 0; k < notes.length; k++) {
                                        Note n = notes[k];
                                        String pitchString = NoteHelper.getPitchString(n.getPitch());
                                        String duration = NoteHelper.getDurationString(n.getDuration());
                                        buffer.append(pitchString + " " + duration + "\n");
                                }
                        }
                }
                return buffer.toString();
        }

        /**
         * file information printed out to the console
         */
        public void print() {
                System.out.println(toString());
        }

        /**
         * Main method where all good Java programs start
         */
        public static void main(String[] args) {
                MIDIFileReader reader = new MIDIFileReader("midi/mozart/requiem/02dies_irae.mid");
//                Vector parts = reader.getParts();
//                System.out.println(parts.size());
//                for (int i = 0; i < parts.size(); i++) {
//                        Part part = (Part)parts.get(i);
//                        System.out.println("Instrument: " + part.getInstrument());
//                }
//                Part part = (Part)parts.get(0);
//                System.out.println("Part String " + reader.getPartString(0));
//                Vector phrs = part.getPhraseList();
                //Phrase phr = (Phrase)phrs.get(0);
//		System.out.println(phrs.size());
                Phrase phr = reader.getLongestPhrase();
                reader.play(phr);
                //reader.print();
                //reader.playMidiFile();
                //Write.xml(reader.getTheScore());

                //					Vector parts = theScore.getPartList();
                //					System.out.println("Number of Parts: " + parts.size());
                //					for (int i = 0; i < parts.size(); i++) {
                //						Part temp = (Part)parts.get(i);
                //						temp.
                //					}
                //		MusicNote note1 = new MusicNote("C");
                //		Scale cMaj = new Scale(note1, Scales.MAJOR_SCALE);
                //		tempPart.add(cMaj.getPhrase());
                //		Play.midi(tempPart);

        }

        /**
         * @return
         */
        public Score getTheScore() {
                return theScore;
        }

        /**
         * @param score
         */
        public void setTheScore(Score score) {
                theScore = score;
        }

}
