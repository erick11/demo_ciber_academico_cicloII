package arreglos;

import java.util.ArrayList;
import clases.Categoria;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ArregloCategorias {
    
    //Atributo
    private ArrayList<Categoria> acat;
    
    //Constructor
    public ArregloCategorias() {
        acat = new ArrayList<Categoria>();

    }

    //Métodos
    public void ingresar(Categoria c) {
        acat.add(c);

    }

    public void eliminar(int i) {
        acat.remove(i);

    }

    public Categoria obtener(int i) {
        return acat.get(i);

    }

    public Categoria buscar(int codigo) {

        for(Categoria c : acat)
            if(c.getCodigo() == codigo)
                return c;

        return null;

    }

    public int tamaño() {
        return acat.size();

    }

    public String getCodigoAutogenerado() {

        if(tamaño() == 0)
            return "10001";
        else
            return ""+(obtener(tamaño()-1).getCodigo()+1);
    }

    public void guardar() {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Archivos/Categorias.txt"));

            for(int i=0;i<tamaño();i++) {
                Categoria c = obtener(i);
                pw.println(c.getCodigo()+","+c.getDescripcion());

            }

            pw.close();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

    public void cargar() {

        try {
            File archivo = new File("Archivos/Categorias.txt");

            if(archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String cadena;

                while((cadena = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(cadena,",");
                    int cod = Integer.parseInt(st.nextToken());
                    String descrip = st.nextToken();

                    ingresar(new Categoria(cod, descrip));
                }

                br.close();

            } else
                JOptionPane.showMessageDialog(null,"¡El archivo no existe!");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

}
