package met;

public class Role {

	private String className;
	private Enum<Multiplicity> classMultiplicity;

	public Role(){};
	
        @Override
	public String toString(){
		return "Class Name: " + this.className + "\tClassMultiplicity: " + this.classMultiplicity.toString() + "\t";
	}
        
	public void setClassName(String name){
		this.className = name;
	}

	public String getClassName(){
		return this.className;
	}

	public void setClassMultiplicity(String multiplicity) throws MetricSyntaxException{
		this.classMultiplicity = getMultiplicityEnum(multiplicity);	
	}

	public Enum<Multiplicity> getClassMultiplicity(){
		return classMultiplicity;

	}

	public Enum<Multiplicity> getMultiplicityEnum(String multiplicity)throws MetricSyntaxException{

            switch(multiplicity){
                case "MANY":
                    return Multiplicity.MANY;
                case "ONE":
                    return Multiplicity.ONE;
                case "ONE_OR_MANY":
                    return Multiplicity.ONE_OR_MANY;
                case "UNEDEFINED":
                    return Multiplicity.UNDEFINED;
                case "OPTIONALLY_ONE":
                    return Multiplicity.OPTIONALLY_ONE;
                default :
                    throw new MetricSyntaxException("MULTIPLICITY INVALID");
            }

	}
}
