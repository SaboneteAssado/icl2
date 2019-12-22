package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VRef;
import type.ASTRefType;
import type.Type;

/**
 * Derefs a node
 * @author 45699
 *
 */
public class ASTDeref implements ASTNode {

	private ASTNode value;

	private Type type;

	public ASTDeref(ASTNode value) {
		this.value = value;
		this.type = null;
	}

	/**
	 * If referenced returns value
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v = value.eval(env);
		if (v instanceof VRef)
			return ((VRef) v).getValue();
		return v;
	}

	/**
	 * Checks the type
	 * @throws Exception 
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		Type type = null;
		type = value.typeCheck(env);
		this.type = type;
		if (type instanceof ASTRefType<?>)
			type = ((ASTRefType<?>) type).getRefType();
		return type;
	}

	/**
	 * Compiles
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
//		Frame frame = code.getCurrFrame();
//		String frame_id = frame.getFrameName();
//		value.compile(code, env);
//		code.emit("checkcast " + frame_id);
//		code.emit("getfield " + frame_id + "/V " + type.getTypeToCompile());
	}

}
