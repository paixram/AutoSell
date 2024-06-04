package espol.edu.ec.autosell.model;

// Import Enums
import dumpfmm.Almacenable;
import dumpfmm.FieldOrder;
import espol.edu.ec.autosell.utils.Malloc;
import espol.edu.ec.autosell.utils.UserRole;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Usuario implements Almacenable {
    @FieldOrder(order = 1)
    int idUsuario ;
    
    @FieldOrder(order = 2)
    String username;
    
    @FieldOrder(order = 3)
    String contrasenia;
    
    @FieldOrder(order = 4)
    UserRole rol; 

    public Usuario(int idUsuario, String username, String contrasenia, UserRole rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
    
    public Usuario() {
        
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", contraseña=" + contrasenia + ", rol=" + rol + '}';
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public boolean validarContrasenia(String contrasenia) {
        return this.contrasenia.equals(contrasenia);
    }    
    public int getIdUsuario(){
        return idUsuario;
    }
    
    public String getUsername(){
        return username;
    }
    
    public UserRole getRole(){
        return rol;
    }
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public void setRol(UserRole rol){
        this.rol = rol;
    }
      
}
