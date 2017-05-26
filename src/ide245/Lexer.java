package ide245;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Segment;

/* The following code was generated by JFlex 1.4.3 on 12.05.12 22:00 */


 

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 12.05.12 22:00 from the specification file
 * <tt>lexer.flex</tt>
 */
public final class Lexer {
    
    public Lexer() {
        
    }
    
    public void parse(Segment segment, int ofst, List<Token> tokens) {
        try {
            CharArrayReader reader = new CharArrayReader(segment.array,
                    segment.offset, segment.count);
            yyreset(reader);
            this.offset = ofst;
            for (Token t = yylex(); t != null; t = yylex()) {
                tokens.add(t);
            }
        } catch (IOException ex) {
            Logger.getLogger(Lexer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;
  public static final int CHARLITERAL = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\7\1\3\1\2\1\0\1\3\1\1\16\7\4\0\1\3\1\54"+
    "\1\14\1\0\1\6\1\54\1\55\1\15\1\42\1\43\1\5\1\57"+
    "\1\50\1\13\1\11\1\4\1\10\11\10\1\50\1\50\1\53\1\51"+
    "\1\52\1\50\1\0\3\6\1\60\1\12\25\6\1\46\1\16\1\47"+
    "\1\54\1\6\1\0\1\22\1\17\1\24\1\33\1\21\1\35\1\41"+
    "\1\40\1\31\1\6\1\23\1\34\1\6\1\27\1\26\2\6\1\20"+
    "\1\25\1\30\1\32\1\36\1\37\3\6\1\44\1\56\1\45\1\50"+
    "\41\7\2\0\4\6\4\0\1\6\2\0\1\7\7\0\1\6\4\0"+
    "\1\6\5\0\27\6\1\0\37\6\1\0\u013f\6\31\0\162\6\4\0"+
    "\14\6\16\0\5\6\11\0\1\6\21\0\130\7\5\0\23\7\12\0"+
    "\1\6\13\0\1\6\1\0\3\6\1\0\1\6\1\0\24\6\1\0"+
    "\54\6\1\0\46\6\1\0\5\6\4\0\202\6\1\0\4\7\3\0"+
    "\105\6\1\0\46\6\2\0\2\6\6\0\20\6\41\0\46\6\2\0"+
    "\1\6\7\0\47\6\11\0\21\7\1\0\27\7\1\0\3\7\1\0"+
    "\1\7\1\0\2\7\1\0\1\7\13\0\33\6\5\0\3\6\15\0"+
    "\4\7\14\0\6\7\13\0\32\6\5\0\13\6\16\7\7\0\12\7"+
    "\4\0\2\6\1\7\143\6\1\0\1\6\10\7\1\0\6\7\2\6"+
    "\2\7\1\0\4\7\2\6\12\7\3\6\2\0\1\6\17\0\1\7"+
    "\1\6\1\7\36\6\33\7\2\0\3\6\60\0\46\6\13\7\1\6"+
    "\u014f\0\3\7\66\6\2\0\1\7\1\6\20\7\2\0\1\6\4\7"+
    "\3\0\12\6\2\7\2\0\12\7\21\0\3\7\1\0\10\6\2\0"+
    "\2\6\2\0\26\6\1\0\7\6\1\0\1\6\3\0\4\6\2\0"+
    "\1\7\1\6\7\7\2\0\2\7\2\0\3\7\11\0\1\7\4\0"+
    "\2\6\1\0\3\6\2\7\2\0\12\7\4\6\15\0\3\7\1\0"+
    "\6\6\4\0\2\6\2\0\26\6\1\0\7\6\1\0\2\6\1\0"+
    "\2\6\1\0\2\6\2\0\1\7\1\0\5\7\4\0\2\7\2\0"+
    "\3\7\13\0\4\6\1\0\1\6\7\0\14\7\3\6\14\0\3\7"+
    "\1\0\11\6\1\0\3\6\1\0\26\6\1\0\7\6\1\0\2\6"+
    "\1\0\5\6\2\0\1\7\1\6\10\7\1\0\3\7\1\0\3\7"+
    "\2\0\1\6\17\0\2\6\2\7\2\0\12\7\1\0\1\6\17\0"+
    "\3\7\1\0\10\6\2\0\2\6\2\0\26\6\1\0\7\6\1\0"+
    "\2\6\1\0\5\6\2\0\1\7\1\6\6\7\3\0\2\7\2\0"+
    "\3\7\10\0\2\7\4\0\2\6\1\0\3\6\4\0\12\7\1\0"+
    "\1\6\20\0\1\7\1\6\1\0\6\6\3\0\3\6\1\0\4\6"+
    "\3\0\2\6\1\0\1\6\1\0\2\6\3\0\2\6\3\0\3\6"+
    "\3\0\10\6\1\0\3\6\4\0\5\7\3\0\3\7\1\0\4\7"+
    "\11\0\1\7\17\0\11\7\11\0\1\6\7\0\3\7\1\0\10\6"+
    "\1\0\3\6\1\0\27\6\1\0\12\6\1\0\5\6\4\0\7\7"+
    "\1\0\3\7\1\0\4\7\7\0\2\7\11\0\2\6\4\0\12\7"+
    "\22\0\2\7\1\0\10\6\1\0\3\6\1\0\27\6\1\0\12\6"+
    "\1\0\5\6\2\0\1\7\1\6\7\7\1\0\3\7\1\0\4\7"+
    "\7\0\2\7\7\0\1\6\1\0\2\6\4\0\12\7\22\0\2\7"+
    "\1\0\10\6\1\0\3\6\1\0\27\6\1\0\20\6\4\0\6\7"+
    "\2\0\3\7\1\0\4\7\11\0\1\7\10\0\2\6\4\0\12\7"+
    "\22\0\2\7\1\0\22\6\3\0\30\6\1\0\11\6\1\0\1\6"+
    "\2\0\7\6\3\0\1\7\4\0\6\7\1\0\1\7\1\0\10\7"+
    "\22\0\2\7\15\0\60\6\1\7\2\6\7\7\4\0\10\6\10\7"+
    "\1\0\12\7\47\0\2\6\1\0\1\6\2\0\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\4\6\1\0\7\6\1\0\3\6\1\0\1\6"+
    "\1\0\1\6\2\0\2\6\1\0\4\6\1\7\2\6\6\7\1\0"+
    "\2\7\1\6\2\0\5\6\1\0\1\6\1\0\6\7\2\0\12\7"+
    "\2\0\2\6\42\0\1\6\27\0\2\7\6\0\12\7\13\0\1\7"+
    "\1\0\1\7\1\0\1\7\4\0\2\7\10\6\1\0\42\6\6\0"+
    "\24\7\1\0\2\7\4\6\4\0\10\7\1\0\44\7\11\0\1\7"+
    "\71\0\42\6\1\0\5\6\1\0\2\6\1\0\7\7\3\0\4\7"+
    "\6\0\12\7\6\0\6\6\4\7\106\0\46\6\12\0\51\6\7\0"+
    "\132\6\5\0\104\6\5\0\122\6\6\0\7\6\1\0\77\6\1\0"+
    "\1\6\1\0\4\6\2\0\7\6\1\0\1\6\1\0\4\6\2\0"+
    "\47\6\1\0\1\6\1\0\4\6\2\0\37\6\1\0\1\6\1\0"+
    "\4\6\2\0\7\6\1\0\1\6\1\0\4\6\2\0\7\6\1\0"+
    "\7\6\1\0\27\6\1\0\37\6\1\0\1\6\1\0\4\6\2\0"+
    "\7\6\1\0\47\6\1\0\23\6\16\0\11\7\56\0\125\6\14\0"+
    "\u026c\6\2\0\10\6\12\0\32\6\5\0\113\6\3\0\3\6\17\0"+
    "\15\6\1\0\4\6\3\7\13\0\22\6\3\7\13\0\22\6\2\7"+
    "\14\0\15\6\1\0\3\6\1\0\2\7\14\0\64\6\40\7\3\0"+
    "\1\6\3\0\2\6\1\7\2\0\12\7\41\0\3\7\2\0\12\7"+
    "\6\0\130\6\10\0\51\6\1\7\126\0\35\6\3\0\14\7\4\0"+
    "\14\7\12\0\12\7\36\6\2\0\5\6\u038b\0\154\6\224\0\234\6"+
    "\4\0\132\6\6\0\26\6\2\0\6\6\2\0\46\6\2\0\6\6"+
    "\2\0\10\6\1\0\1\6\1\0\1\6\1\0\1\6\1\0\37\6"+
    "\2\0\65\6\1\0\7\6\1\0\1\6\3\0\3\6\1\0\7\6"+
    "\3\0\4\6\2\0\6\6\4\0\15\6\5\0\3\6\1\0\7\6"+
    "\17\0\4\7\32\0\5\7\20\0\2\6\23\0\1\6\13\0\4\7"+
    "\6\0\6\7\1\0\1\6\15\0\1\6\40\0\22\6\36\0\15\7"+
    "\4\0\1\7\3\0\6\7\27\0\1\6\4\0\1\6\2\0\12\6"+
    "\1\0\1\6\3\0\5\6\6\0\1\6\1\0\1\6\1\0\1\6"+
    "\1\0\4\6\1\0\3\6\1\0\7\6\3\0\3\6\5\0\5\6"+
    "\26\0\44\6\u0e81\0\3\6\31\0\11\6\6\7\1\0\5\6\2\0"+
    "\5\6\4\0\126\6\2\0\2\7\2\0\3\6\1\0\137\6\5\0"+
    "\50\6\4\0\136\6\21\0\30\6\70\0\20\6\u0200\0\u19b6\6\112\0"+
    "\u51a6\6\132\0\u048d\6\u0773\0\u2ba4\6\u215c\0\u012e\6\2\0\73\6\225\0"+
    "\7\6\14\0\5\6\5\0\1\6\1\7\12\6\1\0\15\6\1\0"+
    "\5\6\1\0\1\6\1\0\2\6\1\0\2\6\1\0\154\6\41\0"+
    "\u016b\6\22\0\100\6\2\0\66\6\50\0\15\6\3\0\20\7\20\0"+
    "\4\7\17\0\2\6\30\0\3\6\31\0\1\6\6\0\5\6\1\0"+
    "\207\6\2\0\1\7\4\0\1\6\13\0\12\7\7\0\32\6\4\0"+
    "\1\6\1\0\32\6\12\0\132\6\3\0\6\6\2\0\6\6\2\0"+
    "\6\6\2\0\3\6\3\0\2\6\3\0\2\6\22\0\3\7\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\3\1\2\2\1\3\1\4\2\2\1\5\1\6"+
    "\14\3\1\7\1\10\1\11\1\12\1\13\1\14\6\2"+
    "\1\15\2\16\1\17\1\1\1\15\1\20\1\0\1\4"+
    "\1\0\1\4\12\3\1\21\3\3\1\2\1\22\2\20"+
    "\2\0\1\4\1\0\10\3\1\23\2\3\1\0\10\3";

  private static int [] zzUnpackAction() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\61\0\142\0\223\0\304\0\365\0\u0126\0\u0157"+
    "\0\u0188\0\u01b9\0\u01ea\0\u021b\0\223\0\223\0\u024c\0\u027d"+
    "\0\u02ae\0\u02df\0\u0310\0\u0341\0\u0372\0\u03a3\0\u03d4\0\u0405"+
    "\0\u0436\0\u0467\0\223\0\223\0\223\0\223\0\223\0\223"+
    "\0\223\0\u0498\0\u04c9\0\u04fa\0\u052b\0\u055c\0\u058d\0\u05be"+
    "\0\223\0\223\0\u05ef\0\u0620\0\u0651\0\u0682\0\u06b3\0\u06e4"+
    "\0\223\0\u0715\0\u0746\0\u0777\0\u07a8\0\u07d9\0\u080a\0\u083b"+
    "\0\u086c\0\u089d\0\u08ce\0\u0188\0\u08ff\0\u0930\0\u0961\0\u0992"+
    "\0\223\0\u09c3\0\223\0\u09f4\0\u0a25\0\u0a56\0\u0a87\0\u0ab8"+
    "\0\u0ae9\0\u0b1a\0\u0b4b\0\u0b7c\0\u0bad\0\u0bde\0\u0c0f\0\u0188"+
    "\0\u0c40\0\u0c71\0\u0ca2\0\u0cd3\0\u0d04\0\u0d35\0\u0d66\0\u0d97"+
    "\0\u0dc8\0\u0df9\0\u0e2a";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\4\1\6\1\7\1\10\1\11\1\4"+
    "\1\12\1\13\1\11\1\14\1\15\1\16\1\4\1\17"+
    "\1\20\1\21\2\11\1\22\1\23\1\11\1\24\1\25"+
    "\1\26\1\11\1\27\1\11\1\30\1\31\1\32\2\11"+
    "\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\10"+
    "\1\42\1\43\1\10\1\44\1\45\1\46\1\11\1\47"+
    "\1\50\1\51\11\47\1\52\1\47\1\53\42\47\1\54"+
    "\1\50\1\51\12\54\1\52\1\53\42\54\63\0\1\4"+
    "\61\0\1\6\61\0\1\55\1\56\43\0\1\41\60\0"+
    "\1\41\15\0\3\11\1\0\1\11\4\0\23\11\16\0"+
    "\1\11\10\0\1\12\1\57\1\60\6\0\1\60\11\0"+
    "\1\61\24\0\1\61\10\0\1\57\63\0\1\41\35\0"+
    "\1\41\15\0\3\11\1\0\1\11\4\0\1\11\1\62"+
    "\5\11\1\63\13\11\16\0\1\11\6\0\3\11\1\0"+
    "\1\11\4\0\2\11\1\64\20\11\16\0\1\11\6\0"+
    "\3\11\1\0\1\11\4\0\15\11\1\65\5\11\16\0"+
    "\1\11\6\0\3\11\1\0\1\11\4\0\3\11\1\65"+
    "\3\11\1\66\11\11\1\67\1\11\16\0\1\11\6\0"+
    "\3\11\1\0\1\11\4\0\11\11\1\70\11\11\16\0"+
    "\1\11\6\0\3\11\1\0\1\11\4\0\13\11\1\71"+
    "\7\11\16\0\1\11\6\0\3\11\1\0\1\11\4\0"+
    "\1\11\1\72\21\11\16\0\1\11\6\0\3\11\1\0"+
    "\1\11\4\0\10\11\1\73\5\11\1\74\4\11\16\0"+
    "\1\11\6\0\3\11\1\0\1\11\4\0\7\11\1\74"+
    "\13\11\16\0\1\11\6\0\3\11\1\0\1\11\4\0"+
    "\3\11\1\21\3\11\1\75\13\11\16\0\1\11\6\0"+
    "\3\11\1\0\1\11\4\0\7\11\1\76\13\11\16\0"+
    "\1\11\6\0\3\11\1\0\1\11\4\0\21\11\1\77"+
    "\1\11\16\0\1\11\51\0\1\41\1\100\57\0\1\41"+
    "\1\0\1\10\56\0\1\41\3\0\1\41\54\0\1\41"+
    "\4\0\1\41\53\0\1\41\5\0\1\41\1\0\1\47"+
    "\2\0\11\47\1\0\1\47\1\0\42\47\2\0\1\51"+
    "\56\0\2\101\1\0\56\101\1\54\2\0\12\54\2\0"+
    "\42\54\1\55\1\102\1\103\56\55\5\104\1\105\53\104"+
    "\10\0\1\57\1\0\1\60\6\0\1\60\11\0\1\61"+
    "\24\0\1\61\10\0\1\106\2\0\1\107\43\0\1\107"+
    "\7\0\3\11\1\0\1\11\4\0\2\11\1\110\20\11"+
    "\16\0\1\11\6\0\3\11\1\0\1\11\4\0\7\11"+
    "\1\111\13\11\16\0\1\11\6\0\3\11\1\0\1\11"+
    "\4\0\3\11\1\111\5\11\1\112\11\11\16\0\1\11"+
    "\6\0\3\11\1\0\1\11\4\0\6\11\1\113\14\11"+
    "\16\0\1\11\6\0\3\11\1\0\1\11\4\0\10\11"+
    "\1\114\12\11\16\0\1\11\6\0\3\11\1\0\1\11"+
    "\4\0\3\11\1\115\17\11\16\0\1\11\6\0\3\11"+
    "\1\0\1\11\4\0\1\11\1\116\21\11\16\0\1\11"+
    "\6\0\3\11\1\0\1\11\4\0\15\11\1\117\5\11"+
    "\16\0\1\11\6\0\3\11\1\0\1\11\4\0\13\11"+
    "\1\113\7\11\16\0\1\11\6\0\3\11\1\0\1\11"+
    "\4\0\11\11\1\120\11\11\16\0\1\11\6\0\3\11"+
    "\1\0\1\11\4\0\1\11\1\74\21\11\16\0\1\11"+
    "\6\0\3\11\1\0\1\11\4\0\12\11\1\121\10\11"+
    "\16\0\1\11\6\0\3\11\1\0\1\11\4\0\12\11"+
    "\1\122\10\11\16\0\1\11\51\0\1\41\1\10\10\0"+
    "\1\103\56\0\5\104\1\123\53\104\4\0\1\103\1\105"+
    "\63\0\1\106\22\0\1\61\24\0\1\61\10\0\1\106"+
    "\56\0\3\11\1\0\1\11\4\0\3\11\1\124\17\11"+
    "\16\0\1\11\6\0\3\11\1\0\1\11\4\0\15\11"+
    "\1\120\5\11\16\0\1\11\6\0\3\11\1\0\1\11"+
    "\4\0\13\11\1\125\7\11\16\0\1\11\6\0\3\11"+
    "\1\0\1\11\4\0\2\11\1\74\20\11\16\0\1\11"+
    "\6\0\3\11\1\0\1\11\4\0\6\11\1\126\2\11"+
    "\1\127\11\11\16\0\1\11\6\0\3\11\1\0\1\11"+
    "\4\0\1\11\1\120\21\11\16\0\1\11\6\0\3\11"+
    "\1\0\1\11\4\0\12\11\1\130\10\11\16\0\1\11"+
    "\6\0\3\11\1\0\1\11\4\0\15\11\1\74\5\11"+
    "\16\0\1\11\6\0\3\11\1\0\1\11\4\0\14\11"+
    "\1\74\6\11\16\0\1\11\6\0\3\11\1\0\1\11"+
    "\4\0\15\11\1\113\5\11\16\0\1\11\4\104\1\103"+
    "\1\123\53\104\6\0\3\11\1\0\1\11\4\0\4\11"+
    "\1\74\16\11\16\0\1\11\6\0\3\11\1\0\1\11"+
    "\4\0\1\11\1\131\21\11\16\0\1\11\6\0\3\11"+
    "\1\0\1\11\4\0\11\11\1\74\11\11\16\0\1\11"+
    "\6\0\3\11\1\0\1\11\4\0\12\11\1\132\10\11"+
    "\16\0\1\11\6\0\3\11\1\0\1\11\4\0\10\11"+
    "\1\133\12\11\16\0\1\11\6\0\3\11\1\0\1\11"+
    "\4\0\10\11\1\74\12\11\16\0\1\11\6\0\3\11"+
    "\1\0\1\11\4\0\10\11\1\72\12\11\16\0\1\11"+
    "\6\0\3\11\1\0\1\11\4\0\22\11\1\120\16\0"+
    "\1\11";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3675];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\10\1\2\11\14\1\7\11\7\1\2\11"+
    "\3\1\1\0\1\1\1\0\1\11\17\1\1\11\1\1"+
    "\1\11\2\0\1\1\1\0\13\1\1\0\10\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

    private Token token(TokenType type) {
        return new Token(type, yychar, yylength());
    }


    private Token token(TokenType type, int pairValue) {
        return new Token(type, yychar, yylength(), (byte)pairValue);
    }


    private static final byte PARAN     = 1;
    private static final byte BRACKET   = 2;
    private static final byte CURLY     = 3;




  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 1760) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 8: 
          { return token(TokenType.OPERATOR, -PARAN);
          }
        case 20: break;
        case 17: 
          { return token(TokenType.KEYWORD);
          }
        case 21: break;
        case 4: 
          { return token(TokenType.NUMBER);
          }
        case 22: break;
        case 2: 
          { return token(TokenType.OPERATOR);
          }
        case 23: break;
        case 9: 
          { return token(TokenType.OPERATOR,  CURLY);
          }
        case 24: break;
        case 10: 
          { return token(TokenType.OPERATOR, -CURLY);
          }
        case 25: break;
        case 13: 
          { tokenLength += yylength();
          }
        case 26: break;
        case 14: 
          { yybegin(YYINITIAL);
          }
        case 27: break;
        case 6: 
          { yybegin(CHARLITERAL); 
                                    tokenStart = yychar; 
                                    tokenLength = 1;
          }
        case 28: break;
        case 19: 
          { return token(TokenType.TYPE);
          }
        case 29: break;
        case 12: 
          { return token(TokenType.OPERATOR, -BRACKET);
          }
        case 30: break;
        case 15: 
          { yybegin(YYINITIAL); 
                                     // length also includes the trailing quote
                                     return new Token(TokenType.STRING, tokenStart, tokenLength + 1);
          }
        case 31: break;
        case 7: 
          { return token(TokenType.OPERATOR,  PARAN);
          }
        case 32: break;
        case 3: 
          { return token(TokenType.IDENTIFIER);
          }
        case 33: break;
        case 18: 
          { tokenLength += 2;
          }
        case 34: break;
        case 11: 
          { return token(TokenType.OPERATOR,  BRACKET);
          }
        case 35: break;
        case 16: 
          { return token(TokenType.COMMENT);
          }
        case 36: break;
        case 5: 
          { yybegin(STRING); 
                                    tokenStart = yychar; 
                                    tokenLength = 1;
          }
        case 37: break;
        case 1: 
          { 
          }
        case 38: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
              {
                return null;
              }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }

    private int tokenStart;
    private int tokenLength;
    private int offset;

}
