package relational;

import astNode.ASTNode;
import astNode.EnvironmentAbs;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VInt;

/**
 * Relation >=
 * @author Miguel Araujo 45699
 *
 */
public class ASTEqGreater implements ASTNode {

	private ASTNode expr1, expr2;

	public ASTEqGreater(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates exps and compares if exp1 is greater or equal to exp2
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) {
		IValue v1 = expr1.eval(env);
		if ( v1 instanceof VInt) {
			IValue v2 = expr2.eval(env);
			if ( v2 instanceof VInt) {
				return new IValueBool(((IValueInt) v1).getValue() >= ((IValueInt)v2).getValue());
	}

//	/**
//	 * Checks the type of the >=
//	 */
//	@Override
//	public IType typeCheck(Enviroment<IType> env) {
//		IType expr1_type = null;
//		IType expr2_type = null;
//		try {
//			expr1_type = expr1.typeCheck(env);
//			expr2_type = expr2.typeCheck(env);
//			if (!(expr1_type instanceof ITypeInt && expr2_type instanceof ITypeInt))
//				throw new InvalidElementsException("Invalid Type of Expressions");
//		} catch (InvalidElementsException e) {
//			System.out.println(e.getMessage());
//		}
//		return expr1_type;
//	}

	/**
	 * Compile >=
	 */
	public void compile (CodeAbs code, CompEnvAbs env) {
		expr1.compile(code, env);
		expr2.compile(code, env);
		String L1 = code.getCurrFrame().getNewLabel();
		String L2 = code.getCurrFrame().getNewLabel();
		code.emit("isub");
		code.emit("ifge " + L1);
		code.emit("sipush 0");
		code.emit("goto " + L2);
		code.emit(L1 + ": ");
		code.emit("sipush 1");
		code.emit(L2 + ": ");
	}
}