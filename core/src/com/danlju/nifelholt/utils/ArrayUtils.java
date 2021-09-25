package com.danlju.nifelholt.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ArrayUtils {

    public static <T> ArrayList<T> merge(Collection<T> a, Collection<T> b) {

        Iterator<T> itA = a.iterator();
        Iterator<T> itB = b.iterator();
        ArrayList<T> result = new ArrayList<T>();

        while (itA.hasNext() || itB.hasNext()) {
            if (itA.hasNext()) result.add(itA.next());
            if (itB.hasNext()) result.add(itB.next());
        }

        return result;
    }
}
