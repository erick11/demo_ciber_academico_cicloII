package clases;

public class Producto {

    //Atributos
    private int codigo;
    private String descripcion;
    private int categoria;
    private double precio;
    private String marca;

    //Contructor
    public Producto(int codigo, String descripcion, int categoria, double precio, String marca) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.marca = marca;

    }

    //Getters y Setters
    public int getCategoria() {
        return categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
