package designPattern.commandPattern;

import java.io.*;

public class Document {
    private final String filePath;
    private BufferedReader br;

    public Document(String filePath) {
        this.filePath = filePath;
        try {
            this.br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.err.println("Not Found" + filePath);
            e.printStackTrace();
        }
    }
}
