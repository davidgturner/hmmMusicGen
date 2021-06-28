package hmmproject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>Title: </p>
 * <p>Description: this class is needed to hack around the difficulty with
 * the Jahmm library not allowing for double sequences</p>
 *
 * @author not attributable
 * @version 1.0
 */

public class RhythmMapper {

    private HashMap doubleToIntegerMap;

    public RhythmMapper() {
        doubleToIntegerMap = new HashMap();
        HashMap map = null; //new HashMap();
        try {
            map = InterfaceUtil.getConstantsNamesMapDouble("jm.constants.Durations");
        } catch (IllegalArgumentException ex) {
        } catch (IllegalAccessException ex) {
        } catch (ClassNotFoundException ex) {
        }
        //InterfaceUtil.print(map);
        init(map);
        //InterfaceUtil.print(doubleToIntegerMap);
    }

    private void init(HashMap doubleMap) {
        int id = 0;
        Set set = doubleMap.keySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            Object element = (Object) iter.next();
            //System.out.println("Key:" + element + " Value:" + doubleMap.get(element));
            if (!doubleToIntegerMap.containsValue(doubleMap.get(element))) {
                doubleToIntegerMap.put(doubleMap.get(element), new Integer(id));
                id++;
            }

        }

    }

    public int greatestInt() {
        int greatest = 0;
        Set set = doubleToIntegerMap.keySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            Object element = (Object) iter.next();
            //System.out.println("Key:" + element + " Value:" + doubleMap.get(element));
            Integer integ = (Integer) doubleToIntegerMap.get(element);
            if (integ.intValue() > greatest)
                greatest = integ.intValue();
        }
        return greatest;

    }


    public int size() {
        return doubleToIntegerMap.size();
    }

    public int getIntValue(double val) {
        Integer integ = (Integer) doubleToIntegerMap.get(new Double(val));
        if (integ == null)
            return 67;
        return integ.intValue();
    }

    public double getDoubleValue(int val) {
        Double doub = null;
        Set set = doubleToIntegerMap.keySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            Object element = (Object) iter.next();
            //System.out.println("Key:" + element + " Value:" + doubleMap.get(element));
            Integer integ = (Integer) doubleToIntegerMap.get(element);
            if (integ.intValue() == val)
                return ((Double) element).doubleValue();
        }
        return -1;
    }


    public static void main(String[] args) {
        RhythmMapper rhythmMapper1 = new RhythmMapper();
        System.out.println("Quarter " + rhythmMapper1.getIntValue(0.25));
        System.out.println("Quarter " + rhythmMapper1.getDoubleValue(78));
        System.out.println("size " + rhythmMapper1.size());
        System.out.println("gresteat " + rhythmMapper1.greatestInt());
    }

}