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



