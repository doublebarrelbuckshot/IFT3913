package met;

import java.util.ArrayList;

public class Association implements Relation{
	private String relationName;
	private final ArrayList<Role> listRole = new ArrayList<>();

	public Association(){};

	public void setRelationName(String name){
		this.relationName = name;
	}

        public String recreateCode(){
    		String result = "RELATION " + this.relationName + "\nROLES\n";
    		for(int i=0; i<listRole.size(); i++){
    			result += "\tCLASS " + listRole.get(i).getClassName() + " " + listRole.get(i).getClassMultiplicity();
    			if(i<listRole.size()-1)
    				result += ",";
    			result += "\n";
    		}
    		return result;
    	}
        
        @Override
	public String printRelation(){
		return "(R) " + relationName;
	}
        
	public String getRelationName(){
		return this.relationName;
	}

	public void addRole(Role role){
		this.listRole.add(role);
	}

	public ArrayList<Role> getListRole(){
		return this.listRole;
	}

        @Override
	public String toString(){
		String result = "Relation Name: " + relationName;
		for (int i =0; i<listRole.size(); i++){
			result += "\tRole #" + i + ": " + listRole.get(i).toString();
		}
		return result;
	}
}

