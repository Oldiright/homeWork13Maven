import com.google.gson.Gson;
import task1.Address;
import task1.Company;
import task1.Holder;
import task1.User;
import task2.CommentAgregator;
import task3.ToDo;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {




    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        Holder holder = new Holder();
        Gson gson = new Gson();
        User user = new User(12, "Pasha", "Petrov", "tes@gmail.com", new Address("Vilna",
                "12", "Lviv", "4455-3", new Address.Geo(24.665f, 544.55f)), "+8852-9984",
                "www.rst.com", new Company("Umbrella", "We are the champions", "bs"));

        /*TASK 1*/
//
//        System.out.println(holder.createUser(user));
//        System.out.println(holder.updateUser(3, user));
//        System.out.println(holder.deleteUser(1));
//        System.out.println(holder.getAllUsers());
//        System.out.println(holder.getUserById(3));
//        System.out.println(gson.toJson(holder.getUserByUserName("Samantha")));


        /*TASK 2*/
//        CommentAgregator agregator = new CommentAgregator();
//        agregator.CreateNewJsonWithAllCommentsFromLastUserPost(3);


        /*TASK 3*/

        ToDo todo = new ToDo();
        System.out.println(todo.getToDoTasks(6));


    }
}
