package chess.protocolBinding;

import chess.ChessReceiver;
import chess.StatusException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


public class StreamBindingReceiver extends Thread {

    private final ChessReceiver receiver;
    private final DataInputStream dis;

    public StreamBindingReceiver(InputStream is, ChessReceiver receiver) {
        this.dis = new DataInputStream(is);
        this.receiver = receiver;
    }

    public void readDice() throws IOException, StatusException {
        int number = dis.readInt();

        this.receiver.diceReceiver(number);

    }

    public boolean readChooseColor() throws StatusException, IOException {
        boolean white = dis.readBoolean();

        this.receiver.chooseColorReceiver(white);

        return white;

    }

    public void readMove() throws IOException, StatusException {
        int from = dis.readInt();
        int to = dis.readInt();

        this.receiver.moveReceiver(from, to);

    }

    public void readMovePawnRule() throws IOException, StatusException {
        int from = dis.readInt();
        int figureType = dis.readInt();

        this.receiver.movePawnRuleReceiver(from, figureType);


    }

    public void readRochade() throws StatusException, IOException {
        int from = dis.readInt();

        this.receiver.rochadeReceiver(from);
    }

    public void readEndGame() throws IOException, StatusException {
        int reason = dis.readInt();

        this.receiver.endGameReceiver(reason);

    }

    public void readProposalEnd() throws IOException, StatusException {
        int reason = dis.readInt();

        this.receiver.proposalEndReceiver(reason);

    }

    public void readProposalAnswer() throws StatusException, IOException {
        boolean accept = dis.readBoolean();

        this.receiver.proposalAnswerReceiver(accept);

    }


    public void run() {

        boolean again = true;
        while (again) {
            try {
                int cmd = this.dis.readInt();

                switch (cmd) {
                    case StreamBinding.DICE: this.readDice(); break;
                    case StreamBinding.MOVE: this.readMove(); break;
                    case StreamBinding.MOVE_PAWN_ROLE: this.readMovePawnRule(); break;
                    case StreamBinding.ROCHADE: this.readRochade(); break;
                    case StreamBinding.END_GAME: this.readEndGame(); break;
                    case StreamBinding.PROPOSAL_END: this.readProposalEnd(); break;
                    case StreamBinding.CHOOSE_COLOR: this.readChooseColor(); break;
                    case StreamBinding.PROPOSAL_ANSWER: this.readProposalAnswer(); break;


                    default: again = false; System.err.println("unkown command code: " + cmd);
                }

            } catch (IOException e) {
                System.err.println("IO Exeption: " + e.getLocalizedMessage());
                again = false;
            } catch (StatusException e) {
                System.err.println("Status Exeption: " + e.getLocalizedMessage());
                again = false;
            }
        }
    }
}
