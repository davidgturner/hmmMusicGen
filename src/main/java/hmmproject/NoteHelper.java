package hmmproject;

import jm.JMC;
import jm.constants.Durations;
import jm.constants.Pitches;
import jm.music.data.Note;

import java.lang.reflect.Field;
import java.util.Vector;


public class NoteHelper {

	public static Vector getAllNotesAsVector() {
		Vector vec = new Vector();
		String[] notes = getAllNotes();
		for (int i = 0; i < notes.length; i++) {
			vec.add(notes[i]);
		}
		return vec;
	}

	/**
	 * gets all 12 different notes in an octave
	 */
	public static String[] getAllNotes() {
		String[] octave = new String[12];
		octave[0] = Note.C;
		octave[1] = Note.G;
		octave[2] = Note.D;
		octave[3] = Note.A;
		octave[4] = Note.E;
		octave[5] = Note.B;
		octave[6] = Note.F_SHARP;
		octave[7] = Note.C_SHARP;
		octave[8] = Note.A_FLAT;
		octave[9] = Note.E_FLAT;
		octave[10] = Note.B_FLAT;
		octave[11] = Note.F;
		return octave;
	}

	/**
	 * given a pitch (MIDI pitch) from the Pitch interface in
	 * jMusic it gives back a string representation of the pitch
	 */
	public static String getPitchString(int pitch) {
		Class scaleClass = Pitches.class;
		Field[] fields = scaleClass.getFields();
		//String pitchStr = "";
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int constant = 0;
			try {
				constant = field.getInt(new Integer(pitch));
				//field is not null, equals the pitch, and first character is uppercase
				if (field != null
						&& constant == pitch
						&& Character.isUpperCase(field.getName().charAt(0))) {

					String constantField = field.getName();

					if (constantField.equals("REST")) {
						return constantField;
					}

					//enforce the conditions that the only notes that can have sharps and flats are F,C,E,A, and B
					boolean noteHasSharp = hasSharp(constantField);
					boolean noteHasFlat = hasFlat(constantField);
					boolean startsWithFOrC =
							constantField.startsWith("F") || constantField.startsWith("C");
					boolean startsWithEOrAOrB =
							constantField.startsWith("E")
									|| constantField.startsWith("A")
									|| constantField.startsWith("B");

					//only f and c should be sharped.
					if (noteHasSharp && !startsWithFOrC) {
						continue;
					}

					//only e, b, and a should be flatted.
					if (noteHasFlat && !startsWithEOrAOrB) {
						continue;
					}

					//System.out.println("IT WORKED ********" + field.getName());
					return constantField;
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "this constant doesn't exist";
		//return "";
	}

	private static boolean hasSharp(String pitchStr) {
		if (pitchStr.indexOf('S') != -1)
			return true;

		return false;
	}

	private static boolean hasFlat(String pitchStr) {
		if (pitchStr.indexOf('F', 1) != -1)
			return true;

		return false;
	}

	/**
	 * returns all the aliaes of the given duration constant as a comma delimited string in brackets
	 *
	 * @param duration
	 * @return
	 */
	public static String getDurationString(double duration) {
		Class scaleClass = Durations.class;
		Field[] fields = scaleClass.getFields();
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			double dur = 0.0;
			try {
				dur = field.getDouble(new Double(duration));
				//field is not null, equals the pitch, and first character is uppercase
				if (field != null && dur == duration) {
					String durationField = field.getName();
					buffer.append(durationField + ",");
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//delete last comma
		int lastComma = buffer.lastIndexOf(",");
		if (lastComma != -1)
			buffer.deleteCharAt(lastComma);

		buffer.append("]");
		return buffer.toString();
	}

	/**
	 * given a pitch (MIDI pitch) from the Pitch interface in
	 * jMusic it gives back a Note object
	 */
	public static Note getNote(int pitch) {
		return null;
	}

	public static void main(String[] args) {
		System.out.println(NoteHelper.getPitchString(JMC.REST));
		System.out.println(NoteHelper.getDurationString(JMC.CROTCHET));
		System.out.println(NoteHelper.getNote(JMC.C4));
		Note note = new Note(JMC.BF4, Note.DEFAULT_DURATION);
		System.out.println("Next Note " + note.nextNote(JMC.MAJOR_SCALE).getNote());
	}
}
