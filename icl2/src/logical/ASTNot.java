package logical;

import astNode.ASTNode;
import astNode.EnvironmentAbs;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VInt;
import iValue.VBool;

/**
 * Logical !
 * @author Miguel Araujo 45699
 *
 */
public class ASTNot implements ASTNode {

	private ASTNode expr1;

	public ASTNot(ASTNode expr1) {
		this.expr1 = expr1;
	}

	/**
	 * Evaluates the expressions and submits them to the logical operation NOT
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v1 = expr1.eval(env);
		if ( v1 instanceof VInt) {
			return new VBool(!((VBool) v1).getVal());
		}
		throw new Exception("Illegal arguments to ! operator");
	}

	/**
	 * Compile !.
	 */
	public void compile (CodeAbs code, CompEnvAbs env) {
		expr1.compile(code, env);
		code.newFrame("");
		String label = code.getCurrFrame().getNewLabel();
		String label2 = code.getCurrFrame().getNewLabel();
		code.emit("ifeq " + label);
		code.emit("iconst_0");
		code.emit("goto " + label2);
		code.emit(label + ": ");
		code.emit("sipush 1");
		code.emit(label2 + ":");
	}
}
