/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dumpfmm;

import espol.edu.ec.autosell.utils.Malloc;

/**
 *
 * @author Luizzz
 */
public class Lexer {
    private String query;
    private int position;
    private Malloc<Token> tokens;
    
    public Lexer(String query) {
        this.query = query;
        this.position = 0;
        this.tokens = new Malloc<>();
    }
    
    public Malloc<Token> tokenizer() {
        while(position < query.length()) {
            // TODO: Leer cada palabra y darle sentido, tokenizar la query y luego crear un arbol
            char currentChar = query.charAt(position);
            
            if(Character.isWhitespace(currentChar)) {
                position++;
            }else if(Character.isLetter(currentChar)) {
                tokens.add(Consultor_OR_Identifier());
            }else if(Character.isDigit(currentChar)) {
                tokens.add(readNumber());
            }else if(".><=[],".indexOf(currentChar) != -1) {
                tokens.add(readOperator());
            }else if(currentChar == '"'){
                tokens.add(readString());
            }else{
                tokens.add(new Token(TokenType.UNKNOWN, Character.toString(currentChar)));
                position++;
            }
        }
        tokens.add(new Token(TokenType.ENDQUERY, ""));
        return tokens;
    }
    
    private Token readOperator() {
        char op = query.charAt(position);
        Token opt = new Token(TokenType.UNKNOWN, "");
        switch(op) {
            case ',':
                opt = new Token(TokenType.COMMA, Character.toString(op));
                break;
            case '.':
                opt = new Token(TokenType.DOT, Character.toString(op));
                break;
            default:
                opt = new Token(TokenType.OPERATOR, Character.toString(op));
                break;
        }
        position++;
        return opt;
    }
    
    private Token readNumber() {
        int start = position;
        while (position < query.length() && Character.isDigit(query.charAt(position))) {
            position++;
        }
        return new Token(TokenType.NUMBER, query.substring(start, position));
    }
    
    private Token readString() {
        position++;
        int start = position;
        while (position < query.length() && query.charAt(position) != '"') {
            position++;
        }
        
        String value = query.substring(start, position);
        position++;
        
        return new Token(TokenType.STRING, "\""+ value + "\"");
    }
    
    private Token Consultor_OR_Identifier() {
        int start = position;
        while(position < query.length() && Character.isLetterOrDigit(query.charAt(position))) {
            position++;
        }
        
        String value = query.substring(start, position);
        TokenType ttype = isConsultor(value) ? TokenType.CONSULTOR : TokenType.IDENTIFIER;
        System.out.println(ttype + " 0 " + value);
        return new Token(ttype, value);
    }
    
    public boolean isConsultor(String value) {
        Malloc<String> consultors = new Malloc();
        consultors.add("GET");
        consultors.add("SET");
        consultors.add("FROM");
        consultors.add("UPDATE");
        consultors.add("DELETE");
        consultors.add("WHEN");
        
        return consultors.indexOf(value) != -1;
    }
    
}
