import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

public class ModifyingCode {
	
	public static void main(String[] args) {
		
		 // --------------JavaParser Modifying Code (DEMO)

		try {
		
		String fileLocation = "C:\\Users\\Alexander Julianto\\Desktop\\CPEN422\\group4\\src\\main\\java\\org\\jpacman\\framework\\model\\Game.java";
		File jpacmanFile = new File(fileLocation);
		CompilationUnit compUnit3 = JavaParser.parse(jpacmanFile);
		
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
		
	}

}
