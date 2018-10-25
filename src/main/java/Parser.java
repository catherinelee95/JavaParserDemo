import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.printer.DotPrinter;
import com.github.javaparser.printer.JsonPrinter;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class Parser {
	public static void main(String[] args) {
		
   //----------------How to Parse (DEMO)

		try {
			File sampleJavaFile = new File("C:\\Users\\Alexander Julianto\\Desktop\\CPEN422\\SampleDemo.java");
			CompilationUnit compUnit1 = JavaParser.parse(sampleJavaFile);

			System.out.println(compUnit1); // Prints the whole file.
		
			// --AST in Dot (graph description language) to .png image file using Graphviz
			DotPrinter printer = new DotPrinter(true);
			FileWriter fileWriter = new FileWriter("C:\\Users\\Alexander Julianto\\Desktop\\Java Parser\\ASTgraphs\\ast.dot");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(printer.output(compUnit1));
			printWriter.close();
			// ----
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	// -----------------------------------
		
	}
}
