package met;

import java.util.ArrayList;
import java.util.Collections;

public class CAC implements Metrics{

	private int Cac;
	
	
	
public CAC(Classe c){
	this.Cac= recursiveCAC(c.getClassName());
		
		
	}

public static int recursiveCAC(String cName){
	ArrayList<Integer> blah = new ArrayList<Integer>();
	blah.add(countCACCurrentClass(cName));
	for(int i=0; i<Model.tempGeneralizations.size(); i++){
		for(int j=0; j<Model.tempGeneralizations.get(i).getChildren().size(); j++){
			if(cName.equals(Model.tempGeneralizations.get(i).getChildren().get(j))){
				blah.add(recursiveCAC(Model.tempGeneralizations.get(i).getParent()));
				
				}
		}
	}
	if(blah.size() > 0)
		return sumup(blah);
	return 0;	
}

public static int sumup(ArrayList<Integer> list)
{
	int result = 0;
	for(int i=0; i<list.size(); i++)
		result += list.get(i);

	return result;
}

public static int countCACCurrentClass(String  current){
	//CAC(ci) : Nombre d’associations (incluant les agrégations)
			//locales/héritées auxquelles participe une classe ci. (Giancarlo)  
				int result = 0;
				//String current  = c.getClassName();
				
				for(int i=0; i<Model.tempRelations.size(); i++){
					for(int k=0; k<Model.tempRelations.get(i).getListRole().size(); k++){
						if(Model.tempRelations.get(i).getListRole().get(k).getClassName().equals(current))
							result++;
					}
				}
				
				for(int j=0; j<Model.tempAggregations.size(); j++){
					if(Model.tempAggregations.get(j).getContainerClass().getClassName().equals(current))
						result++;
					else{
						for(int n=0; n<Model.tempAggregations.get(j).getListRole().size(); n++){
							if(Model.tempAggregations.get(j).getListRole().get(n).getClassName().equals(current)){
								result++;
							}
						}
					}
				}
				return  result;
}
	
	
	@Override
	public String printMetric() {
		return "CAC: " + this.Cac;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "CAC(ci) : Nombre d'associations (incluant les agregations) locales/heritees auxquelles participe une classe ci.";
	}
	
	@Override
	public String getMetricName(){
		return "CAC";
	}
	
	@Override
	public String getMetricValue() {
		return Integer.toString(this.Cac);
	}

}



