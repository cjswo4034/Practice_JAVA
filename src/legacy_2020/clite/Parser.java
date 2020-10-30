package clite;

public class Parser {
	// Recursive descent parser that inputs a C++Lite program and
	// generates its abstract syntax. Each method corresponds to
	// a concrete syntax grammar rule, which appears as a comment
	// at the beginning of the method.

	Token token; // current token from the input stream
	Lexer lexer;

	public Parser(Lexer ts) { // Open the C++Lite source program
		lexer = ts; // as a token stream, and
		token = lexer.next(); // retrieve its first Token
	}

	private String match(TokenType t) {
		String value = token.value();
		if (token.type().equals(t))
			token = lexer.next();
		else
			error(t);
		return value;
	}

	private void error(TokenType tok) {
		System.err.println("Syntax error: expecting: " + tok + "; saw: " + token);
		System.exit(1);
	}

	private void error(String tok) {
		System.err.println("Syntax error: expecting: " + tok + "; saw: " + token);
		System.exit(1);
	}

	public Program program() {
		// Program --> int main ( ) '{' Declarations Statements '}'
		TokenType[] header = { TokenType.Int, TokenType.Main, TokenType.LeftParen, TokenType.RightParen };
		for (int i = 0; i < header.length; i++) // bypass "int main ( )"
			match(header[i]);
		match(TokenType.LeftBrace);

		// student exercise
		Declarations declarations = declarations();
		Block block = statements();
		//

		match(TokenType.RightBrace);
		return new Program(declarations, block); // student exercise
	}

	private Declarations declarations() {
		// Declarations --> { Declaration }
		Declarations declarations = new Declarations();
		while (isType()) {
			declaration(declarations);
		}
		return declarations; // student exercise
	}

	private void declaration(Declarations ds) {
		// Declaration --> Type Identifier { , Identifier } ;
		// student exercise

		Type type = type();

		while (!token.type().equals(TokenType.Semicolon)) {
			Variable var = new Variable(match(TokenType.Identifier));
			ds.add(new Declaration(var, type));

			if(token.type().equals(TokenType.Comma))
				match(TokenType.Comma);
		}

		match(TokenType.Semicolon);
	}

	private Type type() {
		// Type --> int | bool | float | char
		Type t = null;
		switch (token.type()) {
			case Int: t = Type.INT; break;
			case Bool: t = Type.BOOL; break;
			case Char: t = Type.CHAR; break;
			case Float: t = Type.FLOAT; break;
		}

		token = lexer.next();
		// student exercise
		return t;
	}

	private Statement statement() {
		// Statement --> ; | Block | Assignment | IfStatement | WhileStatement
		Statement s = new Skip();
		if(token.type().equals(TokenType.Semicolon)) {
			token = lexer.next();
		}

		switch (token.type()) {
			case LeftBrace: token = lexer.next(); s = statements(); match(TokenType.RightBrace); break;
			case Identifier: s = assignment(); break;
			case If: s = ifStatement(); break;
			case While: s = whileStatement(); break;
		}
		// student exercise
		return s;
	}

	private Block statements() {
		// Block --> '{' Statements '}'
		Block b = new Block();
		while(!token.type().equals(TokenType.RightBrace)) {
			b.members.add(statement());
		}

		// student exercise
		return b;
	}

	private Assignment assignment() {
		// Assignment --> Identifier = Expression ;
		Variable variable = new Variable(match(TokenType.Identifier));
		match(TokenType.Assign);
		Expression expression = expression();

		match(TokenType.Semicolon);
		return new Assignment(variable, expression); // student exercise
	}

	private Conditional ifStatement() {
		// IfStatement --> if ( Expression ) Statement [ else Statement ]
		Conditional condition = null;

		match(TokenType.If);

		match(TokenType.LeftParen);
		Expression expression = expression();
		match(TokenType.RightParen);

		Statement tp = statement();

		switch (token.type()) {
			case Else:
				token = lexer.next();
				condition = new Conditional(expression, tp, statement());
				break;
			default:
				condition = new Conditional(expression, tp);
				break;
		}

		return condition; // student exercise
	}

	private Loop whileStatement() {
		// WhileStatement --> while ( Expression ) Statement
		match(TokenType.While);

		match(TokenType.LeftParen);
		Expression expression = expression();
		match(TokenType.RightParen);

		Statement statement = statement();

		return new Loop(expression, statement); // student exercise
	}

	private Expression expression() {
		// Expression --> Conjunction { || Conjunction }
		Expression conjunction = conjunction();
		while(token.type().equals(TokenType.Or)) {
			Operator op = new Operator(match(token.type()));
			Expression conjunction2 = conjunction();
			conjunction = new Binary(op, conjunction, conjunction2);
		}

		return conjunction;
	}

	private Expression conjunction() {
		// Conjunction --> Equality { && Equality }
		Expression equality = equality();
		while(token.type().equals(TokenType.And)) {
			Operator op = new Operator(match(token.type()));
			Expression equality2 = equality();
			equality = new Binary(op, equality, equality2);
		}

		return equality; // student exercise
	}

	private Expression equality() {
		// Equality --> Relation [ EquOp Relation ]
		Expression relation = relation();
		if(isRelationalOp()) {
			Operator op = new Operator(match(token.type()));
			Expression relation2 = relation();
			relation = new Binary(op, relation, relation2);
		}

		return relation; // student exercise
	}

	private Expression relation() {
		// Relation --> Addition [RelOp Addition]
		Expression addition = addition();
		if(isRelationalOp()) {
			Operator op = new Operator(match(token.type()));
			Expression addition2 = addition();
			addition = new Binary(op, addition, addition2);
		}

		return addition; // student exercise
	}

	private Expression addition() {
		// Addition --> Term { AddOp Term }
		Expression e = term();
		while (isAddOp()) {
			Operator op = new Operator(match(token.type()));
			Expression term2 = term();
			e = new Binary(op, e, term2);
		}
		return e;
	}

	private Expression term() {
		// Term --> Factor { MultiplyOp Factor }
		Expression e = factor();
		while (isMultiplyOp()) {
			Operator op = new Operator(match(token.type()));
			Expression term2 = factor();
			e = new Binary(op, e, term2);
		}
		return e;
	}

	private Expression factor() {
		// Factor --> [ UnaryOp ] Primary
		if (isUnaryOp()) {
			Operator op = new Operator(match(token.type()));
			Expression term = primary();
			return new Unary(op, term);
		} else
			return primary();
	}

	private Expression primary() {
		// Primary --> Identifier | Literal | ( Expression )
		// | Type ( Expression )
		Expression e = null;
		if (token.type().equals(TokenType.Identifier)) {
			e = new Variable(match(TokenType.Identifier));
		} else if (isLiteral()) {
			e = literal();
		} else if (token.type().equals(TokenType.LeftParen)) {
			token = lexer.next();
			e = expression();
			match(TokenType.RightParen);
		} else if (isType()) {
			Operator op = new Operator(match(token.type()));
			match(TokenType.LeftParen);
			Expression term = expression();
			match(TokenType.RightParen);
			e = new Unary(op, term);
		} else
			error("Identifier | Literal | ( | Type");
		return e;
	}

	private Value literal() {
		Value val = null;

		switch(token.type()) {
			case IntLiteral: return new IntValue(Integer.parseInt(match(TokenType.IntLiteral)));
			case CharLiteral: return new CharValue(match(TokenType.CharLiteral).charAt(0));
			case FloatLiteral: return new FloatValue(Float.parseFloat(match(TokenType.FloatLiteral)));
			case True: match(TokenType.True); return new BoolValue(true);
			case False: match(TokenType.False); return new BoolValue(false);
		}

		return val; // student exercise
	}

	private boolean isAddOp() {
		return token.type().equals(TokenType.Plus) || token.type().equals(TokenType.Minus);
	}

	private boolean isMultiplyOp() {
		return token.type().equals(TokenType.Multiply) || token.type().equals(TokenType.Divide);
	}

	private boolean isUnaryOp() {
		return token.type().equals(TokenType.Not) || token.type().equals(TokenType.Minus);
	}

	private boolean isEqualityOp() {
		return token.type().equals(TokenType.Equals) || token.type().equals(TokenType.NotEqual);
	}

	private boolean isRelationalOp() {
		return token.type().equals(TokenType.Less) || token.type().equals(TokenType.LessEqual)
				|| token.type().equals(TokenType.Greater) || token.type().equals(TokenType.GreaterEqual);
	}

	private boolean isType() {
		return token.type().equals(TokenType.Int) || token.type().equals(TokenType.Bool)
				|| token.type().equals(TokenType.Float) || token.type().equals(TokenType.Char);
	}

	private boolean isLiteral() {
		return token.type().equals(TokenType.IntLiteral) || isBooleanLiteral()
				|| token.type().equals(TokenType.FloatLiteral) || token.type().equals(TokenType.CharLiteral);
	}

	private boolean isBooleanLiteral() {
		return token.type().equals(TokenType.True) || token.type().equals(TokenType.False);
	}

	public static void main(String args[]) {
		Parser parser = new Parser(new Lexer("Homework1"));
		Program prog = parser.program();
		prog.display(); // display abstract syntax tree
	} // main

} // Parser