package com.Gryshin.cloud_api.controller;

import com.Gryshin.cloud_api.model.Post;
import com.Gryshin.cloud_api.service.ApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cloud-api")
public class CloudRestController {

    private final ApiService apiService;

    public CloudRestController(ApiService apiService) {
        this.apiService = apiService;
    }

    // GET-запит (отримуємо пост за ID)
    @GetMapping("/posts/{id}")
    public Mono<Post> getPostById(@PathVariable int id) {
        return apiService.getPostById(id);
    }

    // POST-запит (створюємо новий пост)
    @PostMapping("/posts")
    public Mono<Post> createPost(@RequestBody Post newPost) {
        return apiService.createPost(newPost);
    }

    // DELETE-запит (видаляємо пост)
    @DeleteMapping("/posts/{id}")
    public Mono<ResponseEntity<Void>> deletePost(@PathVariable int id) {
        return apiService.deletePost(id);
    }
}
