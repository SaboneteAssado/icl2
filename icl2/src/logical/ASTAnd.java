package logical;

import astNode.ASTNode;
import astNode.EnvironmentAbs;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VBool;

/**
 * Logical && 
 * @author Miguel Araujo 45699
 *
 */
public class ASTAnd implements ASTNode {

	private ASTNode expr1, expr2;

	public ASTAnd(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates the exps and && exps
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v1 = expr1.eval(env);
		if ( v1 instanceof VBool ) {
			IValue v2 = expr2.eval(env);
			if ( v2 instanceof VBool ) {
				return new VBool(((VBool) v1).getVal() && ((VBool)v2).getVal());
			}
		}
		throw new Exception("Illegal arguments to && operator");
	}

	/**
	 * Compile &&
	 */
	public void compile (CodeAbs code, CompEnvAbs env) {
		expr1.compile(code, env);
		expr2.compile(code, env);
		code.emit("iand");
	}

}
