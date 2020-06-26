package chess;

import java.io.IOException;

public interface ChessReceiver {

    void diceReceiver(int number) throws StatusException, IOException;

    void chooseColorReceiver(boolean white) throws StatusException;

    void moveReceiver(int from, int to) throws StatusException;

    void movePawnRuleReceiver(int from, int figureType) throws StatusException;

    void rochadeReceiver(int from) throws StatusException;

    void endGameReceiver(int reason) throws StatusException;

    void proposalEndReceiver(int reason) throws StatusException;

    void proposalAnswerReceiver(boolean accept) throws StatusException;
}
