package met;

import java.util.ArrayList;

public class Model {

    private String modelName;
    private ArrayList<Classe> listClass;

    public Model() {};	
	
    public Model(String modelName) {
        this.modelName = modelName;
        this.listClass = new ArrayList<>();
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

    public ArrayList<Classe> getListClass() {
        return listClass;
    }

    public void addClass(Classe modClass) {
        listClass.add(modClass);
    }
}
