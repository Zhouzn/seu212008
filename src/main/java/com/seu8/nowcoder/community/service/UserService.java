package com.seu8.nowcoder.community.service;

import com.seu8.nowcoder.community.dao.UserMapper;
import com.seu8.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
