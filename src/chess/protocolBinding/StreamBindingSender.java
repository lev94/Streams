package chess.protocolBinding;

import chess.ChessSender;
import chess.StatusException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamBindingSender implements ChessSender {

    private final DataOutputStream dos;

    public StreamBindingSender(OutputStream os) {
        this.dos = new DataOutputStream(os);
    }

    @Override
    public void sendDice(int number) throws StatusException, IOException {
        this.dos.writeInt(StreamBinding.DICE);
        this.dos.writeInt(number);
    }

    @Override
    public void chooseColorSender(boolean white) throws IOException {
        this.dos.writeBoolean(white);
    }

    @Override
    public void moveSender(int from, int to) throws StatusException, IOException {
        this.dos.writeInt(StreamBinding.MOVE);
        this.dos.writeInt(from);
        this.dos.writeInt(to);
    }

    @Override
    public void movePawnRuleSender(int from, int figureType) throws StatusException, IOException {
        this.dos.writeInt(StreamBinding.MOVE_PAWN_ROLE);
        this.dos.writeInt(from);
        this.dos.writeInt(figureType);

    }

    @Override
    public void rochadeSender(int from) throws StatusException, IOException {
        this.dos.writeInt(StreamBinding.ROCHADE);
        this.dos.writeInt(from);
    }

    @Override
    public void endGameSender(int reason) throws StatusException, IOException {
        this.dos.writeInt(StreamBinding.END_GAME);
        this.dos.writeInt(reason);
    }

    @Override
    public void proposalEnd(int reason) throws StatusException, IOException {
        this.dos.writeInt(StreamBinding.PROPOSAL_END);
        this.dos.writeInt(reason);
    }

}
