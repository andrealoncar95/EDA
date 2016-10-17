package labo1;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

 
public class FitxategiaKargatu {
 
    private static StopWatch timer;
 
    public static void main(String[] args) throws IOException {
        timer= new StopWatch();
        FitxategiaKargatu fk =new FitxategiaKargatu();
        //Frogatzeko artxiboa duzun direktorioan jarri behar duzu
        //fk.Fitxategia("C:\\Users\\aalonso\\git\\EDA\\E\\src\\txt\\Fitxategi.txt");
        ListaAktorea.getListaAktorea().reset();
        fk.Fitxategia("C:\\Users\\aalonso\\Dropbox\\EDA\\DEA BERRIA\\Film.txt");
        System.out.println("Denbora totala: " + timer.elapsedTime() + "s\n");
        //fk.AktoreakIdatzi();
    }
 
    public void Fitxategia(String nomFile){
        try{
            Scanner entrada = new Scanner(new FileReader(nomFile));
            String linea;
            Aktorea akt;
            HashMap<String,Aktorea> ht=new HashMap<String,Aktorea>();
            while (entrada.hasNext()) {
                linea = entrada.nextLine();
                String[] datuak=linea.split("\\s+--->\\s+");
                Pelikula pelikula=new Pelikula(datuak[0]);
                ListaPelikula.getListaPelikula().gehituPelikula(pelikula);
                datuak=datuak[1].split("\\s+&&&\\s+");
                for(int i=0;i<datuak.length;i++){
                	String[] izenaAbizen= datuak[i].split(",\\s+");
                	String gakoa=null;
                	if (izenaAbizen.length==1){
			//trim metodoa zuriuneak kentzeko balio du
                		gakoa= izenaAbizen[0].trim();
                	}
                	else{
                		gakoa= izenaAbizen[0].trim()+izenaAbizen[1].trim();
                	}
                	
                    if(ht.containsKey(gakoa)){
                        akt=ht.get(gakoa);
                    }
                    else{
                    	if (izenaAbizen.length==1){
                    		akt= new Aktorea(null,izenaAbizen[0]);
                    	}else{
                    		akt = new Aktorea(izenaAbizen[1],izenaAbizen[0]);
                    	}
                        ht.put(gakoa,akt);
                        ListaAktorea.getListaAktorea().gehituAktorea(akt);
                    }
                    pelikula.gehituAktorea(akt);
                    akt.gehituPelikula(pelikula);
                }
            }
            entrada.close();
        }
        catch(IOException e) {e.printStackTrace();}
    }
 
    public void AktoreakIdatzi(){
    	try{
    		PrintWriter pw= new PrintWriter("listaAktoreak.txt");
    		Aktorea akt;
    		Iterator<Aktorea> i= ListaAktorea.getListaAktorea().getIteradorea();
    		while (i.hasNext()){
    			akt=i.next();
    			pw.print(akt.getIzena()+" "+akt.getAbizena());
    			pw.println();
    		}
    		pw.close();
    		System.out.println("Idatzi egin da");
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
}