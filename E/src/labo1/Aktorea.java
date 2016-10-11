package labo1;
import java.util.ArrayList;
import java.util.Iterator;

public class Aktorea {
	//atributuak
	private String izen;
	private String abizena;

	//metodoak
	public Aktorea(String pIzena, String pAbizena){
        this.izen=pIzena;
        this.abizena=pAbizena;
    }
    
    public boolean izenBerdinaDu(String pIzen, String pAbizen) {
		if (this.izen.equals(pIzen)&&(this.abizena.equals(pAbizen))) {
			return true;
		}
		else {
			return false;
		}
	}
}
