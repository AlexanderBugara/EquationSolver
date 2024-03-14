package org.test.task.preprocessor;

public class SequenceToValueConverter {
    private final double value;
    private final int lastIndex;

    public SequenceToValueConverter(Character ch, int index, String input) {
        StringBuilder sb = new StringBuilder().append(ch);
        index++;
        while (index < input.length() && (Character.isDigit(input.charAt(index)) || input.charAt(index) == '.')) {
            sb.append(input.charAt(index++));
        }
        lastIndex = index;
        this.value = Double.parseDouble(sb.toString());
    }

    public Double getValue() {
        return value;
    }

    public int getLastIndex() {
        return lastIndex;
    }
}
