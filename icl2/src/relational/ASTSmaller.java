package relational;

/**
 * Relation Operation of Smaller.
 * @author Ines Ribeiro 47226 Sofia Martins 47508
 *
 */
public class ASTSmaller implements ASTNode {

	private ASTNode expr1, expr2;
	
	public ASTSmaller(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates the exps and checks if exp1 is smaller than exp2
	 */
	@Override
	public IValue eval(Enviroment<IValue> env) {
		IValue v1;
		v1 = expr1.eval(env);
		IValue v2 = expr2.eval(env);
		return new IValueBool(((IValueInt) v1).getValue() < ((IValueInt)v2).getValue());
	}

	/**
	 * Compile < 
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		expr1.compile(code, env);
		expr2.compile(code, env);
		String L1 = code.getCurrFrame().getNewLabel();
		String L2 = code.getCurrFrame().getNewLabel();
		code.emit("isub");
		code.emit("iflt " + L1);
		code.emit("sipush 0");
		code.emit("goto " + L2);
		code.emit(L1 + ": ");
		code.emit("sipush 1");
		code.emit(L2 + ": ");		
	}
}
