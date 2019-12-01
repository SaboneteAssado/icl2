package arithmetic;

import astNode.ASTNode;
import astNode.EnvironmentAbs;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VInt;

/**
 * Class to evaluate the symmetric value. 
 * @author Miguel Araujo 45699
 *
 */
public class ASTNeg implements ASTNode {

	private ASTNode num;

	public ASTNeg(ASTNode num) {
		this.num = num;
	}

	/**
	 * Evaluates num and does -num
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v = num.eval(env);
		if ( v instanceof VInt)
			return new VInt(-((VInt)v).getVal());
		throw new Exception("Illegal arguments to - operator");
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		num.compile(code, Cenv);
		code.emit("ineg");
	}
}
