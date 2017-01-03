package test;

import Model.Galaxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by feder on 23/12/2016.
 */
public class SplitClass {

    public static void main(String[] args) {

        ArrayList<Galaxy> list = new ArrayList<>();
        list.add(new Galaxy("df", "dojn", 33, 23.300));
        list.add(new Galaxy(null, null, 3, 22.1));

        Collections.sort(list);
        System.out.println(list.get(0).getRelativeDistance());
        System.out.println(list.get(1).getRelativeDistance());


    }

}
