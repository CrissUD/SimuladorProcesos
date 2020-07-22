package app.client.components.tablaProcesos;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Dimension;

import app.services.servicesGraphics.GraficosAvanzadosService;
import app.services.servicesGraphics.ObjGraficosService;
import app.services.servicesGraphics.RecursosService;

public class TablaProcesosTemplate extends JPanel{
    
    private static final long serialVersionUID = -5539047217938871273L;
    
    private TablaProcesosComponent tablaProcesosComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    // Declaración objetos para JTable
    private JScrollPane pTabla;
    private JPanel pCorner;
    private JTable tabla;
    private JTableHeader header;
    private DefaultTableModel modelo;
    private String [] cabecera={"No", "Llegada", "Ráfaga", "Final"};

    public TablaProcesosTemplate(TablaProcesosComponent tablaProcesosComponent){
        this.tablaProcesosComponent = tablaProcesosComponent;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();
        this.sGraficosAvanzados = GraficosAvanzadosService.getService();

        this.crearJTable();

        this.setSize(370, 630); 
        this.setLayout(null);
        this.setVisible(true);
    }

    public void crearJTable(){
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(cabecera);

        tabla = new JTable();
        tabla.setModel(modelo);
        tabla.addMouseListener(tablaProcesosComponent);

        tabla.setRowHeight(25);
        tabla.setGridColor(sRecursos.getColorSecundario());
        header = tabla.getTableHeader();
        header.setPreferredSize(new Dimension(370, 25));
        header.setDefaultRenderer(sGraficosAvanzados.devolverTablaPersonalizada(
            sRecursos.getColorTerciario(), null , null, sRecursos.getColorFuente(), sRecursos.getFontBotones()
        ));
        tabla.setDefaultRenderer(Object.class, sGraficosAvanzados.devolverTablaPersonalizada(
            sRecursos.getColorPrimario(), sRecursos.getColorSecundario() , sRecursos.getColorAzul(), 
            sRecursos.getColorFuente(), sRecursos.getFontBotones()
        ));
        tabla.addMouseListener(tablaProcesosComponent);

        pTabla = sObjGraficos.construirPanelBarra(tabla, 0, 0, 370, 630, sRecursos.getColorPrimario(), sRecursos.getBorderGris());
    
        pTabla.getVerticalScrollBar().setUI(
            sGraficosAvanzados.devolverScrollPersonalizado(
                7, 10, sRecursos.getColorPrimario(), sRecursos.getColorCuaternario(), sRecursos.getColorTerciario()
            )
        );
        pCorner = new JPanel();
        pCorner.setBackground(sRecursos.getColorTerciario());
        pTabla.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pCorner);
        this.add(pTabla);
    }

    public JTable getTabla(){
        return tabla;
    }

    public DefaultTableModel getModelo(){
        return modelo;
    }

}