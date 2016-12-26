package test;

/**
 * Created by feder on 23/12/2016.
 */
public class SplitClass {

    public static void main(String[] args) {

        String input = "Mrk334  ;   0;  983";
        String[] st = input.split(";");

        System.out.println(st[2]);

    }

}
