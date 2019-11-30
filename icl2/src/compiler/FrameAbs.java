package compiler;

import java.io.FileNotFoundException;
import java.util.List;

public interface FrameAbs {

	/**
	 * Get methods
	 */
	String getLabel();

	int getNumLabel();

	String getNewLabel();

	String getFrameName();

	String getStaticLink();

	List<String> getVars();

	void setStaticLink(String sl);

	/**
	 * Method to add a var to frame
	 * @param var - variable 
	 */
	void addVar(String var);

	/**
	 * Dump for jasmin
	 */
	void dump() throws FileNotFoundException;

}