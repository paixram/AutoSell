package espol.edu.ec.autosell;

public class Usuario {
    String idUsuario ;
    UserRole rol;
    
    public Usuario(String idUsuario, UserRole rol){
        this.idUsuario = idUsuario;
        this.rol = rol;
    }
    
    @Override
    public String toString(){
        return "Usuario{" + "id=" + idUsuario + ", Rol=" + rol + "}";
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
