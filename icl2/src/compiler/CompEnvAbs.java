package compiler;

public interface CompEnvAbs {

	/**
	 * Add var to the comp env
	 * @param id of var
	 * @param offSet in frame
	 * @param level
	 */
	void addVar(String id, int offSet, int level);

	/**
	 * 
	 * @param id
	 * @return int OffSet
	 */
	int getOffSet(String id);

	/**
	 * 
	 * @param id
	 * @return int level
	 */
	int getLevel(String id);

	/**
	 * 
	 * @param id
	 * @param offSet
	 */
	void setOffSet(String id, int offSet);

	/**
	 * 
	 * @param id
	 * @param level
	 */
	void setLevel(String id, int level);

}