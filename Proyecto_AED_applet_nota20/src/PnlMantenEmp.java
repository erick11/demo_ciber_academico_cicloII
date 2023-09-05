
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
import clases.Empleado;

public class PnlMantenEmp extends JPanel implements  ActionListener,
                                                                 ItemListener {

    //Componentes del panel
    private JTextField txtCodigo;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTextField txtFecIng;
    private JTextField txtContrasena;
    JComboBox cboTienda;
    private JComboBox cboTipo;
    private JTable tblEmpleados;
    private DefaultTableModel datos;
    private JComboBox cboOperacion;
    private JButton btnProcesar;
    private JTextArea txtS;
    private Principal pri;

    //Constructor
    public PnlMantenEmp(Principal p) {
	setLayout(null);

        pri = p;

	JLabel lblCodigo = new JLabel("C�digo",SwingConstants.LEFT);
	lblCodigo.setBounds(40,20,90,20);
	add(lblCodigo);

	txtCodigo = new JTextField();
	txtCodigo.setBounds(140,20,100,20);
        txtCodigo.setEditable(false);
	add(txtCodigo);

	JLabel lblNombres = new JLabel("Nombres",SwingConstants.LEFT);
	lblNombres.setBounds(40,50,90,20);
	add(lblNombres);

	txtNombres = new JTextField();
	txtNombres.setBounds(140,50,200,20);
	add(txtNombres);

	JLabel lblApellidos = new JLabel("Apellidos",SwingConstants.LEFT);
	lblApellidos.setBounds(40,80,90,20);
	add(lblApellidos);

	txtApellidos = new JTextField();
	txtApellidos.setBounds(140,80,200,20);
	add(txtApellidos);

	JLabel lblTelefono = new JLabel("Tel�fono",SwingConstants.LEFT);
	lblTelefono.setBounds(40,110,90,20);
	add(lblTelefono);

	txtTelefono = new JTextField();
	txtTelefono.setBounds(140,110,100,20);
	add(txtTelefono);

	JLabel lblFechaIng = new JLabel("Fec. ingreso",SwingConstants.LEFT);
	lblFechaIng.setBounds(40,140,90,20);
	add(lblFechaIng);

	txtFecIng = new JTextField();
	txtFecIng.setBounds(140,140,100,20);
	add(txtFecIng);

	JLabel lblTienda = new JLabel("Tienda",SwingConstants.LEFT);
	lblTienda.setBounds(360,20,70,20);
	add(lblTienda);

	cboTienda = new JComboBox();
	cboTienda.setBounds(440,20,110,20);
	cboTienda.addItem("Seleccione");
	add(cboTienda);

	JLabel lblTipo = new JLabel("Tipo",SwingConstants.LEFT);
	lblTipo.setBounds(360,50,70,20);
	add(lblTipo);

	cboTipo = new JComboBox();
	cboTipo.setBounds(440,50,110,20);
	cboTipo.addItem("Seleccione");
        cboTipo.addItem("Administrador");
	cboTipo.addItem("Vendedor");
	add(cboTipo);

        JLabel lblContrasena = new JLabel("Contrasena",SwingConstants.LEFT);
        lblContrasena.setBounds(360,80,70,20);
        add(lblContrasena);

        txtContrasena = new JTextField();
        txtContrasena.setBounds(440,80,110,20);
        add(txtContrasena);

	datos = new DefaultTableModel();
	datos.addColumn("C�digo");
	datos.addColumn("Nombres");
	datos.addColumn("Apellidos");
	datos.addColumn("Tel�fono");
	datos.addColumn("Fec. ingreso");
	datos.addColumn("Tienda");
	datos.addColumn("Tipo");

	tblEmpleados = new JTable(datos);

	JScrollPane scpScroll1 = new JScrollPane(tblEmpleados);
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

    public void itemStateChanged(ItemEvent e) {

        txtCodigo.setEditable(false);
        txtCodigo.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtFecIng.setText("");
        cboTienda.setSelectedIndex(0);
        cboTipo.setSelectedIndex(0);
        txtContrasena.setText("");

        switch(cboOperacion.getSelectedIndex()) {
            case 0: break;
            case 1: txtCodigo.setText(pri.aemp.getCodigoAutogenerado());
                    txtNombres.requestFocus();
                    break;
            default: txtCodigo.setText("");
                     txtCodigo.setEditable(true);
                     txtCodigo.requestFocus();
                     
        }

    }

    //*********************** REGISTRO DE EMPLEADOS *******************************
    private void registrar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        String nom = txtNombres.getText();
        String ape = txtApellidos.getText();
        String tel = txtTelefono.getText();
        String fecing = txtFecIng.getText();
        int tiend = Integer.parseInt(cboTienda.getSelectedItem().toString());
        int tip = cboTipo.getSelectedIndex()-1;
        String pass = txtContrasena.getText();

        pri.aemp.ingresar(new Empleado(cod,nom,ape,tel,fecing,tiend,tip,pass));
        JOptionPane.showMessageDialog(this,"�Ingreso realizado!");
        listar();
        cboOperacion.setSelectedIndex(0);
        pri.aemp.guardar();
        
    }

    //********************* CONSULTA DE EMPLEADOS *********************************
    private void consultar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Empleado e = pri.aemp.buscar(cod);

        if(e != null) {
            txtS.setText("********* RESULTADO DE LA CONSULLTA **********\n\n");
            txtS.append(" C�digo: "+e.getCodigo()+"\n");
            txtS.append(" Nombres: "+e.getNombres()+"\n");
            txtS.append(" Apellidos: "+e.getApellidos()+"\n");
            txtS.append(" Tel�fono: "+e.getTelefono()+"\n");
            txtS.append(" Fecha Ingreso: "+e.getFechaing()+"\n");
            txtS.append(" Tienda: "+e.getTienda()+"\n");
            txtS.append(" Tipo: "+e.getTipo()+"\n");
            txtS.append(" Contrasena: "+e.getContrasena());

            JOptionPane.showMessageDialog(this,"�Consulta realizada!");
            cboOperacion.setSelectedIndex(0);

        } else
            JOptionPane.showMessageDialog(this,"El c�digo no existe");

    }

    //********************* MODIFICACION DE EMPLEADOS *****************************
    private void modificar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Empleado e = pri.aemp.buscar(cod);

        if(e != null) {
            String nom = txtNombres.getText();
            String ape = txtApellidos.getText();
            String tel = txtTelefono.getText();
            String fecing = txtFecIng.getText();
            int tiend = Integer.parseInt(cboTienda.getSelectedItem().toString());
            int tip = cboTipo.getSelectedIndex()-1;
            String pass = txtContrasena.getText();

            e.setNombres(nom);
            e.setApellidos(ape);
            e.setTelefono(tel);
            e.setFechaing(fecing);
            e.setTienda(tiend);
            e.setTipo(tip);
            e.setContrasena(pass);

            JOptionPane.showMessageDialog(this,"�Modificaci�n realizada!");
            listar();
            cboOperacion.setSelectedIndex(0);
            pri.aemp.guardar();

        } else
            JOptionPane.showMessageDialog(this,"�El c�digo no existe!");

    }

    //********************* ELIMINACION DE EMPLEADOS ******************************
    private void eliminar() {

        int pos = -1;
        int cod = Integer.parseInt(txtCodigo.getText());

        for(int i=0;i<pri.aemp.tamano();i++) {
            Empleado e = pri.aemp.obtener(i);
            if(e.getCodigo() == cod) {
                pri.aemp.eliminar(i);
                JOptionPane.showMessageDialog(this,"�Eliminaci�n realizada!");
                listar();
                pos = i;
                cboOperacion.setSelectedIndex(0);
                pri.aemp.guardar();
                break;

            }

        }

	if(pos == -1)
            JOptionPane.showMessageDialog(this,"�El c�digo no existe!");


    }

    //*********************** LISTADO DE EMPLEADOS ********************************
    public void listar() {

	while(datos.getRowCount() > 0)
            datos.removeRow(0);

        for(int i=0;i<pri.aemp.tamano();i++) {
            Object fila[] = new Object[7];
            Empleado e = pri.aemp.obtener(i);

            fila[0] = e.getCodigo();
            fila[1] = e.getNombres();
            fila[2] = e.getApellidos();
            fila[3] = e.getTelefono();
            fila[4] = e.getFechaing();
            fila[5] = e.getTienda();
            fila[6] = e.getTipo();

            datos.addRow(fila);

        }

    }

}
