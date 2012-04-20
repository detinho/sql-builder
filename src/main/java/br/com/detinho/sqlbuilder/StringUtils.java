package br.com.detinho.sqlbuilder;

import java.util.Collection;

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
    
//    public static String appendAlias(String theAlias) {
//        theAlias = StringUtils.nullToStr(theAlias);
//        if (!theAlias.equals("")) {
//            return " AS " + theAlias;
//        }
//        return theAlias;
//    }
    
    public static String writeSql(Collection<? extends Writable> writables) {
        return writeSql("", writables);
    }
    
    public static String writeSql(String clause, Collection<? extends Writable> writables) {
        String finalSql = "";
        for (Writable writable : writables) {
            finalSql += writable.write() + ", ";
        }
        
        if (!finalSql.equals("")) {
            if ("".equals(clause))
                finalSql = removeTrailingComma(finalSql);
            else
                finalSql = String.format(" %s %s", clause, removeTrailingComma(finalSql));
        }
        return finalSql;        
    }

    
}
