package chess;

public class ChessBoard {

    private static final int DIM = 64;
    BoardField[] boardField = new BoardField[DIM];

    //ChessBoard figure;
    private boolean rookUnmoved_0 = true;
    private boolean rookUnmoved_7 = true;
    private boolean rookUnmoved_56 = true;
    private boolean rookUnmoved_63 = true;
    private boolean kingUnmoved_4 = true;
    private boolean kingUnmoved_60 = true;
    private boolean rochadeWhite = true;
    private boolean rochadeBlack = true;

    BoardField[] getBoardField() {

        return boardField;
        /*
        for (int i = 0; i < board.length; i++) {
            this.board[i] = BoardField;

        }

         */
    }

    void start() {

        /*
        König - King
        Dame - Queen
        Turm - Rook
        Springer - Knight
        Läufer - Bishop
        Bauer - Pawn
         */

        int i;
        //for (i = 0; i < 8; i++) {
            this.boardField[0] = BoardField.ROOK_WHITE;
            this.boardField[1] = BoardField.KNIGHT_WHITE;
            this.boardField[2] = BoardField.BISHOP_WHITE;
            this.boardField[3] = BoardField.QUEEN_WHITE;
            this.boardField[4] = BoardField.KING_WHITE;
            this.boardField[5] = BoardField.BISHOP_WHITE;
            this.boardField[6] = BoardField.KNIGHT_WHITE;
            this.boardField[7] = BoardField.ROOK_WHITE;
        //}

        for (i = 8; i < 16; i++) {
            this.boardField[i] = BoardField.PAWN_WHITE;
        }

        for (i = 16; i < 48; i++) {
            this.boardField[i] = BoardField.EMPTY;
        }

        for (i = 48; i < 56; i++) {
            this.boardField[i] = BoardField.PAWN_BLACK;
        }

        //for (i = 56; i < 64; i++) {
            this.boardField[56] = BoardField.ROOK_BLACK;
            this.boardField[57] = BoardField.KNIGHT_BLACK;
            this.boardField[58] = BoardField.BISHOP_BLACK;
            this.boardField[59] = BoardField.QUEEN_BLACK;
            this.boardField[60] = BoardField.KING_BLACK;
            this.boardField[61] = BoardField.BISHOP_BLACK;
            this.boardField[62] = BoardField.KNIGHT_BLACK;
            this.boardField[63] = BoardField.ROOK_BLACK;
        //}
    }


    void setBoard(int from, int to) {

        BoardField figure = this.boardField[from];

        BoardField otherFigure = this.boardField[to];


        switch (figure) {

            case PAWN_WHITE:
                checkFieldBlack(otherFigure);
                this.boardField[to] = BoardField.PAWN_WHITE;
                break;
            case ROOK_WHITE:
                checkFieldBlack(otherFigure);
                this.boardField[to] = BoardField.ROOK_WHITE;
                if (figure == this.boardField[0])
                    rookUnmoved_0 = false;
                else if (figure == this.boardField[7])
                    rookUnmoved_7 = false;
                break;
            case KNIGHT_WHITE:
                checkFieldBlack(otherFigure);
                this.boardField[to] = BoardField.KNIGHT_WHITE;
                break;
            case BISHOP_WHITE:
                checkFieldBlack(otherFigure);
                this.boardField[to] = BoardField.BISHOP_WHITE;
                break;
            case QUEEN_WHITE:
                checkFieldBlack(otherFigure);
                this.boardField[to] = BoardField.QUEEN_WHITE;
                break;
            case KING_WHITE:
                checkFieldBlack(otherFigure);
                this.boardField[to] = BoardField.KING_WHITE;
                if (figure == this.boardField[4])
                    kingUnmoved_4 = false;
                break;

            case PAWN_BLACK:
                checkFieldWhite(otherFigure);
                this.boardField[to] = BoardField.PAWN_BLACK;
                break;
            case ROOK_BLACK:
                checkFieldWhite(otherFigure);
                this.boardField[to] = BoardField.ROOK_BLACK;
                if (figure == this.boardField[56])
                    rookUnmoved_56 = false;
                else if (figure == this.boardField[63])
                    rookUnmoved_63 = false;
                break;
            case KNIGHT_BLACK:
                checkFieldWhite(otherFigure);
                this.boardField[to] = BoardField.KNIGHT_BLACK;
                break;
            case BISHOP_BLACK:
                checkFieldWhite(otherFigure);
                this.boardField[to] = BoardField.BISHOP_BLACK;
                break;
            case QUEEN_BLACK:
                checkFieldWhite(otherFigure);
                this.boardField[to] = BoardField.QUEEN_BLACK;
                break;
            case KING_BLACK:
                checkFieldWhite(otherFigure);
                this.boardField[to] = BoardField.KING_BLACK;
                if (figure == this.boardField[60])
                    kingUnmoved_60 = false;
                break;

            default: System.err.println("unknown code");
        }
        this.boardField[from] = BoardField.EMPTY;

    }


    private void checkFieldWhite(BoardField otherFigure) {
        if (otherFigure != BoardField.EMPTY) {
            killFigureWhite(otherFigure);
        }
    }

    private void killFigureWhite(BoardField otherFigure) {

        switch (otherFigure) {
            case PAWN_WHITE:
                BoardField.PAWN_WHITE.kill(BoardField.PAWN_WHITE);
                break;

            case ROOK_WHITE:
                BoardField.ROOK_WHITE.kill(BoardField.ROOK_WHITE);
                break;

            case KNIGHT_WHITE:
                BoardField.KNIGHT_WHITE.kill(BoardField.KNIGHT_WHITE);
                break;

            case BISHOP_WHITE:
                BoardField.BISHOP_WHITE.kill(BoardField.BISHOP_WHITE);
                break;

            case QUEEN_WHITE:
                BoardField.QUEEN_WHITE.kill(BoardField.QUEEN_WHITE);
                break;

            case KING_WHITE:
                BoardField.KING_WHITE.kill(BoardField.KING_WHITE);
                break;
        }
    }


    private void checkFieldBlack(BoardField otherFigure) {
        if (otherFigure != BoardField.EMPTY) {
            killFigureBlack(otherFigure);
        }
    }

    private void killFigureBlack(BoardField otherFigure) {

        switch (otherFigure) {
            case PAWN_BLACK:
                BoardField.PAWN_BLACK.kill(BoardField.PAWN_BLACK);
                break;

            case ROOK_BLACK:
                BoardField.ROOK_BLACK.kill(BoardField.ROOK_BLACK);
                break;

            case KNIGHT_BLACK:
                BoardField.KNIGHT_BLACK.kill(BoardField.KNIGHT_BLACK);
                break;

            case BISHOP_BLACK:
                BoardField.BISHOP_BLACK.kill(BoardField.BISHOP_BLACK);
                break;

            case QUEEN_BLACK:
                BoardField.QUEEN_BLACK.kill(BoardField.QUEEN_BLACK);
                break;

            case KING_BLACK:
                BoardField.KING_BLACK.kill(BoardField.KING_BLACK);
                break;

        }
    }


    void setBoardPawnRule(int from, int figuretype) {
        BoardField figure = this.boardField[from];

        if (figure == BoardField.PAWN_WHITE
                && (figure == this.boardField[56]
                || figure == this.boardField[57]
                || figure == this.boardField[58]
                || figure == this.boardField[59]
                || figure == this.boardField[60]
                || figure == this.boardField[61]
                || figure == this.boardField[62]
                || figure == this.boardField[63])) {

            // Queen = 1; Rook = 2; Knight = 3; Bishop = 4;
            if (figuretype == 1)
                this.boardField[from] = BoardField.QUEEN_WHITE;
            else if (figuretype == 2)
                this.boardField[from] = BoardField.ROOK_WHITE;
            else if (figuretype == 3)
                this.boardField[from] = BoardField.KNIGHT_WHITE;
            else if (figuretype == 4)
                this.boardField[from] = BoardField.BISHOP_WHITE;
            else
                System.err.println("unkown command code: " + figuretype);
        }

        else if (figure == BoardField.PAWN_BLACK
                && (figure == this.boardField[0]
                || figure == this.boardField[1]
                || figure == this.boardField[2]
                || figure == this.boardField[3]
                || figure == this.boardField[4]
                || figure == this.boardField[5]
                || figure == this.boardField[6]
                || figure == this.boardField[7])) {

            // Queen = 1; Rook = 2; Knight = 3; Bishop = 4;
            if (figuretype == 1)
                this.boardField[from] = BoardField.QUEEN_BLACK;
            else if (figuretype == 2)
                this.boardField[from] = BoardField.ROOK_BLACK;
            else if (figuretype == 3)
                this.boardField[from] = BoardField.KNIGHT_BLACK;
            else if (figuretype == 4)
                this.boardField[from] = BoardField.BISHOP_BLACK;
            else
                System.err.println("unkown command code: " + figuretype);
        }

        else
            System.err.println("the pawn is in the wrong position or the figure was chosen incorrectly");

    }

    void setRochade(int from) {
        BoardField figure = this.boardField[from];
        BoardField king = this.boardField[4];


        if (figure == BoardField.ROOK_WHITE
                && figure == this.boardField[0]
            && king == this.boardField[4]
            && rookUnmoved_0 == true
            && kingUnmoved_4 == true
            && this.boardField[1] == BoardField.EMPTY
            && this.boardField[2] == BoardField.EMPTY
            && this.boardField[3] == BoardField.EMPTY
            && rochadeWhite == true) {

            this.boardField[3] = BoardField.ROOK_WHITE;
            this.boardField[2] = BoardField.KING_WHITE;
            this.boardField[0] = BoardField.EMPTY;
            this.boardField[4] = BoardField.EMPTY;
            rochadeWhite = false;
        }
        else if (figure == BoardField.ROOK_WHITE
                && figure == this.boardField[7]
                && king == this.boardField[4]
                && rookUnmoved_7 == true
                && kingUnmoved_4 == true
                && this.boardField[5] == BoardField.EMPTY
                && this.boardField[6] == BoardField.EMPTY
                && rochadeWhite == true) {

            this.boardField[5] = BoardField.ROOK_WHITE;
            this.boardField[6] = BoardField.KING_WHITE;
            this.boardField[7] = BoardField.EMPTY;
            this.boardField[4] = BoardField.EMPTY;
            rochadeWhite = false;
        }
        else if (figure == BoardField.ROOK_BLACK
                && figure == this.boardField[56]
                && king == this.boardField[60]
                && rookUnmoved_56 == true
                && kingUnmoved_60 == true
                && this.boardField[57] == BoardField.EMPTY
                && this.boardField[58] == BoardField.EMPTY
                && this.boardField[59] == BoardField.EMPTY
                && rochadeBlack == true) {

            this.boardField[59] = BoardField.ROOK_BLACK;
            this.boardField[58] = BoardField.KING_BLACK;
            this.boardField[56] = BoardField.EMPTY;
            this.boardField[60] = BoardField.EMPTY;
            rochadeBlack = false;
        }
        else if (figure == BoardField.ROOK_BLACK
                && figure == this.boardField[63]
                && king == this.boardField[60]
                && rookUnmoved_63 == true
                && kingUnmoved_60 == true
                && this.boardField[61] == BoardField.EMPTY
                && this.boardField[62] == BoardField.EMPTY
                && rochadeBlack == true) {

            this.boardField[61] = BoardField.ROOK_BLACK;
            this.boardField[62] = BoardField.KING_BLACK;
            this.boardField[63] = BoardField.EMPTY;
            this.boardField[60] = BoardField.EMPTY;
            rochadeBlack = false;
        }
        else {
            System.err.println("rochade cannot be performed");
        }
    }
}

/*          Für den Bauer im ersten Zug


        BoardField figure = this.board[from];

        if (figure == BoardField.PAWN_WHITE
            && (figure == this.board[8]
                || figure == this.board[9]
                || figure == this.board[10]
                || figure == this.board[11]
                || figure == this.board[12]
                || figure == this.board[13]
                || figure == this.board[14]
                || figure == this.board[15])) {
            this.board[from] = BoardField.EMPTY;
 */



