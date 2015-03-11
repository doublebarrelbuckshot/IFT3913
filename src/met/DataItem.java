package met;

public class DataItem {
	
	private String attributeName;
	private String attributeType;
	
	public DataItem(String attributeName, String attributeType){
		this.attributeName = attributeName;
		this.attributeType = attributeType;		
	}
        
	public DataItem(){};
	
	public void setName(String attributeName){
		this.attributeName = attributeName;
	}

	public String printAttribute(){
		return this.attributeType + " " + this.attributeName;
	}
	
	public String getAttributeType(){
		return this.attributeType;
	}
	
	public String getAttributeName(){
		return this.attributeName;
	}
	public void setType(String attributeType){
		this.attributeType = attributeType;
		
	}
        @Override
	public String toString(){
		return "\tDataItem Name: " + this.attributeName 
                        + "\tDataItem Type: " + this.attributeType;
	}
}

