package labo1;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ListaPelikula {
	//atributuak
	private ArrayList <Pelikula> lista;
	private static ListaPelikula nireListaPelikula=null;

	// eraikitzailea
	private ListaPelikula(){
		this.lista = new ArrayList<Pelikula>();
	}

	// gainontzeko metodoak0
	public static ListaPelikula getListaPelikula() {
		if (nireListaPelikula==null){
			nireListaPelikula = new ListaPelikula();
			return nireListaPelikula;
		}
		else{
			return nireListaPelikula;
		}

	}
	private Iterator<Pelikula> getIteradorea() {
		return this.lista.iterator();
	}

	public void gehituPelikula(Pelikula peli) {
		if (this.lista.contains(peli)) {
			System.out.println("Pelikula listan zegoen jadanik.");
		}
		else {
			this.lista.add(peli);
		}
	}

	public void ezabatuPelikula(Pelikula peli) {
		if (this.lista.contains(peli)) {
			this.lista.remove(peli);
		}
		else {
			System.out.println("Pelikula ez zegoen listan.");
		}
	}

	public void pelikulaBatekoAktoreakPantailaratu(String izen){
		Pelikula p = bilatuPelikula(izen);
		if(p==null){
			System.out.println("Pelikula ez dago zerrendan");
		}
		else{
			p.AktoreakPantailaratu();
		}
	}

	public void pelikuletatikAktoreaEzabatu(Aktorea akt) {
		Iterator <Pelikula>i=getIteradorea();
		Pelikula p1=null;
		while(i.hasNext()) {
			p1.ezabatuAktorea(akt);
		}
	}

	public Pelikula bilatuPelikula (String izen){
		Iterator <Pelikula>i=getIteradorea();
		Pelikula p1=null;
		boolean topatu = false;
		while(i.hasNext()&&(!topatu)){
			p1=i.next();
			if(p1.izenBerdinaDu(izen)){
				topatu=true;
			}
		}
		if(!topatu){
			p1=null;
		}
		return p1;
	}

	public void FitxategiaKargatu(String nomFile){
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
					if(ht.containsKey(datuak[i])){
						akt=ht.get(datuak[i]);
					}
					else{
						akt= new Aktorea(datuak[i]);
						ht.put(datuak[i],akt);
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

	public void reset() {
		ListaPelikula.getListaPelikula().lista.clear();
	}

	public int tamaina() {
		return this.lista.size();
	}
	
	public void kideakAurkitu(){
        //PostBaldintza:Aktore bakoitzari berarekin Pelikula ezberdinetan lan egin duten kideen zerrenda sortuko du "kideak" izenekoa.
		HashMap<String,Pelikula> ht;//Aktorearekin lan egin duten aktoreak ez errepikatzeko
        int l=ListaPelikula.getListaPelikula().lista.size();
        int m;
        int n;
        for(int i=0;i<l;i++){//Aktore guztietatik pasatzeko
            ht=new HashMap<String,Pelikula>();//Aktore bakoitzeko HashMap berria
            Pelikula p=ListaPelikula.getListaPelikula().lista.get(i);
            ht.put(p.getIzena(),p);//aktorearen kideen artean bere burua ager ez dadin
            m=p.getAktoreLista().size();
            for(int k=0;k<m;k++){//Aktore bakoitzaren pelikula guztietatik pasatzeko
                Aktorea ak=p.getAktoreLista().get(k);
                n=ak.pelikulaKopurua();
                for(int j=0;j<n;j++){//Pelikula bakoitzeko aktore guztietatik pasatzeko
                    Pelikula current=ak.getPelikulaLista().get(j);
                    if(!ht.containsKey(current.getIzena())){//Aktorearen Pelikula bateko aktoreak kideen
                                                                //zerrendan ez badaude begiratzen du
                        ht.put(current.getIzena(),current);//Kide berria hasMap-ean gorde bere helbidearekin
                        p.kideaGehitu(current);//Oraindik AKtorearen kideen zerrenda ezdagoenez zerrendan gehitu
                    }
                }
            }
        }
        //Kostua:ListaAktoreko aktore guztietatik(n), aktore bakoitzaren pelikula guztietatik(m), 
                //eta pelikula bakoitzaren aktore guztietatik(p) pasatu behar denez kostu maximoa n*m*p da:O(n*m*p)
    }

	public boolean erlazionatuta(String p1,String p2){
		//AurreBaldintza:String motako bi elementu
		//PostBaldintza:Elementuetariko bat listan ez badago edo beraien artean erlaziorik ez badago false bueltatuko du.
		//Erlazioa baldin badago true
		Pelikula unekoa=this.bilatuPelikula(p1);
		boolean badago=false;
		HashMap<String,String> ht=new HashMap<String,String>();
		if(ListaPelikula.getListaPelikula().bilatuPelikula(p1)==null ||
				ListaPelikula.getListaPelikula().bilatuPelikula(p2)==null){//Bi elementuak listan badaude begiratzen da
			System.out.println("Konparatu nahi diren elementuetariko bat ez dago listan!");
			return false;}//Listan bi elementuetariko bat ez badago mezu bat pantailaratuko da eta false bueltatuko da
		else if(unekoa.getIzena().equals(p2) ){
			System.out.println("Pelikula berdina ari zara bilatzen");
			return true;}//Bi pelikulak berdinak badira mezu bat pantailaratuko da eta true bueltatuko da.
		else{
			HashSet<String> bisitatuak=new HashSet<String>();//aztertu egin diren elementuak HashSet batean gordeko dira
			Queue<Pelikula> aztGabeak=new LinkedList<Pelikula>();//aztertu ez diren pelikuleen zerrenda
			aztGabeak.add(unekoa);
			ht.put(unekoa.getIzena(),"");//uneko pelikula ez datorrelako beste pelikula batetik
			bisitatuak.add(unekoa.getIzena());//uneko pelikula bisitatuak zerrendan gordeko da

			
			while(!badago && !aztGabeak.isEmpty()){//erlazioa topatu ez den bitartean eta aztGabeak zerrenda hutsa ez den bitartean loop
				unekoa=aztGabeak.remove();//aztGabeak zerrendako lehenengo elementua ateratzen da 
				if(unekoa.getIzena().equals(p2) ){badago=true;}//unekoa eta p2 berdinak direnean erlazioa topatu da
				else{
					int k=unekoa.getKideak().size();
					for(int i=0;i<k;i++){//unekoa aktorearen kideak aztGabeak zerrendan zartuko dira
						if(!bisitatuak.contains(unekoa.getKideak().get(i).getIzena())){//bisitatuen zerrendan ez dauden bitartean
							aztGabeak.add(unekoa.getKideak().get(i));//unekoaren kideak aztertu gabeen zerrendan gordetzen dira
							ht.put(unekoa.getKideak().get(i).getIzena(),unekoa.getIzena());//unekoaren kideak nondik datozen jakiteko(zein aktoretik) 
							bisitatuak.add(unekoa.getKideak().get(i).getIzena());//uneko aktorea bisitatuak zerrendan gordeko da
						}
					}
				}
			}
		}

		if(badago){//Erlazioa topatu badugu nondik topatu dugun kalkulatzeko
			ArrayList<String> lista=this.erlazionatuNondik(ht,p2);
			inprimatu(lista);
		}
		return badago;
		//Kostua:Kostu maximoa aktore guztietatik(n) pasatu behar izanez gero gertatuko da, eta aktore bakoitzaren kide guztiak(m)
		//aztertu behar direla kontuan edukita kostu totala n*m: O(n*m)
	}

	private void inprimatu(ArrayList<String> lista) {
		 //Post:Lista inprimatzen du.
    	System.out.println("-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-");
        System.out.println(lista.get(0)+" eta "+lista.get(lista.size()-1)+" pelikuleen arteko erlazioa hemendik dator:");
    	System.out.println("==============================================================================================");
        int count=0;
        while(count<lista.size()-2){
            System.out.println(lista.get(count)+"   "+lista.get(++count)+"   "+lista.get(++count));
            }	
		
	}

	private ArrayList<String> erlazionatuNondik(HashMap<String, String> ht, String izen) {
		//Post:Lista batean gordeko ditu aztertu nahi diren bi aktoreen erlazioak eta erlazio ahuek lotzen dituen pelikulak
        ArrayList<String> lista=new ArrayList<String>();
        String current=izen;
        String aurreko="";
        lista.add(current);//Lehenengo aktorea gordetzen da
        while(!ht.get(current).equals("")){
            aurreko=current;
            current=ht.get(current);//aurreko aktorearen izena helbide moduan erabilita, zein aktorerekin duen erlazioa ikus daiteke
            lista.add(bilatuBienPelikula(aurreko, current));//Bi aktoreak erlazionatzen dituen pelikula txertatu
            lista.add(current);
        }
        return lista;
	}

	private String bilatuBienPelikula(String pel1, String pel2) {
		 //Aurre:Bi aktoreak listan egon behar dira
        //Post:Bi aktoreek pelikula batetan batera egin badute lan pelikularen izena bueltatuko du.
        //Ez badute batera lan egin " " bueltatuko du.
        String emaitza="";
        Pelikula p1=this.bilatuPelikula(pel1);
        Pelikula p2=this.bilatuPelikula(pel2);
        Aktorea ak=null;
        if(!pel2.equals("") && !pel1.equals("")){
        Iterator<Aktorea> itr=p1.getAktoreLista().iterator();
        while(itr.hasNext() && emaitza==""){//ak1 ten pelikulak banan banan pasatzen ditu
            ak=itr.next();   
            if(ak.getPelikulaLista().contains(p2)){//ak1en uneko pelikularen aktoreetariko bat ak2 den begiratzen du
                emaitza=ak.getAbizenaIzena(); 
            }
        }
        }
        if (emaitza.equals("")){
        	return "ez dute pelikularik amankomunean";
        }else
        return emaitza;
	}
}