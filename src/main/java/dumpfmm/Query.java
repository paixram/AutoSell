/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dumpfmm;

/**
 *
 * @author Luizzz
 */  

/*
 - Ejemplo de una consulta: FROM ... GET/UPDATE/DELETE [Spec Field]/[... all fields] WHEN field_target =/<=/=>/</> data_target
 - Sample for find/get: FROM Usuarios GET Id WHEN Name = "Luis"
 - Sample for Update: FROM Usuarios UPDATE Name = "Luis", Id = 1, ... When Id = 2;
 - Sample for Delete: FROM Usuarios DELETE WHEN Id = 2;
*/
public interface Query {
    
    // Find Methods
    default <E> Query FindByFieldEQ(String field, E value) {
        return FindByField(field, FindType.EQUALS, value);
    };
    
    default <T> Query FindByFieldGT(String field, T value) {
        return FindByField(field, FindType.GREAT_THAN, value);
    };
    default <O> Query FindByFieldLT(String field, O value) {
        return FindByField(field, FindType.LESS_THAN, value);
    };
    default <B> Query FindByFieldGET(String field, B value) {
        return FindByField(field, FindType.GREAT_OR_EQUALS_THAN, value);
    };
    default <A> Query FindByFieldLET(String field, A value) {
        return FindByField(field, FindType.LESS_OR_EQUALS_THAN, value);
    };
    
    <R> Query FindByField(String field, FindType ft, R value);
    
    // Query Builder Methods
    Query From(String model);
    Query Get(String fieldName);
    Query Update(String fieldName, String value);
    Query Delete();
    
   
    
    String toQuery();
}
