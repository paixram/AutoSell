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
public interface Queriable {
    
    // Find Methods
    default <E> Queriable FindByFieldEQ(String field, E value) {
        return FindByField(field, FindType.EQUALS, value);
    };
    
    default <T> Queriable FindByFieldGT(String field, T value) {
        return FindByField(field, FindType.GREAT_THAN, value);
    };
    default <O> Queriable FindByFieldLT(String field, O value) {
        return FindByField(field, FindType.LESS_THAN, value);
    };
    default <B> Queriable FindByFieldGET(String field, B value) {
        return FindByField(field, FindType.GREAT_OR_EQUALS_THAN, value);
    };
    default <A> Queriable FindByFieldLET(String field, A value) {
        return FindByField(field, FindType.LESS_OR_EQUALS_THAN, value);
    };
    
    <R> Queriable FindByField(String field, FindType ft, R value);
    
    // Queriable Builder Methods
    Queriable From(String model);
    Queriable Get(String fieldName);
    Queriable Update(String fieldName, String value);
    Queriable Delete();
    
   
    
    String toQuery();
}
