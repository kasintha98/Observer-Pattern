package com.company;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Observer observer1 = new Type1Observer();


        Subject subject = new Subject();

        subject.subscribe(observer1);


        subject.start(subject);

    }
}
