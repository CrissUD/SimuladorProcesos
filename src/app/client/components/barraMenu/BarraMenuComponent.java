package app.client.components.barraMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import app.client.vistaPrincipal.VistaPrincipalComponent;

public class BarraMenuComponent implements ActionListener, MouseListener, MouseMotionListener{

    // Inyección
    private BarraMenuTemplate barraMenuTemplate;

    // Componentes Gráficos
    private VistaPrincipalComponent vistaPrincipalComponent;

    private int posicionInicialX, posicionInicialY;

    public BarraMenuComponent(VistaPrincipalComponent vistaPrincipalComponent, JPanel pContenedor){
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.barraMenuTemplate = new BarraMenuTemplate(this, pContenedor);
    }
    
    public BarraMenuTemplate getBarraMenuTemplate(){
        return barraMenuTemplate;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.posicionInicialX = e.getX();
        this.posicionInicialY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.vistaPrincipalComponent.moverVentana(e.getXOnScreen() - posicionInicialX, e.getYOnScreen() - posicionInicialY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.barraMenuTemplate.getBCerrar())
            this.vistaPrincipalComponent.cerrarVentana();
        if(e.getSource() == this.barraMenuTemplate.getBMaximizar())
            this.vistaPrincipalComponent.maximizarVentana();
        if(e.getSource() == this.barraMenuTemplate.getBMinimizar())
            this.vistaPrincipalComponent.minimizarVentana();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}