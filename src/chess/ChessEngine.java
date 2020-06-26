package chess;

import chess.protocolBinding.StreamBindingReceiver;

import java.io.IOException;
import java.util.Random;

public class ChessEngine implements ChessReceiver /*ChessSender*/, ChessUsage {

    public static final int UNDEFINE_DICE = -1;
     int sentDice = UNDEFINE_DICE;
    private ChessStatus status;
    private int receivedRandom;
    private ChessStatus player;
    private ChessStatus otherPlayer;
    private final ChessSender sender;
    StreamBindingReceiver reader;
    ChessBoard board = new ChessBoard();
    private static final int DIM = 64;
    BoardField[] boardField = new BoardField[DIM];
    //boolean white = false;
    boolean end;


    public ChessEngine(ChessSender sender) {
        this.status = ChessStatus.START;
        this.sender = sender;
        start();
    }

    void start() {
        this.board.start();
    }

    void get() {
        this.board.getBoardField();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      remote engine support                                        //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void diceReceiver(int number) throws StatusException, IOException {
        if (this.status != ChessStatus.START
            && this.status != ChessStatus.SENT_DICE) {
            throw new StatusException();
        }

        this.receivedRandom = number;

        if (this.status == ChessStatus.SENT_DICE) {
            whoChoose();
        } else {
            this.status = ChessStatus.RECEICE_DICE;
        }

    }

    @Override
    public void chooseColorReceiver(boolean white) throws StatusException {
        if (this.status != ChessStatus.WAIT_FOR_COLOR){
            throw new StatusException();
        }

        // after a color has been selected
        if (white == true) {
            this.status = ChessStatus.PASSIVE;
            this.player = ChessStatus.BLACK;
            this.otherPlayer = ChessStatus.WHITE;

        } else {
            this.status = ChessStatus.ACTIVE;
            this.player = ChessStatus.WHITE;
            this.otherPlayer = ChessStatus.BLACK;
        }
    }

    @Override
    public void moveReceiver(int from, int to) throws StatusException {
        if (this.status != ChessStatus.PASSIVE){
            throw new StatusException();
        }

        this.board.setBoard(from, to);
        this.status = ChessStatus.ACTIVE;
    }

    @Override
    public void movePawnRuleReceiver(int from, int figureType) throws StatusException {
        if (this.status != ChessStatus.PASSIVE){
            throw new StatusException();
        }
        this.board.setBoardPawnRule(from, figureType);
        this.status = ChessStatus.ACTIVE;
    }

    @Override
    public void rochadeReceiver(int from) throws StatusException {
        if (this.status != ChessStatus.PASSIVE){
            throw new StatusException();
        }
        this.board.setRochade(from);
        this.status = ChessStatus.ACTIVE;
    }

    @Override
    public void endGameReceiver(int reason) throws StatusException {
        if (this.status != ChessStatus.PASSIVE){
            throw new StatusException();
        }

        this.status = ChessStatus.ANSWERING;

    }

    @Override
    public void proposalEndReceiver(int reason) throws StatusException {
        if (this.status != ChessStatus.PASSIVE){
            throw new StatusException();
        }

        this.status = ChessStatus.ANSWERING;

    }

    @Override
    public void proposalAnswerReceiver(boolean accept) throws StatusException {
        if (this.status != ChessStatus.ANSWERING){
            throw new StatusException();
        }

        if (accept == true) {
            this.status = ChessStatus.END;
            end = false;
        } else {
            this.status = ChessStatus.ACTIVE;
        }
    }


    public void whoChoose() throws IOException, StatusException {
        if (this.receivedRandom == sentDice){
            this.status = ChessStatus.START;
        }
        else if (this.receivedRandom < sentDice) {
            this.status = ChessStatus.WAIT_FOR_COLOR;
            //this.chooseColorReceiver(color);
        }
        else if (this.receivedRandom > sentDice) {
            this.status = ChessStatus.CHOOSE_COLOR;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      user interface support                                       //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void doDice() throws StatusException, IOException {
        if (this.status != ChessStatus.START
                && this.status != ChessStatus.RECEICE_DICE) {
            throw new StatusException();
        }

        Random r = new Random();
        this.sentDice = r.nextInt();

        this.sender.sendDice(this.sentDice);

        this.status = ChessStatus.SENT_DICE;

        /*
        if (this.status == ChessStatus.RECEICE_DICE){
            this.whoChoose();
        } else {
            this.status = ChessStatus.SENT_DICE;
        }
         */

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void set(int from, int to) throws ChessException, StatusException {
        this.status = ChessStatus.ACTIVE;

        if (this.status != ChessStatus.ACTIVE){
            throw new StatusException();
        }

        this.board.setBoard(from, to);

        this.status = ChessStatus.PASSIVE;
    }

    @Override
    public ChessStatus getStatus() {
        return this.status;
    }

    @Override
    public void chooseColor(boolean white) {

        // after a color has been selected
        if (white == true) {
            this.status = ChessStatus.ACTIVE;
            this.player = ChessStatus.WHITE;
            this.otherPlayer = ChessStatus.BLACK;
        } else {
            this.status = ChessStatus.PASSIVE;
            this.player = ChessStatus.BLACK;
            this.otherPlayer = ChessStatus.WHITE;
        }
    }

    @Override
    public boolean checkEnd() {
        return end;
    }


    public void printBoard(){
        BoardField[] field = this.boardField;
        int dimension = 0;
        System.out.println("____________________");
        for (int i = 0; i < 8; i++) {
            get(field, dimension);
            System.out.println(" ");
            dimension = dimension + 8;
        }
        System.out.println("____________________");
    }

    public static void get(BoardField[] field, int dimension) {

        int dimensionMax = dimension + 8;

        for (int j = dimension; j < dimensionMax; j++) {
            switch(field[j].getStatus()){
                case PAWN_WHITE:
                    System.out.print(" ♙ ");
                    break;
                case KING_WHITE:
                    System.out.print(" ♔ ");
                    break;
                case ROOK_WHITE:
                    System.out.print(" ♖ ");
                    break;
                case KNIGHT_WHITE:
                    System.out.print(" ♘ ");
                    break;
                case QUEEN_WHITE:
                    System.out.print(" ♕ ");
                    break;
                case BISHOP_WHITE:
                    System.out.print(" ♗ ");
                    break;
                case PAWN_BLACK:
                    System.out.print(" ♟ ");
                    break;
                case KING_BLACK:
                    System.out.print(" ♚ ");
                    break;
                case ROOK_BLACK:
                    System.out.print(" ♜ ");
                    break;
                case QUEEN_BLACK:
                    System.out.print(" ♛ ");
                    break;
                case BISHOP_BLACK:
                    System.out.print(" ♝ ");
                    break;
                case KNIGHT_BLACK:
                    System.out.print(" ♞ ");
                    break;
                case EMPTY:
                    System.out.print(" ── ");
                    //System.out.print("  " + j + "  ");
                    break;
            }
        }
    }
}
