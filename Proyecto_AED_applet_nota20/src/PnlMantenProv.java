
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
import clases.Proveedor;

public class PnlMantenProv extends JPanel implements ActionListener,
                                                                ItemListener {

    //Componentes del panel
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtRuc;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTable tblProveedores;
    private DefaultTableModel datos;
    private JComboBox cboOperacion;
    private JButton btnProcesar;
    private JTextArea txtS;
    private Principal pri;

    //Constructor
    public PnlMantenProv(Principal p) {
	setLayout(null);

        pri = p;

	JLabel lblCodigo = new JLabel("C�digo",SwingConstants.LEFT);
	lblCodigo.setBounds(40,20,90,20);
	add(lblCodigo);

	txtCodigo = new JTextField();
	txtCodigo.setBounds(140,20,100,20);
        txtCodigo.setEditable(false);
	add(txtCodigo);

	JLabel lblNombre = new JLabel("Nombre",SwingConstants.LEFT);
	lblNombre.setBounds(40,50,90,20);
	add(lblNombre);

	txtNombre = new JTextField();
	txtNombre.setBounds(140,50,250,20);
	add(txtNombre);

	JLabel lblRuc = new JLabel("RUC",SwingConstants.LEFT);
	lblRuc.setBounds(40,80,90,20);
	add(lblRuc);

	txtRuc = new JTextField();
	txtRuc.setBounds(140,80,100,20);
	add(txtRuc);

	JLabel lblTelefono = new JLabel("Tel�fono",SwingConstants.LEFT);
	lblTelefono.setBounds(40,110,90,20);
	add(lblTelefono);

	txtTelefono = new JTextField();
	txtTelefono.setBounds(140,110,100,20);
	add(txtTelefono);

	JLabel lblEmail = new JLabel("Email",SwingConstants.LEFT);
	lblEmail.setBounds(40,140,90,20);
	add(lblEmail);

	txtEmail = new JTextField();
	txtEmail.setBounds(140,140,150,20);
	add(txtEmail);

	datos = new DefaultTableModel();
	datos.addColumn("C�digo");
	datos.addColumn("Nombre");
	datos.addColumn("RUC");
	datos.addColumn("Tel�fono");
	datos.addColumn("Email");

	tblProveedores = new JTable(datos);

	JScrollPane scpScroll1 = new JScrollPane(tblProveedores);
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

        txtCodigo.setText("");
        txtCodigo.setEditable(false);
        txtNombre.setText("");
        txtRuc.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");

        switch(cboOperacion.getSelectedIndex()) {
            case 0: break;
            case 1: txtCodigo.setText(pri.aprov.getCodigoAutogenerado());
                    txtNombre.requestFocus();
                    break;
            default: txtCodigo.setEditable(true);
                     txtCodigo.setText("");
                     txtCodigo.requestFocus();

        }

    }

    //********************** INGRESO DE PROVEEDORES *******************************
    private void registrar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        String nom = txtNombre.getText();
        String ruc = txtRuc.getText();
        String tel = txtTelefono.getText();
        String email = txtEmail.getText();

        pri.aprov.ingresar(new Proveedor(cod,nom,ruc,tel,email));
        JOptionPane.showMessageDialog(this,"�Ingreso realizado!");
        listar();
        cboOperacion.setSelectedIndex(0);
        pri.aprov.guardar();

    }

    //********************* CONSULTA DE PROVEEDORES *******************************
    private void consultar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Proveedor p = pri.aprov.buscar(cod);

        if(p != null) {
            txtS.setText("****** RESULTADO DE LA CONSULTA ******\n\n");
            txtS.append("C�digo: "+p.getCodigo()+"\n");
            txtS.append("Nombre: "+p.getNombre()+"\n");
            txtS.append("Ruc: "+p.getRuc()+"\n");
            txtS.append("Tel�fono: "+p.getTelefono()+"\n");
            txtS.append("Email: "+p.getEmail());

            JOptionPane.showMessageDialog(this,"�Consulta realizada!");
            cboOperacion.setSelectedIndex(0);

        } else
            JOptionPane.showMessageDialog(this,"�El c�digo no existe!");

    }

    //********************* MODIFICACION DE PROVEEDORES ***************************
    private void modificar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Proveedor p = pri.aprov.buscar(cod);

        if(p != null) {
            String nom = txtNombre.getText();
            String ruc = txtRuc.getText();
            String tel = txtTelefono.getText();
            String email = txtEmail.getText();

            p.setNombre(nom);
            p.setRuc(ruc);
            p.setTelefono(tel);
            p.setEmail(email);

            JOptionPane.showMessageDialog(this,"Modificaci�n realizada");
            listar();
            cboOperacion.setSelectedIndex(0);
            pri.aprov.guardar();

        } else
            JOptionPane.showMessageDialog(this,"El c�digo no existe");
    }

    //******************** ELIMINACION DE PROVEEDORES *****************************
    private void eliminar() {

        int pos = -1;
        int cod = Integer.parseInt(txtCodigo.getText());

        for(int i=0;i<pri.aprov.tamano();i++) {
            Proveedor p = pri.aprov.obtener(i);
            if(p.getCodigo() == cod) {
                pri.aprov.eliminar(i);
                JOptionPane.showMessageDialog(this,"�Eliminaci�n realizada!");
                listar();
                pos = i;
                cboOperacion.setSelectedIndex(0);
                pri.aprov.guardar();
                break;

            }
        }

        if(pos == -1)
            JOptionPane.showMessageDialog(this,"El c�digo no existe");

    }

    //******************** LISTADO DE PROVEEDORES *********************************
    public void listar() {

	while(datos.getRowCount() > 0)
            datos.removeRow(0);

        for(int i=0;i<pri.aprov.tamano();i++) {
            Object fila[] = new Object[5];
            Proveedor p = pri.aprov.obtener(i);

            fila[0] = p.getCodigo();
            fila[1] = p.getNombre();
            fila[2] = p.getTelefono();
            fila[3] = p.getRuc();
            fila[4] = p.getEmail();

            datos.addRow(fila);

        }

    }

}
