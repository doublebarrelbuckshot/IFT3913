package met;

public class CAC implements Metrics{

	private int Cac;
	
	
	public CAC(Classe c){
		
		//CAC(ci) : Nombre d’associations (incluant les agrégations)
		//locales/héritées auxquelles participe une classe ci. (Giancarlo)  
			int result = 0;
			String current  = c.getClassName();
			
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
			//////////////////////////////
			this.Cac= result;
		
	}
	@Override
	public String printMetric() {
		return "CAC: " + this.Cac;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "CAC(ci) : Nombre d�associations (incluant les agr�gations) locales/h�rit�es auxquelles participe une classe ci.";
	}

}


////CAC(ci) : Nombre d’associations (incluant les agrégations)
////locales/héritées auxquelles participe une classe ci. (Giancarlo)  
//public int CAC(){
//	int result = 0;
//	String current  = this.getClassName();
//	
//	for(int i=0; i<Model.tempRelations.size(); i++){
//		for(int k=0; k<Model.tempRelations.get(i).getListRole().size(); k++){
//			if(Model.tempRelations.get(i).getListRole().get(k).getClassName().equals(current))
//				result++;
//		}
//	}
//	
//	for(int j=0; j<Model.tempAggregations.size(); j++){
//		if(Model.tempAggregations.get(j).getContainerClass().getClassName().equals(current))
//			result++;
//		else{
//			for(int n=0; n<Model.tempAggregations.get(j).getListRole().size(); n++){
//				if(Model.tempAggregations.get(j).getListRole().get(n).getClassName().equals(current)){
//					result++;
//				}
//			}
//		}
//	}
//	//////////////////////////////
//	return result;
//}
