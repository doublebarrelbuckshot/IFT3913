package met;

import java.util.ArrayList;
//import java.util.stream.Collectors;

public class Aggregation implements Relation {

    private Role container;
    private final ArrayList<Role> listRole;

    public Aggregation() {
        this.listRole = new ArrayList<>();
    };

    public void setContainer(Role container) {
        this.container = container;
    }

    @Override
    public String printRelation() {
        String result = "(A) ";

        for (int i = 0; i < listRole.size(); i++) {
            result += "P_" + listRole.get(i).getClassName();
            if (i < listRole.size() - 1) {
                result += ", ";
            }
        }
        return result;

    }

    public String recreateCode() {
        String result = "AGGREGATION\nCONTAINER\n\tCLASS " 
                + this.container.getClassName() + " "
                + this.container.getClassMultiplicity() + "\nROLES\n";
        for (int i = 0; i < listRole.size(); i++) {
            result += "\tCLASS " + this.listRole.get(i).getClassName() + " " 
                    + this.listRole.get(i).getClassMultiplicity();
            if (i < listRole.size() - 1) {
                result += ",";
            }
            result += "\n";
        }

        return result;
    }

    public Role getContainerClass() {
        return this.container;
    }

    public void addRole(Role role) {
        this.listRole.add(role);
    }

    public ArrayList<Role> getListRole() {
        return this.listRole;
    }

    @Override
    public String toString() {
        String result = "Container: " + container.getClassName()
                + "  ContainerMultiplicity: "
                + container.getClassMultiplicity().toString();
        for (int i = 0; i < listRole.size(); i++) {
            result += "\tRole #" + i + ": "
                    + listRole.get(i).toString();
        }
        return result;
    }

}
