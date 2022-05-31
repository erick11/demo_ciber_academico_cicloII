import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import arreglos.*;
import clases.Categoria;
import utileria.Fecha;

public class Principal extends JPanel implements ActionListener {

    //Atributos
    JButton btnMantenimiento;
    JButton btnMovimientos;
    JButton btnReportes;
    JButton btnVentas;
    JButton btnLogOut;
    JToolBar tbrHerramientas;
    JLabel lblFondo;

     //Componentes del Applet
    PnlMantenimiento pnlMantenimiento;
    PnlMovimientos pnlMovimientos;
    PnlReportes pnlReportes;
    PnlVentas pnlVentas;
    PnlIngresoSistema pnlIngresoSistema;

    //Arrays del Appelt
    ArregloCategorias acat = new ArregloCategorias();
    ArregloProductos aprod = new ArregloProductos();
    ArregloEmpleados aemp = new ArregloEmpleados();
    ArregloProveedores aprov = new ArregloProveedores();
    ArregloKardex akard = new ArregloKardex();
    ArregloTienda atien = new ArregloTienda();
    ArregloProdAlma aproal = new ArregloProdAlma();
    ArregloProdTiend aproti = new ArregloProdTiend();

    public void init() {
    	try {
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch(Exception e) {}
    	
        setSize(800,700);
        setLayout(null);

        //Se carga la data de los archivos a sus respectivos arrays
        acat.cargar();
        aprod.cargar();
        aemp.cargar();
        aprov.cargar();
        atien.cargar();
        akard.cargar();
        aproal.cargar();
        aproti.cargar();
        
        JLabel lblLogo = new JLabel(new ImageIcon("Imagenes/Logo.jpg"),SwingConstants.CENTER);
        lblLogo.setBounds(0,0,800,125);
        lblLogo.setOpaque(true);
        lblLogo.setBackground(Color.WHITE);
        add(lblLogo);

        pnlMantenimiento = new PnlMantenimiento(this);
        add(pnlMantenimiento);

        pnlMovimientos = new PnlMovimientos(this);
        add(pnlMovimientos);

        pnlReportes = new PnlReportes(this);
        add(pnlReportes);

        pnlVentas = new PnlVentas(this);
        add(pnlVentas);

        pnlIngresoSistema = new PnlIngresoSistema(this);        add(pnlIngresoSistema);

        btnMantenimiento = new JButton("Mantenimiento");
        btnMantenimiento.setEnabled(false);
        btnMantenimiento.addActionListener(this);

        btnMovimientos = new JButton("Movimientos");
        btnMovimientos.setEnabled(false);
        btnMovimientos.addActionListener(this);

        btnReportes = new JButton("Reportes");
        btnReportes.setEnabled(false);
        btnReportes.addActionListener(this);

        btnVentas = new JButton("Ventas");
        btnVentas.setEnabled(false);
        btnVentas.addActionListener(this);

        btnLogOut = new JButton("LogOut");
        btnLogOut.addActionListener(this);

        tbrHerramientas = new JToolBar();
        tbrHerramientas.setBounds(0,125,800,25);
        tbrHerramientas.add(btnMantenimiento);
        tbrHerramientas.add(btnMovimientos);
        tbrHerramientas.add(btnReportes);
        tbrHerramientas.add(btnVentas);
        tbrHerramientas.add(btnLogOut);
        tbrHerramientas.setBackground(Color.BLUE);
        tbrHerramientas.setVisible(false);
        add(tbrHerramientas);

        crearFondo();

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        pnlMantenimiento.setVisible(false);
        pnlMovimientos.setVisible(false);
        pnlReportes.setVisible(false);

        if(e.getSource() == btnMantenimiento)
            pnlMantenimiento.setVisible(true);

        else if(e.getSource() == btnMovimientos) {
            cargarCategorias(pnlMovimientos.cboCategoria);
            pnlMovimientos.txtFecha.setText(Fecha.getFecha());
            pnlMovimientos.listar();
            pnlMovimientos.setVisible(true);
          
        }

        else if(e.getSource() == btnReportes)
            pnlReportes.setVisible(true);

        else if(e.getSource().equals(btnVentas)) {
            cargarCategorias(pnlVentas.cboCategorias);
            pnlVentas.setVisible(true);

        }
            

        else {
            logOut();
            
        }

    }

    private void cargarCategorias(JComboBox x) {

        x.removeAllItems();
        x.addItem("Seleccione");
        
        for(int i=0;i<acat.tamaño();i++) {
            Categoria c = acat.obtener(i);
            x.addItem(c.getCodigo());

        }

    }

    private void crearFondo() {

        lblFondo = new JLabel();
        lblFondo.setBounds(0,150,800,550);
        add(lblFondo);

    }

    private void logOut() {

        int aceptacion = JOptionPane.showConfirmDialog(this,"¿Desea cerrar su sesión?",
                "Cerrar sesión",JOptionPane.YES_NO_OPTION);

        if(aceptacion == 0) {
            pnlIngresoSistema.txtUsuario.setText("");
            pnlIngresoSistema.pwfContraseña.setText("");
            tbrHerramientas.setVisible(false);
            btnMantenimiento.setEnabled(false);
            btnMovimientos.setEnabled(false);
            btnReportes.setEnabled(false);
            btnVentas.setEnabled(false);
            pnlVentas.setVisible(false);
            pnlIngresoSistema.setVisible(true);

        }
    }

    public static void main(String[] args) {

        Principal p = new Principal();
        p.setSize(900,700);
        p.setVisible(true);

        JWindow w = new JWindow();
        w.setSize(900,700);
        w.setVisible(true);
        w.add(p);

    }
}
