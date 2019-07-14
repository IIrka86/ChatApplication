package kov.irok.WebClient.controller;

import kov.irok.WebClient.consoleServer.Connection;
import kov.irok.WebClient.consoleServer.ConnectionListener;
import kov.irok.WebClient.consoleServer.Server;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@RestController
@RequestMapping("/server")
public class ServerController {

    final static Logger logger = Logger.getLogger(Server.class);

    @Autowired
    public ConnectionListener consoleServer;

    @GetMapping
    public String startServer(){
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try (ServerSocket server = new ServerSocket(8000)){
                        logger.info("Server started at port 8000");

                        while (true)
                            try{
                                Socket socket = server.accept();
                                new Connection(consoleServer, socket);
                                logger.info("Connected client. IP: " + socket.getInetAddress());
                            }catch (IOException e){
                                logger.error("Connection exception: " + e);
                            }
                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
            return "Server started";
        }catch (Exception e){
            logger.error("Connection exception: " + e);
            return "Error";
        }
    }


}
