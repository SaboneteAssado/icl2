package astNode;

import java.util.List;

import atsnode.Enviroment;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import compiler.Frame;
import compiler.FrameAbs;
import exceptions.DontFindException;
import iValue.IValue;
import itype.IType;
import type.Type;

/**
 * Class that represents vars
 * @author Miguel Araujo 45699
 *
 */
public class ASTId implements ASTNode{

	private String id;

	public ASTId ( String id ) {
		this.id = id;
	}

	/**
	 * gets the id from env
	 * @param env
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) {
		IValue v = env.findId(id);
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
		FrameAbs frame = code.getCurrFrame();
		String frameid = frame.getFrameName();
		
		Cenv.addVar(id, code.getCurrFrame().getNumLabel(), code.getCurrFrameNumber());
		
		int level = Cenv.getLevel(id);
		int offSet = Cenv.getOffSet(id);

		code.emit("aload 0");
		for( int i = 0; i <= level; i++) {
			String next = code.getFrame((code.getCurrFrameNumber() - (i+1))).getFrameName();
			code.emit("getfield " + frameid + "/sl L" + next);
			frameid = next;
		}
		code.emit("getfield " + frameid + "/x" + offSet + " " + frameid);
	}

	@Override
	public Type typeCheck(EnvironmentAbs<Type> env) {
		Type v = null;
		try {
			v = env.find(id);
			this.type = v;
			System.out.println("1 " + type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return v;
	}
}
