package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for the comp code
 * @author Miguel Araujo 45699
 *
 */
public class Code implements CodeAbs {

	private static final String EXTENSION = ".j";

	private static final String IDENT = "	";

	private List<String> commands;
	private List<Frame> frames;
	
	private int frameCounter, currFrame;

	public Code() {
		this.commands = new LinkedList<String>();
		frameCounter = 0;
		currFrame = -1;
		frames = new ArrayList<Frame>();
		commands.add("aconst_null");
		commands.add("astore 0");
	}

	/* (non-Javadoc)
	 * @see compiler.CodeAbs#newFrame(java.lang.String)
	 */
	@Override
	public FrameAbs newFrame(String sl){
		Frame frame = new Frame(frameCounter, sl);
		if (!frames.isEmpty()) {
			frame.setStaticLink(frames.get(frameCounter-1).getFrameName());	
		}
		frames.add(frame);
		frameCounter++;
		currFrame++;
		return frame;
	}
	

	/* (non-Javadoc)
	 * @see compiler.CodeAbs#getFrame(int)
	 */
	@Override
	public FrameAbs getFrame(int i) {
		return frames.get(i);
	}

	/* (non-Javadoc)
	 * @see compiler.CodeAbs#getCurrFrameNumber()
	 */
	@Override
	public int getCurrFrameNumber() {
		return currFrame;
	}

	/* (non-Javadoc)
	 * @see compiler.CodeAbs#getCurrFrame()
	 */
	@Override
	public FrameAbs getCurrFrame() {
		if (frames.isEmpty())
			return null;
		return frames.get(frameCounter - 1);
	}

	/* (non-Javadoc)
	 * @see compiler.CodeAbs#emit(java.lang.String)
	 */
	@Override
	public void emit(String command) {
		commands.add(command);
	}

	/* (non-Javadoc)
	 * @see compiler.CodeAbs#dumpHeader(java.io.PrintStream, java.lang.String)
	 */
	@Override
	public void dumpHeader(PrintStream out, String name) {
		out.print(".class public " + name + "\r\n");
		out.print(".super java/lang/Object\r\n"); 
		out.print("\r\n");
		out.print(".method public <init>()V\r\n");
		out.print("aload_0\r\n");
		out.print("   invokenonvirtual java/lang/Object/<init>()V\r\n" );
		out.print("   return\r\n");
		out.print(".end method\r\n");
		out.print("\r\n");
		out.print(".method public static main([Ljava/lang/String;)V\r\n");
		out.print("       .limit locals 10\r\n");
		out.print("       .limit stack 256\r\n");
		out.print("       getstatic java/lang/System/out Ljava/io/PrintStream;\r\n");
		out.println("");
	}

	/* (non-Javadoc)
	 * @see compiler.CodeAbs#dumpFooter(java.io.PrintStream)
	 */
	@Override
	public void dumpFooter(PrintStream out) {
		out.println("");
		out.print("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\r\n");
		out.print("       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\r\n");
		out.print("\r\n");
		out.print("       return\r\n");
		out.print(".end method");
	}

	/* (non-Javadoc)
	 * @see compiler.CodeAbs#dump(java.lang.String)
	 */
	@Override
	public void dump(String filename) throws FileNotFoundException {
		File file = new File(filename + EXTENSION);
		PrintStream out = new PrintStream(file);
		dumpHeader(out, filename);
		for (String command: commands)
			out.println(IDENT + command);
		dumpFooter(out);
		dumpFrames();
	}

	/**
	 * Dump frames
	 * @throws FileNotFoundException if the file is not found.
	 */
	private void dumpFrames() throws FileNotFoundException {
		for (FrameAbs frame: frames)
			frame.dump();
	}
}