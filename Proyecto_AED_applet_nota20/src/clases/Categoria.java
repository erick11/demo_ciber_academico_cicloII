package clases;

public class Categoria {

    //Atributos
    private int codigo;
    private String descripcion;

    //Constructor
    public Categoria(int codigo, String descripcion) {

        this.codigo = codigo;
        this.descripcion = descripcion;

    }

    //Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
