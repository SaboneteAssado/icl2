package type;

public class ASTBoolType implements Type{

	public static final ASTBoolType singleton = new ASTBoolType();

	private ASTBoolType() {}

	@Override
	public String toString() {
		return "BoolType";
	}

	@Override
	public boolean equals(Object other) {
		return other == this;
	}

	@Override
	public int hashCode() {
		return System.identityHashCode(this);
	}
	
}
