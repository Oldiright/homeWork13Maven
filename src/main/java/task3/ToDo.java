package task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ToDo {


    private static final String URL = "https://jsonplaceholder.typicode.com/";
    private  static final Gson GSON = new Gson();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();



    public List<Task> getToDoTasks(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "user/" + userId + "/todos"))
                .GET().build();

        Type collectionType = new TypeToken<Collection<Task>>() {
        }.getType();
        List<Task> tasks = GSON.fromJson(getResponse(request).body(), collectionType);

        return tasks.stream().filter((i) -> !i.isCompleted()).collect(Collectors.toList());

    }
    private HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}

