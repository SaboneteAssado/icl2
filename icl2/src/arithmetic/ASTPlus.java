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
public class ASTPlus implements ASTNode {

	private ASTNode expr1, expr2;

	public ASTPlus(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates each expression and adds.
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) {
		IValue v1 = expr1.eval(env);
		IValue v2 = expr2.eval(env);
		return new VInt(((VInt)v1).getVal() + ((VInt)v2).getVal());
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		expr1.compile(code, Cenv);
		expr2.compile(code, Cenv);
		code.emit("iadd");
	}
}
