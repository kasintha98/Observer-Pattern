package com.company;

//Liking the video
public class Type2Observer implements Observer{
    @Override
    public void notifyObserver(String video) {
        System.out.println("Liking the video: " + video);
    }
}
