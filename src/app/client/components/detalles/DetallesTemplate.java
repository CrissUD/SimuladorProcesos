package app.client.components.detalles;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

public class DetallesTemplate extends JPanel{
    
    private static final long serialVersionUID = -5860140452970696288L;

    // Inyección y servicios
    private DetallesComponent detallesComponent;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;

    // Objetos gráficos
    private JPanel pBotones, pDetallesProceso, pSiguienteProceso;
    private JButton bDetalles, bCola, bTiempos, bEstadisticas;
    private JButton bDespliegueDetalles, bDespliegueSiguienteProceso;
    private JLabel lTitulo, lNumProceso, lTLlegada, lTRafaga, lTRetorno, lTEspera, lTFinal;
    private JLabel lNumProcesoValor, lTLlegadaValor, lTRafagaValor, lTRetornoValor, lTEsperaValor, lTFinalValor;
    private JLabel lNumSigProceso, lTLlegadaSigProceso, lTFinalSigProceso, lSigProceso;
    private JLabel lNumSigProcesoValor, lTLlegadaSigProcesoValor, lTFinalSigProcesoValor, lSigProcesoValor;

    // Objetos Decoradores
    private ImageIcon iDetalles, iCola, iReloj, iEstadistica, iAbajo, iDimAux;

    public DetallesTemplate(DetallesComponent detallesComponent){
        this.detallesComponent = detallesComponent;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();

        this.crearObjetosDecoradores();
        this.crearJPanels();
        this.crearContenidoPBotones();
        this.crearContenidoPDetallesProceso();
        this.crearContenidoPSiguienteProceso();

        this.setSize(230, 630);
        this.setLayout(null);
        this.setBackground(sRecursos.getColorTerciario());
        this.setVisible(true);
    }

    public void crearObjetosDecoradores(){
        iDetalles = new ImageIcon("resources/images/detalles.png");
        iCola = new ImageIcon("resources/images/cola.png");
        iReloj = new ImageIcon("resources/images/reloj.png");
        iEstadistica = new ImageIcon("resources/images/estadistica.png");
        iAbajo = new ImageIcon("resources/images/abajo.png");
    }
    public void crearJPanels(){
        this.pBotones = sObjGraficos.construirJPanel(0, 0, 230, 50, null, null);
        this.add(pBotones);

        this.pDetallesProceso = sObjGraficos.construirJPanel(0, 60, 230, 70, sRecursos.getColorTerciario(), null);
        this.add(pDetallesProceso);

        this.pSiguienteProceso = sObjGraficos.construirJPanel(0, 155, 230, 40, sRecursos.getColorTerciario(), null);
        this.add(pSiguienteProceso);
    }

    public void crearContenidoPBotones(){
        iDimAux = new ImageIcon(iDetalles.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
        bDetalles = sObjGraficos.construirJButton(
            null, 0, 0, 59, 50, sRecursos.getCMano(), iDimAux, null, sRecursos.getColorTerciario(), null, null, "c", true
        );
        this.pBotones.add(bDetalles);

        iDimAux = new ImageIcon(iCola.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
        bCola = sObjGraficos.construirJButton(
            null, 59, 0, 57, 50, sRecursos.getCMano(), iDimAux, null, sRecursos.getColorSecundario(), null, null, "c", true
        );
        this.bCola.addMouseListener(detallesComponent);
        this.pBotones.add(bCola);

        iDimAux = new ImageIcon(iReloj.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
        bTiempos = sObjGraficos.construirJButton(
            null, 116, 0, 57, 50, sRecursos.getCMano(), iDimAux, null, sRecursos.getColorSecundario(), null, null, "c", true
        );
        this.bTiempos.addMouseListener(detallesComponent);
        this.pBotones.add(bTiempos);

        iDimAux = new ImageIcon(iEstadistica.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
        bEstadisticas = sObjGraficos.construirJButton(
            null, 173, 0, 57, 50, sRecursos.getCMano(), iDimAux, null, sRecursos.getColorSecundario(), null, null, "c", true
        );
        this.bEstadisticas.addMouseListener(detallesComponent);
        this.pBotones.add(bEstadisticas);
    }

    public void crearContenidoPDetallesProceso(){
        this.lTitulo = sObjGraficos.construirJLabel(
            "Detalles Procesos", (230 - 180) / 2, 10, 180, 20, null, null, sRecursos.getFontPequenia(), 
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pDetallesProceso.add(lTitulo);

        
        iDimAux = new ImageIcon(iAbajo.getImage().getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING));
        bDespliegueDetalles = sObjGraficos.construirJButton(
            "   Detalles Proceo", 10, 40, 120, 20, sRecursos.getCMano(), iDimAux, sRecursos.getFontBotones(), 
            null, sRecursos.getColorFuente(), null, "l", false
        );
        this.bDespliegueDetalles.addActionListener(detallesComponent);
        this.pDetallesProceso.add(bDespliegueDetalles);

        this.lNumProceso = sObjGraficos.construirJLabel(
            "Numero Proceso:", 30, 80, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pDetallesProceso.add(lNumProceso);

        this.lTLlegada = sObjGraficos.construirJLabel(
            "Tiempo de Llegada:", 30, 115, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pDetallesProceso.add(lTLlegada);

        this.lTRafaga = sObjGraficos.construirJLabel(
            "Tiempo de Rafaga:", 30, 150, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pDetallesProceso.add(lTRafaga);

        this.lTRetorno = sObjGraficos.construirJLabel(
            "Tiempo de Retorno:", 30, 185, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pDetallesProceso.add(lTRetorno);

        this.lTEspera = sObjGraficos.construirJLabel(
            "Tiempo de Espera:", 30, 220, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pDetallesProceso.add(lTEspera);

        this.lTFinal = sObjGraficos.construirJLabel(
            "Tiempo Final:", 30, 255, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pDetallesProceso.add(lTFinal);

        this.lNumProcesoValor = sObjGraficos.construirJLabel(
            "0", 170, 80, 40, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "c"
        );
        this.pDetallesProceso.add(lNumProcesoValor);

        this.lTLlegadaValor = sObjGraficos.construirJLabel(
            "0", 170, 115, 40, 20, null, null, sRecursos.getFontBotones(),
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pDetallesProceso.add(lTLlegadaValor);

        this.lTRafagaValor = sObjGraficos.construirJLabel(
            "0", 170, 150, 40, 20, null, null, sRecursos.getFontBotones(),
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pDetallesProceso.add(lTRafagaValor);

        this.lTRetornoValor = sObjGraficos.construirJLabel(
            "0", 170, 185, 40, 20, null, null, sRecursos.getFontBotones(),
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pDetallesProceso.add(lTRetornoValor);

        this.lTEsperaValor = sObjGraficos.construirJLabel(
            "0", 170, 220, 40, 20, null, null, sRecursos.getFontBotones(),
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pDetallesProceso.add(lTEsperaValor);

        this.lTFinalValor = sObjGraficos.construirJLabel(
            "0", 170, 255, 40, 20, null, null, sRecursos.getFontBotones(),
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pDetallesProceso.add(lTFinalValor);
    }

    public void crearContenidoPSiguienteProceso(){
        iDimAux = new ImageIcon(iAbajo.getImage().getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING));
        bDespliegueSiguienteProceso = sObjGraficos.construirJButton(
            "   Siguiente Proceo", 10, 10, 150, 20, sRecursos.getCMano(), iDimAux, sRecursos.getFontBotones(), 
            null, sRecursos.getColorFuente(), null, "l", false
        );
        this.bDespliegueSiguienteProceso.addActionListener(detallesComponent);
        this.pSiguienteProceso.add(bDespliegueSiguienteProceso);

        this.lNumSigProceso = sObjGraficos.construirJLabel(
            "Numero Proceso:", 30, 50, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pSiguienteProceso.add(lNumSigProceso);

        this.lTLlegadaSigProceso = sObjGraficos.construirJLabel(
            "Tiempo de Llegada:", 30, 85, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pSiguienteProceso.add(lTLlegadaSigProceso);

        this.lTFinalSigProceso = sObjGraficos.construirJLabel(
            "Tiempo Final:", 30, 120, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pSiguienteProceso.add(lTFinalSigProceso);

        this.lSigProceso = sObjGraficos.construirJLabel(
            "Siguiente proceso:", 30, 155, 120, 20, null, null, sRecursos.getFontBotones(),
            null, sRecursos.getColorFuente(), null, "l"
        );
        this.pSiguienteProceso.add(lSigProceso);

        this.lNumSigProcesoValor = sObjGraficos.construirJLabel(
            "0", 170, 50, 40, 20, null, null, sRecursos.getFontBotones(), null, sRecursos.getColorFuente(), null, "c"
        );
        this.pSiguienteProceso.add(lNumSigProcesoValor);

        this.lTLlegadaSigProcesoValor = sObjGraficos.construirJLabel(
            "0", 170, 85, 40, 20, null, null, sRecursos.getFontBotones(),
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pSiguienteProceso.add(lTLlegadaSigProcesoValor);

        this.lTFinalSigProcesoValor = sObjGraficos.construirJLabel(
            "0", 170, 120, 40, 20, null, null, sRecursos.getFontBotones(),
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pSiguienteProceso.add(lTFinalSigProcesoValor);

        this.lSigProcesoValor = sObjGraficos.construirJLabel(
            "0", 170, 155, 40, 20, null, null, sRecursos.getFontBotones(),
            sRecursos.getColorSecundario(), sRecursos.getColorFuente(), null, "c"
        );
        this.pSiguienteProceso.add(lSigProcesoValor);

    }

    public JButton getBDespliegueDetalles(){
        return bDespliegueDetalles;
    }

    public JButton getBDespliegueSiguienteProceso(){
        return bDespliegueSiguienteProceso;
    }

    public JPanel getPDetallesProceso(){
        return pDetallesProceso;
    }

    public JPanel getPSiguienteProceso(){
        return pSiguienteProceso;
    }

    public JLabel getLNumProcesoValor(){
        return lNumProcesoValor;
    }

    public JLabel getLTLlegadaValor(){
        return lTLlegadaValor;
    }

    public JLabel getLTRafagaValor(){
        return lTRafagaValor;
    }

    public JLabel getLTRetornoValor(){
        return lTRetornoValor;
    }

    public JLabel getLTEsperaValor(){
        return lTEsperaValor;
    }

    public JLabel getLTFinalValor(){
        return lTFinalValor;
    }

    public JLabel getLNumSigProcesoValor(){
        return lNumSigProcesoValor;
    }

    public JLabel getLTLlegadaSigProcesoValor(){
        return lTLlegadaSigProcesoValor;
    }

    public JLabel getLTFinalSigProcesoValor(){
        return lTFinalSigProcesoValor;
    }

    public JLabel getLSigProcesoValor(){
        return lSigProcesoValor;
    }
}