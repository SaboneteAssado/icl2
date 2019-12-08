package type;

public class ASTIntType implements Type {

	public static final ASTIntType singleton = new ASTIntType();

	private ASTIntType() {}

	@Override
	public String toString() {
		return "IntType";
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
