package clases;

public class Tienda {

    //Atributos
    private int codigo;
    private String descripcion;
    private int capacidad;
    private int ocupado;

    //Constructor
    public Tienda(int codigo,String descripcion,int capacidad,int ocupado) {

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.ocupado = ocupado;

    }

    //Getters
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getOcupado() {
        return ocupado;
    }

    public void setOcupado(int ocupado) {
        this.ocupado = ocupado;

    }

}
