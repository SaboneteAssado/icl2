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
public class ASTLet implements ASTNode{

	private Map<ASTNode, ASTNode> nodeVals;
	private ASTNode exp;
	private List<Type> types;

	public ASTLet( Map<ASTNode, ASTNode> nodeVals, ASTNode exp, List<Type> types) {
		this.nodeVals = nodeVals;
		this.exp = exp;
		this.types = types;
		this.types = types;
	}

	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		env = env.beginScope();

		Set<ASTNode> keys = nodeVals.keySet();
		Iterator<ASTNode> it = keys.iterator();
		while ( it.hasNext() ) {
			ASTNode key = it.next();
			env.assoc( ((ASTId) key).getId() , nodeVals.get(key).eval(env));
		}
		IValue v = exp.eval(env);
		env = env.endScope();
		return v;
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

		//kk
		Iterator<ASTNode> it  = nodeVals.keySet().iterator();
		int i = 0;
		while ( it.hasNext() ) {
			ASTNode key = it.next();
			ASTNode val = nodeVals.get(key);

			val.compile(code, Cenv);
			code.emit("putfield " + frameid + "/x" + i + " I" );
			i++;
		}

		code.emit("aload " + frameid);
		code.emit("putfield " + frameid + "/sl Ljava/lang/Object;" );
		code.emit("astore " + frameid);
	}

	@Override
	/**
	 * checks the type
	 */
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		Type type = null;
		env = env.beginScope();
		for (int i = 0; i < nodeVals.size(); i++) {
			if (types.get(i) instanceof ASTClosureType) {
				((ASTClosureType)types.get(i)).setEnv(env);
			}


			Set<ASTNode> keys = nodeVals.keySet();
			Iterator<ASTNode> it = keys.iterator();
			while ( it.hasNext() ) {
				ASTNode key = it.next();
				if (!key.typeCheck(env).getTypeString().equals(types.get(i).getTypeString())) 
					throw new Exception("Invalid Type for args of let.");
				env.assoc( ((ASTId) key).getId() , types.get(i));
			}
		}
		type = exp.typeCheck(env);
		env.endScope();
		return type;
	}

}
