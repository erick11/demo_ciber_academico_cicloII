
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.StringTokenizer;
import java.util.Calendar;
import clases.Empleado;
import clases.Kardex;
import clases.Producto;

public class PnlReportes extends JPanel implements ActionListener,ItemListener {

    //Componenetes del panel;
    private JLabel lblSlash1;
    private JLabel lblSlash2;
    private JLabel lblSlash3;
    private JLabel lblSlash4;
    private JLabel lblFechaInicial;
    private JLabel lblFechaFinal;
    private JLabel lblEmpleado;
    private JComboBox cboEmpleado;
    private JComboBox cboReporte;
    private JButton btnRealizar;
    private JComboBox cboDiaInicial;
    private JComboBox cboMesInicial;
    private JComboBox cboAñoInicial;
    private JComboBox cboDiaFinal;
    private JComboBox cboMesFinal;
    private JComboBox cboAñoFinal;
    private JTable tblReportes;
    private DefaultTableModel datos;
    private Principal pri;

    //Constructor
    public PnlReportes(Principal p) {
        setBounds(0,150,800,550);
        setLayout(null);

        pri = p;

        JLabel lblReporte = new JLabel("Reporte:",SwingConstants.LEFT);
        lblReporte.setBounds(40,20,80,20);
        add(lblReporte);

        String reportes[] = {"Seleccione","Relación de productos por tienda",
                             "Relación de ventas realizadas por empleado",
                             "Productos vendidos en un rango de fechas",
                             "Relación de transacciones por tipo de operación"};

        cboReporte = new JComboBox(reportes);
        cboReporte.setBounds(40,50,300,20);
        cboReporte.addItemListener(this);
        add(cboReporte);

        btnRealizar = new JButton("Realizar");
        btnRealizar.setBounds(350,50,100,20);
        btnRealizar.addActionListener(this);
        add(btnRealizar);
        
        lblFechaInicial = new JLabel("Fecha Inicial",SwingConstants.LEFT);
        lblFechaInicial.setBounds(500,20,90,20);
        lblFechaInicial.setVisible(false);
        add(lblFechaInicial);

        cboDiaInicial = new JComboBox();
        cboDiaInicial.setBounds(600,20,40,20);
        cboDiaInicial.setVisible(false);
        add(cboDiaInicial);

        lblSlash1 = new JLabel("/",SwingConstants.CENTER);
        lblSlash1.setBounds(640,20,10,20);
        lblSlash1.setVisible(false);
        add(lblSlash1);

        cboMesInicial = new JComboBox();
        cboMesInicial.setBounds(650,20,40,20);
        cboMesInicial.setVisible(false);
        add(cboMesInicial);

        lblSlash2 = new JLabel("/",SwingConstants.CENTER);
        lblSlash2.setBounds(690,20,10,20);
        lblSlash2.setVisible(false);
        add(lblSlash2);

        cboAñoInicial = new JComboBox();
        cboAñoInicial.setBounds(700,20,60,20);
        cboAñoInicial.setVisible(false);
        add(cboAñoInicial);

        lblFechaFinal = new JLabel("Fecha Final",SwingConstants.LEFT);
        lblFechaFinal.setBounds(500,50,100,20);
        lblFechaFinal.setVisible(false);
        add(lblFechaFinal);

        cboDiaFinal = new JComboBox();
        cboDiaFinal.setBounds(600,50,40,20);
        cboDiaFinal.setVisible(false);
        add(cboDiaFinal);

        lblSlash3 = new JLabel("/",SwingConstants.CENTER);
        lblSlash3.setBounds(640,50,10,20);
        lblSlash3.setVisible(false);
        add(lblSlash3);

        cboMesFinal = new JComboBox();
        cboMesFinal.setBounds(650,50,40,20);
        cboMesFinal.setVisible(false);
        add(cboMesFinal);

        lblSlash4 = new JLabel("/",SwingConstants.CENTER);
        lblSlash4.setBounds(690,50,10,20);
        lblSlash4.setVisible(false);
        add(lblSlash4);

        cboAñoFinal = new JComboBox();
        cboAñoFinal.setBounds(700,50,60,20);
        cboAñoFinal.setVisible(false);
        add(cboAñoFinal);

        cargarDias();
        cargarMeses();
        cargarAños();

        datos = new DefaultTableModel();

        tblReportes = new JTable(datos);

        JScrollPane scpScroll = new JScrollPane(tblReportes);
        scpScroll.setBounds(40,130,720,390);
        add(scpScroll);

        lblEmpleado = new JLabel("Empleado",SwingConstants.LEFT);
        lblEmpleado.setBounds(40,80,70,20);
        lblEmpleado.setVisible(false);
        add(lblEmpleado);

        cboEmpleado = new JComboBox();
        cboEmpleado.setBounds(120,80,100,20);
        cboEmpleado.setVisible(false);
        add(cboEmpleado);

        setVisible(false);

    }

    private void cargarDias() {

        for(int i=1;i<=31;i++)
            if(i<10) {
                cboDiaInicial.addItem("0"+i);
                cboDiaFinal.addItem("0"+i);

            } else {
                cboDiaInicial.addItem(i);
                cboDiaFinal.addItem(i);

            }

    }

    private void cargarMeses() {

        for(int i=1;i<=12;i++)
            if(i<10) {
                cboMesInicial.addItem("0"+i);
                cboMesFinal.addItem("0"+i);

            } else {
                cboMesInicial.addItem(i);
                cboMesFinal.addItem(i);

            }

    }

    private void cargarAños() {

        for(int i=2011;i<=2015;i++){
            cboAñoInicial.addItem(i);
            cboAñoFinal.addItem(i);

        }

    }

    public void actionPerformed(ActionEvent e) {

        switch(cboReporte.getSelectedIndex()) {
            case 0: JOptionPane.showMessageDialog(this,"¡Seleccione un reporte!");
                    break;
            case 1: productosTienda();
                    break;
            case 2: ventasEmpleado();
                    break;
            case 3: productosVendidos();
                    break;
            default: TransaccionesRegistradas();

        }
    }

    public void itemStateChanged(ItemEvent e) {

        limpiarTabla();
        lblFechaInicial.setVisible(false);
        lblFechaFinal.setVisible(false);
        cboDiaInicial.setVisible(false);
        cboMesInicial.setVisible(false);
        cboAñoInicial.setVisible(false);
        cboDiaFinal.setVisible(false);
        cboMesFinal.setVisible(false);
        cboAñoFinal.setVisible(false);
        lblSlash1.setVisible(false);
        lblSlash2.setVisible(false);
        lblSlash3.setVisible(false);
        lblSlash4.setVisible(false);
        lblEmpleado.setVisible(false);
        cboEmpleado.setVisible(false);

        switch(cboReporte.getSelectedIndex()) {

            case 1: datos.addColumn("Tienda");
                    datos.addColumn("Producto");
                    datos.addColumn("Stock");
                    break;
            case 2: datos.addColumn("Empleado");
                    datos.addColumn("Producto");
                    datos.addColumn("Cantidad");
                    datos.addColumn("Total");
                    datos.addColumn("Fecha");
                    cargarEmpleados();
                    lblEmpleado.setVisible(true);
                    cboEmpleado.setVisible(true);
                    break;
            case 3: datos.addColumn("Categoria");
                    datos.addColumn("Producto");
                    datos.addColumn("Cantidad");
                    datos.addColumn("Total");
                    datos.addColumn("Fecha");
                    lblFechaInicial.setVisible(true);
                    lblFechaFinal.setVisible(true);
                    cboDiaInicial.setVisible(true);
                    cboMesInicial.setVisible(true);
                    cboAñoInicial.setVisible(true);
                    cboDiaFinal.setVisible(true);
                    cboMesFinal.setVisible(true);
                    cboAñoFinal.setVisible(true);
                    lblSlash1.setVisible(true);
                    lblSlash2.setVisible(true);
                    lblSlash3.setVisible(true);
                    lblSlash4.setVisible(true);
                    break;
            case 4: datos.addColumn("Tipo Operación");
                    datos.addColumn("Número");
                    datos.addColumn("Operación");
                    datos.addColumn("Empleado");
                    datos.addColumn("Fecha");
                    datos.addColumn("Total");

        }

    }

    private void cargarEmpleados() {

        cboEmpleado.removeAllItems();
        cboEmpleado.addItem("Seleccione");

        for(int i=0;i<pri.aemp.tamaño();i++) {
            Empleado e = pri.aemp.obtener(i);
            cboEmpleado.addItem(e.getCodigo());

        }

    }

    private void productosTienda() {

        for(int i=0;i<pri.atien.tamaño();i++) {
            int tiend = pri.atien.obtener(i).getCodigo();

            for(int j=0;j<pri.aproti.tamaño();j++)
                if(pri.aproti.obtener(j).getTienda() == tiend) {
                    Object fila[] = new Object[3];

                    fila[0] = tiend;
                    fila[1] = pri.aproti.obtener(j).getProducto();
                    fila[2] = pri.aproti.obtener(j).getStock();

                    datos.addRow(fila);

                }

        }

        if(datos.getRowCount() != 0)
            JOptionPane.showMessageDialog(this,"¡Reporte realizado!");

        else
            JOptionPane.showMessageDialog(this,"¡No se han distribuido mercaderias "+
                            "a las tiendas!");

    }

    private void ventasEmpleado() {

        if(cboEmpleado.getSelectedIndex() != 0) {
            int emp = Integer.parseInt(cboEmpleado.getSelectedItem().toString());

            for(int i=0;i<pri.akard.tamaño();i++)
                if(pri.akard.obtener(i).getOperacion() == 3 &&
                        pri.akard.obtener(i).getEmpleado() == emp) {
                    Object fila[] = new Object[5];

                    fila[0] = emp;
                    fila[1] = pri.akard.obtener(i).getProducto();
                    fila[2] = pri.akard.obtener(i).getCantidad();
                    fila[3] = pri.akard.obtener(i).getTotal();
                    fila[4] = pri.akard.obtener(i).getFecha();

                    datos.addRow(fila);

                }

            if(datos.getRowCount() != 0)
                JOptionPane.showMessageDialog(this,"¡Reporte realizado!");

            else
                JOptionPane.showMessageDialog(this,"¡El empleado no tiene ventas "+
                                        "registradas!");

        } else
            JOptionPane.showMessageDialog(this,"¡Seleccione un empleado!");

    }

    private void productosVendidos() {

        if(validaFecha()) {

           for(int i=0;i<pri.acat.tamaño();i++) {
               int cat = pri.acat.obtener(i).getCodigo();

               for(int j=0;j<pri.akard.tamaño();j++)
                   if(pri.akard.obtener(j).getOperacion() == 3) {
                       int cod = pri.akard.obtener(j).getProducto();
                       Producto p = pri.aprod.buscar(cod);

                       if(p.getCategoria() == cat && validaRangoFecha(pri.
                                                akard.obtener(j).getFecha())) {
                           Object fila[] = new Object[5];

                           fila[0] = cat;
                           fila[1] = cod;
                           fila[2] = pri.akard.obtener(j).getCantidad();
                           fila[3] = pri.akard.obtener(j).getTotal();
                           fila[4] = pri.akard.obtener(j).getFecha();

                           datos.addRow(fila);

                       }

                   }

               }

            if(datos.getRowCount() != 0)
                JOptionPane.showMessageDialog(this,"¡Reporte realizado!");

            else
                JOptionPane.showMessageDialog(this,"¡No se han vendido productos "+
                        "en el rango de fechas especificado!");
           
        } else
            JOptionPane.showMessageDialog(this,"¡El rango de fechas es incorrecto!");

    }

    private void TransaccionesRegistradas() {

        String tiposoperacion[] = {"Entrada","Salida"};
        int cont = 0;

        while(cont < 2) {

            for(int i=0;i<pri.akard.tamaño();i++) {
                Kardex k = pri.akard.obtener(i);
                if(k.getTipoperacion().equals(tiposoperacion[cont])) {
                    Object fila[] = new Object[6];
                    fila[0] = tiposoperacion[cont];
                    fila[1] = k.getNumero();
                    switch(k.getOperacion()) {
                        case 0: fila[2] = "Registro";
                                break;
                        case 1: fila[2] = "Distribución";
                                break;
                        case 2: fila[2] = "Devolución";
                                break;
                        default: fila[2] = "Venta";
                    }
                    fila[3] = k.getEmpleado();
                    fila[4] = k.getFecha();
                    fila[5] = k.getTotal();

                    datos.addRow(fila);

                }

            }

            cont++;
        }

        JOptionPane.showMessageDialog(this,"¡Reporte realizado!");

    }

    private boolean validaFecha() {

        //Ingreso de las partes de la fecha inicial
        int dini = cboDiaInicial.getSelectedIndex() + 1;
        int mini = cboMesInicial.getSelectedIndex() + 1;
        int aini = cboAñoFinal.getSelectedIndex() + 2011;

        //Ingreso de las partes de la fecha final
        int dfin = cboDiaFinal.getSelectedIndex() + 1;
        int mfin = cboMesFinal.getSelectedIndex() + 1;
        int afin = cboAñoFinal.getSelectedIndex() + 2011;

        Calendar c = Calendar.getInstance();
        c.set(aini,mini,dini);
        long milis1 = c.getTimeInMillis();
        c.set(afin,mfin,dfin);
        long milis2 = c.getTimeInMillis();

        if(milis1 > milis2)
            return false;
        else
            return true;

    }

    private boolean validaRangoFecha(String fecha) {

        //Ingreso de las partes de la fecha inicial
        int dini = cboDiaInicial.getSelectedIndex() + 1;
        int mini = cboMesInicial.getSelectedIndex() + 1;
        int aini = cboAñoFinal.getSelectedIndex() + 2011;

        //Ingreso de las partes de la fecha final
        int dfin = cboDiaFinal.getSelectedIndex() + 1;
        int mfin = cboMesFinal.getSelectedIndex() + 1;
        int afin = cboAñoFinal.getSelectedIndex() + 2011;

        //Obtenemos las partes de la fecha de la venta
        StringTokenizer st = new StringTokenizer(fecha,"/");
        int dia = Integer.parseInt(st.nextToken());
        int mes = Integer.parseInt(st.nextToken());
        int año = Integer.parseInt(st.nextToken());

        //Operacion
        long milis1,milis2,milis3;
        Calendar c = Calendar.getInstance();
        c.set(aini,mini,dini);
        milis1 = c.getTimeInMillis();
        c.set(afin,mfin,dfin);
        milis2 = c.getTimeInMillis();
        c.set(año,mes,dia);
        milis3 = c.getTimeInMillis();

        if(milis3 >= milis1 && milis3 <= milis2)
            return true;
        else
            return false;

    }

    private void limpiarTabla() {

        while(datos.getRowCount() > 0)
            datos.removeRow(0);

        datos.setColumnCount(0);

    }

}
