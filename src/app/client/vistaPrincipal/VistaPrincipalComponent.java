package app.client.vistaPrincipal;

import java.awt.Frame;

import app.client.components.barraHerramientas.BarraHerramientasComponent;
import app.client.components.barraMenu.BarraMenuComponent;
import app.client.components.detalles.DetallesComponent;
import app.client.components.diagramaGantt.DiagramaGanttComponent;
import app.client.components.tablaProcesos.TablaProcesosComponent;

public class VistaPrincipalComponent {
    // Inyección
    private VistaPrincipalTemplate vistaPrincipalTemplate;

    // Componentes Gráficos
    private BarraMenuComponent barraMenuComponent;
    private BarraHerramientasComponent barraHerramientasComponent;
    private TablaProcesosComponent tablaProcesosComponent;
    private DetallesComponent detallesComponent;
    private DiagramaGanttComponent diagramaGanttComponent;

    private boolean maximizado = false;

    public VistaPrincipalComponent(){
        vistaPrincipalTemplate = new VistaPrincipalTemplate(this);
        barraMenuComponent = new BarraMenuComponent(this, this.vistaPrincipalTemplate.getPBarra());
        barraHerramientasComponent = new BarraHerramientasComponent(this, this.vistaPrincipalTemplate.getPHerramientas());
        tablaProcesosComponent = new TablaProcesosComponent(this);
        detallesComponent = new DetallesComponent();
        diagramaGanttComponent = new DiagramaGanttComponent();

        this.vistaPrincipalTemplate.getPBarra().add(barraMenuComponent.getBarraMenuTemplate());
        this.vistaPrincipalTemplate.getPHerramientas().add(barraHerramientasComponent.getBarraHerramientasTemplate());
        this.vistaPrincipalTemplate.getPTabla().add(tablaProcesosComponent.getTablaProcesosTemplate());
        this.vistaPrincipalTemplate.getPDetalles().add(detallesComponent.getDetallesTemplate());
        this.vistaPrincipalTemplate.getPGantt().add(diagramaGanttComponent.getDiagramaGanttTemplate());

        this.vistaPrincipalTemplate.setVisible(true);
    }

    public VistaPrincipalTemplate getVistaPrincipalTemplate(){
        return vistaPrincipalTemplate;
    }

    public DiagramaGanttComponent gDiagramaGanttComponent(){
        return diagramaGanttComponent;
    }

    public void mostrarProcesosTabla(){
        tablaProcesosComponent.mostrarRegistrosTabla();
        this.diagramaGanttComponent.configurarTamano();
        this.vistaPrincipalTemplate.getPGantt().removeAll();
        this.vistaPrincipalTemplate.crearJScrollPane();
    }

    public void mostrarDetallesProceso(int posicion){
        this.detallesComponent.mostrarDetalles(posicion);
    }

    public void mostrarDiagramaGantt(){
        if(!this.diagramaGanttComponent.getHilo().isAlive())
            this.diagramaGanttComponent.getHilo().start();
    }

    public void moverVentana(int posicionX, int posicionY){
        this.vistaPrincipalTemplate.setLocation(posicionX, posicionY);
    }

    public void cerrarVentana(){
        System.exit(0);
    }

    public void minimizarVentana(){
        this.vistaPrincipalTemplate.setExtendedState(Frame.ICONIFIED);
    }

    public void maximizarVentana(){
        if(!maximizado){
            this.maximizado = true;
            this.vistaPrincipalTemplate.setExtendedState(Frame.MAXIMIZED_BOTH);
        }
        else if(maximizado){
            this.maximizado = false;
            this.vistaPrincipalTemplate.setExtendedState(Frame.NORMAL);
        }
    }
}