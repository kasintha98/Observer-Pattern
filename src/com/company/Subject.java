package com.company;

import java.util.ArrayList;
import java.util.List;

//Youtube channel
public class Subject {

    List<Observer> observers = new ArrayList<>();

    //subscribe
    public void subscribe(Observer observer){

        //call the database and add this observer to database
        observers.add(observer);
    }

    //unsubscribe
    public  void unsubscribe(Observer observer){
        //call the database and run a query to delete this observer
        observers.remove(observer);
    }

    public void start(Subject subject){

    }

    //when  a new video is added
    public void notifyObservers(String video){
        for (Observer observer : observers) {
            observer.notifyObserver(video);
        }
    }

}