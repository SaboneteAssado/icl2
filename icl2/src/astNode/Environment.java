package astNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Environment class
 * @author Miguel Araujo 45699 
 *
 */
public class Environment<E> implements EnvironmentAbs<E> {

	/**
	 * An environment needs to store, besides its parent environment, the list of associations
	 * between identifiers and values. 
	 */
	private EnvironmentAbs<E> prev;

	private Map<String, E> nodeVals;

	public Environment(EnvironmentAbs<E> prev) {
		this.prev = prev;
		nodeVals = new HashMap<String, E>();
	}

	/**
	 * Begins a new scope of environment that is the child of the current environment.
	 * @return the child of the current environment.
	 */
	@Override
	public EnvironmentAbs<E> beginScope() {
		return new Environment<E>(this);
	}

	/**
	 * Ends this scope of environment by going to its parent environment.
	 * @return the parent environment.
	 */
	@Override
	public EnvironmentAbs<E> endScope() {
		return prev;
	}

	/**
	 * Associates a identifier with a value.
	 * @param id
	 * @param val
	 * @throws Exception 
	 */
	@Override
	public void assoc(ASTId id, E val) throws Exception {
		String identifier = id.getId();
		E envVal = findId(identifier);
//		|| ( envVal instanceof IValue && val instanceof IValue)
		if ( envVal == null  )
			nodeVals.put(identifier, val);
		else {
			throw new Exception("ID Already Assigned");
		}
	}

	/**
	 * Searches for an identifier in an environment.
	 * @param id.
	 * @return val.
	 */
	@Override
	public E findId(String id){
		E val = nodeVals.get(id);
		if ( val != null)
			return val;
		else if ( prev != null )
			return prev.findId(id);
		else return val;
	}
}
