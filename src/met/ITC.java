package met;

public class ITC  implements Metrics{
	private int Itc;
	
	public ITC(Classe c){
		
	    	int result = 0;
	    	
	    	for(int i = 0; i < c.getListOperation().size(); i++){
	    		c.setListParameter(c.getListOperation().get(i).getParameter());
	    		
	    		for(int j = 0; j<c.getListParameter().size();j++){
	    			
	    			for(int x = 0; x<Model.getListClass().size(); x++){
						
	    				String nameClass = Model.getListClass().get(x).getClassName();
						String attributeType = c.getListParameter().get(j).getAttributeType();
						
	    				if(nameClass.equals(attributeType)){
	    					result++;
	    				}
	    			}
	    		}	
	    	}
	    	   	
	    	this.Itc =  result;
	    }

	@Override
	public String printMetric() {
		return "ITC: " + this.Itc;
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "ITC(ci) : Nombre de fois où d’autres classes du diagramme apparaissent comme types des arguments des méthodes de ci.";
	}
	}


