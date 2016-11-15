package com.byrneham.mongodb;

public class Counter {
    long count = 0;
    public void inc(){
        count++;
    }

    @Override
    public String toString() {
        return Long.toString( count );
    }
}
