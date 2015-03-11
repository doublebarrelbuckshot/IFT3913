package met;

import java.util.ArrayList;

public class Classe {

    private String className;
    private final ArrayList<DataItem> listAttribute;
    private final ArrayList<Operation> listOperation;
    private final ArrayList<Association> listAssociation;
    private final ArrayList<Generalisation> listGeneralization;
    private ArrayList<Aggregation> listAggregation;

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
}
