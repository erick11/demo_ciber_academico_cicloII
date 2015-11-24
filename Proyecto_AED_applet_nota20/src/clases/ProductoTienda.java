package clases;

public class ProductoTienda {

    //Atributos
    private int producto;
    private int tienda;
    private int stock;

    //Constructor
    public ProductoTienda(int producto, int tienda, int stock) {

        this.producto = producto;
        this.tienda = tienda;
        this.stock = stock;

    }

    //Getters y Setters
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProducto() {
        return producto;
    }

    public int getStock() {
        return stock;
    }

    public int getTienda() {
        return tienda;
    }
  
}
