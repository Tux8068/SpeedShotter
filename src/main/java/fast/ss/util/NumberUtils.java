package fast.ss.util;


import java.time.LocalDate;
import java.util.Random;

public class NumberUtils {

    public static LocalDate GetDate() {

        return java.time.LocalDate.now();
    }

    public static int GetRng() {

        Random r = new Random();

        return r.nextInt(1000000000);
    }
}
