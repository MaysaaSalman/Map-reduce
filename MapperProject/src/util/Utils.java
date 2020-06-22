package util;

import java.text.DecimalFormat;

public class Utils {

    private static final ThreadLocal<Integer> serial = ThreadLocal.withInitial(() -> 1);
    private static DecimalFormat formatter = new DecimalFormat("000");

    public static String getSerial() {
        int current = serial.get();
        serial.set(current + 1);
        return formatter.format(current);
    }
}