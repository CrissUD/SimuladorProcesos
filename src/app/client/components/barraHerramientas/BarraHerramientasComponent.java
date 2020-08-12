package app.client.components.barraHerramientas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import app.client.vistaPrincipal.VistaPrincipalComponent;
import app.services.graphicServices.RecursosService;
import app.services.logicServices.ColaService;


public class BarraHerramientasComponent implements ActionListener, MouseListener, FocusListener{

    private BarraHerramientasTemplate barraHerramientasTemplate;
    private ColaService sCola;
    private VistaPrincipalComponent vistaPrincipalComponent;

    public BarraHerramientasComponent(VistaPrincipalComponent vistaPrincipalComponent, JPanel pContenedor){
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.sCola = ColaService.getService();
        this.barraHerramientasTemplate = new BarraHerramientasTemplate(this, pContenedor);
        UIManager.put("OptionPane.background", RecursosService.getService().getColorPrimario());
        UIManager.put("OptionPane.messageForeground", RecursosService.getService().getColorFuente());
        UIManager.put("Button.background", RecursosService.getService().getColorBotones());
        UIManager.put("Button.foreground", RecursosService.getService().getColorFuente());
        UIManager.put("Panel.background", RecursosService.getService().getColorPrimario());
    }

    public BarraHerramientasTemplate getBarraHerramientasTemplate(){
        return barraHerramientasTemplate;
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
        boton.setBackground(Color.DARK_GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton boton = (JButton) e.getSource();
        boton.setBackground(barraHerramientasTemplate.getSRecursos().getColorBotones());
    }

    @Override
    public void focusGained(FocusEvent e) {
        JTextField texto = (JTextField) e.getSource();
        texto.setBorder(barraHerramientasTemplate.getSRecursos().getBorderAzul());
        if(texto.getText().equals("0"))
            texto.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField texto = (JTextField) e.getSource();
        texto.setBorder(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == barraHerramientasTemplate.getBProcesos()){
            try{
                sCola.crearCola(
                    Integer.parseInt(barraHerramientasTemplate.getTNumProcesos().getText())
                );
                JOptionPane.showMessageDialog(null, "Proceso Creado Exitosamente", "Exito", 1);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Debe ingresar un numero", "Error", 2);
            }
        }
        if(e.getSource() == barraHerramientasTemplate.getBTabla())
            this.vistaPrincipalComponent.mostrarProcesosTabla();
        if(e.getSource() == barraHerramientasTemplate.getBGantt())
            this.vistaPrincipalComponent.mostrarDiagramaGantt();
    }
}