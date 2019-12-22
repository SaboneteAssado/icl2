package iValue;

/**
 * Reference val
 * @author 45699
 *
 */
public class VRef implements IValue {

	private IValue cell;
	
	
	public VRef(IValue cell) {
		this.cell = cell;
	}

	public IValue getValue() {
		return cell;
	}
	
	public void setValue(IValue cell) {
		this.cell = cell;
	}
	
	@Override
	public void show() {
		System.out.print("Cell(");
		cell.show();
		System.out.print(")");
	}

}
