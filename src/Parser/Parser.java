package Parser;

import Lexer.Token;
import Lexer.TokenType;

import java.util.Vector;

public class Parser {
    private final Vector<Token> _tokens;
    private int _curr;

    private InstructionBlockNode instructionTree;
    //private InstructionVariableNode variableTree;
    //private ProgramNode programTree;

    public Parser(Vector<Token> tokens){
        _tokens = tokens;
        _tokens.add(new Token(TokenType.EOF,"",-1,-1));
    }
    public InstructionBlockNode getInstrucciones(){
        return instructionTree;
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

    private Token Match(TokenType tokenType) throws SyntaxException {
        if(Current().getType() == tokenType){
            Token tk = Current();
            NextToken();
          return tk;
        }
        throw new SyntaxException(String.format("La palabra '%s' de tipo %s no coincide con el tipo que se esperaba %s",Current().toString(),
                                                                                                                        Current().getType().toString(),
                                                                                                                        tokenType.toString()),
                                                                                                                        Current());
    }

    public void Parse() throws SyntaxException {

        while (Current().getType()!=TokenType.EOF){
            SyntaxTree tree =ParseBlock();
            if(tree.getType() == SyntaxTreeType.InstructionBlockNode){
                instructionTree = (InstructionBlockNode) tree;
            }
        }

    }

    private SyntaxTree ParseBlock() throws SyntaxException {
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

    private InstructionBlockNode ParseInstructionsBlock() throws SyntaxException {
        Match(TokenType.instruccionesRW);
        Match(TokenType.LeftBracket);
        Vector<InstructionNode> ins = new Vector<>();
        while(Current().getType()!=TokenType.RightBracket){
            ins.add(ParseInstruction());
        }
        Match(TokenType.RightBracket);
        return new InstructionBlockNode(ins);
    }

    private InstructionNode ParseInstruction() throws SyntaxException {

        InstructionIdentifierNode identifier = ParseInstructionIdentifier();
        InstructionArgumentNode argument = ParseInstructionArgument();
        Vector<InstructionLineNode> lines = new Vector<>();
        Match(TokenType.LeftBracket);
        while (Current().getType()!= TokenType.RightBracket){
            lines.add(ParseInstructionLine());
        }
        Match(TokenType.RightBracket);



        return new InstructionNode(identifier,argument, lines);
    }



    private InstructionArgumentNode ParseInstructionArgument() throws SyntaxException {
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

    private InstructionIdentifierNode ParseInstructionIdentifier() throws SyntaxException {
        Token tk = Current();
        Match(TokenType.Word);
        return new InstructionIdentifierNode(tk);
    }

    private InstructionLineNode ParseInstructionLine() throws SyntaxException {
        InstructionLineControlNode control= ParseInstructionControl();
        Vector<MicroInstructionNode> microInstr = new Vector<>();
        while (Current().getType()!=TokenType.SemiColon ){
            if(Current().getType() == TokenType.Comma){
                NextToken();
            } //TODO solucionar esto porque está bastante mierdoso
                microInstr.add(ParseMicroInstruction());

        }
        Match(TokenType.SemiColon);
        return new InstructionLineNode(control,microInstr);
    }

    private MicroInstructionNode ParseMicroInstruction() {
        //TODO terminar
        MicroInstruction mi = null;
        if(Current().getType() == TokenType.Word){
            try {
                 mi = MicroInstruction.valueOf(Current().getValue());
            }catch (IllegalArgumentException e){
                //TODO error
            }
            return new MicroInstructionNode(mi);
        }
        else{
            //TODO error
        }
        return null;
    }

    private InstructionLineControlNode ParseInstructionControl() throws SyntaxException {

        ControlConditionsNode conditions = ParseControlConditions();
        Match(TokenType.Arrow);
        ControlResultsNode results = ParseControlResult();
        return new InstructionLineControlNode(conditions,results);
    }



    private ControlConditionsNode ParseControlConditions() throws SyntaxException {
        Match(TokenType.LeftParenthesis);
        Vector<ControlConditionNode> conditions = new Vector<>();
        while (Current().getType()!= TokenType.RightParenthesis && Current().getType()!=TokenType.Comma){
            if(Current().getType()==TokenType.Comma){
                NextToken();
            }
            else if(Current().getType() == TokenType.Word || Current().getType() == TokenType.ExclamationMark){
                conditions.add(ParseControlCondition());
            }
            else{
                //TODO error
            }
        }
        Match(TokenType.RightParenthesis);
        return new ControlConditionsNode(conditions);
    }

    private ControlConditionNode ParseControlCondition() throws SyntaxException {
        boolean activated = true;
        if(Current().getType() == TokenType.ExclamationMark){
            activated=false;
            NextToken();
        }
        Token tk = Match(TokenType.Word);
        BitEstatus bit = null;
        try {
             bit = BitEstatus.valueOf(tk.getValue());
        }catch (IllegalArgumentException e){
            //TODO error is not a valid condition
        }
        return new ControlConditionNode(activated,bit);
    }

    private ControlResultsNode ParseControlResult() throws SyntaxException {
        Match(TokenType.LeftParenthesis);
        Token tk = Match(TokenType.Word);
        ControlResult res = null;
        try {
            res = ControlResult.valueOf(tk.getValue());
        }catch (IllegalArgumentException e){
            //TODO error is not valid result
        }
        Match(TokenType.RightParenthesis);
        return new ControlResultsNode(res);
    }
}
