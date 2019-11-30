.class public heyyy
.super java/lang/Object

.method public <init>()V
aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       .limit locals 10
       .limit stack 256
       getstatic java/lang/System/out Ljava/io/PrintStream;

	aconst_null
	astore 0
	new frame_0
	dup
	invokespecial frame_0/<init>()V
	dup
	aload frame_0
	putfield frame_0/sl Ljava/lang/Obejct;
	astore 0
	
	sipush 1
	putfield frame_0/x0 I
	aload frame_0
	putfield frame_0/sl Ljava/lang/Object;
	astore frame_0

invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

       return
.end method