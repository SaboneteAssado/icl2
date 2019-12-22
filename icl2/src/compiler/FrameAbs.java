package compiler;

import java.io.FileNotFoundException;
import java.util.List;

public interface FrameAbs {

	/* (non-Javadoc)
	 * @see compiler.FramAbs#getLabel()
	 */
	String getLabel();

	/* (non-Javadoc)
	 * @see compiler.FramAbs#getNumLabel()
	 */
	int getNumLabel();

	/* (non-Javadoc)
	 * @see compiler.FramAbs#getNewLabel()
	 */
	String getNewLabel();

	/* (non-Javadoc)
	 * @see compiler.FramAbs#getFrameName()
	 */
	String getFrameName();

	/* (non-Javadoc)
	 * @see compiler.FramAbs#getStaticLink()
	 */
	String getStaticLink();

	/* (non-Javadoc)
	 * @see compiler.FramAbs#getVars()
	 */
	List<String> getVars();

	/* (non-Javadoc)
	 * @see compiler.FramAbs#setStaticLink(java.lang.String)
	 */
	void setStaticLink(String sl);

	/* (non-Javadoc)
	 * @see compiler.FramAbs#addVar(java.lang.String, java.lang.String)
	 */
	void addVar(String var);

	/* (non-Javadoc)
	 * @see compiler.FramAbs#dump()
	 */
	void dump() throws FileNotFoundException;

}