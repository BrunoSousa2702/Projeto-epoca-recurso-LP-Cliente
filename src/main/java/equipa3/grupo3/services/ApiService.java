package equipa3.grupo3.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import equipa3.grupo3.GUI.Model.Utilizador;
import org.json.JSONObject;

public class ApiService {

    private static final String API_URL = "http://localhost:8080/api";
    private final HttpClient httpClient;

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getData(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + endpoint))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HttpResponseCode: " + response.statusCode());
        }

        return response.body();
    }
    public Utilizador getUserById(Integer id) {
        try {
            // Faz a requisição HTTP e obtém a resposta
            String url = "/utilizadores/" + id;
            String response = getData(url); // Supomos que getData faz a requisição GET corretamente
    
            if (response == null || response.isEmpty()) {
                System.out.println("Utilizador com ID " + id + " não encontrado.");
                return null;
            }
    
            // Converte a resposta JSON para um objeto JSONObject
            JSONObject json = new JSONObject(response);
    
            // Criamos um objeto Utilizador usando o construtor vazio
            Utilizador utilizador = new Utilizador();
            utilizador.setId(json.getInt("id"));
            utilizador.setNome(json.getString("nome"));
            utilizador.setPassword(json.getString("password"));
            utilizador.setEmail(json.getString("email"));
            utilizador.setUsername(json.getString("username"));
            utilizador.setAdmin(json.getBoolean("isAdmin"));
    
            return utilizador; // Retorna o objeto preenchido
    
        } catch (Exception e) {
            System.out.println("Erro ao obter utilizador: " + e.getMessage());
            return null;
        }
    }
    
    

    public String getData(String endpoint, UUID id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + endpoint + "/" + id.toString()))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HttpResponseCode: " + response.statusCode());
        }

        return response.body();
    }
    public Utilizador getUtilizador(String username, String password) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/utilizador?username=" + username + "&password=" + password))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HttpResponseCode: " + response.statusCode());
        }

        // Assuming you have a method to convert JSON string to Utilizador object
        return convertJsonToUtilizador(response.body());
    }

    private Utilizador convertJsonToUtilizador(String json) {
        // Implement the conversion logic here
        // For example, using Jackson ObjectMapper:
        // ObjectMapper objectMapper = new ObjectMapper();
        // return objectMapper.readValue(json, Utilizador.class);
        return null; // Replace with actual implementation
    }

    public String postData(String endpoint, String json) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("Resposta da API: " + response.body());
            throw new RuntimeException("HttpResponseCode: " + response.statusCode());
        }

        return response.body();
    }

    public String putData(String endpoint, String json) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + endpoint))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("Resposta da API: " + response.body());
            throw new RuntimeException("HttpResponseCode: " + response.statusCode());
        }

        return response.body();
    }
}
