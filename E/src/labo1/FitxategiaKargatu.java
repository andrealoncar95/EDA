package labo1;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
 
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class FitxategiaKargatu {
 
    private static StopWatch timer;
 
    public static void main(String[] args) throws IOException {
        timer= new StopWatch();
        FitxategiaKargatu fk =new FitxategiaKargatu();
        //Frogatzeko artxiboa duzun direktorioan jarri behar duzu
        fk.Fitxategia("/home/aalonso/Escritorio/aktor");
        System.out.println("Denbora totala: " + timer.elapsedTime() + "s\n");
        ListaAktorea.getListaAktorea().AktoreakPantailaratu();
        ListaPelikula.getListaPelikula().pelikulakPantailaratu();
    }
 
    public void Fitxategia(String nomFile){
        try{
            int knt=0;
            Scanner entrada = new Scanner(new FileReader(nomFile));
            String linea;
            Pelikula pel;
            Aktorea akt;
            HashMap<String,Pelikula> ht=new HashMap<String,Pelikula>();
            while (entrada.hasNext()) {
                linea = entrada.nextLine();
                String[] datuak=linea.split("\\s+--->\\s+");
                Pelikula pelikula=new Pelikula(datuak[0]);
                ListaPelikula.getListaPelikula().pelikulaGehitu(pelikula);
                knt++;
                for(int i=1;i<datuak.length;i++){
                    if (knt % 1000 == 0){   
                        System.out.println(knt + " pelikula kargatu dira");
                    }
                    if(ht.containsKey(datuak[i])){
                        pel=ht.get(datuak[i]);
                    }
                    else{
                        pel = new Pelikula(datuak[i],0);
                        ht.put(datuak[i],pel);
                        ListaPelikula.getListaPelikula().pelikulaGehitu(pel);
                    }
                    pel.gehituAktorea(akt);
                    akt.gehituPelikula(pel);
                }
            }
 
 
            entrada.close();
        }
        catch(IOException e) {e.printStackTrace();}
    }
 
    private static Component frame;
 
    static String pathLortu() throws IOException, HeadlessException {
        String path = null;
        String userhome = System.getProperty("user.home");
        JFileChooser fc = new JFileChooser(new File(userhome + "\\Dropbox\\EDA\\eginkizun1"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("movies-actors", "txt", "text");
        fc.setFileFilter(filter);
        fc.setDialogTitle("Aktore Lista Ireki");
        try{
            fc.showOpenDialog(frame);
            path = fc.getSelectedFile().getAbsolutePath();
        }
        catch (Exception textuErrorea)
        {
            System.out.println("Ezin da fitxategia kargatu\n");
            textuErrorea.getMessage();
            textuErrorea.getStackTrace();
            System.exit(1);
        }
        return path;
    }
 
    private static int lerroakKontatu(String path) throws IOException{
 
        FileReader fr = new FileReader(path);
        BufferedReader kontatuLerroak = new BufferedReader(fr);
        int lerroKopurua = 0;
        while (kontatuLerroak.readLine()!= null)
        {
            lerroKopurua++;
        }
        kontatuLerroak.close();
        return lerroKopurua;
    }
 
    static void datuakKargatu(String path) throws IOException, ArrayIndexOutOfBoundsException, PatternSyntaxException, NullPointerException, StackOverflowError {
 
        String[] pelikulaAktoreak = null;
        Aktorea akt=null;
        FileReader fr = new FileReader(path);
        BufferedReader irakurriDatuak = new BufferedReader(fr);
        int bukatu = FitxategiaKargatu.lerroakKontatu(path);
        int i;
        for(i=0; i<bukatu; i++)
        {
            if(i>0 && i % 10000 == 0){
                System.out.println(i+" Aktorea kargatu dira.");
            }
            pelikulaAktoreak=irakurriDatuak.readLine().split("\\s[\\\\]\\s");
            akt=new Aktorea(pelikulaAktoreak[0].toString(), pelikulaAktoreak[0].toString());
            int j = 1;
            Pelikula pel;
            try {
                while (!pelikulaAktoreak[j].isEmpty()){
                    pel= new Pelikula(pelikulaAktoreak[j].toString(), null);
                    akt.gehituPelikula(pel);
                    pel.gehituAktorea(akt);
                    ListaPelikula.getListaPelikula().pelikulaGehitu(pel);
                    j++;
                }
                if(i>0 && i % 10000 == 0){
                    System.out.println(i+" Aktoreak kargatu dira.");
                }
            }
            catch (ArrayIndexOutOfBoundsException arrayaBukatuta){
                arrayaBukatuta.getMessage();
                arrayaBukatuta.getStackTrace();
            }
            ListaAktorea.getListaAktorea().gehituAktorea(akt);
        }
        System.out.println("Aktoreak kargatu dira\n");
        irakurriDatuak.close();
    } 
}