package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sun.nio.file.ExtendedWatchEventModifier.FILE_TREE;
import static java.nio.file.StandardWatchEventKinds.*;

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

    public void start(Subject subject) throws InterruptedException, IOException {
        FileSystem fs = FileSystems.getDefault();
        WatchService ws = fs.newWatchService();
        Path pTemp = Paths.get("D:/Videos");
        pTemp.register(ws, new WatchEvent.Kind[] {ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE}, FILE_TREE);
        while(true)
        {
            WatchKey k = ws.take();
            for (WatchEvent<?> e : k.pollEvents())
            {
                Object c = e.context();

                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(e.kind() + " -> "+ c);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

                try {
                    File newNews = new File("D:/Videos/"+c);

                    if(newNews.exists()){
                        Scanner myReader = new Scanner(newNews);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            subject.notifyObservers(data);
                        }
                        Gmail.sendMail("EnterYourRecipientEmailHere");
                        myReader.close();
                    }else{
                        System.out.println("Cannot Find The File!");
                    }

                } catch (FileNotFoundException err) {
                    System.out.println("An error occurred.");
                    err.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            k.reset();
        }
    }

    //when  a new video is added
    public void notifyObservers(String video){
        for (Observer observer : observers) {
            observer.notifyObserver(video);
        }
    }

}