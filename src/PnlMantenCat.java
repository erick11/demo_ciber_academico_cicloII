
import clases.Categoria;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PnlMantenCat extends JPanel implements ActionListener,ItemListener {

    //Componentes del panel
    private JTextField txtCodigo;
    private JTextField txtDescripcion;
    private JTable tblCategorias;
    private DefaultTableModel datos;
    private JButton btnProcesar;
    private JComboBox cboOperacion;
    private JTextArea txtS;
    private Principal pri;

    //Constructor
    public PnlMantenCat(Principal p) {
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

        datos = new DefaultTableModel();
        datos.addColumn("Código");
        datos.addColumn("Descripción");

        tblCategorias = new JTable(datos);

        JScrollPane scpScroll1 = new JScrollPane(tblCategorias);
        scpScroll1.setBounds(40,100,720,200);
        add(scpScroll1);

        txtS = new JTextArea();
        txtS.setEditable(false);

        JScrollPane scpScroll2 = new JScrollPane(txtS);
        scpScroll2.setBounds(40,320,720,185);
        add(scpScroll2);

        JLabel lblOperacion = new JLabel("Operación",SwingConstants.LEFT);
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

    }

    public void actionPerformed(ActionEvent e) {

        switch(cboOperacion.getSelectedIndex()) {
            case 0: JOptionPane.showMessageDialog(this,"¡Seleccione una operación!");
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
        txtDescripcion.setText("");

        switch(cboOperacion.getSelectedIndex()) {
            case 0: break;
            case 1: txtCodigo.setText(pri.acat.getCodigoAutogenerado());
                    txtDescripcion.requestFocus();
                    break;
            default: txtCodigo.setEditable(true);
                     txtCodigo.requestFocus();
                     
        }
    }

    //************************ REGISTRO DE CATEGORIAS *****************************
    private void registrar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        String descrip = txtDescripcion.getText();

        pri.acat.ingresar(new Categoria(cod,descrip));
        JOptionPane.showMessageDialog(this,"¡Ingreso realizado!");
        listar();
        cboOperacion.setSelectedIndex(0);
        pri.acat.guardar();

    }

    //*********************** CONSULTA DE CATEGORIAS ******************************
    private void consultar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Categoria c = pri.acat.buscar(cod);

        if(c != null) {
            txtS.setText("****** RESULTADO DE LA CONSULTA ******\n\n");
            txtS.append(" Código: "+c.getCodigo()+"\n");
            txtS.append(" Descripción: "+c.getDescripcion());

            JOptionPane.showMessageDialog(this,"¡Consulta realizada!");

            cboOperacion.setSelectedIndex(0);

        } else
            JOptionPane.showMessageDialog(this,"¡El código no existe!");

    }

    //*********************** MODIFICACION DE CATEGORIAS **************************
    private void modificar() {

        int cod = Integer.parseInt(txtCodigo.getText());
        Categoria c = pri.acat.buscar(cod);

        if(c != null) {
            String descrip = txtDescripcion.getText();
            c.setDescripcion(descrip);

            JOptionPane.showMessageDialog(this,"¡Modificación realizada!");
            listar();
            cboOperacion.setSelectedIndex(0);
            pri.acat.guardar();

        } else
            JOptionPane.showMessageDialog(this,"¡El código no existe!");

    }

    //********************** ELIMINACION DE CATEGORIAS ****************************
    private void eliminar() {

        int pos = -1;
        int cod = Integer.parseInt(txtCodigo.getText());

        for(int i=0;i<pri.acat.tamaño();i++) {
            Categoria c = pri.acat.obtener(i);
            if(c.getCodigo() == cod) {
                pri.acat.eliminar(i);
                JOptionPane.showMessageDialog(this,"¡Eliminación realizada!");
                listar();
                pos = i;
                cboOperacion.setSelectedIndex(0);
                pri.acat.guardar();
                break;

            }
        }

        if(pos == -1)
            JOptionPane.showMessageDialog(this,"El código no existe");
    }

    //*********************** LISTADO DE CATEGORIAS *******************************
    public void listar() {

        while(datos.getRowCount() > 0)
            datos.removeRow(0);

        for(int i=0;i<pri.acat.tamaño();i++) {
            Object fila[] = new Object[2];
            Categoria c = pri.acat.obtener(i);

            fila[0] = c.getCodigo();
            fila[1] = c.getDescripcion();

            datos.addRow(fila);

        }
    }

}
