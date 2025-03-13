package com.Gryshin.cloud_api.service;

import com.Gryshin.cloud_api.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {
    private final WebClient webClient;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    // GET-запит
    public Mono<Post> getPostById(int id) {
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class);
    }

    // POST-запит
    public Mono<Post> createPost(Post newPost) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(newPost)
                .retrieve()
                .bodyToMono(Post.class);
    }

    // DELETE-запит
    public Mono<ResponseEntity<Void>> deletePost(int id) {
        return webClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
