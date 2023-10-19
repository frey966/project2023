package com.example.shareSphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shareSphere.entity.Course;
import com.example.shareSphere.entity.Udict;
import com.example.shareSphere.entity.User;
import com.example.shareSphere.mapper.CourseMapper;
import com.example.shareSphere.mapper.UdictMapper;
import com.example.shareSphere.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharedingJdbcTest {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UdictMapper udictMapper;
//---------水平分表-------------------------------
    /**
     * demo1
     * 添加课程方法
     */
    //@Test
    public void addCourse(){
        for (int i = 0; i <11 ; i++) {
            Course course = new Course();
            course.setCname("java-bb"+i);
            course.setUserId(100L);
            course.setCstatus("Normal");
            courseMapper.insert(course);
        }
    }
    //@Test
    public void findCourse(){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",921060593836752896l);
        Course course = courseMapper.selectOne(queryWrapper);
        System.out.println("course="+course);
    }
    //----------水平分库-----------------------------------------
   // @Test
    public void addCourseDb(){
        for (int i = 0; i <11 ; i++) {
            Course course = new Course();
            course.setCname("java-cc"+i);
            course.setUserId(101L);
            course.setCstatus("Normal");
            courseMapper.insert(course);
        }
    }

   // @Test
    public void findCourseDb(){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",921107087025504257l);
        queryWrapper.eq("user_id",100l);
        Course course = courseMapper.selectOne(queryWrapper);
        System.out.println("course="+course);
    }
    //-----------测试垂直分库--------------------------------------------------------------
    //@Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("lucymary");
        user.setUstatus("100");
        userMapper.insert(user);
    }

    //查询操作
    //@Test
    public void findUserDb() {
        QueryWrapper<User>  wrapper = new QueryWrapper<>();
        //设置userid值
        wrapper.eq("user_id",921334696351105025L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    //======================测试公共表===================
    //添加操作
    @Test
    public void addDict() {
        Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
    }

    //删除操作
    @Test
    public void deleteDict() {
        QueryWrapper<Udict>  wrapper = new QueryWrapper<>();
        //设置userid值
        wrapper.eq("dictid",921392094948360193l);
        udictMapper.delete(wrapper);
    }
}
