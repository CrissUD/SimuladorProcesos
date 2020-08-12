package app.client.components.detalles;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;
import javax.swing.JButton;

import app.services.graphicServices.RecursosService;
import app.services.logicServices.ColaService;
import models.Proceso;

import java.awt.event.MouseEvent;

public class DetallesComponent implements ActionListener, MouseListener{
    
    private DetallesTemplate detallesTemplate;
    private ColaService sCola;

    private Timer timer;
    private int boton, direccion;
    private boolean estadoP1 = false, estadoP2 = false;

    public DetallesComponent(){
        sCola = ColaService.getService();
        timer = new Timer(1, this);
        this.detallesTemplate = new DetallesTemplate(this);
    }

    public DetallesTemplate getDetallesTemplate(){
        return detallesTemplate;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton boton = (JButton) e.getSource();
        boton.setBackground(RecursosService.getService().getColorPrimario());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton boton = (JButton) e.getSource();
        boton.setBackground(RecursosService.getService().getColorSecundario());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){
            if(e.getSource() == detallesTemplate.getBDespliegueDetalles()){
                boton = 1;
                estadoP1 = !estadoP1;
                if(estadoP1)
                    direccion = 1;
                else
                    direccion = -1;
            }
            if(e.getSource() == detallesTemplate.getBDespliegueSiguienteProceso()){
                boton = 2;
                estadoP2 = !estadoP2;
                if(estadoP2)
                    direccion = 1;
                else
                    direccion = -1;
            }
            timer.start();
        }
        this.mostrarPanel();
    }

    public void mostrarPanel(){
        if(boton == 1){
            if(
                detallesTemplate.getPDetallesProceso().getHeight() == 70 && !estadoP1 ||
                detallesTemplate.getPDetallesProceso().getHeight() == 300 && estadoP1
            )
                timer.stop();
            else{
                detallesTemplate.getPDetallesProceso().setSize(
                    230, detallesTemplate.getPDetallesProceso().getHeight() + direccion
                );
                detallesTemplate.getPSiguienteProceso().setLocation(
                    0, detallesTemplate.getPSiguienteProceso().getY() + direccion
                );
            }
        }
        if(boton == 2){
            if(
                detallesTemplate.getPSiguienteProceso().getHeight() == 40 && !estadoP2 ||
                detallesTemplate.getPSiguienteProceso().getHeight() == 200 && estadoP2
            )
                timer.stop();
            else{
                detallesTemplate.getPSiguienteProceso().setSize(
                    230, detallesTemplate.getPSiguienteProceso().getHeight() + direccion
                );
            }
        }
        this.detallesTemplate.repaint();
    }

    public void mostrarDetalles(int posicion){
        Proceso procesoActual = sCola.devolverProceso(posicion);
        detallesTemplate.getLNumProcesoValor().setText(procesoActual.getNumeroProceso()+"");
        detallesTemplate.getLTLlegadaValor().setText(procesoActual.getTiempoLlegada()+"");
        detallesTemplate.getLTRafagaValor().setText(procesoActual.getTiempoRafaga()+"");
        detallesTemplate.getLTRetornoValor().setText(procesoActual.getTiempoRetorno()+"");
        detallesTemplate.getLTEsperaValor().setText(procesoActual.getTiempoEspera()+"");
        detallesTemplate.getLTFinalValor().setText(procesoActual.getTiempoFinal()+"");
        detallesTemplate.getLNumSigProcesoValor().setText(
            procesoActual.getSiguienteProceso().getNumeroProceso()+""
        );
        detallesTemplate.getLTLlegadaSigProcesoValor().setText(
            procesoActual.getSiguienteProceso().getTiempoLlegada()+""
        );
        detallesTemplate.getLTFinalSigProcesoValor().setText(
            procesoActual.getSiguienteProceso().getTiempoFinal()+""
        );
        detallesTemplate.getLSigProcesoValor().setText(
            procesoActual.getSiguienteProceso().getSiguienteProceso().getNumeroProceso()+""
        );
    }
}