package logical;

import atsnode.ASTNode;
import atsnode.Enviroment;
import compilacao.Code;
import compilacao.IComp;
import exceptions.InvalidElementsException;
import itype.IType;
import itype.ITypeBool;
import ivalue.IValue;
import ivalue.IValueBool;
/**
 * Logical Operation OR
 * @author Ines Ribeiro 47226 Sofia Martins 47508
 *
 */
public class ASTOr implements ASTNode {

	private ASTNode expr1, expr2;
	

	public ASTOr(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates the expressions and submits them to the logical operation OR
	 */
	@Override
	public IValue eval(Enviroment<IValue> env) {
		IValue v1;
		v1 = expr1.eval(env);
		IValue v2 = expr2.eval(env);
		return new IValueBool(((IValueBool) v1).getValue() || ((IValueBool)v2).getValue());
	}

	/**
	 * Checks the type of a logical OR expression.
	 */
	@Override
	public IType typeCheck(Enviroment<IType> env) {
		IType expr1_type = null;
		IType expr2_type = null;
		try {
			expr1_type = expr1.typeCheck(env);
			expr2_type = expr2.typeCheck(env);
			if (!(expr1_type instanceof ITypeBool && expr2_type instanceof ITypeBool))
					throw new InvalidElementsException("Invalid Type of Expressions.") ;
		} catch (InvalidElementsException e) {
			System.out.println(e.getMessage());
		}
		return expr1_type;
	}
	
	/**
	 * Emits the code necessary for jasmin to compile the logical OR.
	 */
	public void compile (Code code, IComp env) {
		expr1.compile(code, env);
		expr2.compile(code, env);
		code.emit("ior");
	}

}
