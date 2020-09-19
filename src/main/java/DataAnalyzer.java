import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.CategoryStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.XYStyler;
import stemmer.SnowballStemmer;
import stemmer.ext.EnglishStemmer;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Syniuk Valentyn
 */
public class DataAnalyzer {
    
    // Стеммер, который позволяет находить основы слова для заданного исходного слова
    public static SnowballStemmer stemmer = new EnglishStemmer();
    
    // Список стоп-слов, которые должны быть изъяты из текста сообщений
    public static List<String> stopWords = fillStopWords();
    
    // Списки, что хранят слова, которые использовались в сообщениях
    public static List<Word> hamWords = new ArrayList<>();
    public static List<Word> spamWords = new ArrayList<>();
    
    // Словари, которые представляют собой :: слово=частота_использования
    public static Map<String, Integer> hamWordDictionary = new HashMap<>();
    public static Map<String, Integer> spamWordDictionary = new HashMap<>();
    
    // Словари, которые представляют собой :: длина_сообщения=частота_использования
    public static Map<Integer, Integer> hamMessageDictionary = new TreeMap<>();
    public static Map<Integer, Integer> spamMessageDictionary = new TreeMap<>();
    
    // Словари, которые представляют собой :: слово=количество_использований
    public static Map<String, Integer> frequentlyUsedHamWordsDictionary = new LinkedHashMap<>();
    public static Map<String, Integer> frequentlyUsedSpamWordsDictionary = new LinkedHashMap<>();
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/sms-spam-corpus.csv"), StandardCharsets.UTF_8));
             Writer hamWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./output/ham.txt"), StandardCharsets.UTF_8));
             Writer spamWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./output/spam.txt"), StandardCharsets.UTF_8))) {
            
            reader.lines()
                    .skip(1) // Skips the first line
                    .forEach(line -> {
                        String[] subLines = line.split(",", 2);
                        
                        if (subLines.length > 1) {
                            subLines[1] = processData(subLines[1]);
                            String[] words = subLines[1].split(" ");
                            
                            if (subLines[0].equals("ham")) {
                                addWordsToWordDictionary(words, hamWordDictionary);
                                addLineToMessageDictionary(subLines[1], hamMessageDictionary);
                            } else if (subLines[0].equals("spam")) {
                                addWordsToWordDictionary(words, spamWordDictionary);
                                addLineToMessageDictionary(subLines[1], spamMessageDictionary);
                            }
                        }
                    });
            
            writeDictionaryToFile(hamWordDictionary, hamWriter);
            writeDictionaryToFile(spamWordDictionary, spamWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Получаем списки слов из словарей
        getWordsFromWordDictionaries();
        
        // Получаем списки часто употребляемых слов из словарей
        getFrequentlyUsedWordsFromDictionaries();
        
        // Графики для 4.a
        displayHistogramCharts();
        
        // Графики для 4.b
        displayXYCharts();
        
        // Графики для 4.c
        displayStickCharts();
    }
    
    /**
     * Заполняет список стоп-слов
     */
    private static List<String> fillStopWords() {
        String[] arrayStopWords = new String[]{
                "", "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z",
                "the", "to", "in", "on", "at", "of",
                "am", "is", "are", "be", "being", "been",
                "have", "has", "having", "had",
                "do", "does", "doing", "did" };
        return Arrays.asList(arrayStopWords);
    }
    
    /**
     * Обрабатывает текст сообщений от лишних данных + стемминг
     */
    public static String processData(String line) {
        StringBuilder resultLine = new StringBuilder();
        String[] words = line.trim().replaceAll("([^a-z ])", "").toLowerCase().split(" ");
        for (String word : words) {
            if (word != null && word.length() != 0 && !stopWords.contains(word)) {
                stemmer.setCurrent(word);
                stemmer.stem();
                resultLine.append(stemmer.getCurrent().trim()).append(" ");
            }
        }
        return resultLine.toString();
    }
    
    /**
     * Добавляет слова в словарь слов для определённой категории. key = слово, value = частота_использования
     */
    public static void addWordsToWordDictionary(String[] words, Map<String, Integer> dictionary) {
        Arrays.stream(words).forEach(key -> dictionary.put(key, dictionary.getOrDefault(key, 0) + 1));
    }
    
    /**
     * Получает и заполняет списки слов из имеющихся словарей слов.
     * Поскольку списки и словари статичны, то отпадает необходимость передавать их как параметры.
     */
    private static void getWordsFromWordDictionaries() {
        hamWords = getWordsFromWordDictionary(hamWordDictionary);
        spamWords = getWordsFromWordDictionary(spamWordDictionary);
    }
    
    /**
     * Получает список слов из словаря слов, определённой категории
     */
    public static List<Word> getWordsFromWordDictionary(Map<String, Integer> dictionary) {
        List<Word> words = new ArrayList<>();
        dictionary.forEach((k, v) -> {
            words.add(new Word(k, k.length(), v));
        });
        return words;
    }
    
    /**
     * Добавляет в словарь сообщений, для определённой категории, пару: key = длина_сообщения, value = частота_использования
     */
    public static void addLineToMessageDictionary(String line, Map<Integer, Integer> dictionary) {
        dictionary.put(line.length(), dictionary.getOrDefault(line.length(), 0) + 1);
    }
    
    /**
     * Записывает словарь в соответствующий файл
     */
    public static void writeDictionaryToFile(Map<String, Integer> dictionary, Writer writer) {
        dictionary.forEach((k, v) -> {
            try {
                writer.write(k + "=" + v + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    /**
     * Обобщает данные, что бы получить зависимость длины и частоты использования слов в сообщениях
     */
    private static Map<Integer, Integer> generalizeLengthAndFrequencyWords(List<Word> words) {
        Map<Integer, Integer> generalizedData = new HashMap<>();
        words.forEach(key -> generalizedData.put(key.getLength(), generalizedData.getOrDefault(key.getLength(), 0) + key.getFrequency()));
        return generalizedData;
    }
    
    /**
     * Получает и заполняет словари часто используемых слов.
     * Поскольку списки статичны, то отпадает необходимость передавать их как параметры.
     */
    private static void getFrequentlyUsedWordsFromDictionaries() {
        frequentlyUsedHamWordsDictionary = getFrequentlyUsedWordsFromDictionary(hamWordDictionary);
        frequentlyUsedSpamWordsDictionary = getFrequentlyUsedWordsFromDictionary(spamWordDictionary);
    }
    
    /**
     * Формирует новый словарь из исходного.
     * Будет содержать самые часто используемые слова в сообщениях для каждой категории
     */
    private static Map<String, Integer> getFrequentlyUsedWordsFromDictionary(Map<String, Integer> dictionary) {
        Map<String, Integer> frequentlyUsedWordsDictionary = new LinkedHashMap<>();
        dictionary.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(20)
                .forEach(e -> frequentlyUsedWordsDictionary.put(e.getKey(), e.getValue()));
        return frequentlyUsedWordsDictionary;
    }
    
    /**
     * Вызывает показ графиков, что отображают распределение слов по их длине для каждой категории.
     * Перед визуализацией данных, необходимо провести обобщение имеющихся данных.
     */
    private static void displayHistogramCharts() {
        
        // Обобщаем данные, что бы получить зависимость длины слова и частоты использования
        Map<Integer, Integer> hamGeneralizedData = generalizeLengthAndFrequencyWords(hamWords);
        Map<Integer, Integer> spamGeneralizedData = generalizeLengthAndFrequencyWords(spamWords);
        
        new Visualizer(hamGeneralizedData, TextType.WORD, "длине слов, для ham-сообщений").display();
        new Visualizer(spamGeneralizedData, TextType.WORD, "длине слов, для spam-сообщений").display();
    }
    
    /**
     * Вызывает показ графиков, что отображают распределение сообщений по их длине для каждой категории
     */
    private static void displayXYCharts() {
        new Visualizer(hamMessageDictionary, TextType.MESSAGE, "длине сообщений, для ham-сообщений").display();
        new Visualizer(spamMessageDictionary, TextType.MESSAGE, "длине сообщений, для spam-сообщений").display();
    }
    
    /**
     * Вызывает показ графиков, что отображают распределение самых используемых слов по каждой категории
     */
    private static void displayStickCharts() {
        new Visualizer(frequentlyUsedHamWordsDictionary, "ham-сообщений").display();
        new Visualizer(frequentlyUsedSpamWordsDictionary, "spam-сообщений").display();
    }
    
    /**
     * Внутренний класс, что обеспечивает визуализацию данных на различных графиках
     */
    public static class Visualizer {
        
        private final Font baseFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        private final Font axisFont = new Font(Font.DIALOG, Font.PLAIN, 16);
        
        private Chart<? extends Styler, ? extends Series> chart;
        private List<Integer> xData = new ArrayList<>();
        private List<Integer> yData = new ArrayList<>();
        
        public Visualizer(Map<Integer, Integer> data, TextType textType, String title) {
            if (textType.equals(TextType.WORD)) {
                chart = initializeAndGetHistogramChart(data, title);
            } else if (textType.equals(TextType.MESSAGE)) {
                chart = initializeAndGetXYChart(data, title);
            }
        }
        
        public Visualizer(Map<String, Integer> data, String title) {
            chart = initializeAndGetStickChart(data, title);
        }
        
        public void display() {
            if (chart != null) {
                customizeChart(chart);
                new SwingWrapper<>(chart).displayChart();
            }
        }
        
        private Chart<CategoryStyler, CategorySeries> initializeAndGetHistogramChart(Map<Integer, Integer> data, String title) {
            
            // Create Chart
            CategoryChart chart = new CategoryChartBuilder()
                    .width(1760).height(960)
                    .title("Распределение по " + title)
                    .xAxisTitle("Длина слова")
                    .yAxisTitle("Частота использования")
                    .build();
            chart.getStyler().setAxisTitleFont(axisFont);
            
            xData.clear();
            yData.clear();
            AtomicInteger totalLength = new AtomicInteger();
            AtomicInteger totalWordsCount = new AtomicInteger();
            
            // Set Data
            data.forEach((k, v) -> {
                if (k != 0) {
                    xData.add(k);
                    yData.add(v);
                    totalLength.addAndGet(k * v);
                    totalWordsCount.addAndGet(v);
                }
            });
            chart.addSeries("Частота", xData, yData);
            chart.addSeries("Средняя длина слова: " + totalLength.intValue() / totalWordsCount.intValue(),
                    Collections.singletonList(totalLength.intValue() / totalWordsCount.intValue()), Collections.singletonList(null));
            
            return chart;
        }
        
        private Chart<XYStyler, XYSeries> initializeAndGetXYChart(Map<Integer, Integer> data, String title) {
            
            // Create Chart
            XYChart chart = new XYChartBuilder()
                    .width(1760).height(960)
                    .title("Распределение по " + title)
                    .xAxisTitle("Длина сообщения")
                    .yAxisTitle("Количество сообщений")
                    .build();
            chart.getStyler().setAxisTitleFont(axisFont);
            
            xData.clear();
            yData.clear();
            AtomicInteger totalLength = new AtomicInteger();
            AtomicInteger totalMessagesCount = new AtomicInteger();
            
            // Set Data
            data.forEach((k, v) -> {
                if (k != null && k != 0) {
                    xData.add(k);
                    yData.add(v);
                    totalLength.addAndGet(k * v);
                    totalMessagesCount.addAndGet(v);
                }
            });
            chart.addSeries("Количество", xData, yData);
            chart.addSeries("Средняя длина сообщения: " + totalLength.intValue() / totalMessagesCount.intValue(),
                    Collections.singletonList(totalLength.intValue() / totalMessagesCount.intValue()), Collections.singletonList(null));
            
            return chart;
        }
        
        private Chart<CategoryStyler, CategorySeries> initializeAndGetStickChart(Map<String, Integer> data, String title) {
            
            // Create Chart
            CategoryChart chart = new CategoryChartBuilder()
                    .width(1760).height(960)
                    .title("Часто употребляемые слова, для " + title)
                    .xAxisTitle("Слова")
                    .yAxisTitle("Частота использования")
                    .build();
            chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Stick);
            chart.getStyler().setAxisTitleFont(axisFont);
            
            List<String> xData = new ArrayList<>();
            yData.clear();
            
            // Set Data
            data.forEach((k, v) -> {
                if (k != null && !k.equals("")) {
                    xData.add(k);
                    yData.add(v);
                }
            });
            chart.addSeries("Частота", xData, yData);
            
            return chart;
        }
        
        private void customizeChart(Chart<? extends Styler, ? extends Series> chart) {
            chart.getStyler().setLegendVisible(true);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
            chart.getStyler().setHasAnnotations(true);
            chart.getStyler().setChartTitleVisible(true);
            chart.getStyler().setBaseFont(baseFont);
            chart.getStyler().setChartTitleFont(baseFont);
            chart.getStyler().setLegendFont(baseFont);
        }
    }
    
}
