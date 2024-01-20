package Parser;

import Lexer.Token;
import Lexer.TokenType;

import java.util.Vector;

public class Parser {
    private Vector<Token> _tokens;
    private int _curr;

    private InstructionBlockNode instructionTree;

    //private InstructionVariableNode variableTree;
    //private ProgramNode programTree;
    public Parser(Vector<Token> tokens){
        _tokens = tokens;
        _tokens.add(new Token(TokenType.EOF,"",-1,-1));
    }

    private Token Current(){
        return _tokens.get(_curr);
    }

    private Token NextToken(){
        if(_curr+1>=_tokens.size()){
            return new Token(TokenType.EOF,"",-1,-1);
        }
        _curr++;
        return _tokens.get(_curr);

    }

    private Token Match(TokenType tokenType){
        if(Current().getType() == tokenType){
          return NextToken();//Cambiar alomejor como funciona esto
        }
        return new Token(tokenType,"",-1,-1);
    }

    public void Parse(){

        while (Current().getType()!=TokenType.EOF){
            SyntaxTree tree =ParseBlock();
            if(tree.getType() == SyntaxTreeType.InstructionBlockNode){
                instructionTree = (InstructionBlockNode) tree;
            }
        }

    }
    public InstructionBlockNode getInstrucciones(){
        return instructionTree;
    }
    private SyntaxTree ParseBlock(){
        if(Current().getType() == TokenType.instruccionesRW){
            return ParseInstructionsBlock();
        } else if (Current().getType() == TokenType.variablesRW) {
            //return ParseVariablesBlock();
        }
        else if(Current().getType() == TokenType.programaRW){
            //return ParseProgramBlock();
        }

        //TODO error?
        return null;


    }

    private InstructionBlockNode ParseInstructionsBlock() {
        Match(TokenType.instruccionesRW);
        Match(TokenType.LeftBracket);
        Vector<InstructionNode> ins = new Vector<>();
        while(Current().getType()!=TokenType.RightBracket){
            ins.add(ParseInstruction());
        }
        Match(TokenType.RightBracket);
        return new InstructionBlockNode(ins);
    }

    private InstructionNode ParseInstruction() {

        InstructionIdentifierNode identifier = ParseInstructionIdentifier();
        InstructionArgumentNode argument = ParseInstructionArgument();
        Vector<InstructionLineNode> lines = new Vector<>();
        Match(TokenType.LeftBracket);
        while (Current().getType()!= TokenType.RightBracket){
            //lines.add(ParseInstructionLine());
        }
        Match(TokenType.RightBracket);



        return new InstructionNode(identifier,argument, lines);
    }

    private InstructionArgumentNode ParseInstructionArgument() {
        Match(TokenType.LeftParenthesis);
        Token tk = null;
        if(Current().getType() == TokenType.dirRW || Current().getType() == TokenType.valueRW){
            tk = Current();
            NextToken();
        }
        else{
            //TODO error
        }
        Match(TokenType.RightParenthesis);
        return new InstructionArgumentNode(tk);
    }

    private InstructionIdentifierNode ParseInstructionIdentifier() {
        Token tk = Current();
        Match(TokenType.Word);
        return new InstructionIdentifierNode(tk);
    }


}
