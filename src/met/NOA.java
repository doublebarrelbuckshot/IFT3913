package met;

public class NOA implements Metrics{
	private int Noa;
	
	public NOA(Classe c){
	    	this.Noa = c.getListAttribute().size();
	    }
	

public String printMetric(){
	return "NOA: " + this.Noa;
}


@Override
public String getDescription() {
	// TODO Auto-generated method stub
	return "NOA(ci) : Nombre d’attributs locaux/hérités de la classe ci.";
}
	

}
