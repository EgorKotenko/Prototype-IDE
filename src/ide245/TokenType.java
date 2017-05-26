package ide245;

/**
 * @author КК
 */

public enum TokenType {

    OPERATOR, // Language operators
    DELIMITER, // Delimiters.  Constructs that are not necessarily operators for a language
    KEYWORD, // language reserved keywords
    KEYWORD2, // Other language reserved keywords, like C #defines
    IDENTIFIER, // identifiers, variable names, class names
    NUMBER,     // numbers in various formats
    STRING,     // String
    STRING2,    // For highlighting meta chars within a String
    COMMENT,    // comments
    COMMENT2,   // special stuff within comments
    REGEX,      // regular expressions
    REGEX2,     // special chars within regular expressions
    TYPE,       // Types
    DEFAULT;    // any other text

    public static boolean isComment(Token t) {
        if (t != null && (t.type == COMMENT || t.type == COMMENT2)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isKeyword(Token t) {
        if (t != null && (t.type == KEYWORD || t.type == KEYWORD2)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isString(Token t) {
        if (t != null && (t.type == STRING || t.type == STRING2)) {
            return true;
        } else {
            return false;
        }
    }
}
