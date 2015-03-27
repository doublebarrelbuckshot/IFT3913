package met;

import java.util.*;

public class Generalisation {
    private String parent;
    private ArrayList<String> children;

    public Generalisation() {
        children = new ArrayList<>();
    };
	
    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParent() {
        return parent;
    }

    public void addChildren(String child) {
        children.add(child);
    }

    public ArrayList<String> getChildren() {
        return children; //return list de sous-classes
    }

    @Override
    public String toString() {
        
        String result = "Parent: " + parent + "\t";

        for (int i = 0; i < children.size(); i++) {
            result += "SubClass #" + i + ": " + this.children.get(i) + "\t";
        }
        
        return result;
    }
}
