package espol.edu.ec.autosell.utils;

public enum UserRole {
    VENDEDOR,COMPRADOR;

    public static UserRole valueOf(Object get) {
        switch((String)get) {
            case "VENDEDOR":
                return VENDEDOR;
            case "COMPRADOR":
                return COMPRADOR;
        }
        
        return null;
    }
}
