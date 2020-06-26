package chess;

public enum BoardField {
    EMPTY(0),
    KING_WHITE(1),
    QUEEN_WHITE(1),
    ROOK_WHITE(2),
    BISHOP_WHITE(2),
    KNIGHT_WHITE(2),
    PAWN_WHITE(8),
    KING_BLACK(1),
    QUEEN_BLACK(1),
    ROOK_BLACK(2),
    BISHOP_BLACK(2),
    KNIGHT_BLACK(2),
    PAWN_BLACK(8);


    int figureNumber;

    BoardField(int figureNumber) {
        this.figureNumber = figureNumber;
    }

    void kill(BoardField figure) {
        figure.figureNumber--;
    }

    public BoardField getStatus() {
        return this;
    }

}
