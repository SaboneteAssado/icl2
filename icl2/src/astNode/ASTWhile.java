package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VBool;
import type.ASTBoolType;
import type.Type;

/**
 * Evals while
 * @author 45699
 *
 */
public class ASTWhile implements ASTNode {

	private ASTNode condition, expr;
	
	public ASTWhile(ASTNode condition, ASTNode expr) {
		this.condition = condition;
		this.expr = expr;
	}

	/**
	 * While the condition true keeps evaluating
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v1, v2 = null;
		v1 = condition.eval(env);
		if (((VBool) v1).getVal() == false)
			v2 = new VBool(false);
		while (((VBool) v1).getVal()) {
			v2 = expr.eval(env);
			v1 = condition.eval(env);
		}
		return v2;
	}

	/**
	 * Checks the type while
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) {
		Type typeCond = null;
		try {
			typeCond = condition.typeCheck(env);
			if (typeCond instanceof ASTBoolType)
				expr.typeCheck(env);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return typeCond;
	}

	/**
	 * Emits the code necessary for jasmin to compile the while expression.
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		code.newFrame("");
		
		String Label = code.getCurrFrame().getNewLabel();
		String Exit = code.getCurrFrame().getNewLabel();
		
		code.emit(Label + ":");
		condition.compile(code, env);
		code.emit("ifeq " + Exit);
		expr.compile(code, env);
		
		code.emit("pop");
		code.emit("goto " + Label);
		code.emit(Exit + ":");
		code.emit("push 0");
		
	}

}
