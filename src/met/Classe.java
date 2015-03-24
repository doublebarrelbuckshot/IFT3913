package met;

import java.util.ArrayList;

public class Classe {

    private String className;
    private final ArrayList<DataItem> listAttribute;
    private final ArrayList<Operation> listOperation;
    private final ArrayList<Association> listAssociation;
    private final ArrayList<Generalisation> listGeneralization;
    private ArrayList<Aggregation> listAggregation;
    private ArrayList<DataItem> listParameter = new ArrayList<DataItem>();

    public Classe() {
        this.listGeneralization = new ArrayList<>();
        this.listAggregation = new ArrayList<>();
        this.listAssociation = new ArrayList<>();
        this.listOperation = new ArrayList<>();
        this.listAttribute = new ArrayList<>();
    };

    public Classe(String className) {
        this.listGeneralization = new ArrayList<>();
        this.listAggregation = new ArrayList<>();
        this.listAssociation = new ArrayList<>();
        this.listOperation = new ArrayList<>();
        this.listAttribute = new ArrayList<>();
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

    public String recreateAttributeCode() {
        String result = "ATTRIBUTES\n";
        for (int i = 0; i < listAttribute.size(); i++) {
            result += "\t" + listAttribute.get(i).getAttributeType() + " " + listAttribute.get(i).getAttributeName();
            if (i < listAttribute.size() - 1) {
                result += ",";
            }
            result += "\n";
        }
        return result;
    }

    public String recreateOperationCode() {
        String result = "OPERATIONS\n";
        for (int i = 0; i < listOperation.size(); i++) {
            result += "\t" + listOperation.get(i).printOperation();
            if (i < listOperation.size() - 1) {
                result += ",";
            }
            result += "\n";
        }
        result += ";";
        return result;
    }

    public String recreateGeneralizationCode() {

        String result = "";

        for (Generalisation listGeneralization1 : listGeneralization) {

            result = "GENERALIZATION " + Generalisation.getParent()
                    + "\n\tSUBCLASSES ";

            for (int j = 0; j < Generalisation.getChildren().size(); j++) {
                result += Generalisation.getChildren().get(j);

                if (j < Generalisation.getChildren().size() - 1) {
                    result += ", ";
                }
            }
            result += ";";
        }
        return result;
    }

    public void addAttribute(DataItem newAttribute) {
        listAttribute.add(newAttribute);
    }

    public void addAssociation(Association newRelation) {
        listAssociation.add(newRelation);
    }

    public void addGeneralization(Generalisation newGeneralization) {
        listGeneralization.add(newGeneralization);
    }

    public void addAggregation(Aggregation newAggregation) {
        listAggregation.add(newAggregation);
    }

    public void addOperation(Operation newOperation) {
        listOperation.add(newOperation);
    }

    public void setListAggregation(ArrayList<Aggregation> listAggregation) {
        this.listAggregation = listAggregation;
    }

    public ArrayList<Association> getListAssociation() {
        return this.listAssociation;
    }

    public ArrayList<Generalisation> getListGeneralization() {
        return this.listGeneralization;
    }

    public ArrayList<Aggregation> getListAggregation() {
        return this.listAggregation;
    }

    public ArrayList<DataItem> getListAttribute() {
        return this.listAttribute;
    }

    public ArrayList<Operation> getListOperation() {
        return this.listOperation;
    }
    
    //ANA(ci) : Nombre moyen d’arguments des méthodes locales pour la
    //classe ci.
    public double ANA(){
    	double result = 0;  	
    	int divisor = listOperation.size();
    	
    	if(divisor == 0){
    		return 0;
    	}
    	else{
	    	for(int i = 0; i<listOperation.size();i++){
	    		result += listOperation.get(i).getNumParameters(); 		
	    	}
	    	result = result/divisor;
	    }   	
    	
    	return result;
    }
    
    //NOM(ci) : Nombre de méthodes locales/héritées de la classe
    //ci. Dans le cas où une méthode est héritée et redéfinie localement (même
    //nom, même ordre et types des arguments et même type de retour), elle ne
    //compte qu’une fois.    
    public int NOM(){
    	int result = 0;
    	
    	//TO DO
    	
    	return result;
    }
    
    //NOA(ci) : Nombre d’attributs locaux/hérités de la classe ci.
    public int NOA(){ 
    	return listAttribute.size();
    }
    
    //ITC(ci) : Nombre de fois où d’autres classes du diagramme
    //apparaissent comme types des arguments des méthodes de ci.
    public int ITC(){
    	int result = 0;
    	
    	for(int i = 0; i < listOperation.size(); i++){
    		listParameter = listOperation.get(i).getParameter();
    		
    		for(int j = 0; j<listParameter.size();j++){
    			
    			for(int x = 0; x<Model.getListClass().size(); x++){
					
    				String nameClass = Model.getListClass().get(x).getClassName();
					String attributeType = listParameter.get(j).getAttributeType();
					
    				if(nameClass.equals(attributeType)){
    					result++;
    				}
    			}
    		}	
    	}
    	   	
    	return result;
    }
    
    //ETC(ci) : Nombre de fois où ci apparaît comme type des arguments
    //dans les méthodes des autres classes du diagramme.  
    public int ETC(){
    	int result = 0;
    	String myClassName = getClassName();
    	
    	for(int i = 0; i < Model.getListClass().size(); i++){
    		for(int j = 0; j< Model.getListClass().get(i).getListOperation().size(); j++){
    			
    			listParameter = Model.getListClass().get(i).getListOperation().get(j).getParameter();
    			
    			for(int x = 0; x<listParameter.size();x++){
    				String attributeType = listParameter.get(x).getAttributeType();
    				
    				if(attributeType.equals(myClassName)){
    					result++;
    				}		
    			}
    		}
    	}
    	
    	return result;
    }
    
    //CAC(ci) : Nombre d’associations (incluant les agrégations)
    //locales/héritées auxquelles participe une classe ci.   
    public int CAC(){
    	int result = 0;
    	
    	for(int i = 0; i < Model.getListClass().size(); i++){
    			Classe myClass =  Model.getListClass().get(i);
    			//System.out.println(Aggregation.getListRole());
    	}
    	
    	return result;
    }
    
    //DIT(ci) : Taille du chemin le plus long reliant une classe ci à une
    //classe racine dans le graphe d’héritage.    
    public int DIT(){
    	int result = 0;
    	
    	//TO DO
    	
    	return result;
    }
    
    //CLD(ci) : Taille du chemin le plus long reliant une classe ci à une
    //classe feuille dans le graphe d’héritage.   
    public int CLD(){
    	int result = 0;
    	
    	//TO DO
    	
    	return result;
    }
    
    //NOC(ci) : Nombre de sous-classes directes de ci.
    public int NOC(){
    	
    	//PROBLEM getChildren retourne la meme liste de children pour toutes les classes...
    
    	return Generalisation.getChildren().size();
    }
    
    //NOD(ci) : Nombre de sous-classes directes et indirectes de ci.
    
    public int NOD(){
    	int result = NOC();
    	
    	//PROBLEM supposer devenir ok mais rentre dans une boucle infinie pour le moment à cause de getChildren qui ne fonctionne pas...
    	
    	for(int i = 0; i<Generalisation.getChildren().size(); i++){
    		//result += Model.getClassFromName(Generalisation.getChildren().get(i)).NOD();
    		//System.out.println("Sous-Classe :" + Generalisation.getChildren().get(i));
    		//System.out.println("Nombre de sous-classe :" + className.NOC());
    	}
    	
    	return result;
    }
    
    //FIN EXTENSION
}
