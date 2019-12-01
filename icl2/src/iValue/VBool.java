package iValue;

/**
 * Boolean
 * @author Miguel Araujo 45699
 *
 */
public class VBool implements IValue {

	private boolean bool;

	public VBool(boolean bool) {
		this.bool = bool;
	}

	public boolean getValue() {
		return bool;
	}

	public void setValue(boolean bool) {
		this.bool = bool;
	}

	@Override
	public void show() {
		System.out.print(bool);
	}

}
