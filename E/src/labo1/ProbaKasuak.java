package labo1;

import java.io.IOException;

public class ProbaKasuak {
 
    private static StopWatch timer;
 
    public static void main(String[] args) throws IOException {
        timer= new StopWatch();
        //Frogatzeko artxiboa duzun direktorioan jarri behar duzu
        //fk.Fitxategia("C:\\Users\\aalonso\\git\\EDA\\E\\src\\txt\\Fitxategi.txt");
        ListaAktorea.getListaAktorea().reset();
        ListaPelikula.getListaPelikula().reset();
        ListaPelikula.getListaPelikula().FitxategiaKargatu("C:\\Users\\aalonso\\Dropbox\\EDA\\DEA BERRIA\\Film.txt");
        System.out.println("Denbora totala: " + timer.elapsedTime() + "s\n");
        //fk.AktoreakIdatzi();
    }    
}