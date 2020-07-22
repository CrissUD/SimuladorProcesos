package logic;

import java.util.Random;

import models.Proceso;

public class Cola {
    private Proceso cab, aux;
    private Proceso nuevo, dev;
    private Random rnd= new Random();
    private int tiempoFinal;
    
    public void calcularFinal(int tiempoFinalAnterior, Proceso nuevo){
        nuevo.setTiempoFinal(nuevo.getTiempoRafaga() + tiempoFinalAnterior);
    }
    
    public void calcularRetorno(Proceso nuevo){
        nuevo.setTiempoRetorno(nuevo.getTiempoFinal() - nuevo.getTiempoLlegada());
    }
    
    public void calcularEspera(Proceso nuevo) {
        nuevo.setTiempoEspera(nuevo.getTiempoRetorno() - nuevo.getTiempoRafaga());
    }
    
    public void crearProcesos(int numeroProcesos) {
        for (int i = 0; i <= numeroProcesos; i++) {
            nuevo = new Proceso();
            if (i == 0) {
                nuevo.setNumeroProceso(i + 1);
                nuevo.setTiempoLlegada(0, 0);
                nuevo.setTiempoRafaga(rnd.nextInt(9) + 1);
                this.calcularFinal(0,nuevo);
                this.calcularRetorno(nuevo);
                this.calcularEspera(nuevo);
                cab = aux = nuevo;
                cab.setSiguienteProceso(nuevo);
            } 
            else {
                if (i == numeroProcesos) {
                    tiempoFinal = aux.getTiempoFinal();
                    aux.setSiguienteProceso(cab);
                } 
                else {
                    aux.setSiguienteProceso(nuevo);
                    nuevo.setNumeroProceso(i + 1);
                    nuevo.setTiempoRafaga(rnd.nextInt(5) + 1);
                    int a = rnd.nextInt(aux.getTiempoRafaga());
                    nuevo.setTiempoLlegada(a, aux.getTiempoLlegada());
                    this.calcularFinal(aux.getTiempoFinal(), nuevo);
                    this.calcularRetorno(nuevo);
                    this.calcularEspera(nuevo);
                    aux = nuevo;
                }
            }
        }
    }
    
    public Proceso devolverNodo(int numeroProceso){
        dev = cab;
        for(int i = 1; i <= numeroProceso; i++){
            dev = dev.getSiguienteProceso();
        }
        return dev;
    }
    
    public int devolverTamañoCola(){
        int c = 1;
        dev = cab;
        while(!(dev.getSiguienteProceso() == cab)){
            dev = dev.getSiguienteProceso();
            c++;
        }
        return c;
    }

    public int devolverTiempoFinal() {
        return tiempoFinal;
    }
}