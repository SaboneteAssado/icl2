package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class frame.
 * @author Miguel Araujo 45699
 *
 */
public class Frame implements FrameAbs {

	private static final String CLASS = ".class public ";
	private static final String EXTENSION = ".j";
	private static final String NAME = "frame_";
	private static final String LABEL = "label_";
	private static final String OBJECT = "java/lang/Object";

	private String name, sl;
	
	private int labelCounter;
	
	private List<String> vars;
	
	/**
	 * Constructor
	 * @param frame number
	 * @param vars
	 * @param sl - static link
	 */
	public Frame(int frame, String sl) {
		this.name = NAME + frame;
		vars = new ArrayList<String>();
		this.sl = sl;
		labelCounter = -1;
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#getLabel()
	 */
	@Override
	public String getLabel() {
		return LABEL + labelCounter;
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#getNumLabel()
	 */
	@Override
	public int getNumLabel() {
		return labelCounter;
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#getNewLabel()
	 */
	@Override
	public String getNewLabel() {
		labelCounter++;
		return LABEL + labelCounter;
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#getFrameName()
	 */
	@Override
	public String getFrameName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#getStaticLink()
	 */
	@Override
	public String getStaticLink() {
		return sl;
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#getVars()
	 */
	@Override
	public List<String> getVars() {
		return vars;
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#setStaticLink(java.lang.String)
	 */
	@Override
	public void setStaticLink(String sl) {
		this.sl = sl;
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#addVar(java.lang.String, java.lang.String)
	 */
	@Override
	public void addVar(String var) {
		vars.add(var);
	}
	
	/* (non-Javadoc)
	 * @see compiler.FramAbs#dump()
	 */
	@Override
	public void dump() throws FileNotFoundException {
		String filename = name + EXTENSION;
		File file = new File(filename);
		PrintStream out = new PrintStream(file);
		
		out.println(CLASS + name);
		out.println(".super " + OBJECT);
		
		if(!sl.equals("")) {
			out.println(".field public sl L" + sl + ";");
		} else
			out.println(".field public sl L"+OBJECT+";");
		Iterator<String> iter = vars.iterator();
		
		while(iter.hasNext()){
			String next = iter.next();
			out.println(".field public " + next);
		}
		
		out.println();
		out.println(".method public <init>()V ");
		out.println("aload_0");
		out.println("invokenonvirtual " + OBJECT + "/<init>()V");
		out.println("return");
		out.println(".end method");
		out.close();
	}
}