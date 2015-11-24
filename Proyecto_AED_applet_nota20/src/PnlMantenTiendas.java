
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
import clases.Tienda;

public class PnlMantenTiendas extends JPanel implements ActionListener,
                                                                ItemListener {

    //Componentes del panel
    private JTextField txtCodigo;
    private JTextField txtDescripcion;
    private JTextField txtCapcidad;
    private JButton btnProcesar;
    private JComboBox cboOperacion;
    private JTable tblTiendas;
    private DefaultTableModel datos;
    private JTextArea txtS;
    private Principal pri;

    //Constructor
    public PnlMantenTiendas(Principal p) {
        setLayout(null);

        pri = p;

        JLabel lblCodigo = new JLabel("Código",SwingConstants.LEFT);
	lblCodigo.setBounds(40,20,90,20);
	add(lblCodigo);

	txtCodigo = new JTextField();
	txtCodigo.setBounds(140,20,100,20);
	txtCodigo.setEditable(false);
	add(txtCodigo);

	JLabel lblDescripcion = new JLabel("Descripción",SwingConstants.LEFT);
	lblDescripcion.setBounds(40,50,90,20);
	add(lblDescripcion);

	txtDescripcion = new JTextField();
	txtDescripcion.setBounds(140,50,250,20);
	add(txtDescripcion);

        JLabel lblCapacidad = new JLabel("Capacidad",SwingConstants.LEFT);
        lblCapacidad.setBounds(40,80,90,20);
        add(lblCapacidad);

        txtCapcidad = new JTextField();
        txtCapcidad.setBounds(140,80,100,20);
        add(txtCapcidad);

        datos = new DefaultTableModel();
	datos.addColumn("Código");
	datos.addColumn("Descripción");
	datos.addColumn("Capacidad");
	datos.addColumn("Cantidad Prendas");

	tblTiendas = new JTable(datos);

	JScrollPane scpScroll1 = new JScrollPane(tblTiendas);
	scpScroll1.setBounds(40,130,720,200);
	add(scpScroll1);

	JLabel lblOperacion = new JLabel("Operación",SwingConstants.LEFT);
	lblOperacion.setBounds(570,20,80,20);
	add(lblOperacion);

	String operaciones[] = {"Seleccione","Registrar","Consultar","Modificar"};

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
            case 0: JOptionPane.showMessageDialog(this,"¡Seleccione una operación!");
                    break;
            case 1: registrar();
                    break;
            case 2: consultar();
                    break;
            default: modificar();

        }
    }

    public void itemStateChanged(ItemEvent e) {

        txtCodigo.setEditable(false);
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtCapcidad.setText("");

        switch(cboOperacion.getSelectedIndex()) {
            case 0: break;
            case 1: txtCodigo.setText(pri.atien.getCodigoAutogenerado());
                    txtDescripcion.requestFocus();
                    break;
            default: txtCodigo.setText("");
                     txtCodigo.setEditable(true);
                     txtCodigo.requestFocus();

        }

    }

    private void registrar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        String descrip = txtDescripcion.getText();
        int capac = Integer.parseInt(txtCapcidad.getText());

        pri.atien.ingresar(new Tienda(cod,descrip,capac,0));
        JOptionPane.showMessageDialog(this,"¡Ingreso realizado!");
        listar();
        cboOperacion.setSelectedIndex(0);
        pri.atien.guardar();

    }

    private void consultar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Tienda t = pri.atien.buscar(cod);

        if(t != null) {
            txtS.setText("******* RESULTADO DE LA CONSULTA *******\n\n");
            txtS.append(" Código: "+t.getCodigo()+"\n");
            txtS.append(" Descripción: "+t.getDescripcion()+"\n");
            txtS.append(" Capacidad: "+t.getCapacidad()+"\n");
            txtS.append(" Cantidad de prendas: "+t.getOcupado());

            JOptionPane.showMessageDialog(this,"¡Consulta realizada!");
            cboOperacion.setSelectedIndex(0);

        } else
            JOptionPane.showMessageDialog(this,"¡El código no existe!");

    }

    private void modificar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Tienda t = pri.atien.buscar(cod);

        if(t != null) {
            String descrip = txtDescripcion.getText();
            int capac = Integer.parseInt(txtCapcidad.getText());

            t.setDescripcion(descrip);
            t.setCapacidad(capac);
            JOptionPane.showMessageDialog(this,"¡Modificación realizada!");
            listar();
            cboOperacion.setSelectedIndex(0);
            pri.atien.guardar();

        } else
            JOptionPane.showMessageDialog(this,"¡El código no existe!");

    }

    public void listar() {

        while(datos.getRowCount() > 0)
            datos.removeRow(0);

        for(int i=0;i<pri.atien.tamaño();i++) {
            Object fila[] = new Object[4];
            Tienda t = pri.atien.obtener(i);

            fila[0] = t.getCodigo();
            fila[1] = t.getDescripcion();
            fila[2] = t.getCapacidad();
            fila[3] = t.getOcupado();

            datos.addRow(fila);

        }
    }

}
