package pay.kakao.Mapper;


import org.apache.ibatis.annotations.Mapper;
import pay.kakao.VO.CartVO;

import java.util.List;

@Mapper
public interface CartMapper {

    List<CartVO> cart();

    String getItem(String partner_order_id);
    String getAmount(String partner_order_id);
    String getQuantity(String partner_order_id);
    String getUserId(String partner_Order_id);


}
