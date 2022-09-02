package pay.kakao.Mapper;

import org.apache.ibatis.annotations.Mapper;
import pay.kakao.VO.KakaoPayApproveVO;
@Mapper
public interface KakaoApproveMapper {
    int insertApprove(KakaoPayApproveVO kakaoApprove);//추가
}
