package relational;

import astNode.ASTNode;
import astNode.EnvironmentAbs;
import atsnode.Enviroment;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import exceptions.InvalidElementsException;
import iValue.IValue;
import iValue.VBool;
import iValue.VInt;
import itype.IType;
import itype.ITypeBool;
import itype.ITypeInt;
import type.ASTBoolType;
import type.ASTIntType;
import type.Type;

/**
 * Equals relational
 * @author Miguel Araujo 45699
 *
 */
public class ASTEquals implements ASTNode{

	private ASTNode expr1, expr2;

	public ASTEquals(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates exps and exp1 == exp2
	 */
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception{
		IValue v1 = expr1.eval(env);
		if ( v1 instanceof VInt) {
			IValue v2 = expr2.eval(env);
			if ( v2 instanceof VInt) {
				VBool result =  new VBool(((VInt) v1).getVal() == ((VInt)v2).getVal());
				return result;
			}
			v2 = expr2.eval(env);
			return new VBool(((VBool) v1).getVal() == ((VBool)v2).getVal());
		}
		throw new Exception("Illegal arguments to == operator");
	}

	/**
	 * Compile ==
	 */
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

	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		Type expr1_type = null;
		Type expr2_type = null;
			expr1_type = expr1.typeCheck(env);
			expr2_type = expr2.typeCheck(env);
			if (!(expr1_type instanceof ASTIntType && expr2_type instanceof ASTIntType))
				if (!(expr1_type instanceof ASTBoolType && expr2_type instanceof ASTBoolType))
					throw new Exception("Invalid Type of Expressions.");
		return new ASTBoolType();
	}

}

