package arreglos;

import java.util.ArrayList;
import clases.ProductoTienda;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ArregloProdTiend {

    //Atributo
    private ArrayList<ProductoTienda> aproti;

    //Constructor
    public ArregloProdTiend() {
        aproti = new ArrayList<ProductoTienda>();

    }

    //Métodos
    public void ingresar(ProductoTienda pt) {
        aproti.add(pt);

    }
    
    public ProductoTienda obtener(int i) {
        return aproti.get(i);
        
    }

    public int tamaño() {
        return aproti.size();

    }

    public ProductoTienda buscar(int tiend,int prod) {

        for(ProductoTienda pt : aproti)
            if(pt.getTienda() == tiend && pt.getProducto() == prod)
                return pt;

        return null;
    }

    public void guardar() {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Archivos/ProductoTienda.txt"));

            for(int i=0;i<tamaño();i++) {
                ProductoTienda pt = obtener(i);
                pw.println(pt.getProducto()+","+pt.getTienda()+","+pt.getStock());

            }

            pw.close();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

    public void cargar() {

        try {
            File archivo = new File("Archivos/ProductoTienda.txt");

            if(archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String cadena;

                while((cadena = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(cadena,",");
                    int prod = Integer.parseInt(st.nextToken());
                    int tiend = Integer.parseInt(st.nextToken());
                    int stock = Integer.parseInt(st.nextToken());

                    ingresar(new ProductoTienda(prod,tiend,stock));
                    
                }

            } else
                JOptionPane.showMessageDialog(null,"¡El archivo no existe!");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }


    }

}
