/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dumpfmm;

import java.util.HashMap;

/**
 *
 * @author Luizzz
 */
public class Condition {
    private final String column;
    private final FindType operator;
    private final String value;
    private final TokenType type_value;
    
    public Condition(String column, FindType operator, String value, TokenType tv) {
        this.column = column;
        this.operator = operator;
        this.value = value;
        this.type_value = tv;
    }
    
    public boolean evaluate(HashMap<String, Object> row) {
        Object fieldValue = row.get(column);
        System.out.println("Evlauando:" + fieldValue);
        if(fieldValue == null) {
            return false;
        }
        
        switch(type_value) {
            case STRING:
                return evaluateString((String) fieldValue);
            case NUMBER:
                System.out.println("Por aqui paja");
                return evaluateInteger(Integer.valueOf(fieldValue.toString()));
            default:
                throw new IllegalArgumentException("Tipo de valor desconocido: " + type_value);
        }
        
    }
    
     private boolean evaluateString(String fieldValue) {
        switch (operator) {
            case EQUALS:
                return fieldValue.equals(value);
            /*case NOT_EQUALS:
                return !fieldValue.equals(value);*/
            // Agrega más operadores según tus necesidades
            default:
                throw new IllegalArgumentException("Operador desconocido para STRING: " + operator);
        }
    }
     
     private boolean evaluateInteger(Integer fieldValue) {
        int intValue = Integer.parseInt(value);
        switch (operator) {
            case EQUALS:
                return fieldValue.equals(intValue);
            /*case NOT_EQUALS:
                return !fieldValue.equals(intValue);*/
            case LESS_THAN:
                return fieldValue < intValue;
            case LESS_OR_EQUALS_THAN:
                return fieldValue <= intValue;
            case GREAT_THAN:
                return fieldValue > intValue;
            case GREAT_OR_EQUALS_THAN:
                return fieldValue >= intValue;
            // Agrega más operadores según tus necesidades
            default:
                throw new IllegalArgumentException("Operador desconocido para INTEGER: " + operator);
        }
    }

    @Override
    public String toString() {
        return "Condition{" +
                "column='" + column + '\'' +
                ", operator='" + operator + '\'' +
                ", value='" + value + '\'' +
                ", Type value='" + type_value + '\'' +
                '}';
    }
}
