package logical;

import astNode.ASTNode;
import astNode.EnvironmentAbs;
import atsnode.Enviroment;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import exceptions.InvalidElementsException;
import iValue.IValue;
import iValue.VInt;
import itype.IType;
import itype.ITypeBool;
import type.ASTBoolType;
import type.Type;
import iValue.VBool;

/**
 * Logical ||
 * @author Miguel Araujo 45699
 *
 */
public class ASTOr implements ASTNode {

	private ASTNode expr1, expr2;


	public ASTOr(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates the exps and checks exp1 || exp2
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception{
		IValue v1 = expr1.eval(env);
		if ( v1 instanceof VInt) {
			IValue v2 = expr2.eval(env);
			if ( v2 instanceof VInt) {
				return new VBool(((VBool) v1).getVal() || ((VBool)v2).getVal());
			}
		}
		throw new Exception("Illegal arguments to || operator");
	}

	/**
	 * Compile ||
	 */
	public void compile (CodeAbs code, CompEnvAbs env) {
		expr1.compile(code, env);
		expr2.compile(code, env);
		code.emit("ior");
	}

	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		Type expr1_type = null;
		Type expr2_type = null;
		expr1_type = expr1.typeCheck(env);
		expr2_type = expr2.typeCheck(env);
		if (!(expr1_type instanceof ASTBoolType && expr2_type instanceof ASTBoolType))
			throw new Exception("Invalid Type of Expressions.") ;
		return expr1_type;
	}
}
