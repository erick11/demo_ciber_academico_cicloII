
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import clases.Categoria;
import clases.Tienda;

public class PnlMantenimiento extends JPanel implements ChangeListener {

    //Componentes del panel
    private PnlMantenCat pnlMantenCat;
    private PnlMantenProd pnlMantenProd;
    private PnlMantenEmp pnlMantenEmp;
    private PnlMantenProv pnlMantenProv;
    private PnlMantenTiendas pnlMantenTiendas;
    private JTabbedPane tbpPestanas;
    private Principal pri;

    //Constructor
    public PnlMantenimiento(Principal p) {
	setBounds(0,150,800,550);
	setLayout(null);

        pri = p;

        pnlMantenCat = new PnlMantenCat(p);
        pnlMantenCat.listar();

	pnlMantenProd = new PnlMantenProd(p);
        pnlMantenProd.listar();

	pnlMantenEmp = new PnlMantenEmp(p);
        pnlMantenEmp.listar();

	pnlMantenProv = new PnlMantenProv(p);
        pnlMantenProv.listar();

        pnlMantenTiendas = new PnlMantenTiendas(p);
        pnlMantenTiendas.listar();

	tbpPestanas = new JTabbedPane();
	tbpPestanas.setBounds(0,0,800,550);
        tbpPestanas.addTab("Categor�as",pnlMantenCat);
	tbpPestanas.addTab("Productos",pnlMantenProd);
	tbpPestanas.addTab("Empleados",pnlMantenEmp);
	tbpPestanas.addTab("Proveedores",pnlMantenProv);
        tbpPestanas.addTab("Tiendas",pnlMantenTiendas);
        tbpPestanas.addChangeListener(this);
	add(tbpPestanas);

	setVisible(false);

    }

    public void stateChanged(ChangeEvent e) {

        switch(tbpPestanas.getSelectedIndex()) {
            case 1: cargarCategorias();
                    break;
            case 2: cargarTiendas();
                    break;
        }
    }

    private void cargarCategorias() {

        pnlMantenProd.cboCategoria.removeAllItems();
        pnlMantenProd.cboCategoria.addItem("Seleccione");

        for(int i=0;i<pri.acat.tamano();i++){
            Categoria c = pri.acat.obtener(i);
            pnlMantenProd.cboCategoria.addItem(c.getCodigo());

        }

    }

    private void cargarTiendas() {

        pnlMantenEmp.cboTienda.removeAllItems();
        pnlMantenEmp.cboTienda.addItem("Seleccione");

        for(int i=0;i<pri.atien.tamano();i++) {
            Tienda t = pri.atien.obtener(i);
            pnlMantenEmp.cboTienda.addItem(t.getCodigo());
            
        }

    }

}

