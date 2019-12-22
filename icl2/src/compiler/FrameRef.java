package compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import type.ASTBoolType;
import type.ASTIntType;
import type.ASTRefType;
import type.Type;

/**
 * Class frame reference
 * @author 45699
 *
 */
public class FrameRef implements FrameRefAbs {
	
	private static final String REF = "Ref_";
	private String id;
	private Type type;

	/**
	 * Constructor
	 * @param type
	 * @param refCounter
	 */
	public FrameRef(Type type, int refCounter) {
		String refString = REF;
		this.type = type;
		while(this.type instanceof ASTRefType<?>){
			refString += REF;
			this.type = ((ASTRefType<?>)this.type).getRefType();
		}
		if(this.type instanceof ASTIntType)
			refString += "int";
		else if(this.type instanceof ASTBoolType)
			refString += "bool";
		id = refString + refCounter;
	}

	@Override
	public Type getType(){
		return type;
	}

	@Override
	public String getId(){
		return id;
	}

	/**
	 * Compile
	 */
	@Override
	public void dump() throws FileNotFoundException {
		String filename = id + ".j";
		PrintStream out = new PrintStream(new FileOutputStream(filename));
		out.println(".class public " + id);
		out.println(".super java/lang/Object");

		Type nextType = type;
		String value = "";
		if(!(nextType instanceof ASTRefType))
			value = "I";
		else {
			value += "L";
			while(nextType instanceof ASTRefType){
				value += REF;
				nextType = ((ASTRefType<?>)nextType).getRefType();
			}
			if(nextType instanceof ASTIntType)
				value += "int;";
			else if(nextType instanceof ASTBoolType)
				value += "bool;";			
		}
		out.println(".field public value " + value);
		out.println(".method public <init>()V ");
		out.println("aload_0");
		out.println("invokenonvirtual java/lang/Object/<init>()V");
		out.println("return");
		out.println(".end method");
		out.close();
	}
	
}
