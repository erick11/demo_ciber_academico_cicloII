package arreglos;

import java.util.ArrayList;
import clases.Tienda;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ArregloTienda {

    //Atributo
    private ArrayList<Tienda> atien;

    //Constructor
    public ArregloTienda() {
        atien = new ArrayList<Tienda>();

    }

    //M�todo
    public void ingresar(Tienda t) {
        atien.add(t);

    }

    public int tamano() {
        return atien.size();

    }

    public Tienda obtener(int i) {
        return atien.get(i);

    }

    public Tienda buscar(int codigo) {

        for(Tienda t : atien)
            if(t.getCodigo() == codigo)
                return t;

        return null;
    }

    public String getCodigoAutogenerado() {
        if(tamano() == 0)
            return "10001";
        else
            return ""+(obtener(tamano()-1).getCodigo()+1);
    }

    public void guardar() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Archivos/Tiendas.txt"));

            for(int i=0;i<tamano();i++) {
                Tienda t = obtener(i);
                pw.println(t.getCodigo()+","+t.getDescripcion()+","+
                           t.getCapacidad()+","+t.getOcupado());

            }

            pw.close();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

    public void cargar() {

        try {
            File archivo = new File("Archivos/Tiendas.txt");

            if(archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String cadena;

                while((cadena = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(cadena,",");

                    int cod = Integer.parseInt(st.nextToken());
                    String descrip = st.nextToken();
                    int capac = Integer.parseInt(st.nextToken());
                    int ocup = Integer.parseInt(st.nextToken());

                    ingresar(new Tienda(cod,descrip,capac,ocup));

                }

                br.close();

            } else
                JOptionPane.showMessageDialog(null,"�El archivo no existe!");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

}
