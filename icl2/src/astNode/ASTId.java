package astNode;

import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import type.Type;

/**
 * Class that represents vars
 * @author Miguel Araujo 45699
 *
 */
public class ASTId implements ASTNode{

	private String id;
	private Type type;

	public ASTId (String id) {
		this.id = id;
		this.type = null;
	}

	/**
	 * gets the id from env
	 * @param env
	 * @throws Exception 
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) throws Exception {
		IValue v = env.find(id);
		return v;
	}

	/**
	 * Gets the id
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets id to id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
//		FrameAbs frame = code.getCurrFrame();
//		String frameid = frame.getFrameName();
//		
//		Cenv.addVar(id, code.getCurrFrame().getNumLabel(), code.getCurrFrameNumber());
//		
//		int level = Cenv.getLevel(id);
//		int offSet = Cenv.getOffSet(id);
//
//		code.emit("aload 0");
//		for( int i = 0; i <= level; i++) {
//			String next = code.getFrame((code.getCurrFrameNumber() - (i+1))).getFrameName();
//			code.emit("getfield " + frameid + "/sl L" + next);
//			frameid = next;
//		}
//		code.emit("getfield " + frameid + "/x" + offSet + " " + frameid);
//	}
//
//	@Override
//	public Type typeCheck(EnvironmentAbs<Type> env) {
//		Type v = null;
//		try {
//			v = env.find(id);
//			this.type = v;
//			System.out.println("1 " + type);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return v;
//	}
}

	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) throws Exception {
		Type v = null;
			v = env.find(id);
			this.type = v;
			System.out.println("1 " + type);
		return v;
	}
}
