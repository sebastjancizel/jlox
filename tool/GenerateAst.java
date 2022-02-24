package tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {
	public static void main(String[] args) throws IOException{
		if (args.length != 1){
			System.err.println("Usage: genrate_ast <output directory>");
			System.exit(64);
		}
		String outputDir = args[0];

		defineAst(outputDir, "Expr", Arrays.asList(
			"Binary  	: Expr left, Token operator, Expr right",
			"Grouping	: Expr expression",
			"Literal	: Object value",
			"Unary		: Token operator, Expr right"
		));
	}

	private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException{
		String path = outputDir + "/" + baseName + ".java";
		PrintWriter writer = new PrintWriter(path, "UTF-8");

		writer.println("package lox;");
		writer.println();
		writer.println("import java.util.List;");
		writer.println();
		// The base class definition
		writer.println("public abstract class " + baseName + " {");

		for (String type : types){
			String className = type.split(":")[0].trim();
			String fields = type.split(":")[1].trim();

			defineType(writer, baseName, className, fields);
		}

		writer.println("}");
		writer.close();
	}

	private static void defineType(PrintWriter writer, String baseName, String className, String fieldList){
		// Derived class for each type
		writer.println(" static class " + className + " extends " + baseName + " {");

		// constructor
		writer.println("	" + className + "(" + fieldList + ") {");
		String prefix = "		";

		String[] fields = fieldList.split(", ");
		for(String field: fields){
			String name = field.split(" ")[1];
			writer.println(prefix + "this." + name + " = " + name + ";");
		}

		writer.println("	}");

		// fields
		writer.println();
		for (String field : fields){
			writer.println("	 final " + field + ";");
		}

		writer.println("}");

	}
}