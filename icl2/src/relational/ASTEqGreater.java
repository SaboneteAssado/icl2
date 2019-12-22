package relational;

import astNode.ASTNode;
import astNode.EnvironmentAbs;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VBool;
import iValue.VInt;
import type.ASTIntType;
import type.Type;

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
	 * Evaluates exps and compares exp1 >= exp2
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v1 = expr1.eval(env);
		if ( v1 instanceof VInt) {
			IValue v2 = expr2.eval(env);
			if ( v2 instanceof VInt) {
				return new VBool(((VInt) v1).getVal() >= ((VInt)v2).getVal());
			}
		}
		throw new Exception("Illegal arguments to >= operator");
	}

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

	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		Type expr1_type = null;
		Type expr2_type = null;
		expr1_type = expr1.typeCheck(env);
		expr2_type = expr2.typeCheck(env);
		if (!(expr1_type instanceof ASTIntType && expr2_type instanceof ASTIntType))
			throw new Exception("Invalid Type of Expressions");
		return expr1_type;
	}
}