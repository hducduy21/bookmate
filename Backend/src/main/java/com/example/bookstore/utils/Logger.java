package com.example.bookstore.utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private volatile static Logger instance;
    private Logger() {}
    public static Logger getInstance(){
        if(instance== null){
            synchronized(Logger.class){
                if(instance == null){
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    public void log(String message){
        LocalDateTime now = LocalDateTime.now();
        message = now+" :   " + message +"\n\n";
        try {
            File file = new File("src/main/java/com/logs/log.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            writer.write(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
