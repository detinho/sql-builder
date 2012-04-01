package br.com.detinho.sqlbuilder;

public final class StringUtils {

    private StringUtils() {}
    
    public static String nullToStr(String str) {
        return str == null ? "" : str;
    }
    
}
