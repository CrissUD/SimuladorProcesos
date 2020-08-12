package app.client.components.barraHerramientas;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

public class BarraHerramientasTemplate extends JPanel{
    
    private static final long serialVersionUID = -5653731796444217702L;
    
    private BarraHerramientasComponent barraHerramientasComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;

    private JLabel lNumProcesos;
    private JTextField tNumProcesos;
    private JButton bProcesos, bTabla, bGantt;

    private ImageIcon iProcesos, iTabla, iGantt, iDimAux;

    public BarraHerramientasTemplate(BarraHerramientasComponent barraHerramientasComponent, JPanel pContenedor ){
        this.barraHerramientasComponent = barraHerramientasComponent;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();

        this.crearImageIcons();
        this.crearFormulario();
        this.crearJButtons();

        this.setSize(pContenedor.getWidth(), pContenedor.getHeight());
        this.setLayout(null);
        this.setBackground(sRecursos.getColorCuaternario());
        this.setVisible(true);
    }

    public void crearImageIcons(){
        iProcesos = new ImageIcon("resources/images/procesos.png");
        iTabla = new ImageIcon("resources/images/tabla.png");
        iGantt = new ImageIcon("resources/images/gantt.png");
    }

    public void crearFormulario(){
        this.lNumProcesos = sObjGraficos.construirJLabel(
            "Numero de procesos:", 30, 15, 160, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.add(lNumProcesos);

        this.tNumProcesos = sObjGraficos.construirJTextField(
            "0", 165, 17, 50, 20, sRecursos.getFontBotones(), sRecursos.getColorPrimario(), 
            sRecursos.getColorFuente(), sRecursos.getColorFuente(), null, "c"
        );
        this.tNumProcesos.addFocusListener(barraHerramientasComponent);
        this.add(tNumProcesos);
    }

    public void crearJButtons(){
        iDimAux = new ImageIcon(iProcesos.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
        this.bProcesos = sObjGraficos.construirJButton(
            "Procesos", 250, 12, 100, 30, sRecursos.getCMano(), iDimAux, sRecursos.getFontBotones(), 
            sRecursos.getColorBotones(), sRecursos.getColorFuente(), sRecursos.getBorderRedondeado(), "c", true
        );
        this.bProcesos.addActionListener(barraHerramientasComponent);
        this.bProcesos.addMouseListener(barraHerramientasComponent);
        this.add(bProcesos);

        iDimAux = new ImageIcon(iTabla.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
        this.bTabla = sObjGraficos.construirJButton(
            "Tabla", 370, 12, 80, 30, sRecursos.getCMano(), iDimAux, sRecursos.getFontBotones(), 
            sRecursos.getColorBotones(), sRecursos.getColorFuente(), sRecursos.getBorderRedondeado(), "c", true
        );
        this.bTabla.addActionListener(barraHerramientasComponent);
        this.bTabla.addMouseListener(barraHerramientasComponent);
        this.add(bTabla);

        iDimAux = new ImageIcon(iGantt.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
        this.bGantt = sObjGraficos.construirJButton(
            "Gantt", 470, 12, 80, 30, sRecursos.getCMano(), iDimAux, sRecursos.getFontBotones(), 
            sRecursos.getColorBotones(), sRecursos.getColorFuente(), sRecursos.getBorderRedondeado(), "c", true
        );
        this.bGantt.addActionListener(barraHerramientasComponent);
        this.bGantt.addMouseListener(barraHerramientasComponent);
        this.add(bGantt);
    }

    public RecursosService getSRecursos(){
        return sRecursos;
    }

    public JButton getBProcesos(){
        return bProcesos;
    }

    public JButton getBTabla(){
        return bTabla;
    }

    public JButton getBGantt(){
        return bGantt;
    }

    public JTextField getTNumProcesos(){
        return tNumProcesos;
    }
}