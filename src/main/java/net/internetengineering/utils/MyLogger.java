package net.internetengineering.utils;

import java.util.ArrayList;

/**
 * Created by Hamed Ara on 2/20/2016.
 */
public class MyLogger {
    ArrayList<String> messages=null;

    public MyLogger(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void info(String s){
        if(messages==null)
            messages = new ArrayList<String>();
        messages.add(s);
    }
    public ArrayList<String> getAndFlushMyLogger(){
        ArrayList<String> s =messages;
        messages=null;
        return s;
    }
}
