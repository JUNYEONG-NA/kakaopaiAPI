package pay.kakao.VO;

import lombok.Data;

@Data // @Getter, Setter, ToString, EqualAndHashCode, RequireArgsConstructor 을 합쳐놓은 종합선물세트
public class AmountVO {
    private Integer total,tax_free,vat,point,discount;
}
