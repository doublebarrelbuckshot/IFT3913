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
    private final ArrayList<Metrics> listMetrics;
    private ArrayList<Aggregation> listAggregation;
    private ArrayList<DataItem> listParameter = new ArrayList<DataItem>();

    public Classe() {
        this.listGeneralization = new ArrayList<>();
        this.listAggregation = new ArrayList<>();
        this.listAssociation = new ArrayList<>();
        this.listOperation = new ArrayList<>();
        this.listAttribute = new ArrayList<>();
        this.listMetrics = new ArrayList<>();
    };

    public Classe(String className) {
        this.listGeneralization = new ArrayList<>();
        this.listAggregation = new ArrayList<>();
        this.listAssociation = new ArrayList<>();
        this.listOperation = new ArrayList<>();
        this.listAttribute = new ArrayList<>();
        this.listMetrics = new ArrayList<>();
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
    
    public void addMetric(Metrics metric){
    	listMetrics.add(metric);
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

    public void setListParameter(ArrayList<DataItem> listParameter){
    	this.listParameter = listParameter;
    }
    public void addParameter(DataItem d)
    {
    	this.listParameter.add(d);
    }
    
    public ArrayList<DataItem> getListParameter(){
    	return this.listParameter;
    }
    public ArrayList<Metrics> getListMetrics(){
    	return this.listMetrics;
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
    
   
    

    
   
    
   
    
   
    
  

  
   
    //NOD(ci) : Nombre de sous-classes directes et indirectes de ci. (Nedra)
    
        //FIN EXTENSION
}
