package arreglos;

import java.util.ArrayList;
import clases.Proveedor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ArregloProveedores {

    //Atributo
    private ArrayList<Proveedor> aprov;

    //Constructor
    public ArregloProveedores() {
        aprov = new ArrayList<Proveedor>();

    }

    //Métodos
    public void ingresar(Proveedor p) {
        aprov.add(p);

    }

    public Proveedor obtener(int i) {
	return aprov.get(i);

    }

    public void eliminar(int i) {
	aprov.remove(i);

    }

    public int tamaño() {
	return aprov.size();

    }

    public Proveedor buscar(int codigo) {
        for(Proveedor p : aprov)
            if(p.getCodigo() == codigo)
                return p;

        return  null;
    }

    public String getCodigoAutogenerado() {
        if(tamaño() == 0)
            return ""+10001;
        else
            return ""+(obtener(tamaño()-1).getCodigo()+1);
    }

    public void guardar() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Archivos/Proveedores.txt"));

            for(int i=0;i<tamaño();i++) {
                Proveedor p = obtener(i);
                pw.println(p.getCodigo()+","+p.getNombre()+","+
                p.getRuc()+","+p.getTelefono()+","+p.getEmail());

            }

            pw.close();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

    public void cargar() {

        try {
            File archivo = new File("Archivos/Proveedores.txt");

            if(archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String cadena;

                while((cadena = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(cadena,",");

                    int cod = Integer.parseInt(st.nextToken());
                    String nom = st.nextToken();
                    String ruc = st.nextToken();
                    String telf = st.nextToken();
                    String email = st.nextToken();

                    ingresar(new Proveedor(cod,nom,ruc,telf,email));

                }

                br.close();

            } else
                JOptionPane.showMessageDialog(null,"¡El archivo no existe!");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

}
