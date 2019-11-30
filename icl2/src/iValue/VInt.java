package iValue;

/**
 * Integer value.
 * @author Miguel Araujo 45699
 *
 */
public class VInt implements IValue {

	private int num;
	
	public VInt(int num) {
		this.num = num;
	}
	
	public int getVal() {
		return num;
	}

	@Override
	public void show() {
		System.out.print(num);
	}
}
