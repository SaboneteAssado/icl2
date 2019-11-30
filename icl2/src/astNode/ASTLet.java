package astNode;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import compiler.FrameAbs;
import iValue.IValue;

/**
 * Class for let
 * @author Miguel Araujo 45699
 *
 */
public class ASTLet implements ASTNode{

	private Map<ASTNode, ASTNode> nodeVals;
	private ASTNode exp;
	
	public ASTLet( Map<ASTNode, ASTNode> nodeVals, ASTNode exp) {
		this.nodeVals = nodeVals;
		this.exp = exp;
	}
	
	@Override
	public IValue eval(Environment<IValue> env) {
		env = env.beginScope();
		
		Set<ASTNode> keys = nodeVals.keySet();
		Iterator<ASTNode> it = keys.iterator();
		while ( it.hasNext() ) {
			ASTNode key = it.next();
			try {
				env.assoc( (ASTId) key , nodeVals.get(key).eval(env));
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	//let x=1 in let y= 2 + x in x + y end + x end

}
