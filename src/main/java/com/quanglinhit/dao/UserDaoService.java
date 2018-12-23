package com.quanglinhit.dao;

import com.quanglinhit.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    public static List<User> userList = new ArrayList<>();
    private static int userCount = 3;

    static {
        userList.add(new User(1, "Quang", new Date()));
        userList.add(new User(2, "Minh", new Date()));
        userList.add(new User(3, "Sáng", new Date()));
    }

    public List<User> showAll(){
        return userList;
    }

    public User save(User user){
        if (user.getId() == null){
            user.setId(++userCount);
        }
        userList.add(user);
        return user;
    }

    public User findById(int id){
        for (User user:userList){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()){
            User  user = iterator.next();
            if (user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
