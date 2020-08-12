package app.client.vistaPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import app.services.graphicServices.GraficosAvanzadosService;
import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

public class VistaPrincipalTemplate extends JFrame{
    
    private static final long serialVersionUID = -516636209504763143L;
    
    private VistaPrincipalComponent vistaPrincipalComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    // Declaración objetos Gráficos
    private JPanel pBarra, pHerramientas, pTabla, pGantt, pDetalles, pCorner;
    private JScrollPane pScroll;

    public VistaPrincipalTemplate(VistaPrincipalComponent vistaPrincipalComponent){
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.vistaPrincipalComponent.getClass();
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();
        this.sGraficosAvanzados = GraficosAvanzadosService.getService();
        
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1300, 700);
        this.setUndecorated(true);
        this.setLocationRelativeTo(this);

        this.crearJPaneles();
    }

    public void crearJPaneles(){
        this.pBarra = sObjGraficos.construirJPanel(0, 0, this.getWidth(), 20, null, null);
        this.add(pBarra);

        this.pHerramientas = sObjGraficos.construirJPanel(0, 20, 1300, 50, null, null);
        this.add(pHerramientas);

        this.pTabla = sObjGraficos.construirJPanel(0, 70, 370, 630, null, null);
        this.add(pTabla);

        this.pGantt = sObjGraficos.construirJPanel(370, 70, 700, 630, null, null);
        this.add(pGantt);

        this.pDetalles = sObjGraficos.construirJPanel(1070, 70, 230, 630, null, null);
        this.add(pDetalles);
    }

    public void crearJScrollPane(){
        pScroll = ObjGraficosService.getService().construirPanelBarra(
            vistaPrincipalComponent.gDiagramaGanttComponent().getDiagramaGanttTemplate(), 0, 0, 700, 630, 
            sRecursos.getColorPrimario(), sRecursos.getBorderGris()
        );
        pScroll.getVerticalScrollBar().setUI(
            sGraficosAvanzados.devolverScrollPersonalizado(
                7, 10, sRecursos.getColorPrimario(), sRecursos.getColorCuaternario(), sRecursos.getColorTerciario()
            )
        );
        pScroll.getHorizontalScrollBar().setUI(
            sGraficosAvanzados.devolverScrollPersonalizado(
                7, 10, sRecursos.getColorPrimario(), sRecursos.getColorCuaternario(), sRecursos.getColorTerciario()
            )
        );
        pScroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        pCorner = new JPanel();
        pCorner.setBackground(sRecursos.getColorPrimario());
        pScroll.setCorner(JScrollPane.LOWER_RIGHT_CORNER, pCorner);
        this.pGantt.add(pScroll);
        this.pScroll.getHorizontalScrollBar().setValue(2);
    }

    public JPanel getPBarra(){
        return  pBarra;
    }

    public JPanel getPHerramientas(){
        return  pHerramientas;
    }

    public JPanel getPTabla(){
        return  pTabla;
    }

    public JPanel getPGantt(){
        return  pGantt;
    }

    public JPanel getPDetalles(){
        return  pDetalles;
    }
}