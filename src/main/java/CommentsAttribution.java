import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.printer.DotPrinter;

public class CommentsAttribution {

	public static void main(String[] args) {
	  //--------------JavaParser Comments Attribution (DEMO)
    	
    	try {
    	File sampleJavaFileWithComment = new File("C:\\Users\\Alexander Julianto\\Desktop\\CPEN422\\SampleDemoComment.java");
    	
    	CompilationUnit compUnit4 = JavaParser.parse(sampleJavaFileWithComment);
    	ExpressionStmt expressionStmt = compUnit4.findFirst(ExpressionStmt.class).get();
    	System.out.println("Comment on the expression statement: " + expressionStmt.getComment().get().getContent());
    	
		//--AST in Dot (graph description language) to .png image file using Graphviz
		DotPrinter printer = new DotPrinter(true);
		FileWriter fileWriter = new FileWriter("C:\\Users\\Alexander Julianto\\Desktop\\Java Parser\\ASTgraphs\\astComment.dot");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(printer.output(compUnit4));
		printWriter.close();
		//----
    	
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	//-----------------------------------

	}

}
