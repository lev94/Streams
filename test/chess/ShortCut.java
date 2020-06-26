package chess;

import java.io.IOException;

public class ShortCut implements ChessSender {

    private ChessReceiver receiver;

    public void setReceiver(ChessReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void sendDice(int number) throws StatusException, IOException {
        this.receiver.diceReceiver(number);
    }

    @Override
    public void chooseColorSender(boolean white) throws IOException, StatusException {
        this.receiver.chooseColorReceiver(white);
    }

    @Override
    public void moveSender(int from, int to) throws StatusException, IOException {
        this.receiver.moveReceiver(from, to);
    }

    @Override
    public void movePawnRuleSender(int from, int figureType) throws StatusException, IOException {

    }

    @Override
    public void rochadeSender(int from) throws StatusException, IOException {

    }

    @Override
    public void endGameSender(int reason) throws StatusException, IOException {

    }

    @Override
    public void proposalEnd(int reason) throws StatusException, IOException {

    }
}
