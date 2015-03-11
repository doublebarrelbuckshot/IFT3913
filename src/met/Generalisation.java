package met;

import java.util.*;

public class Generalisation {

    private String parent;
    private final ArrayList<String> children;

    public Generalisation() {
        this.children = new ArrayList<>();
    };
	
    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParent() {
        return this.parent;
    }

    public void addChildren(String child) {
        children.add(child);
    }

    public ArrayList<String> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        
        String result = "Parent: " + this.parent + "\t";

        for (int i = 0; i < this.children.size(); i++) {
            result += "SubClass #" + i + ": " + this.children.get(i) + "\t";
        }
        
        return result;
    }
}
