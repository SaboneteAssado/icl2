package relational;

import astNode.ASTNode;
import iValue.IValue;
import iValue.VInt;

/**
 * Equals relational
 * @author Miguel Araujo 45699
 *
 */
public class ASTEquals {

	private ASTNode expr1, expr2;

	public ASTEquals(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates exps and exp1 == exp2
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception{
		IValue v1 = expr1.eval(env);
		if ( v1 instanceof VInt) {
			IValue v2 = expr2.eval(env);
			if ( v2 instanceof VInt) {
			IValueBool result =  new IValueBool(((IValueInt) v1).getValue() == ((IValueInt)v2).getValue());
			return result;
		}
		IValue v2 = expr2.eval(env);
		return new IValueBool(((IValueBool) v1).getValue() == ((IValueBool)v2).getValue());
	}

	/**
	 * Compile ==
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		expr1.compile(code, env);
		expr2.compile(code, env);
		String L1 = code.getCurrFrame().getNewLabel();
		String L2 = code.getCurrFrame().getNewLabel();
		code.emit("isub");
		code.emit("ifeq " + L1);
		code.emit("sipush 0");
		code.emit("goto " + L2);
		code.emit(L1 + ": ");
		code.emit("sipush 1");
		code.emit(L2 + ": ");
	}

}

