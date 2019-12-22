package type;

public class ASTIntType implements Type {

	private static final String INTEGER = "int";
	
	public ASTIntType() {
	}
	
	@Override
	public void show() {
		System.out.println(INTEGER);
	}

	@Override
	public String getTypeString() {
		return INTEGER;
	}

	@Override
	public String getTypeToCompile() {
		return "I";
	}

}
