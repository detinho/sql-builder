package br.com.detinho.sqlbuilder;

public final class StringUtils {

    private StringUtils() {}
    
    public static String nullToStr(String str) {
        return str == null ? "" : str;
    }
    
    public static String removeTrailingComma(String string) {
        string = string.trim();
        string = string.substring(0, string.length()-1);
        return string;
    }
    
    public static String appendAlias(String theAlias) {
        theAlias = StringUtils.nullToStr(theAlias);
        if (!theAlias.equals("")) {
            return " AS " + theAlias;
        }
        return theAlias;
    }
    
}
