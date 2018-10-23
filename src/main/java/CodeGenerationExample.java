
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;


public class CodeGenerationExample {
	public static void main(String[] args) {
		JSONObject personObj = null;
		
		try {
			personObj = new JSONObject("{ \"name\":\"Bill\",\"occupation\":\"doctor\", \"country\":\"Canada\"}");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		CompilationUnit compilationUnit = new CompilationUnit(); 
		
		//Add the package
		compilationUnit.setPackageDeclaration("com.github.javaparser.GeneratedCode");
	
		ClassOrInterfaceDeclaration myClass = compilationUnit
		        .addClass("MyClass")
		        .setPublic(true);
		
		generateAllFields(personObj.keys(), myClass);
		generateAllMethods(personObj.keys(), myClass);
		
		System.out.println(myClass.toString());
	}
	
	public static void generateAllFields(Iterator<String> parameters, ClassOrInterfaceDeclaration myClass) {
		while(parameters.hasNext()) {
			myClass.addField(String.class, parameters.next(), Modifier.PRIVATE);
		}
	}
	
	public static void generateAllMethods(Iterator<String> parameters, ClassOrInterfaceDeclaration myClass) {
		while(parameters.hasNext()) {
			generateCode(parameters.next(), myClass);
		}
	}
	
	public static void generateCode(String parameter, ClassOrInterfaceDeclaration myClass) {
		String camelCaseName = parameter.toUpperCase().substring(0, 1) + parameter.substring(1, parameter.length());
	
		myClass.addMethod("get" + camelCaseName, Modifier.PUBLIC)
				.setBody(new BlockStmt().addStatement(
						new ReturnStmt(new NameExpr(parameter))));
		
		
		
		myClass.addMethod("set" + camelCaseName, Modifier.PUBLIC)
				.setReceiverParameter(new ReceiverParameter().setType(String.class).setName(parameter))
				.createBody().addStatement("this." + parameter + " = " + parameter + ";");

	}
}
