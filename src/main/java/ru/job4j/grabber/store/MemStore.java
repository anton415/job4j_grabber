package ru.job4j.grabber.store;

import ru.job4j.grabber.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemStore implements Store {
    Map<Integer, Post> posts = new HashMap<>();

    @Override
    public void save(Post post) {
        this.posts.put(post.getId(), post);
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(this.posts.values());
    }

    @Override
    public Post findById(int id) {
        return posts.get(id);
    }
}
