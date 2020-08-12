package app.services.graphicServices;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;


/** @author Cristian Felipe Patiño Cáceres */

public class RecursosService {

    // Declaración Servicios
    private GraficosAvanzadosService sGraficosAvanzados;

    // Declaración Objetos Decoradores
    private Color colorPrimario, colorSecundario, colorTerciario, colorCuaternario, colorQuinario,colorFuente, colorBotones;
    private Color colorRojo, colorVerde, colorAmarillo, colorAzul;
    private Font fontBotones, fontPequenia;
    private Cursor cMano;
    private Border bordeAzul, bordeGris, bordeCircular, bordeRedondeado;

    static private RecursosService servicio;

    private RecursosService(){
        sGraficosAvanzados = GraficosAvanzadosService.getService();
        sGraficosAvanzados.getClass();

        this.colorPrimario = new Color (50, 50, 50);
        this.colorSecundario = new Color (40, 40, 40);
        this.colorTerciario= new Color (60, 60, 60);
        this.colorCuaternario = new Color (75, 75, 75);
        this.colorQuinario = new Color(55, 55, 55);
        this.colorFuente = new Color (225, 225, 225);
        this.colorBotones = new Color (125, 125, 125);
        colorAzul= new Color(0, 172, 193);
        colorRojo= new Color(244, 67, 54);
        colorVerde= new Color(76, 175, 80);
        colorAmarillo= new Color(255, 200, 0);
        fontBotones = new Font("LuzSans-Book", Font.PLAIN, 13);
        fontPequenia = new Font("LuzSans-Book", Font.PLAIN, 11);
        cMano = new Cursor(Cursor.HAND_CURSOR);
        bordeAzul = BorderFactory.createLineBorder(colorAzul, 1, false);
        bordeGris = BorderFactory.createLineBorder(colorCuaternario, 1, false);
        bordeCircular = sGraficosAvanzados.DibujarBordeCircular(null, false, null);
        bordeRedondeado = sGraficosAvanzados.DibujarBordeRedondeado(null, 20, false, null);
    }
    
    public Color getColorPrimario(){
        return colorPrimario;
    }

    public Color getColorSecundario(){
        return colorSecundario;
    }

    public Color getColorTerciario(){
        return colorTerciario;
    }

    public Color getColorCuaternario(){
        return colorCuaternario;
    }

    public Color getColorQuinario(){
        return colorQuinario;
    }

    public Color getColorBotones(){
        return colorBotones;
    }

    public Color getColorFuente(){
        return colorFuente;
    }

    public Color getColorAzul(){
        return colorAzul;
    }

    public Color getColorRojo(){
        return colorRojo;
    }

    public Color getColorAmarillo(){
        return colorAmarillo;
    }

    public Color getColorVerde(){
        return colorVerde;
    }

    public Font getFontBotones(){
        return fontBotones;
    }
    
    public Font getFontPequenia(){
        return fontPequenia;
    }

    public Cursor getCMano(){
        return cMano;
    }

    public Border getBorderAzul(){
        return this.bordeAzul;
    }

    public Border getBorderGris(){
        return this.bordeGris;
    }

    public Border getBorderCircular(){
        return this.bordeCircular;
    }

    public Border getBorderRedondeado(){
        return this.bordeRedondeado;
    }

    public static RecursosService getService(){
        if(servicio == null)
            servicio = new RecursosService();
        return servicio;
    }
}