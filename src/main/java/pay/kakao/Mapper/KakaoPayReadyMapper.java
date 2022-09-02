package pay.kakao.Mapper;

import org.apache.ibatis.annotations.Mapper;
import pay.kakao.VO.KakaoPayReadyVO;

@Mapper
public interface KakaoPayReadyMapper {

    int insertTest(KakaoPayReadyVO kakaoReady);//추가


}
