package br.com.detinho;

public final class StringUtils {

    private StringUtils() {}
    
    public static String nullToStr(String str) {
        return str == null ? "" : str;
    }
    
}
