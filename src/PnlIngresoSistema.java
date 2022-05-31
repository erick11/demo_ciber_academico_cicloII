
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import clases.Tienda;
import utileria.Fecha;

public class PnlIngresoSistema extends JPanel implements ActionListener {

    //Componenetes del panel
    JTextField txtUsuario;
    JPasswordField pwfContraseña;
    private JButton btnIngresar;
    private Principal pri;

    //Constructor
    public PnlIngresoSistema(Principal p) {
        setBounds(0,125,800,575);
        setLayout(null);

        pri = p;

        JLabel lblUsuario = new JLabel("Usuario",SwingConstants.LEFT);
        lblUsuario.setBounds(290,300,100,20);
        lblUsuario.setFont(new Font("seriff",Font.BOLD,16));
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(400,300,110,20);
        add(txtUsuario);

        JLabel lblContraseña = new JLabel("Contraseña",SwingConstants.LEFT);
        lblContraseña.setBounds(290,350,100,20);
        lblContraseña.setFont(new Font("seriff",Font.BOLD,16));
        add(lblContraseña);

        pwfContraseña = new JPasswordField();
        pwfContraseña.setBounds(400,350,110,20);
        pwfContraseña.setEchoChar('*');
        add(pwfContraseña);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(340,400,120,25);
        btnIngresar.setFont(new Font("seriff",Font.BOLD,14));
        btnIngresar.addActionListener(this);
        add(btnIngresar);

        JLabel lblFondo = new JLabel(new ImageIcon("Imagenes/FondoIngreso.jpg"));
        lblFondo.setBounds(0,0,800,575);
        add(lblFondo);
        
    }

    public void actionPerformed(ActionEvent e) {

        //Estructura que permite la validación de usuarios, comparando el user
        //y el password. Además, determina el tipo de usuario que es para asi
        //mostrar los componentes correspondientes.

        int user = getUsuario();
        String pass = getContraseña();
        int cont = 0;

        if(user != -1) {
            for(int i=0;i<pri.aemp.tamaño();i++)
                if(pri.aemp.obtener(i).getCodigo() == user)
                    if(pri.aemp.obtener(i).getContraseña().equals(pass)) {
                        cont++;

                        switch(pri.aemp.obtener(i).getTipo()) {
                            case 0: pri.btnMovimientos.setEnabled(true);
                                    pri.btnReportes.setEnabled(true);
                                    pri.pnlMovimientos.txtEmpleado.setText(""+
                                            pri.aemp.obtener(i).getCodigo());
                                    pri.btnMantenimiento.setEnabled(true);
                                    pri.lblFondo.setIcon(new ImageIcon("Imagenes/Fondo_administrador.jpg"));
                                    break;

                            default: Tienda t = pri.atien.buscar(
                                        pri.aemp.obtener(i).getTienda());
                                     pri.pnlVentas.txtCodigotiend.setText(""+t.getCodigo());
                                     pri.pnlVentas.txtDescripcion.setText(t.getDescripcion());
                                     pri.pnlVentas.txtCodigoEmp.setText(""+
                                             pri.aemp.obtener(i).getCodigo());
                                     pri.pnlVentas.txtNombreEmp.setText(
                                             pri.aemp.obtener(i).getNombres());
                                     pri.pnlVentas.txtApellidoEmp.setText(
                                             pri.aemp.obtener(i).getApellidos());
                                     pri.pnlVentas.txtFecha.setText(Fecha.getFecha());
                                     pri.btnVentas.setEnabled(true);
                                     pri.lblFondo.setIcon(new ImageIcon("Imagenes/Fondo_vendedor.jpg"));

                        }

                        setVisible(false);
                        pri.tbrHerramientas.setVisible(true);
                        JOptionPane.showMessageDialog(pri,"¡Bienvenido "+
                                pri.aemp.obtener(i).getNombres()+" "+
                                pri.aemp.obtener(i).getApellidos()+"!");

                }
        }

        if(cont == 0)
            JOptionPane.showMessageDialog(this,"¡Credenciales incorrectas!");

    }

    private String getContraseña() {
        StringBuffer sb = new StringBuffer("");
        char caracteres[] = pwfContraseña.getPassword();

        for(char c : caracteres)
            sb.append(c);

        return sb.toString();

    }

    private int getUsuario() {
        try {
            return Integer.parseInt(txtUsuario.getText());
        } catch(Exception e) {
            return -1;
        }

   }

}
