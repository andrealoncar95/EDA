package labo1;

import java.io.IOException;
import labo3.GraphHash;

public class ProbaKasuak {
 
    private static StopWatch timer;
 
    public static void main(String[] args) throws IOException {
        timer= new StopWatch();
        //Frogatzeko artxiboa duzun direktorioan jarri behar duzu
        ListaAktorea.getListaAktorea().reset();
        ListaPelikula.getListaPelikula().reset();
        ListaPelikula.getListaPelikula().FitxategiaKargatu("C:\\Users\\aalonso\\Dropbox\\EDA\\DEA BERRIA\\Film.txt");
        System.out.println(ListaAktorea.getListaAktorea().tamaina()+" aktore sartu dira");
        System.out.println(ListaPelikula.getListaPelikula().tamaina()+" pelikula sartu dira");
        double seg=timer.elapsedTime();
        System.out.println("Denbora fitxategia kargatzeko: " + seg + "s\n");
        GraphHash.getInstantzia().grafoaSortu();
        GraphHash.getInstantzia().print();
       
    }    
}