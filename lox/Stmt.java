package lox;

import java.util.List;

public abstract class Stmt {
   interface Visitor<R> {
	  R visitExpressionStmt(Expression stmt);
	  R visitBlockStmt(Block stmt);
	  R visitVarStmt(Var stmt);
	  R visitIfStmt(If stmt);
	  R visitPrintStmt(Print stmt);
    }
 static class Expression extends Stmt {
	Expression(Expr expression) {
		this.expression = expression;
	}

	@Override
    <R> R accept(Visitor<R> visitor) {
 		return visitor.visitExpressionStmt(this);
	}

	 final Expr expression;
}
 static class Block extends Stmt {
	Block(List<Stmt> statements) {
		this.statements = statements;
	}

	@Override
    <R> R accept(Visitor<R> visitor) {
 		return visitor.visitBlockStmt(this);
	}

	 final List<Stmt> statements;
}
 static class Var extends Stmt {
	Var(Token name, Expr initializer) {
		this.name = name;
		this.initializer = initializer;
	}

	@Override
    <R> R accept(Visitor<R> visitor) {
 		return visitor.visitVarStmt(this);
	}

	 final Token name;
	 final Expr initializer;
}
 static class If extends Stmt {
	If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
		this.condition = condition;
		this.thenBranch = thenBranch;
		this.elseBranch = elseBranch;
	}

	@Override
    <R> R accept(Visitor<R> visitor) {
 		return visitor.visitIfStmt(this);
	}

	 final Expr condition;
	 final Stmt thenBranch;
	 final Stmt elseBranch;
}
 static class Print extends Stmt {
	Print(Expr expression) {
		this.expression = expression;
	}

	@Override
    <R> R accept(Visitor<R> visitor) {
 		return visitor.visitPrintStmt(this);
	}

	 final Expr expression;
}

	abstract <R> R accept(Visitor<R> visitor);
}
