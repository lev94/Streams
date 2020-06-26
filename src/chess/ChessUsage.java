package chess;

import java.io.IOException;

public interface ChessUsage {

    /*
     * figure out who starts
     */
    void doDice() throws StatusException, IOException;

    /**
     *
     * @return true if active - player can move a chess piece
     */
    boolean isActive();

    /**
     * move a chess piece from "from" to "to"
     * @param from
     * @param to
     */
    void set(int from, int to) throws ChessException, StatusException;

    ChessStatus getStatus();

    void chooseColor(boolean white);

    boolean checkEnd();
}
