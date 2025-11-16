package cn.orderservice.mapper;


import cn.orderservice.POJO.order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ordermapper {

    @Select("select * from tb_order where id=#{id}")
    order get(Integer id);

    //修改库存
    @Update("update tb_order set num = num-#{i} where id = #{id}")
    void buy(Integer id, int i);
}
