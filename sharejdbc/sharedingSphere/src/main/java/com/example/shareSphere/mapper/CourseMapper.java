package com.example.shareSphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shareSphere.entity.Course;
import org.apache.ibatis.annotations.Mapper;
//@Repository

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}
