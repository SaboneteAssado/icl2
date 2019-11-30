package arithmetic;

import astNode.ASTNode;
import astNode.Environment;
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
	 * Evaluates the value and symmetric
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = num.eval(env);
		return new VInt(-((VInt)v).getVal());
	}
	
	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		num.compile(code, Cenv);
		code.emit("ineg");
	}
}
