package arreglos;

import java.util.ArrayList;
import clases.Producto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ArregloProductos {

    //Atributo
    private ArrayList<Producto> aprod;

    //Constructor
    public ArregloProductos() {
        aprod = new ArrayList<Producto>();

    }

    //M�todos
    public void ingresar(Producto p) {
        aprod.add(p);

    }

    public Producto obtener(int i) {
	return aprod.get(i);

    }

    public void eliminar(int i) {
	aprod.remove(i);

    }

    public int tamano() {
	return aprod.size();

    }

    public Producto buscar(int codigo) {
        for(Producto p : aprod)
            if(p.getCodigo() == codigo)
                return p;

        return  null;
    }

    public String getCodigoAutogenerado() {
        if(tamano() == 0)
            return ""+10001;
        else
            return ""+(obtener(tamano()-1).getCodigo()+1);
    }

    public double getPrecio(int codigo) {

        for(Producto p : aprod)
            if(p.getCodigo() == codigo)
                return p.getPrecio();

        return -1;
    }

    public void guardar() {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Archivos/Productos.txt"));

            for(int i=0;i<tamano();i++) {
                Producto p = obtener(i);
                pw.println(p.getCodigo()+","+p.getDescripcion()+","+
                           p.getCategoria()+","+p.getPrecio()+","+p.getMarca());

            }

            pw.close();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

    public void cargar() {

        try {
            File archivo = new File("Archivos/Productos.txt");

            if(archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String cadena;

                while((cadena = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(cadena,",");
                    int cod = Integer.parseInt(st.nextToken());
                    String descrip = st.nextToken();
                    int cat = Integer.parseInt(st.nextToken());
                    double prec = Double.parseDouble(st.nextToken());
                    String marc = st.nextToken();

                    ingresar(new Producto(cod,descrip,cat,prec,marc));

                }

                br.close();

            } else
                JOptionPane.showMessageDialog(null,"�El archivo no existe!");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

}
