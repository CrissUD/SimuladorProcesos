package models;

public class Proceso {
    private int numeroProceso;
    private int tiempoLlegada;
    private int tiempoRafaga;
    private int tiempoRetorno;
    private int tiempoEspera;
    private int tiempoFinal;
    private Proceso siguienteProceso;
    
    public int getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(int numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada, int llegadaAnterior) {
        if (tiempoLlegada < llegadaAnterior){
            this.tiempoLlegada = llegadaAnterior+1;
        }
        else{            
            this.tiempoLlegada = tiempoLlegada;
        }      
    }

    public int getTiempoRafaga() {
        return tiempoRafaga;
    }

    public void setTiempoRafaga(int tiempoRafaga) {
        this.tiempoRafaga = tiempoRafaga;
    }

    public int getTiempoRetorno() {
        return tiempoRetorno;
    }

    public void setTiempoRetorno(int tiempoRetorno) {
        this.tiempoRetorno = tiempoRetorno;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public int getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(int tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    public Proceso getSiguienteProceso() {
        return siguienteProceso;
    }

    public void setSiguienteProceso(Proceso siguienteProceso) {
        this.siguienteProceso = siguienteProceso;
    }
}