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
 * Logical NOT operation
 * @author Ines Ribeiro 47226 Sofia Martins 47508
 *
 */
public class ASTNot implements ASTNode {

	private ASTNode expr1;
	
	public ASTNot(ASTNode expr1) {
		this.expr1 = expr1;
	}

	/**
	 * Evaluates the expressions and submits them to the logical operation NOT
	 */
	@Override
	public IValue eval(Enviroment<IValue> env) {
		IValue v1;
		v1 = expr1.eval(env);
		return new IValueBool(!((IValueBool) v1).getValue());
	}

	/**
	 * Checks the type of a logical NOT expression.
	 */
	@Override
	public IType typeCheck(Enviroment<IType> env) {
		IType expr1_type = null;
		try {
			expr1_type = expr1.typeCheck(env);
			if (!(expr1_type instanceof ITypeBool))
				throw new InvalidElementsException("Invalid Type of expression.");
		} catch (InvalidElementsException e) {
			System.out.println(e.getMessage());
		}
		return expr1_type;
	}

	/**
	 * Emits the code necessary for jasmin to compile the logical NOT.
	 */
	public void compile (Code code, IComp env) {
		expr1.compile(code, env);
		code.newFrame("");
		String label = code.getCurrFrame().getNewLabel();
		String label2 = code.getCurrFrame().getNewLabel();
		code.emit("ifeq " + label);
		code.emit("iconst_0");
		code.emit("goto " + label2);
		code.emit(label + ": ");
		code.emit("sipush 1");
		code.emit(label2 + ":");
	}
}
