package org.example.repository;

import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Stub
@Repository
public class PostRepository {
    
    private static final ConcurrentHashMap<Long, Post> postMap = new ConcurrentHashMap<>();
    
    public PostRepository() {
        //Post post = new Post("Первый пост");
        //this.save(post);
    }
    
    public List<Post> all() {
        if (postMap.isEmpty()) {
            throw new NotFoundException("Список постов пуст");
        }
        List<Post> data = new ArrayList<>();
        for (Map.Entry<Long, Post> entry : postMap.entrySet()) {
            data.add(entry.getValue());
        }
        return data;
    }
    
    public Optional<Post> getById(Long id) {
        if (id != null && postMap.containsKey(id)) {
            return Optional.ofNullable(postMap.get(id));
        }
        throw new NotFoundException("Неверный идентификатор поста");
    }
    
    public Post save(Post post) {
        if (post.getId() != null && postMap.containsKey(post.getId())) {
            postMap.put(post.getId(), post);
        } else {
            Post newPost = new Post(post.getContent());
            postMap.put(newPost.getId(), newPost);
            return newPost;
        }
        return post;
    }
    
    public void removeById(Long id) {
              if (id != null) postMap.remove(id);
    }
}
