package met;

public class NOM implements Metrics{

	private int Nom;

    //NOM(ci) : Nombre de méthodes locales/héritées de la classe
    //ci. Dans le cas où une méthode est héritée et redéfinie localement (même
    //nom, même ordre et types des arguments et même type de retour), elle ne
    //compte qu’une fois. (Nedra) 
	
	public NOM(Classe c){
		this.Nom = 0;
	}
	@Override
	public String printMetric() {
		// TODO Auto-generated method stub
		return "NOM: " + this.Nom;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "NOM(ci) : Nombre de m�thodes locales/h�rit�es de la classe ci. Dans le cas o� une m�thode est h�rit�e et red�finie localement (m�me nom, m�me ordre et types des arguments et m�me type de retour), elle ne compte qu�une fois.";
	}
	
	@Override
	public String getMetricName(){
		return "NOM";
	}
	
	@Override
	public String getMetricValue() {
		return Integer.toString(this.Nom);
	}

}
