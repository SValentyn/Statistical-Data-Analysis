import classifier.NaiveBayes;
import classifier.data.NaiveBayesKnowledgeBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Syniuk Valentyn
 */
public class NaiveBayesAnalyser {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public static Map<String, String> trainingFiles = new HashMap<>();
    public static Map<String, String[]> trainingExamples = new HashMap<>();
    
    public static void main(String[] args) {
        NaiveBayes snsNaiveBayes = new NaiveBayes();
        NaiveBayes emailNaiveBayes = new NaiveBayes();
        
        boolean smsAlreadyAnalyzed = false;
        boolean emailAlreadyAnalyzed = false;
        
        while (true) {
            System.out.println();
            System.out.println(" 0 :: SMS dataset");
            System.out.println(" 1 :: Email dataset");
            System.out.println("-1 :: Exit");
            
            try {
                System.out.print("\nEnter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 0: {
                        if (!smsAlreadyAnalyzed) {
                            analyzeSMS();
                            setDatasetsFromFiles(TypeOfSet.SMS, "./output/sms/");
                            loadExamplesInMemory();
                            snsNaiveBayes = getTrainedClassifierKnowledgeBase(trainingExamples);
                            smsAlreadyAnalyzed = true;
                        }
                        
                        System.out.print("Enter your message: ");
                        scanner.nextLine();
                        String text = scanner.nextLine();
                    
                        String category = categorizeText(snsNaiveBayes, text);
                        System.out.format("Your message \"%s\" was classified as \"%s\"%n", text, category);
                        break;
                    }
                    case 1: {
                        if (!emailAlreadyAnalyzed) {
                            analyzeEmail();
                            setDatasetsFromFiles(TypeOfSet.EMAIL, "./output/email/");
                            loadExamplesInMemory();
                            emailNaiveBayes = getTrainedClassifierKnowledgeBase(trainingExamples);
                            emailAlreadyAnalyzed = true;
                        }
    
                        System.out.print("Enter your message: ");
                        scanner.nextLine();
                        String text = scanner.nextLine();
                        
                        String category = categorizeText(emailNaiveBayes, text);
                        System.out.format("The sentence \"%s\" was classified as \"%s\".%n", text, category);
                        break;
                    }
                    case -1: {
                        return;
                    }
                    default: {
                        System.out.println("\nIncorrect input..");
                        break;
                    }
                }
            } catch (InputMismatchException | IOException e) {
                System.out.println("The value entered is incorrect. Try again.");
                main(args);
            }
        }
    }
    
    /**
     * Анализирует и обрабатывает исходные данные сообщений из файлов СМС {@link DataAnalyzer#processData(String)}.
     * Записывает результат в директорию "output/sms/"
     */
    private static void analyzeSMS() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/sms/sms-spam-corpus.csv"), StandardCharsets.UTF_8));
             Writer hamWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./output/sms/processedHamWords.txt"), StandardCharsets.UTF_8));
             Writer spamWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./output/sms/processedSpamWords.txt"), StandardCharsets.UTF_8))) {
            
            reader.lines()
                    .skip(1) // Skips the first line
                    .forEach(line -> {
                        String[] subLines = line.split(",", 2);
                        
                        if (subLines.length > 1) {
                            subLines[1] = DataAnalyzer.processData(subLines[1]);
                            String[] words = subLines[1].split(" ");
                            
                            if (subLines[0].equals("ham")) {
                                writeWordsToFile(words, hamWriter);
                            } else if (subLines[0].equals("spam")) {
                                writeWordsToFile(words, spamWriter);
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Анализирует и обрабатывает исходные данные сообщений из файлов эл. сообщений {@link DataAnalyzer#processData(String)}.
     * Записывает результат в директорию "output/email/"
     */
    private static void analyzeEmail() throws IOException {
        ArrayList<File> hamEmailFiles = listFilesForFolder("src/main/resources/email/", ".ham.txt");
        ArrayList<File> spamEmailFiles = listFilesForFolder("src/main/resources/email/", ".spam.txt");
        Writer hamWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./output/email/processedHamWords.txt"), StandardCharsets.UTF_8));
        Writer spamWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./output/email/processedSpamWords.txt"), StandardCharsets.UTF_8));
        
        hamEmailFiles.forEach(file -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
                reader.lines().forEach(line -> {
                    line = DataAnalyzer.processData(line);
                    if (!line.equals("")) {
                        String[] words = line.split(" ");
                        writeWordsToFile(words, hamWriter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        spamEmailFiles.forEach(file -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
                reader.lines().forEach(line -> {
                    line = DataAnalyzer.processData(line);
                    if (!line.equals("")) {
                        String[] words = line.split(" ");
                        writeWordsToFile(words, spamWriter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        hamWriter.flush();
        hamWriter.close();
        spamWriter.flush();
        spamWriter.close();
    }
    
    /**
     * Записывает переданные слова в файл
     */
    public static void writeWordsToFile(String[] words, Writer writer) {
        try {
            for (String word : words) {
                writer.write(word + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Устанавливает наборы данных для обучения классификатора в соответствии с типом набора
     */
    public static void setDatasetsFromFiles(TypeOfSet type, String folder) {
        if (type.equals(TypeOfSet.SMS)) {
            trainingFiles.put("ham", folder + "processedHamWords.txt");
            trainingFiles.put("spam", folder + "processedSpamWords.txt");
        } else if (type.equals(TypeOfSet.EMAIL)) {
            trainingFiles.put("ham", folder + "processedHamWords.txt");
            trainingFiles.put("spam", folder + "processedSpamWords.txt");
        }
    }
    
    /**
     * Загружает тестовые данные из файлов в память
     */
    private static void loadExamplesInMemory() {
        trainingExamples = new HashMap<>();
        for (Map.Entry<String, String> entry : trainingFiles.entrySet()) {
            trainingExamples.put(entry.getKey(), readLines(entry.getValue()));
        }
    }
    
    /**
     * Считывает все строки из указанного файла в список String
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
    
    public static ArrayList<File> listFilesForFolder(String folder, String patternFileFilter) throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get(folder))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> file.getName().endsWith(patternFileFilter))
                .collect(Collectors.toList());
        
        return (ArrayList<File>) filesInFolder;
    }
    
    /**
     * В методе происходит обучение классификатора
     *
     * @param trainingExamples текстовые наборы для обучения классификатора
     * @return обученный классификатор
     */
    private static NaiveBayes getTrainedClassifierKnowledgeBase(Map<String, String[]> trainingExamples) {
        
        // Train classifier
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.setChisquareCriticalValue(6.63);
        naiveBayes.train(trainingExamples);
        
        // Get trained classifier knowledgeBase
        NaiveBayesKnowledgeBase knowledgeBase = naiveBayes.getKnowledgeBase();
        
        return new NaiveBayes(knowledgeBase);
    }
    
    /**
     * Предсказывает категорию текста с помощью уже подготовленного классификатора и возвращает его категорию
     */
    public static String categorizeText(NaiveBayes naiveBayes, String text) {
        return naiveBayes.predict(text);
    }
    
}
