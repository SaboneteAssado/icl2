package relational;

/**
 * Relation Greater.
 * @author Miguel Araujo 45699
 *
 */
public class ASTGreater implements ASTNode {

	private ASTNode expr1, expr2;
	
	public ASTGreater(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates the exps and checks if exp1 is greater than exp2
	 */
	@Override
	public IValue eval(Enviroment<IValue> env) {
		IValue v1;
		v1 = expr1.eval(env);
		IValue v2 = expr2.eval(env);
		return new IValueBool(((IValueInt) v1).getValue() > ((IValueInt)v2).getValue());
	}

	/**
	 * Checks >
	 */
	@Override
	public IType typeCheck(Enviroment<IType> env) {
		IType expr1_type = null;
		IType expr2_type = null;
		try {
			expr1_type = expr1.typeCheck(env);
			expr2_type = expr2.typeCheck(env);
			if (!(expr1_type instanceof ITypeInt && expr2_type instanceof ITypeInt))
				throw new InvalidElementsException("Invalid Type of Expressions.");
		} catch (InvalidElementsException e) {
			System.out.println(e.getMessage());
		}
		return new ITypeBool();
	}

	/**
	 * compile > 
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		expr1.compile(code, env);
		expr2.compile(code, env);
		String L1 = code.getCurrFrame().getNewLabel();
		String L2 = code.getCurrFrame().getNewLabel();
		code.emit("isub");
		code.emit("ifgt " + L1);
		code.emit("iconst_0");
		code.emit("goto " + L2);
		code.emit(L1 + ": ");
		code.emit("iconst_1");
		code.emit(L2 + ": ");
	}
}
