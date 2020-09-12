package libstemmer_java.java.org.tartarus.snowball;

import libstemmer_java.java.org.tartarus.snowball.ext.EnglishStemmer;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestApp {
    
    public static void main(String[] args) throws Throwable {
    
        SnowballStemmer stemmer = new EnglishStemmer();
        
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/sms-spam-corpus.csv"), StandardCharsets.UTF_8));
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/test-sms-spam-corpus.csv"), StandardCharsets.UTF_8));
        
        StringBuilder input = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            char ch = (char) character;
            if (Character.isWhitespace(ch)) {
                stemmer.setCurrent(input.toString());
                stemmer.stem();
                writer.write(stemmer.getCurrent());
                writer.write('\n');
                input.delete(0, input.length());
            } else {
                input.append(ch < 127 ? Character.toLowerCase(ch) : ch);
            }
        }
        writer.flush();
    }
}
