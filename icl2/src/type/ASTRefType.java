package type;

public class ASTRefType {
	
	private final Type type;

	public ASTRefType(Type type) {
		this.type = type;
	}

	public Type nested_type() {
		return type;
	}

	@Override
	public String toString() {
		return "ref(" + type.toString() + ")";
	}

	@Override
	public boolean equals(Object other) {
		if(other == this) {
			return true;
		}

		if(other == null || !(other instanceof ASTRefType)) {
			return false;
		}

		ASTRefType o = (ASTRefType) other;

		return this.nested_type().equals(o.nested_type());
	}

	@Override
	public int hashCode() {
		return this.nested_type().hashCode() * 2 - 1;
	}
}
