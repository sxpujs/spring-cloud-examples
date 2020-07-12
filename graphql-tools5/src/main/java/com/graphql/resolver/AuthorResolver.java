package com.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.dao.PostDao;
import com.graphql.model.Author;
import com.graphql.model.Post;

import java.util.List;

public class AuthorResolver implements GraphQLResolver<Author> {
    private PostDao postDao;

    public AuthorResolver(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<Post> getPosts(Author author) {
        return postDao.getAuthorPosts(author.getId());
    }
}
