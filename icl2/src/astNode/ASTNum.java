package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VInt;
import type.ASTIntType;
import type.Type;

public class ASTNum implements ASTNode{

	private VInt num;

	private static Type type;

	public ASTNum(int num) {
		this.num = new VInt(num);
		type = new ASTIntType().singleton;
	}

	/**
	 * Evaluates the value and symmetric
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) {
		return num;
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		code.emit("sipush " + num.getVal() );
	}

	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		return type;
	}
}
