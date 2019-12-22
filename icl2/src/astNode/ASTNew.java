package astNode;

import type.ASTRefType;
import type.Type;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VRef;

/**
 * Class to create a new memory ref in env
 * @author 45699
 *
 */
public class ASTNew implements ASTNode {

	private ASTNode value;
	@SuppressWarnings("unused")
	private Type type;

	public ASTNew(ASTNode value) {
		this.value = value;
		type = null;
	}
	/**
	 * Evals new
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v = null;
		v = newRef(env);
		return v;
	}

	/**
	 * Creates a new ref 
	 * @param env
	 * @return the reference of a memory space.
	 * @throws Exception 
	 */
	private IValue newRef(EnvironmentAbs<IValue> env) throws Exception {
		VRef ref = null;
		IValue val = value.eval(env);
		ref = new VRef(val);
		return ref;
	}

	/**
	 * Checks the type of a new declaration.
	 * @throws Exception 
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		Type type = null;
		type = value.typeCheck(env);
		this.type = type;
		return new ASTRefType<>(type);
	}

	/**
	 * compiles
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		//TODO
	}
}
