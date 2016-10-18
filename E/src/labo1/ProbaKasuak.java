package labo1;
import java.io.IOException;

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
        ListaAktorea.getListaAktorea().zerrendaOrdenatu(ListaAktorea.getListaAktorea().getLista(), 0, ListaAktorea.getListaAktorea().tamaina()-1);
        ListaAktorea.getListaAktorea().AktoreakIdatzi();
        System.out.println("Denbora totala: " + timer.elapsedTime() + "s\n");
    }    
}