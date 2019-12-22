package compiler;

import java.io.FileNotFoundException;

import type.Type;

public interface FrameRefAbs {

	Type getType();

	String getId();

	/**
	 * Compile
	 */
	void dump() throws FileNotFoundException;

}