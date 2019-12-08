package arithmetic;

import astNode.ASTNode;
import astNode.EnvironmentAbs;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VInt;

/**
 * Class that evaluates additions.
 * @author Miguel Araujo 45699
 *
 */
public class ASTAdd implements ASTNode {

	private ASTNode expr1, expr2;

	public ASTAdd(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates exps and exp1 + exp2.
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception{
		IValue v1 = expr1.eval(env);
		if ( v1 instanceof VInt) {
			IValue v2 = expr2.eval(env);
			if ( v2 instanceof VInt) {
				return new VInt(((VInt)v1).getVal() + ((VInt)v2).getVal());
			}
		}
		throw new Exception("Illegal arguments to + operator");
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		expr1.compile(code, Cenv);
		expr2.compile(code, Cenv);
		code.emit("iadd");
	}
}
