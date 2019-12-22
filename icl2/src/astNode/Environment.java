package astNode;

import java.util.ArrayList;
import java.util.List;

import iValue.IValue;
import iValue.VRef;
import type.ASTRefType;

/**
 * Environment
 * @author 45699
 *
 */
public class Environment<E> implements EnvironmentAbs<E> {

	private EnvironmentAbs<E> prev;

	private List<ASTId> ids;
	private List<E> values;

	public Environment(EnvironmentAbs<E> prev) {
		this.prev = prev;
		ids = new ArrayList<ASTId>();
		values = new ArrayList<E>();
	}

	/**
	 * Begins a new scope of env
	 * @return the child of the current env
	 */
	@Override
	public EnvironmentAbs<E> beginScope() {
		return new Environment<E>(this);
	}

	/**
	 * Ends this scope of env
	 * @return the parent env
	 */
	@Override
	public EnvironmentAbs<E> endScope() {
		return prev;
	}

	/**
	 * Associates
	 * @param id
	 * @param value
	 * @throws Exception 
	 */
	@Override
	public void assoc(String id, E value) throws Exception {
		E resultEnv = searchInEnv(id);
		E result = null;
		if (resultEnv != null) {
			if(resultEnv instanceof ASTRefType && value instanceof IValue) {
				((VRef)resultEnv).setValue((IValue)value);
			}
		} else {
			try {
				result = find(id);
			} catch (Exception e) {
				ids.add(new ASTId(id));
				values.add(value);
			}
			if (result != null && resultEnv == null) {
				ids.add(new ASTId(id));
				values.add(value);
			} else if (result != null && resultEnv != null) {
				throw new Exception("Id is Already Declared");
			}
		}
	}

	/**
	 * Searches for an id in the current env and parent envs
	 * @param id
	 * @return value
	 * @throws Exception 
	 */
	@Override
	public E find(String id) throws Exception{
		E result = searchInEnv(id);
		if (result != null)
			return result;
		else if (prev != null)
			return prev.find(id);
		throw new Exception("Can't Find the Element");
	}

	/**
	 * Searches for an id in an env
	 * @param id
	 * @return value
	 */
	private E searchInEnv(String id) {
		E value = null;
		for (int i = 0; i < this.ids.size(); i++) {
			if(this.ids.get(i).getId().equals(id)) {
				value = values.get(i);
			}
		}
		return value;
	}
}
