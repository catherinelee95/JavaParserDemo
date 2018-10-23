import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class Parser {
	public static void main(String[] args) {
		System.out.println("Initialization");
		CompilationUnit compilationUnit = JavaParser.parse("");
	}
}
