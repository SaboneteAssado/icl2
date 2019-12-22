package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VBool;
import type.ASTBoolType;
import type.Type;

/**
 * Class bool
 * @author Miguel Araujo 45699
 *
 */
public class ASTBool implements ASTNode {

	private VBool bool;
	
	private ASTBoolType type;
	
	public ASTBool (boolean bool) {
		this.bool = new VBool(bool);
		this.type = new ASTBoolType();
	}
	
	/**
	 * Evaluates a boolean - true or false.
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) {
		return bool;
	}

	
	/**
	 * Checks the type of a boolean.
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) {
		return type;
	}
	
	/**
	 * Emits the code necessary for jasmin to compile a boolean.
	 * In compilation environments, booleans are numbers 0 (false) or 1 (true).
	 */
	public void compile (CodeAbs code, CompEnvAbs env) {
		if (bool.getVal())
			code.emit("sipush 1");
		else
			code.emit("sipush 0");
	}

}
