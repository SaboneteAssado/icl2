package astNode;

public interface EnvironmentAbs<E> {

	/**
	 * Begins a new scope of env
	 * @return the child of the current env
	 */
	EnvironmentAbs<E> beginScope();

	/**
	 * Ends this scope of env
	 * @return the parent env
	 */
	EnvironmentAbs<E> endScope();

	/**
	 * Associates
	 * @param id
	 * @param value
	 * @throws Exception 
	 */
	void assoc(String id, E value) throws Exception;

	/**
	 * Searches for an id in the current env and parent envs
	 * @param id
	 * @return value
	 * @throws Exception 
	 */
	E find(String id) throws Exception;

}