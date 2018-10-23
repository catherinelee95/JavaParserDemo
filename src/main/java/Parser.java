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
			CompilationUnit compUnit1 = JavaParser.parse("import com.demo; public class DemoClass{ public void printMyName(String myName){System.out.println(myName);} }");
			File sampleJavaFile = new File("C:\\Users\\Alexander Julianto\\Desktop\\CPEN422\\SampleDemo.java");
			//CompilationUnit compUnit1 = JavaParser.parse(sampleJavaFile);

			//System.out.println(compUnit1); // Prints the whole file.
			//System.out.println(new JsonPrinter(true).output(compUnit1)); // Prints the AST in JSON

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
		
		
		
	//--------------Symbol Solver (DEMO)
    	
	   // Set up a minimal type solver 
       CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
       combinedTypeSolver.add(new ReflectionTypeSolver());
       // Configure JavaParser to use type resolution
       JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedTypeSolver);
       JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
		
		
		 // Parse some code 
       CompilationUnit compUnit2a = JavaParser.parse("public class DemoClass{ public void printMyName(int myName){ System.out.println(myName); } }");
       // Find all the method call expr
       compUnit2a.findAll(MethodCallExpr.class).forEach(be -> {

           // Show the calls and input variable type
           //System.out.println(be.resolveInvokedMethod().getQualifiedSignature());
       });
       
       
       // Parse some code again
       CompilationUnit compUnit2b = JavaParser.parse("class X { double x() { int a = 0; double b = a + 1.0; return 1.0 - 5; } }");

       // Find all the calculations with two sides:
       compUnit2b.findAll(BinaryExpr.class).forEach(be -> {
           // Find out what type it has:
           ResolvedType resolvedType = be.calculateResolvedType();

           //System.out.println(be.toString() + " is a: " + resolvedType);
       });
		
     //-----------------------------------
       
       
       
     // --------------JavaParser Code Analysis (DEMO)

		try {

			String fileLocation = "C:\\Users\\Alexander Julianto\\Desktop\\CPEN422\\group4\\src\\main\\java\\org\\jpacman\\framework\\model\\Game.java";
			File jpacmanFile = new File(fileLocation);
			CompilationUnit compUnit3 = JavaParser.parse(jpacmanFile);

			long numMethods = compUnit3.findAll(MethodDeclaration.class).stream().count();

			Object[] methods = compUnit3.findAll(MethodDeclaration.class)
					.stream()
					.filter(m -> m.getParameters().size() > 1)
					.toArray();

			//System.out.println("Number of methods in the class: " + numMethods);
			//System.out.println("N of methods with more than 1 params: " + methods.length);

			//System.out.println("My methods are: ");
			for (int i = 0; i < methods.length; i++) {
				//System.out.println(((MethodDeclaration) methods[i]).getNameAsString());
			}

     // -----------------------------------

			
			
	 // --------------JavaParser Refactoring Code (DEMO)

			Iterator<MethodDeclaration> allPublicMethods = compUnit3.findAll(MethodDeclaration.class).stream()
					.filter(m -> m.isPublic()).iterator();

			while (allPublicMethods.hasNext()) {
				MethodDeclaration curMethod = allPublicMethods.next();
				curMethod.setPublic(false);
				curMethod.setProtected(true);
			}

			// Writing a new java file
			String content = compUnit3.toString();

			FileWriter fw = new FileWriter("C:\\Users\\Alexander Julianto\\Desktop\\CPEN422\\fileModified.java");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);

			bw.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	  // -----------------------------------
		
		
		
  	  //--------------JavaParser Comments Attribution (DEMO)
    	
    	try {
    	File sampleJavaFileWithComment = new File("C:\\Users\\Alexander Julianto\\Desktop\\CPEN422\\SampleDemoComment.java");
    	
    	CompilationUnit compUnit4 = JavaParser.parse(sampleJavaFileWithComment);
    	ExpressionStmt expressionStmt = compUnit4.findFirst(ExpressionStmt.class).get();
    	//System.out.println("Comment on the expression statement: " + expressionStmt.getComment().get().getContent());
    	
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
