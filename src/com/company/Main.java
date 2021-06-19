package com.company;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Observer observer1 = new Type1Observer();
        Observer observer2 = new Type1Observer();
        Observer observer3 = new Type2Observer();

        Subject subject = new Subject();

        subject.subscribe(observer1);
        subject.subscribe(observer2);
        subject.subscribe(observer3);

        subject.start(subject);

    }
}
