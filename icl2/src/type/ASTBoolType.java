package type;

public class ASTBoolType implements Type{
	
	private static final String BOOLEAN = "bool";

	public ASTBoolType() {
	}

	@Override
	public void show() {
		System.out.println(BOOLEAN);
	}

	@Override
	public String getTypeString() {
		return BOOLEAN;
	}

	@Override
	public String getTypeToCompile() {
		return "I";
	}

}
