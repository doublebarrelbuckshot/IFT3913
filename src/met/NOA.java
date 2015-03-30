package met;

import java.util.ArrayList;

public class NOA implements Metrics{
	private int Noa;


	public NOA(Classe c){
		this.Noa= recursiveNoa(c.getClassName()).size();
			
			
		}

	public static ArrayList<String> recursiveNoa(String cName){
		ArrayList<String> blah = new ArrayList<String>();
		blah = getNOACurrentClass(cName);
		for(int i=0; i<Model.tempGeneralizations.size(); i++){
			for(int j=0; j<Model.tempGeneralizations.get(i).getChildren().size(); j++){
				if(cName.equals(Model.tempGeneralizations.get(i).getChildren().get(j))){
					blah = joinLists(recursiveNoa(Model.tempGeneralizations.get(i).getParent()), blah);//blah.add(recursiveNom(Model.tempGeneralizations.get(i).getParent()));
					
					}
			}
		}
		if(blah.size() > 0)
			return blah;
		return blah;	
	}
	
	public static ArrayList<String>getNOACurrentClass(String cName){
		Classe c = Model.getClassFromName(cName);
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i<c.getListAttribute().size(); i++){
			result.add(c.getListAttribute().get(i).toString());
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

	public String printMetric(){
		return "NOA: " + this.Noa;
	}


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "NOA(ci) : Nombre d'attributs locaux/herites de la classe ci.";
	}

	@Override
	public String getMetricName(){
		return "NOA";
	}

	@Override
	public String getMetricValue() {
		return Integer.toString(this.Noa);
	}

}
