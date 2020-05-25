package schiffeVersenken;

import java.io.IOException;

public interface SchiffeVersenkenSender {
    /**
     *
     * @param zahl
     * @throws StatusException
     */
    void reihenfolgeWuerfelnSend(int zahl) throws StatusException;

    /**
     *
     * @param zeile
     * @param spalte
     * @throws StatusException
     * @throws IOException
     */
    void koordinateSend(int zeile, int spalte) throws StatusException, IOException;

    /**
     *
     * @throws StatusException
     */
    void kapitulationSend() throws StatusException;

    /**
     *
     * @param zahl
     * @throws StatusException
     */
    void bestaetigenreceive(int zahl) throws StatusException;
}
