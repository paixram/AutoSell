/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dumpfmm;

import espol.edu.ec.autosell.utils.Malloc;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Luizzz
 */
public class Parser {

    private Malloc<Token> tokens;
    private int currentToken;
    private HashMap<String, HashMap<String, Object>> metadata;

    public Parser(Malloc<Token> tokens, HashMap<String, HashMap<String, Object>> md) {
        this.tokens = tokens;
        this.metadata = md;
        this.currentToken = 0;
    }

    private Token currentToken() {
        return tokens.get(currentToken);
    }

    public Query parse() {
        // TODO: Parsear los statements de los tokens
        while (currentToken().getType() != TokenType.ENDQUERY) {
            expect(TokenType.CONSULTOR, "FROM");
            String model = currentToken().getValue();

            // checking model
            CheckModelExist(model);

            System.out.println("Model: " + model);
            peek();

            String op = currentToken().getValue();
            peek();

            switch (op) {
                case "GET":
                    return parseGetQuery(model);
                case "SET":
                    return parseSetQuery(model);
                case "UPDATE":
                    break;
                case "DELETE":
                    break;
                default:
                    throw new RuntimeException("Unknown operation: " + op);
            }
        }
        return null;
    }

    private Query parseGetQuery(String model) {
        // Get query settings
        HashMap<String, String> columns = new HashMap<>();

        // Recoger todos los campos de la metadata del model 
        HashMap<String, Object> model_sets = metadata.get(model);
        HashMap<String, String> model_arguments = (HashMap<String, String>) model_sets.get("Fields");

        while (currentToken().getType() != TokenType.CONSULTOR && currentToken().getType() != TokenType.NUMBER) {
            switch (currentToken().getValue()) {
                case ".":
                    peek();
                    expect(TokenType.DOT, ".");

                    model_arguments.forEach((k, v) -> {
                        columns.put(k, v);
                    });

                    break;
                default:
                    // Setteado por campos separados
                    while (true) {
                        //columns.put(currentToken().getValue());
                        model_arguments.forEach((k, v) -> {
                            if (k.equals(currentToken().getValue())) {
                                columns.put(k, v);
                            }
                        });
                        peek();

                        if (currentToken().getType() == TokenType.COMMA) {
                            peek();
                        } else {
                            break;
                        }
                    }

            }

        }

        System.out.println("Query: " + columns.toString() + " - SIZE: " + columns.size());

        // expectear el condicional
        Malloc<Condition> cd = new Malloc();
        
        while(CurrentTokenIs(TokenType.CONSULTOR, "WHEN")) {
            peek();
            Condition conditionn = parseCondition(model_arguments);
            cd.add(conditionn);
        }
        

        for (Condition s : cd) {
            System.out.println(s);
        }
        
        // Ver para otras reglas como el Limit: 1, 2 etc
        String pathReference = (String) model_sets.get("Path Reference");
        return new GetQuery(model, columns, cd, pathReference, model_arguments);
    }
    
    private Query parseSetQuery(String model) {// FROM Usuarios SET 12, luis, V
        // parsear el setQuery 
        System.out.println("Curreent TOken: " + currentToken().getType());
        
        // Recoger todos los campos de la metadata del model 
        HashMap<String, Object> model_sets = metadata.get(model);
        HashMap<String, String> model_arguments = (HashMap<String, String>) model_sets.get("Fields");
        
        // Verificar que la cantidad de elementos a insertar no sea mayor a los argumentos existentes
        System.out.println("Modeeel: " + model_arguments);
        
        Malloc<String> v = new Malloc();
        int position = 0;
        while(currentToken < tokens.size() && currentToken().getType() != TokenType.ENDQUERY) {
            if(CurrentTypeTokenIs(TokenType.NUMBER) || CurrentTypeTokenIs(TokenType.IDENTIFIER) || CurrentTypeTokenIs(TokenType.STRING)) {
                v.add(currentToken().getValue());
                peek();
            }
            
            if(CurrentTokenIs(TokenType.COMMA, ",")) {
                peek();
            }
            
            position++;
           
        }
        
        // Validar longitud de los datos
        if(v.size() > model_arguments.size()) {
            throw new RuntimeException("Too many arguments in the SET query for Model :" + model);
        }
        
        validateValues(v, model_arguments);
        v = cleanComillas(v);
        
        String pathReference = (String) model_sets.get("Path Reference");
        //System.out.println("DATTOS:  " + values);
        // Formatear data con los valores
        return new SetQuery(model, v, pathReference, model_arguments); // Aqui quede
    }

    private Condition parseCondition(HashMap<String, String> md) {

        String pre_targetColumn = currentToken().getValue();
        String targetColumn = md.get(pre_targetColumn);
        // Eval conditions
        peek();
        String op = currentToken().getValue();
        FindType condition_type = FindType.EQUALS;

        peek();
        switch (op) {
            case "=":
                break;
            case ">":
                if (currentToken().getValue().equals("=")) {
                    condition_type = FindType.GREAT_OR_EQUALS_THAN;
                    peek();
                } else {
                    condition_type = FindType.GREAT_THAN;
                }
                break;
            case "<":
                if (currentToken().getValue().equals("=")) {
                    condition_type = FindType.LESS_OR_EQUALS_THAN;
                    peek();
                } else {
                    condition_type = FindType.LESS_THAN;
                }
                break;
            default:
                throw new RuntimeException("Unexpected token: " + TokenType.OPERATOR);
        }

        // Indetificar el valor a tomar
        if ((currentToken().getType() != TokenType.NUMBER) && (currentToken().getType() != TokenType.STRING)) {
            throw new RuntimeException("Unexpected token: " + TokenType.OPERATOR + " - " + currentToken().getValue() + " - " + currentToken().getType());
        }

        String value = currentToken().getValue();
        
        if(currentToken().getType() == TokenType.STRING) {
            value = value.replaceAll("\"", "");
        }

        Condition conditional_n = new Condition(pre_targetColumn, condition_type, value, currentToken().getType());

        peek();
        return conditional_n;
    }
    
    private void validateValues(Malloc<String> values, HashMap<String, String> modelArguments) {
        int index = 0;
        for(Map.Entry<String, String> entry : modelArguments.entrySet()) {
            String fieldName = entry.getKey();
            String fieldType = entry.getValue().split(",")[0];
            
            if(index < values.size()) {
                String value = values.get(index);
                validateValueType(value, fieldType, fieldName);
                index++;
            }else{
                throw new RuntimeException("Missing value for field: " + fieldName);
            }
        }
    }
    
    private void validateValueType(String value, String expectedType, String fieldName) {
        switch (expectedType) {
            case "String":
                if (!isString(value)) {
                    throw new RuntimeException("Expected a String for field '" + fieldName + "' but found: " + value);
                }
                break;
            case "int":
                if (!isInteger(value)) {
                    throw new RuntimeException("Expected an int for field '" + fieldName + "' but found: " + value);
                }
                break;
            default:
                //throw new RuntimeException("Unknown type for field '" + fieldName + "': " + expectedType);
        }
    }
    
    private Malloc<String> cleanComillas(Malloc<String> obj) {
        Malloc<String> nv = new Malloc();
        for(String ob : obj) {
            if(isString(ob)) {
                nv.add(ob.replaceAll("\"", ""));
            }else{
                nv.add(ob);
            }
        }
        
        return nv;
    }
    
    private boolean isString(String value) {
        return value.startsWith("\"") && value.endsWith("\"");
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Error handler
    private Token expect(TokenType type, String value) {
        Token token = currentToken();
        if (token.getType() == type && token.getValue().equals(value)) {
            peek();
            return token;
        }
        throw new RuntimeException("Unexpected token: " + token);
    }
    
    
    private boolean CurrentTokenIs(TokenType type, String value) {
        Token token = currentToken();
        return token.getType() == type && token.getValue().equals(value);
    }
    
    private boolean CurrentTypeTokenIs(TokenType type) {
        Token token = currentToken();
        return token.getType() == type;
    }
    
    private void CheckModelExist(String model) {
        if (!metadata.containsKey(model)) {
            throw new RuntimeException("The model " + model + " dont exists!");
        }
    }

    private void peek() {
        if (currentToken < tokens.size() - 1) {
            currentToken++;
        }
    }
}
