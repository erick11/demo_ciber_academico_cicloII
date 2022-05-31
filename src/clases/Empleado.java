package clases;

public class Empleado {

    //Atributos
    private int codigo;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String fechaing;
    private int tienda;
    private int tipo;
    private String contrase�a;

    //Contructor
    public Empleado(int codigo, String nombres, String apellidos, String telefono,
                    String fechaing, int tienda, int tipo,String contrase�a) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fechaing = fechaing;
        this.tienda = tienda;
        this.tipo = tipo;
        this.contrase�a = contrase�a;

    }

    //Getters y Setters
    public String getApellidos() {
        return apellidos;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getFechaing() {
        return fechaing;
    }

    public String getNombres() {
        return nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getTienda() {
        return tienda;
    }

    public int getTipo() {
        return tipo;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaing(String fechaing) {
        this.fechaing = fechaing;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTienda(int tienda) {
        this.tienda = tienda;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setContrase�a(String contrase�a) {
        this.contrase�a = contrase�a;
    }

    public String getContrase�a() {
        return contrase�a;
    }

}