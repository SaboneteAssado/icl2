package compiler;

import type.Type;

public interface CompEnvAbs {

	/**
	 * Adds var to comp env
	 * @param id 
	 * @param type 
	 * @param offset
	 * @param level
	 */
	void addVar(String id, Type type, int offset, int level);

	/**
	 * Get offset
	 * @param id
	 * @return offset.
	 */
	int getOffset(String id);

	/**
	 * Set offset
	 * @param id
	 * @param offset
	 */
	void setOffset(String id, int offset);

	/**
	 * Get level
	 * @param id
	 * @return level
	 */
	int getLevel(String id);

	/**
	 * Set level
	 * @param id
	 * @param level
	 */
	void setLevel(String id, int level);

	/**
	 * Get type
	 * @param id
	 * @return type
	 */
	Type getType(String id);

	/**
	 * Set type
	 * @param id
	 * @param type
	 */
	void setType(String id, Type type);

}