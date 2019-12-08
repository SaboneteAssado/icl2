package iValue;

import java.util.List;

import astNode.ASTNode;
import astNode.EnvironmentAbs;

/**
 * Closure
 * @author Miguel
 *
 */
public class VClosure implements IValue {

	private ASTNode body;
	
	private List<ASTNode> args;
	
	private EnvironmentAbs<IValue> env;

	
	public VClosure(List<ASTNode> args, ASTNode body, EnvironmentAbs<IValue> env) {
		this.body = body;
		this.args = args;
		this.env = env;
	}
	
	public EnvironmentAbs<IValue> getEnv() {
		return env;
	}
	
	public List<ASTNode> getArgs() {
		return args;
	}
	
	public ASTNode getBody() {
		return body;
	}
	
	public void setEnv(EnvironmentAbs<IValue> env) {
		this.env = env;
	}
	
	public void setArgs(List<ASTNode> args) {
		this.args = args;
	}
	
	public void setBody(ASTNode body) {
		this.body = body;
	}

	@Override
	public void show() {
		System.out.print("Clos");
	}
}
