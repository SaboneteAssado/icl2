package astNode;

public interface EnvironmentAbs<E> {

	/**
	 * Begins a new scope of environment that is the child of the current environment.
	 * @return the child of the current environment.
	 */
	EnvironmentAbs<E> beginScope();

	/**
	 * Ends this scope of environment by going to its parent environment.
	 * @return the parent environment.
	 */
	EnvironmentAbs<E> endScope();

	/**
	 * Associates a identifier with a value.
	 * @param id
	 * @param val
	 * @throws Exception 
	 */
	void assoc(ASTId id, E val) throws Exception;

	/**
	 * Searches for an identifier in an environment.
	 * @param id.
	 * @return val.
	 */
	E findId(String id);

}