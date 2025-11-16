package cn.UserService.mapper;


import cn.UserService.POJO.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface userMapper {


    @Select("select * from tb_user where id=#{id}")
    user get(Integer id);

    @Update("update tb_user set username = #{username} where id = #{id}")
    void update(@Param("username") String username, @Param("id") Integer id);

}
