package clases;

public class Proveedor {

    //Atributos
    private int codigo;
    private String nombre;
    private String ruc;
    private String telefono;
    private String email;

    //Contructor
    public Proveedor(int codigo, String nombre, String ruc, String telefono, String email) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;

    }

    //Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
