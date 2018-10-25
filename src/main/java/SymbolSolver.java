import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class SymbolSolver {

	public static void main(String[] args) {
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
	       compUnit2a.findAll(MethodCallExpr.class).forEach(m -> {

	           System.out.println(m.resolveInvokedMethod().getQualifiedSignature());
	       });
	       
			
	     //-----------------------------------

	}

}
