package Parser;

import Lexer.Token;
import Lexer.TokenType;

import java.util.Vector;

public class Parser {
    private final Vector<Token> _tokens;
    private int _curr;

    IntructionManager instruction;
    ControlLogicManager control;


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

        while (Current().getType()!=TokenType.EOF) {
            ParseBlock();
        }
    }

    private  void ParseBlock() throws SyntaxException {
        if(Current().getType() == TokenType.instruccionesRW){
            instruction = ParseInstructionsBlock();
        } else if (Current().getType() == TokenType.variablesRW) {
            //return ParseVariablesBlock();
        }
        else if(Current().getType() == TokenType.programaRW){
            //return ParseProgramBlock();
        }
        //TODO error?



    }

    private IntructionManager ParseInstructionsBlock() throws SyntaxException {
        IntructionManager ins = new IntructionManager();
        Match(TokenType.instruccionesRW);
        Match(TokenType.LeftBracket);

        while(Current().getType()!=TokenType.RightBracket){
            ins.add(ParseInstruction());
        }
        Match(TokenType.RightBracket);
        return ins;
    }

    private Instruction ParseInstruction() throws SyntaxException {

        String identifier = Match(TokenType.Word).getValue();
        //Args-------------
        Match(TokenType.LeftParenthesis);
        String arg = null;
        if(Current().getType() == TokenType.dirRW || Current().getType() == TokenType.valueRW){
            arg =Current().getValue();
            NextToken();
        }
        else{
            //TODO error
        }
        Match(TokenType.RightParenthesis);
        Instruction ins = new Instruction(identifier,arg);
        //--------------------
        Match(TokenType.LeftBracket);
        while (Current().getType()!= TokenType.RightBracket){
            ins.Add(ParseStep());
        }
        Match(TokenType.RightBracket);



        return ins;
    }


    private Step ParseStep() throws SyntaxException {

        ControlLogic control= ParseControlLogic();

        Step step = new Step(control);
        while (Current().getType()!=TokenType.SemiColon ){
            if(Current().getType() == TokenType.Comma){
                NextToken();
            } //TODO solucionar esto porque está bastante mierdoso
                step.Add(ParseMicroInstruction());
        }
        Match(TokenType.SemiColon);
        return step;
    }

    private MicroInstruction ParseMicroInstruction() {

        MicroInstruction mi = null;
        if(Current().getType() == TokenType.Word){
            try {
                 mi = MicroInstruction.valueOf(Current().getValue());
            }catch (IllegalArgumentException e){
                //TODO error
            }
            return mi;
        }
        else{
            //TODO error
        }
        return null;
    }

    private ControlLogic ParseControlLogic() throws SyntaxException {

        ControlLogic control = new ControlLogic();
        while (Current().getType()!= TokenType.RightParenthesis){

            boolean activated= true;
            SicomeBit bit = null;
            switch (Current().getType()){
                case Comma : NextToken(); break;
                case ExclamationMark:
                    activated = false;
                    NextToken();

                case Word:
                    try {
                        bit =SicomeBit.valueOf(Current().getValue());
                    }catch (IllegalArgumentException e){
                        //TODO error
                    }

                    NextToken();
                    break;
                default:
                    //TODO error
            }
            control.addCondition(new BitStatus(bit,activated));

        }
        Match(TokenType.RightParenthesis);


        Match(TokenType.Arrow);

        Match(TokenType.LeftParenthesis);
        Token tk = Match(TokenType.Word);

        try {
            control.setResult( ControlResult.valueOf(tk.getValue()) );
        }catch (IllegalArgumentException e){
            //TODO error is not valid result
        }
        Match(TokenType.RightParenthesis);
        return control;
    }







}
