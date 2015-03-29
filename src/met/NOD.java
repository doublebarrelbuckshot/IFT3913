package met;

import java.util.ArrayList;

public class NOD implements Metrics {

	private int Nod;

	public NOD(Classe c){

		if(c.getListGeneralization().size() >0)
			this.Nod =  recursiveNOD(c.getClassName());
		else
			this.Nod = 0;


	}

	@Override
	public String printMetric() {
		return "NOD: " + this.Nod;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "NOD(ci) : Nombre de sous-classes directes et indirectes de ci.";
	}

	public static int recursiveNOD(String cName){
		Classe current = Model.getClassFromName(cName);

		ArrayList<Integer> sumof = new ArrayList<Integer>();
		if(current.getListGeneralization().size() > 0){
			for(int i=0; i<current.getListGeneralization().size(); i++){
				sumof.add(current.getListGeneralization().get(i).getChildren().size());
				for(int j=0; j<current.getListGeneralization().get(i).getChildren().size(); j++)
				{

					if(Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().size() > 0){
						sumof.add(recursiveNOD(current.getListGeneralization().get(i).getChildren().get(j) ));

					}

				}
			}

			return sumup(sumof);
		}

		return 0;

	}

	public static int sumup(ArrayList<Integer> list)
	{
		int result = 0;
		for(int i=0; i<list.size(); i++)
			result += list.get(i);

		return result;
	}

	@Override
	public String getMetricName(){
		return "NOD";
	}

	@Override
	public String getMetricValue() {
		return Integer.toString(this.Nod);
	}

}


//public int NOD(){
//	if(this.getListGeneralization().size() >0)
//		return recursiveNOD(this.getClassName());
//	return 0;
//	//PROBLEM supposer devenir ok mais rentre dans une boucle infinie pour le moment à cause de getChildren qui ne fonctionne pas...
//	
//	//for(int i = 0; i<Generalisation.getChildren().size(); i++){
//		//result += Model.getClassFromName(Generalisation.getChildren().get(i)).NOD();
//		//System.out.println("Sous-Classe :" + Generalisation.getChildren().get(i));
//		//System.out.println("Nombre de sous-classe :" + className.NOC());
////	}
//	
//	
//}
//
//public static int recursiveNOD(String cName){
//	Classe current = Model.getClassFromName(cName);
//	
//	ArrayList<Integer> blah = new ArrayList<Integer>();
//	if(current.getListGeneralization().size() > 0){
//		int shit = 0;
//		for(int i=0; i<current.getListGeneralization().size(); i++){
//			blah.add(current.getListGeneralization().get(i).getChildren().size());
//			for(int j=0; j<current.getListGeneralization().get(i).getChildren().size(); j++)
//			{
//				
//				System.out.println("Current: " + current.getClassName() +"     " + current.getListGeneralization().get(i).getChildren().size() );
//				//if(Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().size() > 0)
//				//	System.out.println("SIZE OF CHILD: "+ j +"   " + Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().get(0).getChildren().size());
//				
//				//map.put(current.getListGeneralization().get(i).getChildren().get(j), recursiveCLD(current.getListGeneralization().get(i).getChildren().get(j), count++));
//				if(Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().size() > 0){
//				//	if(!countedCurrent){
//				//		countedCurrent = true;
//				//		blah.add(Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().get(0).getChildren().size());
//				//	}
//						blah.add(recursiveNOD(current.getListGeneralization().get(i).getChildren().get(j) ));
//				//	 System.out.println("Here");
//				}
//				//else blah.add(1);
//				//else blah.add(1);
//				//	System.out.println("Current Node: " + current.getListGeneralization().get(i).getChildren().get(j) + " has this many children: " + blahs);
//			
//			}
//		}
//		for(int i=0; i<blah.size(); i++)
//		{
//			//System.out.println(current.getClassName() + " Kid " + i + blah.get(i));
//		}
//		return sumup(blah);
//	}
//	
//	return 0;
//	
//}
//
//public static int sumup(ArrayList<Integer> list)
//{
//	int result = 0;
//	for(int i=0; i<list.size(); i++)
//		result += list.get(i);
//
//return result;
//}
