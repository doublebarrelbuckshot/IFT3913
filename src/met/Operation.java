package met;

import java.util.ArrayList;

public class Operation {
	private String operationName;
	private final ArrayList<DataItem> parameters = new ArrayList<DataItem>();
	private String operationReturnType;
	
    public Operation() {};
	
	public void setOperationName(String opName) {
        this.operationName = opName;
    }

    public String getOperationName() {
        return this.operationName;
    }
    
    //EXTENSION
    public int getNumParameters(){
    	return this.parameters.size();
    }
    //FIN EXTENSION

    public void addParameter(DataItem DI) {
        parameters.add(DI);
    }

    public void setOperationReturnType(String operationReturnType) {
        this.operationReturnType = operationReturnType;
    }
    
    //EXTENSION
    public ArrayList<DataItem> getParameter(){
    	return this.parameters;
    }
    //FIN EXTENSION
	
    public String printOperation(){
	String result = operationReturnType + " " + operationName+ "(";
	
        for(int i=0; i<parameters.size(); i++){
            result += parameters.get(i).getAttributeType();
            if(i<parameters.size()-1){
		result += ", ";
            }
	}
	
        result += ")";
	return result;
    }
	
    public String toString(){
		String val = "";
		val += "OperationName: " + this.operationName + "     Operation Return Type: " + this.operationReturnType;
		for(int i=0; i<parameters.size(); i++)
		{
			val += parameters.get(i).toString();
		}
		return val;
	}
}
