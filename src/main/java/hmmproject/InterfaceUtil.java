package hmmproject;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class InterfaceUtil {

    /**
     * gets the collection of all constants and their values in an interface
     *
     * @return
     */
    public static HashMap getConstantsNamesMapInt(String interfaceName) throws
            ClassNotFoundException, IllegalAccessException, IllegalArgumentException {
        HashMap map = new HashMap();
        Class class1 = Class.forName(interfaceName);
        Field[] field = class1.getFields();
        for (int i = 0; i < field.length; i++) {
            map.put(field[i].getName(), new Integer((int) field[i].getInt(field[i].getName())));
        }
        return map;
    }

    /**
     * gets the collection of all constants and their values in an interface
     *
     * @return
     */
    public static HashMap getConstantsNamesMapDouble(String interfaceName) throws
            ClassNotFoundException, IllegalAccessException, IllegalArgumentException {
        HashMap map = new HashMap();
        Class class1 = Class.forName(interfaceName);
        Field[] field = class1.getFields();
        for (int i = 0; i < field.length; i++) {
            map.put(field[i].getName(), new Double((double) field[i].getDouble(field[i].getName())));
        }
        return map;
    }


    public static void print(AbstractMap map) {
        Set set = map.keySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            Object element = (Object) iter.next();
            System.out.println("Key:" + element + " Value:" + map.get(element));
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,
            IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        HashMap map = getConstantsNamesMapDouble("jm.constants.Durations");
        print(map);
    }

}