package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import type.ASTRefType;
import type.Type;

/**
 * Assign
 * @author 45699
 *
 */
public class ASTAssign implements ASTNode {

	private ASTNode memory_ref;
	private ASTNode expr;

	public ASTAssign(ASTNode e, ASTNode expr) {
		this.memory_ref = e;
		this.expr = expr;
	}

	/**
	 * Verifies if an expression is a memory ref
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v1 = expr.eval(env);
		if (memory_ref instanceof ASTId) {
			try {
				env.assoc(((ASTId)memory_ref).getId(), v1);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		} else {
			IValue ref = memory_ref.eval(env);
			String id = ref.toString();
			try {
				env.assoc(id, v1);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
		return v1;
	}

	/**
	 * Checks the type
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) {
		Type mem_ref_type, type = null;
		try {
			mem_ref_type = memory_ref.typeCheck(env);
			if(mem_ref_type instanceof ASTRefType<?>) {
				type = expr.typeCheck(env);
				Type refType = ((ASTRefType<?>)mem_ref_type).getRefType();
				if (type.getTypeString().equals(refType.getTypeString()))
					env.assoc(mem_ref_type.toString(), type);
			}
			else if(mem_ref_type instanceof ASTId) {
				type = expr.typeCheck(env);
				env.assoc(((ASTId)memory_ref).getId(), type);
			}
			else
				throw new Exception("The memory reference isn't of refence type.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return type;
	}

	/**
	 * Emits the code necessary for jasmin to compile the expression.
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs env) {
		String type = "";
		String frame_id = code.getCurrFrame().getFrameName();
		if(memory_ref instanceof ASTId)
			type = env.getType(((ASTId)memory_ref).getId()).getTypeToCompile();
		else
			type = "Ljava/lang/Object";
		memory_ref.compile(code, env);
		code.emit("checkcast " + frame_id);
		expr.compile(code, env);
		code.emit("putfield " + frame_id + "/V " + type);
	}

}
