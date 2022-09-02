package pay.kakao.VO;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CartVO {
    String cid,partner_order_id, Partner_user_id,item_name,approval_url,cancel_url,fail_url;
    int CartNO,quantity, total_amount,tex_free_amount;
}
