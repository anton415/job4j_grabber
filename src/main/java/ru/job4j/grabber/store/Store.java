package ru.job4j.grabber.store;

import ru.job4j.grabber.Post;

import java.util.List;

public interface Store {
    /**
     * Save post in DB.
     * @param post - post.
     */
    void save(Post post);

    /**
     * Get all posts from DB.
     * @return list of all posts.
     */
    List<Post> getAll();

    /**
     * Get post from DB by id.
     * @param id - post id.
     * @return - post.
     */
    Post findById(int id);
}