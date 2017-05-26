
 
%% 


%public
%class Lexer
%final
%unicode
%char
%type Token




%{

    private Token token(TokenType type) {
        return new Token(type, yychar, yylength());
    }


    private Token token(TokenType type, int pairValue) {
        return new Token(type, yychar, yylength(), (byte)pairValue);
    }


    private static final byte PARAN     = 1;
    private static final byte BRACKET   = 2;
    private static final byte CURLY     = 3;


%}


/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]


WhiteSpace = {LineTerminator} | [ \t\f]+


/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} 


TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?


/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*


/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
    
/* floating point literals */
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?


FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+


/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]


%state STRING, CHARLITERAL


%%


<YYINITIAL> {


  /* keywords */
  "break"                        |
  "case"                         |
  "const"                        |
  "continue"                     |
  "do"                           |
  "else"                         |
  "for"                          |
  "if"                           |
  "return"                       |
  "void"                         |
  "while"                        |
  
  "true"                         |
  "false"                        |
  "null"                         { return token(TokenType.KEYWORD); }


  /* types */
  "bool"                         |
  "char"                         |
  "real"                         |
  "int"                          |
  "void"                         |
  "string"                       { return token(TokenType.TYPE); }



  /* operators */


  "("                            { return token(TokenType.OPERATOR,  PARAN); }
  ")"                            { return token(TokenType.OPERATOR, -PARAN); }
  "{"                            { return token(TokenType.OPERATOR,  CURLY); }
  "}"                            { return token(TokenType.OPERATOR, -CURLY); }
  "["                            { return token(TokenType.OPERATOR,  BRACKET); }
  "]"                            { return token(TokenType.OPERATOR, -BRACKET); }
  ";"                            | 
  ","                            | 
  "."                            | 
  "="                            | 
  ">"                            | 
  "<"                            |
  "!"                            | 
  "~"                            | 
  "?"                            | 
  ":"                            | 
  "=="                           | 
  "<="                           | 
  ">="                           | 
  "!="                           | 
  "&&"                           | 
  "||"                           | 
  "++"                           | 
  "--"                           | 
  "+"                            | 
  "-"                            | 
  "*"                            | 
  "/"                            | 
  "&"                            | 
  "|"                            | 
  "^"                            | 
  "%"                            | 
  "<<"                           | 
  ">>"                           | 
  ">>>"                          | 
  "+="                           | 
  "-="                           | 
  "*="                           | 
  "/="                           | 
  "&="                           | 
  "|="                           | 
  "^="                           | 
  "%="                           | 
  "<<="                          | 
  ">>="                          | 
  ">>>="                         { return token(TokenType.OPERATOR); } 
  
  /* string literal */
  \"                             {  
                                    yybegin(STRING); 
                                    tokenStart = yychar; 
                                    tokenLength = 1; 
                                 }


  /* character literal */
  \'                             {  
                                    yybegin(CHARLITERAL); 
                                    tokenStart = yychar; 
                                    tokenLength = 1; 
                                 }


  /* numeric literals */


  {DecIntegerLiteral}            |
  
  {DoubleLiteral}                |
  {DoubleLiteral}[dD]            { return token(TokenType.NUMBER); }
  

  /* comments */
  {Comment}                      { return token(TokenType.COMMENT); }


  /* whitespace */
  {WhiteSpace}                   { }


  /* identifiers */ 
  {Identifier}                   { return token(TokenType.IDENTIFIER); }
}




<STRING> {
  \"                             { 
                                     yybegin(YYINITIAL); 
                                     // length also includes the trailing quote
                                     return new Token(TokenType.STRING, tokenStart, tokenLength + 1);
                                 }
  
  {StringCharacter}+             { tokenLength += yylength(); }

  
  /* escape sequences */


  \\.                            { tokenLength += 2; }
  {LineTerminator}               { yybegin(YYINITIAL);  }
}


<CHARLITERAL> {
  \'                             { 
                                     yybegin(YYINITIAL); 
                                     // length also includes the trailing quote
                                     return new Token(TokenType.STRING, tokenStart, tokenLength + 1);
                                 }
  
  {SingleCharacter}+             { tokenLength += yylength(); }
  
  /* escape sequences */


  \\.                            { tokenLength += 2; }
  {LineTerminator}               { yybegin(YYINITIAL);  }
}




/* error fallback */
.|\n                             {  }
<<EOF>>                          { return null; }