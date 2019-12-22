package type;

import type.Type;

public class ASTRefType<E> implements Type {

	private static final String REFERENCE = "ref ";
	
	private Type type;
		
	public ASTRefType(Type type) {
		this.type = type;
	}
	
	@Override
	public void show() {
		System.out.println(REFERENCE + type.getTypeString());
	}

	public Type getRefType() {
		return type;
	}
	
	@Override
	public String getTypeString() {
		return REFERENCE + type.getTypeString();
	}

	@Override
	public String getTypeToCompile() {
		return "Lref_" + type.getTypeToCompile();
	}

}
