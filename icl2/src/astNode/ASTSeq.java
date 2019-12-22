package astNode;

import java.util.List;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import type.Type;

/**
 * Sequence
 * @author 45699
 *
 */
public class ASTSeq implements ASTNode {

	private List<ASTNode> list;

	public ASTSeq(List<ASTNode> list) {
		this.list = list;
	}

	/**
	 * Evaluates the sequence
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue val = null;
		val = list.get(0).eval(env);
		for (int i = 1; i < list.size(); i++) {
			val = list.get(i).eval(env);
		}
		return val;
	}

	/**
	 * Check the type of the sequence.
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) {
		Type type = null;
		try {
			for(ASTNode node: list) {
				type = node.typeCheck(env);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return type;
	}

	/**
	 * Emits the code necessary for jasmin to compile a sequence.
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		for (ASTNode exp: list) {
			exp.compile(code, env);
		}

	}
}
