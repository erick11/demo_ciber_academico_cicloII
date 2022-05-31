package clases;

public class ProductoAlmacen {

    //Atributos
    private int almacen;
    private int producto;
    private int proveedor;
    private int stock;

    //Constructor
    public ProductoAlmacen(int almacen,int producto,int proveedor,int stock) {

        this.almacen = almacen;
        this.producto = producto;
        this.proveedor = proveedor;
        this.stock = stock;

    }

     //Getters
    public int getAlmacen() {
        return almacen;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProducto() {
        return producto;
    }

    public int getProveedor() {
        return proveedor;
    }

    public int getStock() {
        return stock;
    }

}
