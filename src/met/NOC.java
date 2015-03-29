package met;

public class NOC implements Metrics {
private int Noc;

public NOC(Classe c){
	int result  = 0;
	for(int i=0; i<c.getListGeneralization().size(); i++)
		result += c.getListGeneralization().get(i).getChildren().size();
	
	this.Noc = result;
    
    
}
	@Override
	public String printMetric() {
		// TODO Auto-generated method stub
		return "NOC: " + this.Noc;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "NOC(ci) : Nombre de sous-classes directes de ci";
	}
	
	@Override
	public String getMetricName(){
		return "NOC";
	}
	
	@Override
	public String getMetricValue() {
		return Integer.toString(this.Noc);
	}

}
