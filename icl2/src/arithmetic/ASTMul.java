package arithmetic;

import astNode.ASTNode;
import astNode.Environment;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VInt;

/**
 * Class that evaluates muls.
 * @author Miguel Araujo
 *
 */
public class ASTMul implements ASTNode {

	private ASTNode expr1, expr2;

	public ASTMul(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates each expression and then muls.
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v1;
		v1 = expr1.eval(env);
		IValue v2 = expr2.eval(env);
		return new VInt(((VInt)v1).getVal() * ((VInt)v2).getVal());
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		expr1.compile(code, Cenv);
		expr2.compile(code, Cenv);
		code.emit("imul");
	}
}
