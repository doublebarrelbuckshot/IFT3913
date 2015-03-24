package met;

import java.util.*;

public class Generalisation {
    static private String parent;
    static private ArrayList<String> children;

    public Generalisation() {
        children = new ArrayList<>();
    };
	
    public void setParent(String parent) {
        Generalisation.parent = parent;
    }

    static public String getParent() {
        return parent;
    }

    public void addChildren(String child) {
        children.add(child);
    }

    static public ArrayList<String> getChildren() {
        return children; //return list de sous-classes
    }

    @Override
    public String toString() {
        
        String result = "Parent: " + parent + "\t";

        for (int i = 0; i < children.size(); i++) {
            result += "SubClass #" + i + ": " + Generalisation.children.get(i) + "\t";
        }
        
        return result;
    }
}
