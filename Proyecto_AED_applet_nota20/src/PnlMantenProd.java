
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import clases.Producto;

public class PnlMantenProd extends JPanel implements ActionListener,
                                                        ItemListener {

    //Componentes del panel
    private JTextField txtCodigo;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtMarca;
    JComboBox cboCategoria;
    private JTable tblProductos;
    private DefaultTableModel datos;
    private JComboBox cboOperacion;
    private JButton btnProcesar;
    private JTextArea txtS;
    private Principal pri;

    //Constructor
    public PnlMantenProd(Principal p) {
	setLayout(null);

        pri = p;

	JLabel lblCodigo = new JLabel("C�digo",SwingConstants.LEFT);
	lblCodigo.setBounds(40,20,90,20);
	add(lblCodigo);

	txtCodigo = new JTextField();
	txtCodigo.setBounds(140,20,100,20);
	txtCodigo.setEditable(false);
	add(txtCodigo);

	JLabel lblDescripcion = new JLabel("Descripci�n",SwingConstants.LEFT);
	lblDescripcion.setBounds(40,50,90,20);
	add(lblDescripcion);

	txtDescripcion = new JTextField();
	txtDescripcion.setBounds(140,50,250,20);
	add(txtDescripcion);

	JLabel lblCategoria = new JLabel("Categor�a",SwingConstants.LEFT);
	lblCategoria.setBounds(40,80,90,20);
	add(lblCategoria);

	cboCategoria = new JComboBox();
	cboCategoria.setBounds(140,80,110,20);
	add(cboCategoria);

	JLabel lblPrecio = new JLabel("Precio",SwingConstants.LEFT);
	lblPrecio.setBounds(40,110,90,20);
	add(lblPrecio);

	txtPrecio = new JTextField();
	txtPrecio.setBounds(140,110,100,20);
	add(txtPrecio);

	JLabel lblMarca = new JLabel("Marca",SwingConstants.LEFT);
	lblMarca.setBounds(40,140,90,20);
	add(lblMarca);

	txtMarca = new JTextField();
	txtMarca.setBounds(140,140,250,20);
	add(txtMarca);

	datos = new DefaultTableModel();
	datos.addColumn("C�digo");
	datos.addColumn("Descripci�n");
	datos.addColumn("Categor�a");
	datos.addColumn("Precio");
	datos.addColumn("Marca");

	tblProductos = new JTable(datos);

	JScrollPane scpScroll1 = new JScrollPane(tblProductos);
	scpScroll1.setBounds(40,190,720,150);
	add(scpScroll1);

	JLabel lblOperacion = new JLabel("Operaci�n",SwingConstants.LEFT);
	lblOperacion.setBounds(570,20,80,20);
	add(lblOperacion);

	String operaciones[] = {"Seleccione","Registrar","Consultar",
		                "Modificar","Eliminar"};

	cboOperacion = new JComboBox(operaciones);
	cboOperacion.setBounds(660,20,100,20);
	cboOperacion.addItemListener(this);
	add(cboOperacion);

	btnProcesar = new JButton("Procesar");
	btnProcesar.setBounds(660,50,100,20);
	btnProcesar.addActionListener(this);
	add(btnProcesar);

	txtS = new JTextArea();
	txtS.setEditable(false);

	JScrollPane scpScroll2 = new JScrollPane(txtS);
	scpScroll2.setBounds(40,355,720,150);
	add(scpScroll2);

    }

	//****************************** MANEJADORES DE EVENTOS *******************
    public void itemStateChanged(ItemEvent e) {

        txtCodigo.setText("");
        txtDescripcion.setText("");
        cboCategoria.setSelectedIndex(0);
        txtPrecio.setText("");
        txtMarca.setText("");

        switch(cboOperacion.getSelectedIndex()) {
            case 0: break;
            case 1: txtCodigo.setEditable(false);
                    txtCodigo.setText(pri.aprod.getCodigoAutogenerado());
                    txtDescripcion.requestFocus();
                    break;
            default: txtCodigo.setEditable(true);
                     txtCodigo.requestFocus();

        }

    }

    public void actionPerformed(ActionEvent e) {

        switch(cboOperacion.getSelectedIndex()) {
            case 0: JOptionPane.showMessageDialog(this,"�Seleccione una operaci�n!");
                    break;
            case 1: registrar();
                    break;
            case 2: consultar();
		    break;
            case 3: modificar();
                    break;
            default: eliminar();

        }

    }

    //****************************** REGISTRO DE PRODUCTOS ************************
    private void registrar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        String descrip = txtDescripcion.getText();
        int cat = Integer.parseInt(cboCategoria.getSelectedItem().toString());
        double pre = Double.parseDouble(txtPrecio.getText());
        String marc = txtMarca.getText();

        pri.aprod.ingresar(new Producto(cod,descrip,cat,pre,marc));
        JOptionPane.showMessageDialog(this,"�Ingreso realizado!");
        listar();
        cboOperacion.setSelectedIndex(0);
        pri.aprod.guardar();

    }

    //****************************** CONSULTA DE PRODUCTOS ************************
    private void consultar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Producto p = pri.aprod.buscar(cod);

        if(p != null) {
            txtS.setText("****** RESULTADO DE LA CONSULTA ******\n\n");
            txtS.append("C�digo: "+p.getCodigo()+"\n");
            txtS.append("Descripci�n: "+p.getDescripcion()+"\n");
            txtS.append("Categor�a: "+p.getCategoria()+"\n");
            txtS.append("Precio: "+p.getPrecio()+"\n");
            txtS.append("Marca: "+p.getMarca());

            JOptionPane.showMessageDialog(this,"�Consulta realizada!");
            cboOperacion.setSelectedIndex(0);

        } else
            JOptionPane.showMessageDialog(this,"�El c�digo no existe!");

    }

    //***************************** MODIFICACION DE PRODUCTOS *********************
    private void modificar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Producto p = pri.aprod.buscar(cod);

        if(p != null) {
            String descrip = txtDescripcion.getText();
            int cat = Integer.parseInt(cboCategoria.getSelectedItem().toString());
            double pre = Double.parseDouble(txtPrecio.getText());
            String marc = txtMarca.getText();

            p.setDescripcion(descrip);
            p.setCategoria(cat);
            p.setPrecio(pre);
            p.setMarca(marc);

            JOptionPane.showMessageDialog(this,"Modificaci�n realizada");
            listar();
            cboOperacion.setSelectedIndex(0);
            pri.aprod.guardar();

        } else
            JOptionPane.showMessageDialog(this,"�El c�digo no existe!");

    }

    //***************************** ELIMINACION DE PRODUCTOS **********************
    private void eliminar() {

        int pos = -1;
        int cod = Integer.parseInt(txtCodigo.getText());

        for(int i=0;i<pri.aprod.tamano();i++) {
            Producto p = pri.aprod.obtener(i);
            if(p.getCodigo() == cod) {
                pri.aprod.eliminar(i);
                JOptionPane.showMessageDialog(this,"�Eliminaci�n realizada!");
                listar();
                pos = i;
                cboOperacion.setSelectedIndex(0);
                pri.aprod.guardar();
                break;

            }

        }

	if(pos == -1)
            JOptionPane.showMessageDialog(this,"�El c�digo no existe!");

    }

    //*************************** LISTADO DE PRODUCTOS ****************************
    public void listar() {

	while(datos.getRowCount() > 0)
            datos.removeRow(0);

        for(int i=0;i<pri.aprod.tamano();i++) {
            Object fila[] = new Object[5];
            Producto p = pri.aprod.obtener(i);

            fila[0] = p.getCodigo();
            fila[1] = p.getDescripcion();
            fila[2] = p.getCategoria();
            fila[3] = p.getPrecio();
            fila[4] = p.getMarca();

            datos.addRow(fila);
                    
        }

    }

}
