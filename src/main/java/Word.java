import java.util.Objects;

/**
 * @author Syniuk Valentyn
 */
public class Word {
    
    private String value;
    private int length;
    private int frequency;
    
    public Word(String value, int length, int frequency) {
        this.value = value;
        this.length = length;
        this.frequency = frequency;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    @Override
    public String toString() {
        return "Word {" +
                "value = '" + value + '\'' +
                ", length = " + length +
                ", frequency = " + frequency +
                '}';
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Word word = (Word) other;
        return length == word.length &&
                frequency == word.frequency &&
                Objects.equals(value, word.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value, length, frequency);
    }
}
