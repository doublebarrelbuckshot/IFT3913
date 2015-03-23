package met;

import java.util.ArrayList;

public class Model {

    private String modelName;
    static private ArrayList<Classe> listClass;

    public Model() {};	
	
    public Model(String modelName) {
        this.modelName = modelName;
        listClass = new ArrayList<Classe>();
    };
		
    public Classe getClassFromName(String className) {
        for (Classe listClas : listClass) {
            if (listClas.getClassName().equals(className)) {
                return listClas;
            }
        }
        return null;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return this.modelName;
    }

    static public ArrayList<Classe> getListClass() {
        return listClass;
    }

    public void addClass(Classe modClass) {
        listClass.add(modClass);
    }
    
    public int test(){
    	int result=0;
  
    	for (int i = 0; i<listClass.size();i++){
    		
    	}
    	
    	return result;
    }
}
