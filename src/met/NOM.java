package met;

import java.util.ArrayList;

public class NOM implements Metrics{

	private int Nom;

	public NOM(Classe c){
		this.Nom= recursiveNom(c.getClassName()).size();
			
			
		}

	public static ArrayList<String> recursiveNom(String cName){
		ArrayList<String> blah = new ArrayList<String>();
		blah = getNOMCurrentClass(cName);
		for(int i=0; i<Model.tempGeneralizations.size(); i++){
			for(int j=0; j<Model.tempGeneralizations.get(i).getChildren().size(); j++){
				if(cName.equals(Model.tempGeneralizations.get(i).getChildren().get(j))){
					blah = joinLists(recursiveNom(Model.tempGeneralizations.get(i).getParent()), blah);//blah.add(recursiveNom(Model.tempGeneralizations.get(i).getParent()));
					
					}
			}
		}
		if(blah.size() > 0)
			return blah;//return sumup(blah);
		return blah;	
	}
	
	public static ArrayList<String>getNOMCurrentClass(String cName){
		Classe c = Model.getClassFromName(cName);
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i<c.getListOperation().size(); i++){
			result.add(c.getListOperation().get(i).toString());
		}
		return result;
	}
	
	public static ArrayList<String> joinLists(ArrayList<String> l1, ArrayList<String> l2){
		for(int i=0; i<l1.size(); i++){
			String op = l1.get(i);
			boolean duplicate = false;
			for(int j=0; j<l2.size(); j++){
				if(op.equals(l2.get(j))){
					duplicate = true;
				}
			}
			
			if(!duplicate){
				l2.add(op);
			}
		}
		return l2;
		
	}
	
	@Override
	public String printMetric() {
		// TODO Auto-generated method stub
		return "NOM: " + this.Nom;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "NOM(ci) : Nombre de methodes locales/heritees de la classe ci. Dans le cas ou une methode est heritee et redefinie localement (meme nom, meme ordre et types des arguments et meme type de retour), elle ne compte qu'une fois.";
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
