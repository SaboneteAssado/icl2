package arithmetic;
import astNode.ASTNode;
import astNode.EnvironmentAbs;
import compiler.CodeAbs;
import compiler.CompEnvAbs;
import iValue.IValue;
import iValue.VInt;

public class ASTNum implements ASTNode{
	
	private VInt num;

	public ASTNum(int num) {
		this.num = new VInt(num);
	}

	/**
	 * Evaluates the value and symmetric
	 */
	@Override
	public IValue eval(EnvironmentAbs<IValue> env) {
		return num;
	}

	@Override
	public void compile(CodeAbs code, CompEnvAbs Cenv) {
		code.emit("sipush " + num.getVal() );
	}
}
