package chess;

import java.io.IOException;

public interface ChessSender {

    void sendDice(int number) throws StatusException, IOException;

    void chooseColorSender(boolean white) throws IOException, StatusException;

    void moveSender(int from, int to) throws StatusException, IOException;

    void movePawnRuleSender(int from, int figureType) throws StatusException, IOException;

    void rochadeSender(int from) throws StatusException, IOException;

    void endGameSender(int reason) throws StatusException, IOException;

    void proposalEnd(int reason) throws StatusException, IOException;


    //void proposalAnswer(boolean accept);
}
