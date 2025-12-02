import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        ArrayList<Actor> actors = dataManager.getActors("Bob", "Ferret");

        for (Actor a : actors) {
            System.out.println(a.getActorId() + " " + a.getFirstName() + " " + a.getLastName());
        }


    }
}
