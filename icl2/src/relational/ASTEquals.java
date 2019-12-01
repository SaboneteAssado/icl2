package relational;

import astNode.ASTNode;

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
	 * Evaluates exps and compares if exps are equal
	 */
	@Override
	public IValue eval(Enviroment<IValue> env) {
		IValue v1;
		v1 = expr1.eval(env);
		if (v1 instanceof IValueInt) {
			IValue v2 = expr2.eval(env);
			IValueBool result =  new IValueBool(((IValueInt) v1).getValue() == ((IValueInt)v2).getValue());
			return result;
		}
		IValue v2 = expr2.eval(env);
		return new IValueBool(((IValueBool) v1).getValue() == ((IValueBool)v2).getValue());
	}

	/**
	 * Checks ==
	 */
	@Override
	public IType typeCheck(Enviroment<IType> env) {
		IType expr1_type = null;
		IType expr2_type = null;
		try {
			expr1_type = expr1.typeCheck(env);
			expr2_type = expr2.typeCheck(env);
			if (!(expr1_type instanceof ITypeInt && expr2_type instanceof ITypeInt))
				if (!(expr1_type instanceof ITypeBool && expr2_type instanceof ITypeBool))
					throw new InvalidElementsException("Invalid Type of Expressions.");
		} catch (InvalidElementsException e) {
			System.out.println(e.getMessage());
		}
		return new ITypeBool();
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

