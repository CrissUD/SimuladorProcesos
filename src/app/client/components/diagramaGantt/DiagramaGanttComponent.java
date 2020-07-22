package app.client.components.diagramaGantt;

import javax.swing.JOptionPane;

import java.awt.Dimension;

import app.services.servicesLogic.ColaService;
import models.Proceso;

public class DiagramaGanttComponent implements Runnable{
    
    private DiagramaGanttTemplate diagramaGanttTemplate;
    private ColaService sCola;
    private Thread hilo;
    
    private boolean corriendo = true, activado = false, estado = false;
    private int tiempoFinal, numeroProcesos, iteracionActual = 0;

    public DiagramaGanttComponent(){
        hilo = new Thread(this);
        this.sCola = ColaService.getService();
        diagramaGanttTemplate = new DiagramaGanttTemplate(this);
    }

    public DiagramaGanttTemplate getDiagramaGanttTemplate(){
        return diagramaGanttTemplate;
    }

    public int getNumeroProcesos(){
        return numeroProcesos;
    }

    public int getTiempoFinal(){
        return tiempoFinal;
    }

    public boolean getEstado(){
        return estado;
    }

    public boolean estaCorriendo(){
        return corriendo;
    }

    public boolean estaActivado(){
        return activado;
    }

    public Proceso obtenerProceso(int posicion){
        return sCola.devolverProceso(posicion);
    }

    public void configurarTamano(){
        this.tiempoFinal = sCola.devolverTiempoTotal();
        this.numeroProcesos = sCola.devolverNumeroProcesos();
        this.estado = true;
        this.diagramaGanttTemplate.setPreferredSize(new Dimension(
            tiempoFinal * 30,
            (numeroProcesos * 25) + 35
        ));
        this.diagramaGanttTemplate.dibujarEsquemaBase();
    }

    @Override  
    public void run() {
        activado = true;
        while (corriendo) {
            try {
                Thread.sleep(300);
                this.diagramaGanttTemplate.dibujarGantt();
                if(iteracionActual == tiempoFinal + 1){
                    corriendo = false;
                }
            } 
            catch (InterruptedException ex) {
            }
        }
        JOptionPane.showMessageDialog(null,"Finalizado","Fin",JOptionPane.ERROR_MESSAGE);
        hilo.interrupt();
        activado = false;
    }

    public int getIteracionActual(){
        return iteracionActual;
    }

    public void setIteracionActual(int iteracionActual){
        this.iteracionActual = iteracionActual;
    }

    public Thread getHilo() {
        return hilo;
    }
}