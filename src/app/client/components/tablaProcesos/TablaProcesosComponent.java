package app.client.components.tablaProcesos;

import java.awt.event.MouseListener;

import app.client.vistaPrincipal.VistaPrincipalComponent;
import app.services.logicServices.ColaService;
import models.Proceso;

import java.awt.event.MouseEvent;

public class TablaProcesosComponent implements MouseListener{

    private TablaProcesosTemplate tablaProcesosTemplate;
    private ColaService sCola;
    private Proceso proceso;
    private VistaPrincipalComponent vistaPrincipalComponent;

    public TablaProcesosComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.sCola = ColaService.getService();
        tablaProcesosTemplate = new TablaProcesosTemplate(this);
    }

    public TablaProcesosTemplate getTablaProcesosTemplate(){
        return  tablaProcesosTemplate;
    }

    public void mostrarRegistrosTabla(){
        for(int i = 0; i < sCola.devolverNumeroProcesos(); i++){
            proceso = sCola.devolverProceso(i);
            this.agregarRegistro(proceso);
        }
        if(sCola.devolverNumeroProcesos() >= 25)
            this.agregarRegistro(null);
    }

    public void agregarRegistro(Proceso proceso){
        if(proceso != null)
            tablaProcesosTemplate.getModelo().addRow(
                new Object[]{"Proceso "+proceso.getNumeroProceso(), proceso.getTiempoLlegada(), proceso.getTiempoRafaga(), proceso.getTiempoFinal()}
            );
        else
        tablaProcesosTemplate.getModelo().addRow(new Object[]{"", "", "", ""});
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila = tablaProcesosTemplate.getTabla().getSelectedRow();
        vistaPrincipalComponent.mostrarDetallesProceso(fila);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}