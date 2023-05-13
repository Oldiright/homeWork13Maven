package task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;

public class Holder {

    private static final String URL = "https://jsonplaceholder.typicode.com";
    private static final Gson GSON = new Gson();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON = "application/json; charset=UTF-8";
    public User createUser(User user) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users"))
                .header(CONTENT_TYPE, JSON)
                .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(user)))
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(getStatusCode(request));

        return GSON.fromJson(response.body(), User.class);

    }

    public Collection<User> getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users"))
                .GET()
                .build();
        Type collectionType = new TypeToken<Collection<User>>() {
        }.getType();

        return GSON.fromJson(getResponse(request).body(), collectionType);


    }

    public int getStatusCode(HttpRequest request) throws IOException, InterruptedException {
        return getResponse(request).statusCode();
    }

    public int deleteUser(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users/" + userId))
                .header(CONTENT_TYPE, JSON)
                .DELETE()
                .build();
        return getStatusCode(request);
    }
    public User getUserById(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users/" + userId))
                .header(CONTENT_TYPE, JSON)
                .GET()
                .build();
        return GSON.fromJson(getResponse(request).body(), User.class);
    }

    public Collection<User> getUserByUserName(String userName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users?username=" + userName))
                .GET()
                .build();
        Type listType = new TypeToken<List<User>>(){}.getType();
        return GSON.fromJson(getResponse(request).body(), listType);
    }

    public User updateUser(int userId,  User updatedUser) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/users/" + userId))
                .header(CONTENT_TYPE, JSON)
                .PUT(HttpRequest.BodyPublishers
                        .ofString(GSON.toJson(updatedUser)))
                .build();


        return GSON.fromJson(getResponse(request).body(), User.class);
    }

    public HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}
