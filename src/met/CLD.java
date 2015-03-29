package met;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CLD implements Metrics{

	private int Cld;
	
	public CLD(Classe c){
		//CLD(ci) : Taille du chemin le plus long reliant une classe ci à une
		//classe feuille dans le graphe d’héritage. (Giancarlo)

			
			this.Cld= recursiveCLD(c.getClassName(), 0);
			

		}

		public static int recursiveCLD(String cName, int count){
			Classe current = Model.getClassFromName(cName);
			Map<String, Integer> map = new HashMap<String, Integer>();

			ArrayList<Integer> blah = new ArrayList<Integer>();
			if(current.getListGeneralization().size() > 0){
				for(int i=0; i<current.getListGeneralization().size(); i++){
					for(int j=0; j<current.getListGeneralization().get(i).getChildren().size(); j++)
					{
						//map.put(current.getListGeneralization().get(i).getChildren().get(j), recursiveCLD(current.getListGeneralization().get(i).getChildren().get(j), count++));
						blah.add(recursiveCLD(current.getListGeneralization().get(i).getChildren().get(j), count+1));
					}
				}
				return Collections.max(blah);
			}
			
			return count;
		}

		@Override
		public String printMetric() {
			return "CLD: " + this.Cld;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "CLD(ci) : Taille du chemin le plus long reliant une classe ci � une classe feuille dans le graphe d�h�ritage.";
		}
	}

////CLD(ci) : Taille du chemin le plus long reliant une classe ci à une
////classe feuille dans le graphe d’héritage. (Giancarlo)
//public int CLD(){
//	
//	return recursiveCLD(this.getClassName(), 0);
//	
//
//}
//
//public static int recursiveCLD(String cName, int count){
//	Classe current = Model.getClassFromName(cName);
//	Map<String, Integer> map = new HashMap<String, Integer>();
//
//	ArrayList<Integer> blah = new ArrayList<Integer>();
//	if(current.getListGeneralization().size() > 0){
//		for(int i=0; i<current.getListGeneralization().size(); i++){
//			for(int j=0; j<current.getListGeneralization().get(i).getChildren().size(); j++)
//			{
//				//map.put(current.getListGeneralization().get(i).getChildren().get(j), recursiveCLD(current.getListGeneralization().get(i).getChildren().get(j), count++));
//				blah.add(recursiveCLD(current.getListGeneralization().get(i).getChildren().get(j), count+1));
//			}
//		}
//		return Collections.max(blah);
//	}
//	
//	return count;
//}
