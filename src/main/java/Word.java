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
        return "Word{" +
                "value='" + value + '\'' +
                ", length=" + length +
                ", frequency=" + frequency +
                '}';
    }
}
