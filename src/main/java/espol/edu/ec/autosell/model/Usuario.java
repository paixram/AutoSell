package espol.edu.ec.autosell.model;

// Import Enums
import dumpfmm.Almacenable;
import espol.edu.ec.autosell.utils.Malloc;
import espol.edu.ec.autosell.utils.UserRole;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Usuario implements Almacenable {
    String idUsuario ;
    String contrasenia;
    UserRole rol;

    public Usuario(String idUsuario, String contrasenia, UserRole rol) {
        this.idUsuario = idUsuario;
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
    public String getIdUsuario(){
        return idUsuario;
    }
    
    public UserRole getRole(){
        return rol;
    }
    
    public void setIdUsuario(String idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public void setRol(UserRole rol){
        this.rol = rol;
    }
      
}
