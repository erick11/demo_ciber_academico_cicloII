
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import clases.Producto;
import clases.Proveedor;
import clases.Tienda;
import clases.Kardex;
import clases.ProductoAlmacen;
import clases.ProductoTienda;
import javax.swing.border.TitledBorder;


public class PnlMovimientos extends JPanel implements ActionListener,
                                                                ItemListener {

    //Componentes publicos del panel
    JTextField txtNumero;
    JTextField txtEmpleado;
    JTextField txtFecha;
    JComboBox cboCategoria;

    //Componentes privados del panel
    private JLabel lblTienProv;
    private JTextField txtCantidad;
    private JComboBox cboTienProv;
    private JComboBox cboTipoDoc;
    private JComboBox cboTransaccion;
    private JButton btnProcesar;
    private JList lstProductos;
    private DefaultListModel items;
    private Principal pri;

    //Componenetes de la tabla de productos en el almacen
    private JTable tblProductos;
    private DefaultTableModel datos;

    //Constructor
    public PnlMovimientos(Principal p) {
        setBounds(0,150,800,550);
        setLayout(null);

        pri = p;

        datos = new DefaultTableModel();
        datos.addColumn("Código Almacén");
        datos.addColumn("Proveedor");
        datos.addColumn("Código Producto");
        datos.addColumn("Stock");

        tblProductos = new JTable(datos);

        JScrollPane scpScroll1 = new JScrollPane(tblProductos);
        scpScroll1.setBounds(30,30,740,200);
        add(scpScroll1);

        JLabel lblNumero = new JLabel("Número",SwingConstants.LEFT);
        lblNumero.setBounds(50,270,80,20);
        add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(50,290,100,20);
        txtNumero.setEditable(false);
        add(txtNumero);

        JLabel lblTipoDoc = new JLabel("Tipo de documento",SwingConstants.LEFT);
        lblTipoDoc.setBounds(50,320,110,20);
        add(lblTipoDoc);

        String tipodoc[] = {"Seleccione","Boleta","Factura","Guía de remisión",
                           "Orden de compra"};

        cboTipoDoc = new JComboBox(tipodoc);
        cboTipoDoc.setBounds(50,340,130,20);
        add(cboTipoDoc);

        JLabel lblFecha = new JLabel("Fecha",SwingConstants.LEFT);
        lblFecha.setBounds(50,370,60,20);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(50,390,100,20);
        txtFecha.setEditable(false);
        add(txtFecha);

        JLabel lblEmpleado = new JLabel("Empleado",SwingConstants.LEFT);
        lblEmpleado.setBounds(50,420,70,20);
        add(lblEmpleado);

        txtEmpleado = new JTextField();
        txtEmpleado.setBounds(50,440,100,20);
        txtEmpleado.setEditable(false);
        add(txtEmpleado);

        lblTienProv = new JLabel("",SwingConstants.LEFT);
        lblTienProv.setBounds(50,470,110,20);
        add(lblTienProv);

        cboTienProv = new JComboBox();
        cboTienProv.setBounds(50,490,130,20);
        cboTienProv.addItem("Seleccione");
        add(cboTienProv);

        JLabel lblCategoria = new JLabel("Categoría de producto",SwingConstants.LEFT);
        lblCategoria.setBounds(220,270,130,20);
        add(lblCategoria);

        cboCategoria = new JComboBox();
        cboCategoria.setBounds(220,290,130,20);
        cboCategoria.addItemListener(this);
        add(cboCategoria);

        JLabel lblProducto = new JLabel("Producto",SwingConstants.LEFT);
        lblProducto.setBounds(220,320,80,20);
        add(lblProducto);

        items = new DefaultListModel();

        lstProductos =  new JList(items);
        lstProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scpScroll = new JScrollPane(lstProductos);
        scpScroll.setBounds(220,340,130,120);
        add(scpScroll);

        JLabel lblCantidad = new JLabel("Cantidad",SwingConstants.LEFT);
        lblCantidad.setBounds(220,470,70,20);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(220,490,100,20);
        add(txtCantidad);

        JLabel lblTransaccion = new JLabel("Transacción",SwingConstants.LEFT);
        lblTransaccion.setBounds(550,270,90,20);
        add(lblTransaccion);

        String transacciones[] = {"Seleccione","Registrar","Distribuir",
                                  "Devolver"};

        cboTransaccion = new JComboBox(transacciones);
        cboTransaccion.setBounds(640,270,100,20);
        cboTransaccion.addItemListener(this);
        add(cboTransaccion);

        btnProcesar = new JButton("Procesar");
        btnProcesar.setBounds(640,300,100,20);
        btnProcesar.addActionListener(this);
        add(btnProcesar);

        JLabel lblFondo = new JLabel();
        lblFondo.setBounds(30,250,740,270);
        lblFondo.setBorder(new TitledBorder("Transacciones"));
        add(lblFondo);

        setVisible(false);

    }

    //********************* MANEJADORES DE EVENTOS ********************************
    public void actionPerformed(ActionEvent e) {

        switch(cboTransaccion.getSelectedIndex()) {
            case 0: JOptionPane.showMessageDialog(this,"¡Seleccione una operación!");
                    break;
            case 1: registrar();
                    break;
            case 2: distribuir();
                    break;
            default: devolver();
        }

    }

    public void itemStateChanged(ItemEvent e) {

        if(e.getSource() == cboCategoria) {
            if(cboCategoria.getItemCount() != 0) {
                items.clear();

                switch(cboCategoria.getSelectedIndex()) {
                    case 0: break;
                    default: cargarProductos();
                }
                
            }

        } else {
            lblTienProv.setText("");
            cboTienProv.removeAllItems();
            cboTienProv.addItem("Seleccione");
            txtNumero.setText("");

            switch(cboTransaccion.getSelectedIndex()) {
                case 0: break;
                case 1: cargarProveedores();
                        lblTienProv.setText("Proveedor");
                        txtNumero.setText(pri.akard.getCodigoAutogenerado());
                        break;
                default: cargarTiendas();
                         lblTienProv.setText("Tiendas");
                         txtNumero.setText(pri.akard.getCodigoAutogenerado());
            }

        }

    }

    private void cargarProductos() {

        int cat = Integer.parseInt(cboCategoria.getSelectedItem().toString());

        for(int i=0;i<pri.aprod.tamaño();i++) {
            Producto p = pri.aprod.obtener(i);
            if(p.getCategoria() == cat)
                items.addElement(p.getCodigo());

        }

    }

    private void cargarProveedores() {

        for(int i=0;i<pri.aprov.tamaño();i++) {
            Proveedor p = pri.aprov.obtener(i);
            cboTienProv.addItem(p.getCodigo());
        }

    }

    private void cargarTiendas() {

        for(int i=0;i<pri.atien.tamaño();i++) {
           Tienda t = pri.atien.obtener(i);
           cboTienProv.addItem(t.getCodigo());

        }

    }

    //********************* REGISTRO DE MERCADERIA ********************************
    private void registrar() { //envia 1 al generarKardex()

        //Lectura de datos desde la interfaz grafica
        int prod = Integer.parseInt(lstProductos.getSelectedValue().toString());
        int prov = Integer.parseInt(cboTienProv.getSelectedItem().toString());
        int cant = Integer.parseInt(txtCantidad.getText());

        //Busqueda del producto en el almacen
        ProductoAlmacen pa = pri.aproal.buscar1(prod,prov);

        //Si es que se ha ingresado el producto al almacen
        if(pa != null) {
            pa.setStock(pa.getStock()+cant);

        //Si es que nunca se ha ingresado el producto al almacen
        } else {
            ProductoAlmacen nuevo = new ProductoAlmacen(10001,prod,prov,cant);
            pri.aproal.ingresar(nuevo);

        }

         //Genera el documento kardex
         generarKardex(1,prov,prod,cant);

        //Guarda los datos del registro en el archivo
        pri.aproal.guardar();

        //muestra mensaje de registro
        JOptionPane.showMessageDialog(this,"¡Registro realizado!");

        //Lista todos los productos del almacen en la tabla
        listar();

    }

    //******************** DISTRIBUCION DE MERCADERIA *****************************
    private void distribuir() { //envia 2 al generarKardex()

       //Lectura de datos desde la interfaz grafica
       int prod = Integer.parseInt(lstProductos.getSelectedValue().toString());
       int cantreq = Integer.parseInt(txtCantidad.getText());
       //Cantidad disponible del producto en el almacén
       int cantdisp = pri.aproal.cantidadProducto(prod);

       //Si la cantidad disponible satisface la cantidad requerida
       if(cantdisp >= cantreq) {

           //Lectura de datos desde la interfaz grafica
           int tiend = Integer.parseInt(cboTienProv.getSelectedItem().toString());
           //Busqueda de la tienda
           Tienda t = pri.atien.buscar(tiend);
           //Capacidad de almacenamiento actual de la tienda
           int capacdisp = t.getCapacidad() - t.getOcupado();

           //Si es que se puede almacenar la cantidad requerida en la tienda
           if(cantreq <= capacdisp) {

               //Busqueda del producto en la tienda
               ProductoTienda pt = pri.aproti.buscar(tiend,prod);

               //Si es que nunca se ha ingresado el producto en la tienda
               if(pt == null) {
                   pri.aproti.ingresar(new ProductoTienda(prod,tiend,cantreq));

               //Si es que ya se ingreso el producto en la tienda
               } else
                   pt.setStock(pt.getStock() + cantreq);

               //Genera el documento Kardex
               generarKardex(2,tiend,prod,cantreq);

               //Actualiza la cantidad de prendas en el almacén
               t.setOcupado(t.getOcupado() + cantreq);

               //Se disminuye el stock del producto en el almacen
               for(int i=0;i<pri.aproal.tamaño();i++) {
                   ProductoAlmacen pa = pri.aproal.buscar2(prod);
                   if(cantreq != 0)
                       if(pa.getStock() >= cantreq){

                           pa.setStock(pa.getStock()-cantreq);
                           cantreq = 0;

                       } else {

                           cantreq -= pa.getStock();
                           pa.setStock(0);

                       }

               }

               //Guarda los cambios de capacidad y almacenamiento de las tiendas
               pri.atien.guardar();

               //Guarda los datos de la operacion en los archivos
               pri.aproal.guardar();
               pri.aproti.guardar();

               //Actualiza el contenido de la tabla
               listar();

               //Muestra mensaje de distrubucion
               JOptionPane.showMessageDialog(this,"¡Distribución realizada!");

           //Si es que no se puede almacenar la cantidad requerida en la tienda
           } else

               switch(capacdisp) {
                   case 0: JOptionPane.showMessageDialog(this,"¡Ya no se pueden "+
                                        "ingresar más prendas en esta tienda!");
                           break;
                   default: JOptionPane.showMessageDialog(this,"¡Solo se pueden "+
                                  "ingresar "+capacdisp+" prendas en la tienda!");

               }

       //Si la cantidad disponible no satisface la cantidad requerida
       } else

           switch(cantdisp) {
               case 0: JOptionPane.showMessageDialog(this,"¡No hay este tipo "+
                                "de prenda en el almacén!");
                       break;
               default: JOptionPane.showMessageDialog(this,"¡Solo existen "+cantdisp+
                                        " prendas de este tipo en el almacén!");

           }

    }

    //******************** DEVOLUCION DE MERCADERIA *******************************
    private void devolver() { //envia 3 al generarKardex()

        
        












    }

    private void generarKardex(int i,int tienprov,int prod,int cant) {

        //Referencia de tipo kardex
        Kardex nuevo = null;

        //Ingreso de datos desde la interfaz grafica
        int num = Integer.parseInt(txtNumero.getText());
        int oper = cboTransaccion.getSelectedIndex()-1;
        String fec = txtFecha.getText();
        int emp = Integer.parseInt(txtEmpleado.getText());
        int tipdoc = cboTipoDoc.getSelectedIndex()-1;
        double prec = pri.aprod.getPrecio(prod);
        double tot = cant*prec;

        //Si la tansaccion es de registro
        if(i == 1) {
            String tipoper = "Entrada";
            nuevo = new Kardex(num,tipoper,oper,fec,emp,tipdoc,tienprov,prod,cant,
                               prec,tot);

        //Si la transaccion es de distribucion
        } else if(i == 2) {
            String tipoper = "Salida";
            nuevo = new Kardex(num,tipoper,oper,fec,emp,tipdoc,tienprov,prod,cant,
                               prec,tot);

        //Si la transaccion es de devolucion
        } else {
            String tipoper = "Entrada";
            nuevo = new Kardex(num,tipoper,oper,fec,emp,tipdoc,tienprov,prod,cant,
                               prec,tot);

        }

        //Ingreso del kardex al Array
        pri.akard.ingresar(nuevo);

        //Guarda los datos del kardex en el archivo
        pri.akard.guardar();

    }

    //********************* LISTADO DE PRODUCTOS EN EL ALMACEN *********************
    public void listar() {

        while(datos.getRowCount() > 0)
            datos.removeRow(0);

        for(int i=0;i<pri.aproal.tamaño();i++) {
            ProductoAlmacen pa = pri.aproal.obtener(i);
            Object fila[] = new Object[4];

            fila[0] = pa.getAlmacen();
            fila[1] = pa.getProveedor();
            fila[2] = pa.getProducto();
            fila[3] = pa.getStock();

            datos.addRow(fila);

        }

    }

}
