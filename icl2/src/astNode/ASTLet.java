package astNode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import compiler.FrameAbs;
import iValue.IValue;
import type.ASTClosureType;
import type.Type;

/**
 * Class for let
 * @author Miguel Araujo 45699
 *
 */
public class ASTLet implements ASTNode {

	private List<ASTNode> ids;

	private List<ASTNode> id_values;

	private List<Type> types;

	private ASTNode expr2;

	public ASTLet(List<ASTNode> ids, List<Type> types, List<ASTNode> exprs1, ASTNode expression) {
		this.ids = ids;
		this.id_values = exprs1;
		this.expr2 = expression;
		this.types = types;
	}

	/**
	 * Evaluates a let expression (a declaration). This expression defines identifiers and their values (let part of declaration)
	 * and then requests a appliance of the same values in an expression (in). 
	 * This method defines the same identifiers and then evaluates the expression. 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) {
		IValue v2 = null;
		try {
			env = env.beginScope();
			for(int i = 0; i < id_values.size(); i++) {
				IValue v1 = id_values.get(i).eval(env);
				env.assoc(((ASTId)ids.get(i)).getId(), v1);		 
			}
			v2 = expr2.eval(env);
			env = env.endScope();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return v2;
	}

	/**
	 * Checks the type of a let construction.
	 */
	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		Type type = null;
		env = env.beginScope();
		for (int i = 0; i < ids.size(); i++) {
			if (types.get(i) instanceof ASTClosureType) {
				((ASTClosureType)types.get(i)).setEnv(env);
			}
			env.assoc(((ASTId)ids.get(i)).getId(), types.get(i));
			if (!ids.get(i).typeCheck(env).getTypeString().equals(types.get(i).getTypeString())) {
				throw new Exception("Invalid Type for args of let.");
			}
		}
		type = expr2.typeCheck(env);
		env.endScope();
		return type;
	}

	/**
	 * Emits jasmin to compile let (buggy)
	 */
	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		FrameAbs currFrame = code.getCurrFrame();
		String sl = "";

		if (currFrame != null)
			sl = code.getCurrFrame().getStaticLink();

		FrameAbs newFrame = code.newFrame(sl);
		String frameid = newFrame.getFrameName();

		//create new frame
		code.emit("new " + frameid);
		code.emit("dup");
		code.emit("invokespecial " + frameid + "/<init>()V");
		code.emit("dup");

		//store SL
		code.emit("aload " + frameid);
		code.emit("putfield " + frameid + "/sl Ljava/lang/Obejct;");

		//update
		code.getCurrFrame().setStaticLink(frameid);
		sl = code.getCurrFrame().getStaticLink();
		code.emit("astore 0");
		code.emit("");

//		//kk
//		Iterator<ASTNode> it  = nodeVals.keySet().iterator();
//		int i = 0;
//		while ( it.hasNext() ) {
//			ASTNode key = it.next();
//			ASTNode val = nodeVals.get(key);
//
//			val.compile(code, Cenv);
//			code.emit("putfield " + frameid + "/x" + i + " I" );
//			i++;
//		}
//
//		code.emit("aload " + frameid);
//		code.emit("putfield " + frameid + "/sl Ljava/lang/Object;" );
//		code.emit("astore " + frameid);
	}
}
