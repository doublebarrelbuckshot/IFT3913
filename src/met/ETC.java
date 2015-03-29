package met;

public class ETC implements Metrics{

	private int Etc;
	
	public ETC(Classe c)
	{
		 //ETC(ci) : Nombre de fois où ci apparaît comme type des arguments
	    //dans les méthodes des autres classes du diagramme.  

	    	int result = 0;
	    	String myClassName = c.getClassName();
	    	
	    	for(int i = 0; i < Model.getListClass().size(); i++){
	    		for(int j = 0; j< Model.getListClass().get(i).getListOperation().size(); j++){
	    			
	    			c.setListParameter(Model.getListClass().get(i).getListOperation().get(j).getParameter());
	    			
	    			for(int x = 0; x<c.getListParameter().size();x++){
	    				String attributeType = c.getListParameter().get(x).getAttributeType();
	    				
	    				if(attributeType.equals(myClassName)){
	    					result++;
	    				}		
	    			}
	    		}
	    	}
	    	
	    	this.Etc =  result;
	    
	}
	@Override
	public String printMetric() {
		return "ETC: " + this.Etc;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "ETC(ci) : Nombre de fois o� ci appara�t comme type des arguments dans les m�thodes des autres classes du diagramme.";
	}

	
	
	
//	 //ETC(ci) : Nombre de fois où ci apparaît comme type des arguments
//    //dans les méthodes des autres classes du diagramme.  
//    public int ETC(){
//    	int result = 0;
//    	String myClassName = getClassName();
//    	
//    	for(int i = 0; i < Model.getListClass().size(); i++){
//    		for(int j = 0; j< Model.getListClass().get(i).getListOperation().size(); j++){
//    			
//    			listParameter = Model.getListClass().get(i).getListOperation().get(j).getParameter();
//    			
//    			for(int x = 0; x<listParameter.size();x++){
//    				String attributeType = listParameter.get(x).getAttributeType();
//    				
//    				if(attributeType.equals(myClassName)){
//    					result++;
//    				}		
//    			}
//    		}
//    	}
//    	
//    	return result;
//    }
}
