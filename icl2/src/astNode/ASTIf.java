package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VBool;
import type.ASTBoolType;
import type.Type;

/**
 * Class if
 * @author 45699
 *
 */
public class ASTIf implements ASTNode {

	private ASTNode condition, expr1, expr2;

	public ASTIf(ASTNode condition, ASTNode expr1, ASTNode expr2) {
		this.condition = condition;
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Eval
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v1, v2 = null;
		v1 = condition.eval(env);
		EnvironmentAbs<IValue> env2 = env.beginScope();
		if (((VBool) v1).getVal()) {
			v2 = expr1.eval(env2);
			env2.endScope();
		} else {
			v2 = expr2.eval(env2);
			env2.endScope();
		}
		return v2;
	}

	/**
	 * Checks the type of a condition
	 * @throws Exception 
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {

		Type exp1_type = null;
		Type exp2_type = null;
		Type cond_type = null;
		env = env.beginScope();
		cond_type = condition.typeCheck(env);
		if(cond_type instanceof ASTBoolType) {
			exp1_type = expr1.typeCheck(env);
			exp2_type = expr2.typeCheck(env);
		}
		env.endScope();
		if(!exp1_type.getTypeString().equals(exp2_type.getTypeString())) {
			throw new Exception("The expressions don't have the same type");
		}
		return exp1_type;
	}


	/**
	 * Compiles
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		condition.compile(code,env);
		code.newFrame("");
		
		String L1 = code.getCurrFrame().getNewLabel();
		String L2 = code.getCurrFrame().getNewLabel();
		
		code.emit("ifeq " + L1);
		expr1.compile(code, env);
		code.emit("goto " + L2);
		code.emit(L1 + ":");
		
		expr2.compile(code, env);
		code.emit(L2 + ":");
	}
}
