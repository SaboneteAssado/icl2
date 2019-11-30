package compiler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class comp env
 * @author Miguel Araujo 45699
 *
 */
public class CompEnv implements CompEnvAbs {
	
	private Map<String, Integer> offSets;
	private Map<String, Integer> levels;
	
	public CompEnv(){
		offSets = new HashMap<String, Integer>();
		levels = new HashMap<String, Integer>();
	}
	/* (non-Javadoc)
	 * @see compiler.IEnvAbs#addVar(java.lang.String, int, int)
	 */
	@Override
	public void addVar(String id, int offSet, int level) {
		offSets.put(id, offSet);
		levels.put(id, level);
	}
	
	/* (non-Javadoc)
	 * @see compiler.IEnvAbs#getOffSet(java.lang.String)
	 */
	@Override
	public int getOffSet(String id) {
		return offSets.get(id);
	}
	
	/* (non-Javadoc)
	 * @see compiler.IEnvAbs#getLevel(java.lang.String)
	 */
	@Override
	public int getLevel(String id) {
		return levels.get(id);
	}
	
	/* (non-Javadoc)
	 * @see compiler.IEnvAbs#setOffSet(java.lang.String, int)
	 */
	@Override
	public void setOffSet(String id, int offSet) {
		offSets.put(id, offSet);
	}
	
	/* (non-Javadoc)
	 * @see compiler.IEnvAbs#setLevel(java.lang.String, int)
	 */
	@Override
	public void setLevel(String id, int level) {
		levels.put(id, level);
	}
}
