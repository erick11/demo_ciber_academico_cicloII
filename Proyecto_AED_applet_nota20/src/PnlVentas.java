
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import clases.Producto;
import clases.ProductoTienda;
import clases.Kardex;

public class PnlVentas extends JPanel implements ActionListener,ItemListener {

    //Componentes del panel
    JTextField txtCodigotiend;
    JTextField txtDescripcion;
    JTextField txtCodigoEmp;
    JTextField txtNombreEmp;
    JTextField txtApellidoEmp;
    JTextField txtFecha;
    JComboBox cboCategorias;
    private JButton btnAgregar;
    private JButton btnVender;
    private JList lstProductos;
    private DefaultListModel items;
    private JTextField txtCantidad;
    private JTextField txtTotal;
    private JComboBox cboComprobante;
    private JTable tblVenta;
    private DefaultTableModel datos;
    private Principal pri;

    //Constructor
    public PnlVentas(Principal p) {
        setBounds(0,150,800,550);
        setLayout(null);

        pri = p;

        JLabel lblCodigoTiend = new JLabel("Código",SwingConstants.LEFT);
        lblCodigoTiend.setBounds(30,40,90,20);
        add(lblCodigoTiend);

        txtCodigotiend = new JTextField();
        txtCodigotiend.setBounds(130,40,100,20);
        txtCodigotiend.setEditable(false);
        add(txtCodigotiend);

        JLabel lblDescripcion = new JLabel("Descripción",SwingConstants.LEFT);
        lblDescripcion.setBounds(30,70,90,20);
        add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(130,70,250,20);
        txtDescripcion.setEditable(false);
        add(txtDescripcion);

        JLabel lblFondo1 = new JLabel();
        lblFondo1.setBounds(20,20,760,80);
        lblFondo1.setBorder(new TitledBorder("Tienda"));
        add(lblFondo1);

        JLabel lblCodigoEmp = new JLabel("Código",SwingConstants.LEFT);
        lblCodigoEmp.setBounds(30,130,90,20);
        add(lblCodigoEmp);

        txtCodigoEmp = new JTextField();
        txtCodigoEmp.setBounds(130,130,100,20);
        txtCodigoEmp.setEditable(false);
        add(txtCodigoEmp);

        JLabel lblNombreEmp = new JLabel("Nombres",SwingConstants.LEFT);
        lblNombreEmp.setBounds(30,160,90,20);
        add(lblNombreEmp);

        txtNombreEmp = new JTextField();
        txtNombreEmp.setBounds(130,160,250,20);
        txtNombreEmp.setEditable(false);
        add(txtNombreEmp);

        JLabel lblApellidoEmp = new JLabel("Apellidos",SwingConstants.LEFT);
        lblApellidoEmp.setBounds(30,190,90,20);
        add(lblApellidoEmp);

        txtApellidoEmp = new JTextField();
        txtApellidoEmp.setBounds(130,190,250,20);
        txtApellidoEmp.setEditable(false);
        add(txtApellidoEmp);

        JLabel lblFondo2 = new JLabel();
        lblFondo2.setBounds(20,110,760,110);
        lblFondo2.setBorder(new TitledBorder("Empleado"));
        add(lblFondo2);

        JLabel lblCategoria = new JLabel("Categoría",SwingConstants.LEFT);
        lblCategoria.setBounds(45,250,80,20);
        add(lblCategoria);

        cboCategorias = new JComboBox();
        cboCategorias.setBounds(45,270,100,20);
        cboCategorias.addItemListener(this);
        add(cboCategorias);

        JLabel lblProductos = new JLabel("Productos",SwingConstants.LEFT);
        lblProductos.setBounds(45,300,80,20);
        add(lblProductos);

        items = new DefaultListModel();

        lstProductos = new JList(items);
        lstProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scpScroll1 = new JScrollPane(lstProductos);
        scpScroll1.setBounds(45,320,100,120);
        add(scpScroll1);

        JLabel lblCantidad = new JLabel("Cantidad",SwingConstants.LEFT);
        lblCantidad.setBounds(45,450,80,20);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(45,470,100,20);
        add(txtCantidad);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(45,500,100,20);
        btnAgregar.addActionListener(this);
        add(btnAgregar);

        JLabel lblFondo3 = new JLabel();
        lblFondo3.setBounds(20,230,150,300);
        lblFondo3.setBorder(new TitledBorder("Productos"));
        add(lblFondo3);

        datos = new DefaultTableModel();
        datos.addColumn("Producto");
        datos.addColumn("Descripción");
        datos.addColumn("Precio");
        datos.addColumn("Cantidad");
        datos.addColumn("SubTotal");

        tblVenta = new JTable(datos);

        JScrollPane scpScroll2 = new JScrollPane(tblVenta);
        scpScroll2.setBounds(210,270,550,170);
        add(scpScroll2);

        JLabel lblComprobante = new JLabel("Comprobante",SwingConstants.LEFT);
        lblComprobante.setBounds(210,460,80,20);
        add(lblComprobante);

        String comprobantes[] = {"Seleccione","Boleta","Factura"};

        cboComprobante = new JComboBox(comprobantes);
        cboComprobante.setBounds(300,460,100,20);
        add(cboComprobante);

        JLabel lblFecha = new JLabel("Fecha",SwingConstants.LEFT);
        lblFecha.setBounds(210,500,80,20);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(300,500,100,20);
        txtFecha.setEditable(false);
        add(txtFecha);

        JLabel lblTotal = new JLabel("Total",SwingConstants.LEFT);
        lblTotal.setBounds(420,460,40,20);
        add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(470,460,100,20);
        txtTotal.setEditable(false);
        add(txtTotal);

        btnVender = new JButton("Vender");
        btnVender.setBounds(660,500,100,20);
        btnVender.addActionListener(this);
        add(btnVender);

        JLabel lblFondo4 = new JLabel();
        lblFondo4.setBounds(190,230,590,300);
        lblFondo4.setBorder(new TitledBorder("Detalle Venta"));
        add(lblFondo4);

        setVisible(false);

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnAgregar)
            agregarProducto();

        else
            vender();

    }

    public void itemStateChanged(ItemEvent e) {
        //Solo es invocado por el combo de categorias.
        items.clear();

        if(cboCategorias.getItemCount() > 1 && cboCategorias.getSelectedIndex() != 0) {
            int cat = Integer.parseInt(cboCategorias.getSelectedItem().toString());
            int tiend = Integer.parseInt(txtCodigotiend.getText());

            for(int i=0;i<pri.aproti.tamaño();i++)
                if(pri.aproti.obtener(i).getTienda() == tiend) {
                    Producto p = pri.aprod.buscar(pri.aproti.obtener(i).getProducto());
                    if(p.getCategoria() == cat)
                        items.addElement(p.getCodigo());

                }

        }

    }

    //******************** OBTIENE LA CANTIDAD DE LA GUI **************************
    private int getCantidad() {

        try {
            return Integer.parseInt(txtCantidad.getText());

        } catch(Exception e) {
            return -1;

        }
    }

    //*********************** AGREGA PRODUCTOS A LA VENTA *************************
    private void agregarProducto() {

        if(lstProductos.getSelectedValue() == null)
            JOptionPane.showMessageDialog(this,"¡Seleccione un producto!");

        else if(getCantidad() < 1)
            JOptionPane.showMessageDialog(this,"¡Cantidad incorrecta!");

        else {
            int cant = Integer.parseInt(txtCantidad.getText());
            int tiend = Integer.parseInt(txtCodigotiend.getText());
            int prod = Integer.parseInt(lstProductos.getSelectedValue().toString());

            ProductoTienda pt = pri.aproti.buscar(tiend,prod);

            if(cant > pt.getStock())
                switch(pt.getStock()) {
                    case 0: JOptionPane.showMessageDialog(this,"¡Producto agotado!");
                            break;
                    default: JOptionPane.showMessageDialog(this,"¡Solo hay "+
                            pt.getStock()+" prendas de este tipo en la tienda!");

                }

            else {
                Producto p = pri.aprod.buscar(prod);
                double subtotal;

                Object fila[] = new Object[5];
                fila[0] = prod;
                fila[1] = p.getDescripcion();
                fila[2] = p.getPrecio();
                fila[3] = cant;
                fila[4] = p.getPrecio()*cant;

                datos.addRow(fila);

                if(txtTotal.getText().equals(""))
                    subtotal = 0;
                else
                    subtotal = Double.parseDouble(txtTotal.getText());

                txtTotal.setText(""+(subtotal + p.getPrecio()*cant));

                //Disminuye el stock del producto en la tienda
                pt.setStock(pt.getStock() - cant);
                //Disminuye la cantidad de prendas de la tienda
                pri.atien.buscar(tiend).setOcupado(pri.atien.buscar(tiend).getOcupado()
                        - cant);

                //Guarda la data de las operaciones en archivos
                pri.aproti.guardar();
                pri.atien.guardar();

            }

        }

    }

    //************************** VENTA DE PRODUCTOS *******************************
    private void vender() {

        if(datos.getRowCount() != 0) {
            int tiend = Integer.parseInt(txtCodigotiend.getText());
            int emp = Integer.parseInt(txtCodigoEmp.getText());
            String fec = txtFecha.getText();
            int tipdoc = cboComprobante.getSelectedIndex()-1;
            
            int filas = datos.getRowCount();

            for(int i=0;i<filas;i++) {
                int num = Integer.parseInt(pri.akard.getCodigoAutogenerado());
                int prod = Integer.parseInt(datos.getValueAt(i,0).toString());
                double prec = Double.parseDouble(datos.getValueAt(i,2).toString());
                int cant = Integer.parseInt(datos.getValueAt(i,3).toString());
                double tot = Double.parseDouble(datos.getValueAt(i,4).toString());

                Kardex nuevo = new Kardex(num,"Salida",3,fec,emp,tipdoc,tiend,
                                prod,cant,prec,tot);
                pri.akard.ingresar(nuevo);
            }

            //Guarda los datos de la venta en los archivos
            pri.akard.guardar();
            //Restaura el modulo para otra venta
            restaurar();

            JOptionPane.showMessageDialog(this,"¡Venta realizada!");
            
        } else
            JOptionPane.showMessageDialog(this,"¡Agregue productos a la venta!");

    }

    private void restaurar() {

        cboCategorias.setSelectedIndex(0);
        txtCantidad.setText("");
        cboComprobante.setSelectedIndex(0);
        txtTotal.setText("");

        while(datos.getRowCount() > 0)
            datos.removeRow(0);

    }

}
