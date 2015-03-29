package met;

import java.util.ArrayList;
import java.util.Collections;

public class DIT implements Metrics {
	private int Dit;

	public DIT(Classe c){

		//DIT(ci) : Taille du chemin le plus long reliant une classe ci Ã  une
		//classe racine dans le graphe dâ€™hÃ©ritage. (Giancarlo)       	
		this.Dit= recursiveDIT(c.getClassName(), 0);

	}


	//WORKS BUT NOT FOR HERITAGE MULTIPLE	
	//	 public static int recursiveDIT(String cName, int count){
	//	    	for(int i=0; i<Model.tempGeneralizations.size(); i++){
	//	    		for(int j=0; j<Model.tempGeneralizations.get(i).getChildren().size(); j++){
	//	    			if(cName.equals(Model.tempGeneralizations.get(i).getChildren().get(j)))
	//	    				return recursiveDIT(Model.tempGeneralizations.get(i).getParent(), count+1);
	//	    		}
	//	    	}
	//	    	return count;
	//	    	
	//
	//	    	
	//	    }
	//	 


	//MODIFYING!
	public static int recursiveDIT(String cName, int count){
		ArrayList<Integer> blah = new ArrayList<Integer>();
		for(int i=0; i<Model.tempGeneralizations.size(); i++){
			for(int j=0; j<Model.tempGeneralizations.get(i).getChildren().size(); j++){
				if(cName.equals(Model.tempGeneralizations.get(i).getChildren().get(j))){
					blah.add(recursiveDIT(Model.tempGeneralizations.get(i).getParent(), count+1));
					}
			}
		}
		if(blah.size() > 0)
			return Collections.max(blah);
		return count;	
	}


	@Override
	public String printMetric() {
		return "DIT: " + this.Dit;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "DIT(ci) : Taille du chemin le plus long reliant une classe ci à une classe racine dans le graphe d’héritage.";
	}

}
