package com.byrneham.mongodb;

public class Timer {
    long startNanos;
    long endNanos;

    public Timer() {
        startNanos = System.nanoTime();
    }

    public float stop(){
        endNanos = System.nanoTime();
        return elapsed();
    }

    public float elapsed(){
        return (endNanos - startNanos)/1000000f;
    }

    @Override
    public String toString() {
        return Float.toString( elapsed() ) + " mS";
    }
}
