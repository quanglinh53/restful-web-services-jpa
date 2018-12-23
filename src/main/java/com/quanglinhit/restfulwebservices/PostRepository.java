package com.quanglinhit.restfulwebservices;

import com.quanglinhit.entity.Post;
import com.quanglinhit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
