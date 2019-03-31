/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.cheng.thymeleaf.respository
 *
 *    Filename:    UserRepositoryImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     Digital Telemedia Co.,Ltd
 *
 *    @author:     57608
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年3月31日 下午4:32:24
 *
 *    Revision:
 *
 *    2019年3月31日 下午4:32:24
 *        - first revision
 *
 *****************************************************************/
package com.cheng.thymeleaf.respository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.cheng.thymeleaf.domain.User;


/**
 * @ClassName UserRepositoryImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author 57608
 * @Date 2019年3月31日 下午4:32:24
 * @version 1.0.0
 */
@Repository
public class UserRepositoryImpl implements UserRespository {
    
    private static AtomicLong counter = new AtomicLong();
    
    private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>();

//    {
//        userMap.put((long) 1, new User((long) 1, "AAA", "A@QQ.com"));
//        userMap.put((long) 2, new User((long) 2, "BBB", "B@QQ.com"));
//        userMap.put((long) 3, new User((long) 3, "CCC", "C@QQ.com"));
//    }
    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if(id == null){
            id = counter.incrementAndGet();
            user.setId(id);
        }
        this.userMap.put(id, user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        this.userMap.remove(id);
    }

    @Override
    public User getUserById(Long id) {
        return this.userMap.get(id);
    }
    @Override
    public List<User> listUsers() {
        return new ArrayList<>(this.userMap.values());
    }

 

}
