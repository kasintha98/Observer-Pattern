package com.company;

//watching the video
public class Type1Observer implements Observer{

    @Override
    public void notifyObserver(String video) {
        System.out.println("Watching the video: " + video);
    }
}
