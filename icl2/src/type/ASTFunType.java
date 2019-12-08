package type;

import java.util.List;

public class ASTFunType implements Type{

	private final List<Type> params;
	private final Type ret;

	public ASTFunType(List<Type> params_and_ret) {
		final int arity = params_and_ret.size() - 1;

		this.params = params_and_ret.subList(0, arity);
		this.ret = params_and_ret.get(arity);
	}

	public List<Type> params() {
		return params;
	}

	public Type ret() {
		return ret;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();

		for(Type param : params) {
			final boolean is_fun = param instanceof ASTFunType;

			if(is_fun) {
				sb.append("(");
			}

			sb.append(param.toString());

			if(is_fun) {
				sb.append(")");
			}

			sb.append(" -> ");
		}

		final boolean is_fun = ret instanceof ASTFunType;

		if(is_fun) {
			sb.append("(");
		}

		sb.append(ret.toString());

		if(is_fun) {
			sb.append(")");
		}

		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other == this) {
			return true;
		}

		if(other == null || !(other instanceof ASTFunType)) {
			return false;
		}

		ASTFunType o = (ASTFunType) other;

		return this.params().equals(o.params()) && this.ret().equals(o.ret());
	}

	@Override
	public int hashCode() {
		return this.params().hashCode() + this.ret().hashCode();
	}
	
}
