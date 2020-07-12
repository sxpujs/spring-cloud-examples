package com.graphql.resolver;


import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.dao.AuthorDao;
import com.graphql.model.Author;
import com.graphql.model.Post;

import java.util.Optional;

public class PostResolver implements GraphQLResolver<Post> {
    private AuthorDao authorDao;

    public PostResolver(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Optional<Author> getAuthor(Post post) {
        return authorDao.getAuthor(post.getAuthorId());
    }
}
