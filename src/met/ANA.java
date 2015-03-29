package met;

import java.util.ArrayList;

public class ANA implements Metrics{

	private double Ana;
	

    public ANA(Classe c){
    	ArrayList<Operation> listOperation = c.getListOperation();
    	double result = 0;  	
    	int divisor = listOperation.size();
    	
    	if(divisor == 0){
    		this.Ana= 0;
    	}
    	else{
	    	for(int i = 0; i<listOperation.size();i++){
	    		result += listOperation.get(i).getNumParameters(); 		
	    	}
	    	result = result/divisor;
	    }   	
    	
    	this.Ana = result;
    }
    
    public String printMetric(){
    	return "ANA: " + this.Ana;
    }

	@Override
	public String getDescription() {

		return "ANA(ci) : Nombre moyen d’arguments des méthodes locales pour la classe ci.";
	}
    
}
