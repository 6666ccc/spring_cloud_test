package cn.orderservice.POJO;

import lombok.Data;

@Data
public class order {
    Integer id;
    Integer user_id;
    String name;
    Integer price;
    Integer num;
    user user;
}
