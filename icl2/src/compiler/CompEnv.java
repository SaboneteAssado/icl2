package compiler;

import java.util.HashMap;
import java.util.Map;

import type.Type;

/**
 * Class compilation env
 * @author Miguel Araujo 45699
 *
 */
public class CompEnv implements CompEnvAbs {

	private Map<String, Type> types;
	private Map<String, Integer> offsets;
	private Map<String, Integer> levels;
	
	public CompEnv() {
		offsets = new HashMap<String, Integer>();
		levels = new HashMap<String, Integer>();
		types = new HashMap<String, Type>();
	}
	
	/**
	 * Adds var to comp env
	 * @param id 
	 * @param type 
	 * @param offset
	 * @param level
	 */
	@Override
	public void addVar(String id, Type type, int offset, int level) {
		types.put(id, type);
		offsets.put(id, offset);
		levels.put(id, level);
	}

	/**
	 * Get offset
	 * @param id
	 * @return offset.
	 */
	@Override
	public int getOffset(String id) {
		return offsets.get(id);
	}

	/**
	 * Set offset
	 * @param id
	 * @param offset
	 */
	@Override
	public void setOffset(String id, int offset) {
		offsets.put(id, offset);
	}

	/**
	 * Get level
	 * @param id
	 * @return level
	 */
	@Override
	public int getLevel(String id) {
		return levels.get(id);
	}

	/**
	 * Set level
	 * @param id
	 * @param level
	 */
	@Override
	public void setLevel(String id, int level) {
		levels.put(id, level);
	}

	/**
	 * Get type
	 * @param id
	 * @return type
	 */
	@Override
	public Type getType(String id) {
		return types.get(id);
	}

	/**
	 * Set type
	 * @param id
	 * @param type
	 */
	@Override
	public void setType(String id, Type type) {
		types.put(id, type);
	}

}