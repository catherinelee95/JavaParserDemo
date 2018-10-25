import java.io.File;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

public class CodeAnalysis {

	public static void main(String[] args) {
		  // --------------JavaParser Code Analysis (DEMO)

				try {

					String fileLocation = "C:\\Users\\Alexander Julianto\\Desktop\\CPEN422\\group4\\src\\main\\java\\org\\jpacman\\framework\\model\\Game.java";
					File jpacmanFile = new File(fileLocation);
					CompilationUnit compUnit3 = JavaParser.parse(jpacmanFile);

					Object[] methods = compUnit3.findAll(MethodDeclaration.class)
							.stream()
							.filter(m -> m.getParameters().size() > 1)
							.toArray();
					
					System.out.println("N of methods with more than 1 params: " + methods.length);

					System.out.println("My methods are: ");
					for (int i = 0; i < methods.length; i++) {
						System.out.println(((MethodDeclaration) methods[i]).getNameAsString());
					}
				}
					
				catch(Exception e) {
					e.printStackTrace();
				}

		     // -----------------------------------
	}

}
