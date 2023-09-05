package arreglos;

import java.util.ArrayList;
import clases.Empleado;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ArregloEmpleados {

    //Atributo
    private ArrayList<Empleado> aemp;

    //Constructor
    public ArregloEmpleados() {
        aemp = new ArrayList<Empleado>();

    }

    //M�todos
    public void ingresar(Empleado e) {
        aemp.add(e);

    }

    public Empleado obtener(int i) {
	return aemp.get(i);

    }

    public void eliminar(int i) {
	aemp.remove(i);

    }

    public int tamano() {
	return aemp.size();

    }

    public Empleado buscar(int codigo) {
        for(Empleado e : aemp)
            if(e.getCodigo() == codigo)
                return e;

        return  null;
    }

    public String getCodigoAutogenerado() {
        if(tamano() == 0)
            return ""+10001;
        else
            return ""+(obtener(tamano()-1).getCodigo()+1);
    }

    public void guardar() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Archivos/Empleados.txt"));

            for(int i=0;i<tamano();i++) {
                Empleado e = obtener(i);
                pw.println(e.getCodigo()+","+e.getNombres()+","+
                e.getApellidos()+","+e.getTelefono()+","+
                e.getFechaing()+","+e.getTienda()+","+
                e.getTipo()+","+e.getContrasena());

            }
            
            pw.close();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

    public void cargar() {

        try {
            File archivo = new File("Archivos/Empleados.txt");

            if(archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String cadena;

                while((cadena = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(cadena,",");

                    int cod = Integer.parseInt(st.nextToken());
                    String nom = st.nextToken();
                    String ape = st.nextToken();
                    String telf = st.nextToken();
                    String fecing = st.nextToken();
                    int tiend = Integer.parseInt(st.nextToken());
                    int tip = Integer.parseInt(st.nextToken());
                    String pass = st.nextToken();

                    ingresar(new Empleado(cod,nom,ape,telf,fecing,tiend,tip,
                                          pass));

                }

                br.close();

            } else
                JOptionPane.showMessageDialog(null,"�El archivo no existe!");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

}

