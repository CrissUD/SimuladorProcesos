package app.services.logicServices;

import logic.Cola;
import models.Proceso;

public class ColaService {
    private static ColaService servicio;
    private Cola cola;

    public ColaService(){
        cola = new Cola();
    }

    public void crearCola(int numeroProcesos){
        cola.crearProcesos(numeroProcesos);
    }

    public int devolverNumeroProcesos(){
        return cola.devolverTamañoCola();
    }

    public int devolverTiempoTotal(){
        return cola.devolverTiempoFinal();
    }

    public Proceso devolverProceso(int posicion){
        return cola.devolverNodo(posicion);
    }

    public static ColaService getService(){
        if(servicio == null)
            servicio = new ColaService();
        return servicio;
    }
}