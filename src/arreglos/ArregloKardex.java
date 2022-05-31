package arreglos;

import java.util.ArrayList;
import clases.Kardex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ArregloKardex {

    //Atributo
    private ArrayList<Kardex> akard;

    //Constructor
    public ArregloKardex() {
        akard = new ArrayList<Kardex>();

    }

    //Métodos
    public void ingresar(Kardex k) {
        akard.add(k);
    }

    public Kardex obtener(int i) {
        return akard.get(i);

    }

    public int tamaño() {
        return akard.size();

    }

    public String getCodigoAutogenerado() {
        if(tamaño() == 0)
            return ""+10001;
        else
            return ""+(obtener(tamaño()-1).getNumero()+1);

    }

    public void guardar() {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Archivos/Kardex.txt"));

            for(int i=0;i<tamaño();i++) {
                Kardex k = obtener(i);
                pw.println(k.getNumero()+","+k.getTipoperacion()+","+
                           k.getOperacion()+","+k.getFecha()+","+k.getEmpleado()+","+
                           k.getTipdocumento()+","+k.getTienda()+","+k.getProducto()+","+
                           k.getCantidad()+","+k.getPrecio()+","+k.getTotal());

            }

            pw.close();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

    public void cargar() {

        try {
            File archivo = new File("Archivos/Kardex.txt");

            if(archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String cadena;

                while((cadena = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(cadena,",");
                        int numero = Integer.parseInt(st.nextToken());
                        String tipoperacion = st.nextToken();
                        int operacion = Integer.parseInt(st.nextToken());
                        String fecha = st.nextToken();
                        int empleado = Integer.parseInt(st.nextToken());
                        int tipdocumento = Integer.parseInt(st.nextToken());
                        int tienda = Integer.parseInt(st.nextToken());
                        int producto = Integer.parseInt(st.nextToken());
                        int cantidad = Integer.parseInt(st.nextToken());
                        double precio = Double.parseDouble(st.nextToken());
                        double total = Double.parseDouble(st.nextToken());

                        ingresar(new Kardex(numero,tipoperacion,operacion,fecha,
                                            empleado,tipdocumento,tienda,producto,
                                            cantidad,precio,total));

                }

                br.close();

            } else
                JOptionPane.showMessageDialog(null,"¡El archivo no existe!");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);

        }

    }

}
