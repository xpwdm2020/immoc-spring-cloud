package cn.mrcode.imooc.spring.cloud.order.converter;

import cn.mrcode.imooc.spring.cloud.order.dataobject.OrderDetail;
import cn.mrcode.imooc.spring.cloud.order.dto.OrderDTO;
import cn.mrcode.imooc.spring.cloud.order.enums.ResultEnum;
import cn.mrcode.imooc.spring.cloud.order.exception.OrderException;
import cn.mrcode.imooc.spring.cloud.order.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-10 17:38
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                                            new TypeToken<List<OrderDetail>>() {
                                            }.getType()
            );
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
