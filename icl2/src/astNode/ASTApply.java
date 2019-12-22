package astNode;

import java.util.List;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VClosure;
import type.ASTClosureType;
import type.Type;

/**
 * 
 * @author 45699
 */
public class ASTApply implements ASTNode {

	private ASTNode val;

	private List<ASTNode> values;

	public ASTApply(ASTNode val, List<ASTNode> values) {
		this.val = val;
		this.values = values;
	}

	/**
	 * Evals a closure
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		VClosure closure = (VClosure) val.eval(env);
		EnvironmentAbs<IValue> new_env = closure.getEnv().beginScope();
		for (int i = 0; i < closure.getArgs().size(); i++) {
			new_env.assoc(((ASTId)closure.getArgs().get(i)).getId(), values.get(i).eval(env));
		}
		IValue val = closure.getBody().eval(new_env);
		new_env.endScope();
		return val;
	}

	/**
	 * Checks the type
	 * @throws Exception 
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception{
		if (val.typeCheck(env) instanceof ASTClosureType) {
			ASTClosureType closure = (ASTClosureType) val.typeCheck(env);
			EnvironmentAbs<Type> new_env = closure.getEnv().beginScope();
			for (int i = 0; i < closure.getTypeArgs().size(); i++) {
				if (!closure.getTypeArgs().get(i).getTypeString().equals(values.get(i).typeCheck(env).getTypeString())) 
					throw new Exception("Invalid Element to Apply");
				new_env.assoc(closure.getId(), values.get(i).typeCheck(env));
				new_env.endScope();
			}
			return closure.getType();
		} else
			throw new Exception("Invalid Element to Apply");
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		// TODO Auto-generated method stub

	}

}
