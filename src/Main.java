import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        serializeSimple();
        deserializeSimple();

    }

    static void serializeSimple(){
        Todos person = new Todos("walk the dog", false, 0, 3, "dog");
        Todos person2 = new Todos("pay bills", false, 1, 1, "bills");
        ArrayList<Todos> todosList = new ArrayList<>();
        todosList.add(person);
        todosList.add(person2);

        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("data.json")){

            gson.toJson(todosList,writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void deserializeSimple(){

        try (FileReader reader = new FileReader("data.json")){
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Todos>>(){}.getType();
            ArrayList<Todos> losdias = gson.fromJson(jsonElement,type);


            System.out.println(losdias);
            String value = losdias.get(0).getBody();
            System.out.println(value);
            losdias.get(0).setBody("Walk the fish");
            System.out.println(losdias);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Todos {
    private String body;
    private boolean done;
    private int id;
    private int priority;
    private String title;

    public Todos(String body, boolean done, int id, int priority, String title) {
        this.body = body;
        this.done = done;
        this.id = id;
        this.priority = priority;
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Todos{" +
                "body='" + body + '\'' +
                ", done=" + done +
                ", id=" + id +
                ", priority=" + priority +
                ", title='" + title + '\'' +
                '}';
    }
}