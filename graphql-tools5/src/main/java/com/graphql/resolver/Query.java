package com.graphql.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphql.dao.PostDao;
import com.graphql.model.Post;

import java.util.List;

public class Query implements GraphQLQueryResolver {
    private PostDao postDao;

    public Query(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<Post> getRecentPosts(int count, int offset) {
        return postDao.getRecentPosts(count, offset);
    }
}
