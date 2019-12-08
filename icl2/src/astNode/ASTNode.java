package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import type.Type;

/**
 * Interface for abstract syntax tree.
 * @author Miguel Araujo 45699
 *
 */
public interface ASTNode {

	/**
	 * evals the value of an exp
	 * @param env 
	 * @return the value exp
	 */
	IValue eval(EnvironmentAbs<IValue> env) throws Exception;
	
	/**
	 * checks the type of the exp
	 * @param env 
	 * @return the t
	 */
	Type typeCheck(EnvironmentAbs<Type> env) throws Exception;
	
	/**
	 * copile method for comp
	 * @param code
	 * @param Cenv
	 */
	void compile(CodeAbs code, CompEnvAbs Cenv);
}