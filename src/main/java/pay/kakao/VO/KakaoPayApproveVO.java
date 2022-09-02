package pay.kakao.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
@Data
@Getter
public class KakaoPayApproveVO {
    private String aid,tid,cid, sid;
    private  String partner_order_id, partner_user_id, payment_method_type;
    private String item_name, item_code;
    private AmountVO amount;
    private CardVO card_info;
    private Integer quantity, tax_free_amount,vat_amount;
    private String created_at, approved_at;

}
