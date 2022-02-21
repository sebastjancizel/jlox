package lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Lox{
	public static void main(String[] args) throws IOException {
		if (args.length > 1){
			System.out.println("Usage: lox [script]");
			System.exit(64);
		} else if (args.length == 1){
			runFile(args[0]);
		} else {
			runPrompt();
		}
	}
	static boolean hadError = false;
}

public static void runFile(String path) throws IOException {
	byte[] bytes = Files.readAllBytes(Path.of(path));
	run(new String(bytes, Charset.defaultCharset()));
	if (hadError) System.exit(65);
}

public static void runPrompt() throws IOException{
	InputStreamReader input = new InputStreamReader(System.in);
	BufferedReader reader = new BufferedReader(input);
	for (;;){
		System.out.println("> ");
		String line = reader.readLine();
		if (line == null) break;
		run(line);
		hadError = false;
	}
}

public static void run(String source){
	Scanner scanner = new Scanner(source);
	List<Token> tokens = scanner.scanTokens();

	for (Token token: tokens){
		System.out.println(token);
	}
}

static void error(int line, String message){
	report(line, "", message);
}

private static void report(int line, String where, String message){
	System.err.println( "[line " + line + "] Error" + where + ": " + message);
	hadError = True;
}