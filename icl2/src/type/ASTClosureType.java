package type;

import java.util.List;

import astNode.EnvironmentAbs;

public class ASTClosureType implements Type {

	private static final String CLOSURE = "Closure";

	private List<Type> listArgs;
	
	private Type type;
	
	private EnvironmentAbs<Type> env;
	
	private String id;
	
	public ASTClosureType(String id, List<Type> listArgs, Type type) {
		this.listArgs = listArgs;
		this.type = type;
		this.id = id;
	}
	
	public void setEnv(EnvironmentAbs<Type> env) {
		this.env = env;
	}
	
	public EnvironmentAbs<Type> getEnv() {
		return env;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getId() {
		return id;
	}
	
	public List<Type> getTypeArgs() {
		return listArgs;
	}

	@Override
	public void show() {
		System.out.println(CLOSURE);
	}

	@Override
	public String getTypeString() {
		return CLOSURE;
	}

	@Override
	public String getTypeToCompile() {
		return "Ljava/lang/Object";
	}

}
