package com.seu8.nowcoder.community.dao;

import com.seu8.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;
@Mapper
public interface DiscussPostMapper {

    List<DiscussPost>selectDiscussPosts(int userId, int offset, int limit);

    //@Param for alias for parameter
    //necessary for if when there is only one Param
    int selectDiscussPostRows(@Param("userId")int userId);



}
