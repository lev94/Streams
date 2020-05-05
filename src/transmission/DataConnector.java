package transmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DataConnector implements DataConnection {
    private final Socket socket;

    public DataConnector(String address, int port) throws IOException, InterruptedException {
        this.socket = new Socket(address, port);

    }

    public DataConnector(int port) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(port);
        this.socket = serverSocket.accept();
    }

    @Override
    public DataInputStream getDataInputStream() throws IOException{
        return new DataInputStream(this.socket.getInputStream());
    }

    @Override
    public DataOutputStream getDataOutputStream() throws IOException{
        return new DataOutputStream(this.socket.getOutputStream());
    }
}
