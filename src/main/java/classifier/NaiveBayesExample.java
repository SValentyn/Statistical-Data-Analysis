package classifier;

import classifier.data.NaiveBayesKnowledgeBase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveBayesExample {
    
    public static void main(String[] args) {
        
        // Map of dataset files
        Map<String, String> trainingFiles = new HashMap<>();
        trainingFiles.put("spam", "spam.txt");
        trainingFiles.put("ham", "ham.txt");
        
        // Loading examples in memory
        Map<String, String[]> trainingExamples = new HashMap<>();
        for (Map.Entry<String, String> entry : trainingFiles.entrySet()) {
            trainingExamples.put(entry.getKey(), readLines(entry.getValue()));
        }
        
        // Train classifier
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.setChisquareCriticalValue(6.63);
        naiveBayes.train(trainingExamples);
        
        // Get trained classifier knowledgeBase
        NaiveBayesKnowledgeBase knowledgeBase = naiveBayes.getKnowledgeBase();
    
        // Use classifier
        naiveBayes = new NaiveBayes(knowledgeBase);
        String example = "1";
        String category = naiveBayes.predict(example);
        System.out.format("The sentence \"%s\" was classified as \"%s\".%n", example, category);
    }
    
    /**
     * Reads the all lines from a file and places it a String array.
     * In each record in the String array we store a training example text.
     */
    public static String[] readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return lines.toArray(new String[0]);
    }
    
}
