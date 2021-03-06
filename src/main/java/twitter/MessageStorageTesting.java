package twitter;

import model.myDTO;

import java.util.ArrayList;
import java.util.List;

public class MessageStorageTesting {

    private List<myDTO> storage = new ArrayList<>();

    public void count(){

        System.out.println(storage.size());
    }

    public void put(myDTO message) {
        storage.add( message );
        System.out.println(message.toString());
    }

    public String toString() {
        StringBuffer info = new StringBuffer();
        storage.forEach( msg -> info.append( msg ).append( "<br/>" ) );
        return info.toString();
    }

    public void clear() {
        storage.clear();
    }
}
