package org.test.task.evaluator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class StringTokenizerIterator implements Iterator<String> {
    private StringTokenizer tokens;
    public StringTokenizerIterator(StringTokenizer tokens) {
        this.tokens = tokens;
    }
    private String nextToken = null;
    @Override
    public boolean hasNext() {
        return buildNextToken();
    }
    @Override
    public String next() {
        if (!buildNextToken()) {
            throw new NoSuchElementException();
        }
        String token = nextToken;
        nextToken = null;
        return token;
    }
    private boolean buildNextToken() {
        while ((nextToken == null) && tokens.hasMoreTokens()) {
            nextToken = tokens.nextToken();
            if (nextToken.isEmpty()) {
                nextToken = null;
            }
        }
        return nextToken!=null;
    }
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
