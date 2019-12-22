package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import type.Type;

public class ASTPrint implements ASTNode {
	//so se tivermos as Strings feitas
	
	ASTNode expr;
	
	public ASTPrint(ASTNode expr) {
		this.expr = expr;
	}

	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		return expr.eval(env);
	}

	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		return expr.typeCheck(env);
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		expr.compile(code, env);
	}
}
