package app.client.components.diagramaGantt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import app.services.servicesGraphics.RecursosService;
import models.Proceso;

public class DiagramaGanttTemplate extends JPanel{
    
    private static final long serialVersionUID = -5062305706201329185L;
    
    private DiagramaGanttComponent diagramaGanttComponent;
    private RecursosService sRecursos;

    private Image imagen;
    private Graphics2D g2d;
    int margenX = 30, margenY = 25, colorEscogido = -1;

    public DiagramaGanttTemplate(DiagramaGanttComponent diagramaGanttComponent){
        this.diagramaGanttComponent = diagramaGanttComponent;
        sRecursos = RecursosService.getService();

        this.setBounds(0, 0, 700, 630);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        if(!diagramaGanttComponent.estaActivado()){
            imagen = createImage(this.getWidth(), this.getHeight());
            g2d = (Graphics2D) imagen.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
    
            g2d.setColor(sRecursos.getColorPrimario());
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    
            g2d.setColor(sRecursos.getColorTerciario());
            g2d.fillRect(0, 0, this.getWidth(), 26);
    
            if(this.diagramaGanttComponent.getEstado())
                this.dibujarEsquemaBase();
    
            if(!this.diagramaGanttComponent.estaCorriendo()){
                try{
                    this.dibujarGantt();
                }
                catch(Exception e){
    
                }
            }
        }
        g.drawImage(imagen, 0, 0, this);
    }
    
    @Override
    public void update(Graphics g){
        g.drawImage(imagen, 0, 0, this);
    }

    public void dibujarEsquemaBase(){
        for (int j = 1; j < this.diagramaGanttComponent.getNumeroProcesos() + 1; j++) {
            if(j % 2 != 0)
                g2d.setColor(sRecursos.getColorSecundario());
            else
                g2d.setColor(sRecursos.getColorPrimario());
            g2d.fillRect(0, j * this.margenY + 1, diagramaGanttComponent.getTiempoFinal() * this.margenX, this.margenY);
        }
        for (int i = 1; i < this.diagramaGanttComponent.getTiempoFinal() + 1; i++) {
            g2d.setColor(sRecursos.getColorCuaternario());
            if(i % 10 == 0)
                g2d.drawLine((int) (margenX * i), 0, (int) (margenX * i), this.getHeight());
            else
                g2d.drawLine((int) (margenX * i) , 0, (int) (margenX * i), 24);
            g2d.setColor(sRecursos.getColorFuente());
            g2d.setFont(sRecursos.getFontPequenia());
            if(i > 99)
                g2d.drawString(i + "", (((int) ((margenX * i)) + (int) ((margenX * (i - 1)) )) / 2) - 10, 15);
            else if(i > 9)
                g2d.drawString(i + "", (((int) ((margenX * i)) + (int) ((margenX * (i - 1)) )) / 2) - 6, 15);
            else
                g2d.drawString(i + "", (((int) ((margenX * i)) + (int)((margenX * (i - 1)))) / 2) - 2, 15);
        }
        if(diagramaGanttComponent.getNumeroProcesos() % 2 == 0){
            g2d.setColor(sRecursos.getColorSecundario());
            g2d.drawLine(
                0, 
                (diagramaGanttComponent.getNumeroProcesos() + 1) * this.margenY, 
                diagramaGanttComponent.getTiempoFinal() * this.margenX, 
                (diagramaGanttComponent.getNumeroProcesos() + 1) * this.margenY
            );
        }
    }

    public void dibujarRejilla(){
        for (int i = 1; i < this.diagramaGanttComponent.getTiempoFinal() + 1; i++) {
            g2d.setColor(sRecursos.getColorCuaternario());
            if(i % 10 == 0)
                g2d.drawLine((int) (margenX * i), 0, (int) (margenX * i), this.getHeight());
        }
    }

    public void dibujarGantt() throws InterruptedException {
        Graphics g = this.getGraphics();

        Proceso cabeza = this.diagramaGanttComponent.obtenerProceso(0);
        Proceso procesoActual = cabeza;
        int inicioEjecucion = 0;
        do{
            int finEspera = procesoActual.getTiempoLlegada() + procesoActual.getTiempoEspera();
            int finEjecucion = procesoActual.getTiempoFinal();
            if (finEspera > diagramaGanttComponent.getIteracionActual())
                finEspera = diagramaGanttComponent.getIteracionActual();
            if (finEjecucion > diagramaGanttComponent.getIteracionActual())
                finEjecucion = diagramaGanttComponent.getIteracionActual();

            g2d.setColor(sRecursos.getColorQuinario());
            for (int i = procesoActual.getTiempoLlegada(); i < finEspera; i++)
                g2d.fillRoundRect(
                    (margenX * i)  + 1, (margenY * procesoActual.getNumeroProceso()) + 5, margenX + 6, margenY - 10, 10, 10
                );

            g2d.setColor(sRecursos.getColorAzul());
            for (int i = inicioEjecucion; i < finEjecucion; i++)
                g2d.fillRoundRect(
                    (margenX * i)  + 1, (margenY * procesoActual.getNumeroProceso()) + 5, margenX + 6, margenY - 10, 10, 10
                );
            
                inicioEjecucion = procesoActual.getTiempoFinal();
            procesoActual = procesoActual.getSiguienteProceso();
        }
        while (procesoActual != cabeza);
        this.dibujarRejilla();
        diagramaGanttComponent.setIteracionActual(diagramaGanttComponent.getIteracionActual() + 1);
        g.drawImage(imagen, 0, 0, this);
    }
}