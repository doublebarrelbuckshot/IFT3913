package met;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

//    public String recreateGeneralizationCode() {
//
//        String result = "";
//
//        for (@SuppressWarnings("unused") Generalisation listGeneralization1 : listGeneralization) {
//
//            result = "GENERALIZATION " + listGeneralization..getParent()
//                    + "\n\tSUBCLASSES ";
//
//            for (int j = 0; j < Generalisation.getChildren().size(); j++) {
//                result += Generalisation.getChildren().get(j);
//
//                if (j < Generalisation.getChildren().size() - 1) {
//                    result += ", ";
//                }
//            }
//            result += ";";
//        }
//        return result;
//    }
    
    public String recreateGeneralizationCode() {

        String result = "";

        for (Generalisation listGeneralization1 : listGeneralization) {

            result = "GENERALIZATION " + listGeneralization1.getParent()
                    + "\n\tSUBCLASSES ";

            for (int j = 0; j < listGeneralization1.getChildren().size(); j++) {
                result += listGeneralization1.getChildren().get(j);

                if (j < listGeneralization1.getChildren().size() - 1) {
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
    //compte qu’une fois. (Nedra)   
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
    //locales/héritées auxquelles participe une classe ci. (Giancarlo)  
    public int CAC(){
    	int result = 0;
    	String current  = this.getClassName();
    	
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
    	return result;
    }
    
    //DIT(ci) : Taille du chemin le plus long reliant une classe ci à une
    //classe racine dans le graphe d’héritage. (Giancarlo)   
    public int DIT(){
    	
    	return recursiveDIT(this.getClassName(), 0);
    }
    
    public static int recursiveDIT(String cName, int count){
    	for(int i=0; i<Model.tempGeneralizations.size(); i++){
    		for(int j=0; j<Model.tempGeneralizations.get(i).getChildren().size(); j++){
    			if(cName.equals(Model.tempGeneralizations.get(i).getChildren().get(j)))
    				return recursiveDIT(Model.tempGeneralizations.get(i).getParent(), count+1);
    		}
    	}
    	return count;
    	

    	
    }
  
    //CLD(ci) : Taille du chemin le plus long reliant une classe ci à une
    //classe feuille dans le graphe d’héritage. (Giancarlo)
    public int CLD(){
    	
    	return recursiveCLD(this.getClassName(), 0);
    	
    
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
    
    //NOC(ci) : Nombre de sous-classes directes de ci. (Nedra)
    public int NOC(){
    	
    	//PROBLEM getChildren retourne la meme liste de children pour toutes les classes...
    
    	return 0;//  Generalisation.getChildren().size();
    }
    
    //NOD(ci) : Nombre de sous-classes directes et indirectes de ci. (Nedra)
    
    public int NOD(){
    	if(this.getListGeneralization().size() >0)
    		return recursiveNOD(this.getClassName());
    	return 0;
    	//PROBLEM supposer devenir ok mais rentre dans une boucle infinie pour le moment à cause de getChildren qui ne fonctionne pas...
    	
    	//for(int i = 0; i<Generalisation.getChildren().size(); i++){
    		//result += Model.getClassFromName(Generalisation.getChildren().get(i)).NOD();
    		//System.out.println("Sous-Classe :" + Generalisation.getChildren().get(i));
    		//System.out.println("Nombre de sous-classe :" + className.NOC());
    //	}
    	
    	
    }
    
    public static int recursiveNOD(String cName){
    	Classe current = Model.getClassFromName(cName);
    	
    	ArrayList<Integer> blah = new ArrayList<Integer>();
    	if(current.getListGeneralization().size() > 0){
    		int shit = 0;
    		for(int i=0; i<current.getListGeneralization().size(); i++){
    			blah.add(current.getListGeneralization().get(i).getChildren().size());
    			for(int j=0; j<current.getListGeneralization().get(i).getChildren().size(); j++)
    			{
    				
    				System.out.println("Current: " + current.getClassName() +"     " + current.getListGeneralization().get(i).getChildren().size() );
    				//if(Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().size() > 0)
    				//	System.out.println("SIZE OF CHILD: "+ j +"   " + Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().get(0).getChildren().size());
    				
    				//map.put(current.getListGeneralization().get(i).getChildren().get(j), recursiveCLD(current.getListGeneralization().get(i).getChildren().get(j), count++));
    				if(Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().size() > 0){
    				//	if(!countedCurrent){
    				//		countedCurrent = true;
    				//		blah.add(Model.getClassFromName(current.getListGeneralization().get(i).getChildren().get(j)).getListGeneralization().get(0).getChildren().size());
    				//	}
    						blah.add(recursiveNOD(current.getListGeneralization().get(i).getChildren().get(j) ));
    				//	 System.out.println("Here");
    				}
    				//else blah.add(1);
    				//else blah.add(1);
    				//	System.out.println("Current Node: " + current.getListGeneralization().get(i).getChildren().get(j) + " has this many children: " + blahs);
    			
    			}
    		}
    		for(int i=0; i<blah.size(); i++)
    		{
    			//System.out.println(current.getClassName() + " Kid " + i + blah.get(i));
    		}
    		return sumup(blah);
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
    //FIN EXTENSION
}
