package task2;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;



public class CommentAgregator {

    private static final String URL =  "https://jsonplaceholder.typicode.com/";

    private  static final Gson GSON = new Gson();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private int getLastUserPostId(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL +"users/"+ userId + "/posts"))
                .GET()
                .build();

        Type collectionType = new TypeToken<Collection<Post>>() {
        }.getType();

        Collection<Post> post = GSON.fromJson(getResponse(request).body(), collectionType);

        return  post.stream()
                .map(Post::getId)
                .mapToInt(Integer:: intValue)
                .max()
                .getAsInt();

    }

    public void CreateNewJsonWithAllCommentsFromLastUserPost(int userId) throws IOException, InterruptedException {
        String  LastUserPostId = String.valueOf(getLastUserPostId(userId));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "post/" + LastUserPostId + "/comments"))
                .GET()
                .build();

        String commentsInJson = getResponse(request).body();
        System.out.println(commentsInJson);
        FileWriter fr = new FileWriter(String.format("src/main/java/task2/user-%s-post-%s-comments.json", userId, LastUserPostId));
        fr.write(commentsInJson);
        fr.flush();
        fr.close();

    }

    public HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }


}


