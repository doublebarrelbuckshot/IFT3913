package met;

public enum Multiplicity{
	ONE("ONE"),
	MANY("MANY"),
	ONE_OR_MANY("ONE_OR_MANY"),
	OPTIONALLY_ONE("OPTIONALLY_ONE"),
	UNDEFINED("UNDEFINED");
	
	 private final String str;

    private Multiplicity(String str) {
      this.str = str;
    }

    public String getStr() {
        return this.str;
    }
}