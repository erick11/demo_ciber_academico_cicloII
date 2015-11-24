package arreglos;

import java.util.ArrayList;
import clases.ProductoAlmacen;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ArregloProdAlma {

    //Atributo
    private ArrayList<ProductoAlmacen> aproal;

    //Constructor
    public ArregloProdAlma() {
        aproal = new ArrayList<ProductoAlmacen>();

    }

    //Métodos
    public void ingresar(ProductoAlmacen pa) {
        aproal.add(pa);

    }
    
    public ProductoAlmacen obtener(int i) {
        return aproal.get(i);
        
    }

    public int tamaño() {
        return aproal.size();

    }

    public ProductoAlmacen buscar1(int prod,int prov) {
        for(ProductoAlmacen pa : aproal)
            if(pa.getProducto() == prod && pa.getProveedor() == prov)
                return pa;

        return null;

    }
    
     public ProductoAlmacen buscar2(int producto) {
        
        for(ProductoAlmacen pa : aproal)
            if(pa.getProducto() == producto && pa.getStock() != 0)
                return pa;
        
        return null;
        
    }

    public int cantidadProducto(int codigo) {

        int cantidad = 0;

        for(ProductoAlmacen pa : aproal)
            if(pa.getProducto() == codigo)
                cantidad+=pa.getStock();

        return cantidad;

    }

    public void guardar() {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Archivos/ProductoAlmacen.txt"));

            for(int i=0;i<tamaño();i++) {
                ProductoAlmacen pa = obtener(i);
                pw.println(pa.getAlmacen()+","+pa.getProveedor()+","+
                           pa.getProducto()+","+pa.getStock());

            }

            pw.close();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

    public void cargar() {

        try {
            File archivo = new File("Archivos/ProductoAlmacen.txt");

            if(archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String cadena;

                while((cadena = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(cadena,",");
                    int alma = Integer.parseInt(st.nextToken());
                    int prov = Integer.parseInt(st.nextToken());
                    int prod = Integer.parseInt(st.nextToken());
                    int stock = Integer.parseInt(st.nextToken());

                    ingresar(new ProductoAlmacen(alma,prov,prod,stock));

                }

                br.close();

            } else
                JOptionPane.showMessageDialog(null,"¡El archivo no existe!");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }
        
    }

}
