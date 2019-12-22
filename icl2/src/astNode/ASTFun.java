package astNode;

import java.util.ArrayList;
import java.util.List;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VClosure;
import type.ASTClosureType;
import type.Type;

/**
 * AST Fun
 * @author 45699
 *
 */
public class ASTFun implements ASTNode {

	private ASTNode body;

	private List<ASTNode> args;

	private List<Type> types;

	public ASTFun(List<ASTNode> args, List<Type> types, ASTNode body) {
		this.body = body;
		this.args = args;
		this.types = types;
	}

	/**
	 * Calls for a closure with the arguments and the body presented in the function.
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) {
		return new VClosure(args, body, env);
	}

	/**
	 * Checks the type of a function.
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) {
		env = env.beginScope();
		Type type = null;
		Type bodyType = null;
		List<Type> argsType = new ArrayList<Type>();
		try {
			for (int i = 0; i < args.size(); i++) {
				try {
					env.assoc(((ASTId)args.get(i)).getId(), types.get(i));
					type = args.get(i).typeCheck(env);
					if (type instanceof ASTClosureType) {
						argsType = ((ASTClosureType)type).getTypeArgs();
						if (!type.getTypeString().equals(argsType.get(i).getTypeString())) {
							throw new Exception("Invalid Type for arg of fun.");
						}
					} else {
						if (!type.getTypeString().equals(types.get(i).getTypeString())) {
							throw new Exception("Invalid Type for arg of fun.");
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			type = body.typeCheck(env);
			if (type instanceof ASTClosureType) {
				bodyType = ((ASTClosureType)type).getType();
				if (!bodyType.getTypeString().equals(type.getTypeString())) {
					throw new Exception("Invalid Type for body of fun.");
				}
			}
			env.endScope();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return type;
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		// TODO Auto-generated method stub
		
	}

}
