package bioinformatic.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    private static final String resourcePath = "./resources/";
    private File file;
    private String content;

    public FileReader(String path) {
        file = new File(resourcePath + path);
        content = "";
    }

    public void readFile() {
        if (!file.exists()) {
            System.out.println(file.getName() + " does not exist.");
            return;
        }
        if (!(file.isFile() && file.canRead())) {
            System.out.println(file.getName() + " cannot be read from.");
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            char current;
            while (fis.available() > 0) {
                current = (char) fis.read();
                content += current;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        return content;
    }
}
