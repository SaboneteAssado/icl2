package compiler;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public interface CodeAbs {

	/**
	 * Creates a new frame.
	 * @return the name of the frame.
	 */
	FrameAbs newFrame(String sl);

	FrameAbs getFrame(int i);

	/**
	 * 
	 * @return an integer representing the number of the current frame.
	 */
	int getCurrFrameNumber();

	/**
	 * 
	 * @return an object type Frame representing the current frame.
	 */
	FrameAbs getCurrFrame();

	/**
	 * Adds commands
	 * @param command to be added.
	 */
	void emit(String command);

	/**
	 * Header
	 * @param out PrintStream to the file
	 * @param name of the .j file
	 */
	void dumpHeader(PrintStream out, String name);

	/**
	 * Footer
	 * @param out PrintStream to the file
	 */
	void dumpFooter(PrintStream out);

	/**
	 * Dump utils
	 * @param filename - name of the file.
	 * @throws FileNotFoundException if the file is not found.
	 */
	void dump(String filename) throws FileNotFoundException;

}