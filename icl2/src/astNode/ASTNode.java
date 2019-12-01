package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;

/**
 * Interface for abstract syntax tree.
 * @author Miguel Araujo 45699
 *
 */
public interface ASTNode {

	/**
	 * Evaluates the value of an expression.
	 * @param env - Environment of IValues.
	 * @return the value of the expression.
	 */
	IValue eval(EnvironmentAbs<IValue> env) throws Exception;
	
	/**
	 * copile method for comp
	 * @param code
	 * @param Cenv
	 */
	void compile(CodeAbs code, CompEnvAbs Cenv);
}