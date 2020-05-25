package schiffeVersenken;

import java.io.IOException;

public interface SchiffeVersenkenReceive {
    /**
     *
     * @param zahl
     * @throws Exception
     * @throws StatusException
     */
    void reihenfolgeWuerfelnReceive(int zahl) throws Exception, StatusException;

    /**
     *
     * @param zeile
     * @param spalte
     * @throws StatusException
     */
    void koordinateReceive(int zeile, int spalte) throws StatusException;

    /**
     *
     * @throws StatusException
     */
    void kapitulationReceive() throws StatusException;

    /**
     *
     * @param zahl
     * @throws StatusException
     * @throws IOException
     */
    void bestaetigenSend(int zahl) throws StatusException, IOException;


}
