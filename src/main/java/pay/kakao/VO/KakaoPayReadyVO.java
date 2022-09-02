package pay.kakao.VO;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
public class KakaoPayReadyVO {


    private String tid, next_redirect_pc_url;
    private Date created_at;
}
