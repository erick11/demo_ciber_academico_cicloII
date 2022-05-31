package clases;

public class Kardex {

    //Atributos
    private int numero;
    private String tipoperacion;
    private int operacion;
    private String fecha;
    private int empleado;
    private int tipdocumento;
    private int tienda;
    private int producto;
    private int cantidad;
    private double precio;
    private double total;

    //Contructor
    public Kardex(int numero,String tipoperacion,int operacion,String fecha,
                  int empleado,int tipdocumento,int tienda,int producto,
                  int cantidad,double precio,double total) {

        this.numero = numero;
        this.tipoperacion = tipoperacion;
        this.operacion = operacion;
        this.fecha = fecha;
        this.empleado = empleado;
        this.tipdocumento = tipdocumento;
        this.tienda = tienda;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;

    }

    //Getters
    public int getCantidad() {
        return cantidad;
    }

    public int getEmpleado() {
        return empleado;
    }

    public String getFecha() {
        return fecha;
    }

    public int getNumero() {
        return numero;
    }

    public int getOperacion() {
        return operacion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getProducto() {
        return producto;
    }

    public int getTienda() {
        return tienda;
    }

    public int getTipdocumento() {
        return tipdocumento;
    }

    public String getTipoperacion() {
        return tipoperacion;
    }

    public double getTotal() {
        return total;
    }

}
