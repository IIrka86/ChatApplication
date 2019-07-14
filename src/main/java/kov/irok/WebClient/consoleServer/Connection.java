package kov.irok.WebClient.consoleServer;

import java.io.*;
import java.net.Socket;

public class Connection {

    private final Socket socket;
    private boolean isLogined;
    private int userId;
    private final BufferedWriter writer;
    private final BufferedReader reader;
    private Thread thread;
    private ConnectionListener listener;

    public Connection(ConnectionListener listener, Socket socket) throws IOException {
        this.listener = listener;
        this.socket = socket;
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    listener.onConnectionReady(Connection.this);
                    while (!thread.isInterrupted()){
                        listener.onReceiveString(Connection.this, reader.readLine());
                    }
                }catch (IOException e){
                    listener.onException(Connection.this, e);
                }finally {
                    listener.onDisconnect(Connection.this);
                }
            }
        });
        thread.start();
    }

    public boolean isLogined() {
        return isLogined;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public synchronized void sendString(String string){
        try{
            writer.write(string);
            writer.newLine();
            writer.flush();
        }catch (IOException e){
            listener.onException(Connection.this, e);
            disconnect();
        }
    }

    public synchronized String getString(){
        try{
            return reader.readLine();
        }catch (IOException e){
            listener.onException(Connection.this, e);
            disconnect();
        }
        return null;
    }

    public synchronized void disconnect(){
        thread.interrupt();
    }
}