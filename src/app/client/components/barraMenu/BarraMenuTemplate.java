package app.client.components.barraMenu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.services.servicesGraphics.ObjGraficosService;
import app.services.servicesGraphics.RecursosService;

public class BarraMenuTemplate extends JPanel{

    private static final long serialVersionUID = 4070449126241394445L;
    
    private BarraMenuComponent barraMenuComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;

    private JButton bCerrar, bMaximizar, bMinimizar;
    private JLabel lTitulo;

    public BarraMenuTemplate(BarraMenuComponent barraMenuComponent, JPanel pContenedor){
        this.barraMenuComponent = barraMenuComponent;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();

        this.crearJButtons();

        this.lTitulo = sObjGraficos.construirJLabel(
            "Simulador de procesos", (pContenedor.getWidth() - 150) / 2, 0, 150, 20, 
            null, sRecursos.getColorFuente(), null, sRecursos.getFontBotones(), "c"
        );
        this.add(lTitulo);
        
        this.setSize(pContenedor.getWidth(), pContenedor.getHeight());
        this.setLayout(null);
        this.setBackground(sRecursos.getColorCuaternario());
        this.addMouseListener(barraMenuComponent);
        this.addMouseMotionListener(barraMenuComponent);
        this.setVisible(true);
    }

    public void crearJButtons(){
        this.bCerrar = sObjGraficos.construirJButton(
            null, 5, 4, 12, 12, sRecursos.getCMano(), null, null, sRecursos.getColorRojo(), 
            null, sRecursos.getBorderCircular(), "c", true
        );
        bCerrar.addActionListener(barraMenuComponent);
        this.add(bCerrar);
        
        this.bMaximizar = sObjGraficos.construirJButton(
            null, 25, 4, 12, 12, sRecursos.getCMano(), null, null, sRecursos.getColorAmarillo(), 
            null, sRecursos.getBorderCircular(), "c", true
        );
        bMaximizar.addActionListener(barraMenuComponent);
        this.add(bMaximizar);
        
        this.bMinimizar = sObjGraficos.construirJButton(
            null, 45, 4, 12, 12, sRecursos.getCMano(), null, null, sRecursos.getColorVerde(), 
            null, sRecursos.getBorderCircular(), "c", true
        );
        bMinimizar.addActionListener(barraMenuComponent);
        this.add(bMinimizar);
    }

    public JButton getBCerrar(){
        return  bCerrar;
    }

    public JButton getBMaximizar(){
        return  bMaximizar;
    }

    public JButton getBMinimizar(){
        return  bMinimizar;
    }
}