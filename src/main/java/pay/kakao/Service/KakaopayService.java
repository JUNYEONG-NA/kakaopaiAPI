package pay.kakao.Service;

import lombok.extern.java.Log;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pay.kakao.Mapper.CartMapper;
import pay.kakao.Mapper.KakaoApproveMapper;
import pay.kakao.Mapper.KakaoPayReadyMapper;
import pay.kakao.VO.KakaoPayApproveVO;
import pay.kakao.VO.KakaoPayReadyVO;

@Service
@Log
public class KakaopayService {

    @Autowired
    private KakaoPayReadyMapper kakaoReadyMapper;
    @Autowired
    private KakaoApproveMapper kakaoApproveMapper;
    @Autowired
    private CartMapper cartMapper;

    private static final String HOST = "https://kapi.kakao.com";
    private KakaoPayApproveVO kakaoPayApproveVO;
    private KakaoPayReadyVO kakaoPayReadyVO;

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + "6944c4771096e7e9cc99cd537774e037");
        headers.set("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return headers;
    }

        String partner_order_id = "10005";
    public String kakaoPayReady() {

        // 서버로 요청할 Body

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");            // 필수값 - 가맹점코드
        params.add("partner_order_id", partner_order_id);    // 필수값 - 가맹점 주문번호
        params.add("partner_user_id", cartMapper.getUserId(partner_order_id));    // 필수값 - 가맹점 회원 id
        params.add("item_name", cartMapper.getItem(partner_order_id));      // 필수값 - 상품이름
        params.add("quantity", cartMapper.getQuantity(partner_order_id));                // 필수값 - 상품수량
        params.add("total_amount", cartMapper.getAmount(partner_order_id));       // 필수값 - 총 금액
        params.add("tax_free_amount", "100");       // 필수값 - 면세금액
        params.add("approval_url", "http://localhost:8080/payment/kakaoPaySuccess");    // 필수값 - 결제성공시 연결 url
        params.add("cancel_url", "http://localhost:8080/payment/kakaoPayCancel");       // 필수값 - 결제 취소
        params.add("fail_url", "http://localhost:8080/payment/kakaoPaySuccessFail");    // 필수값 - 결제 실패


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, this.getHeaders());

        String url = "https://kapi.kakao.com/v1/payment/ready";


        kakaoPayReadyVO = restTemplate.postForObject(url, body, KakaoPayReadyVO.class);
        JSONObject jsonObject = new JSONObject(kakaoPayReadyVO);

        kakaoReadyMapper.insertTest(kakaoPayReadyVO);
        log.info("--------------------------------------------------------------------------------------");
        log.info("결제준비 요청 바디 : " + body);
//        log.info("json type return : " + jsonObject);
        log.info("결제준비 정보 : " + kakaoPayReadyVO);
        log.info("--------------------------------------------------------------------------------------");


        return kakaoPayReadyVO.getNext_redirect_pc_url();

    }

    public KakaoPayApproveVO kakaoPayInfo(String pg_token) {
        log.info("kakaoPayInfoVo................");
        log.info("--------------------------------------------------------------------------------------");

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", partner_order_id);
        params.add("partner_user_id", cartMapper.getUserId(partner_order_id));
        params.add("pg_token", pg_token);
        params.add("total_amount", cartMapper.getAmount(partner_order_id));


        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, this.getHeaders());
        String url = "https://kapi.kakao.com/v1/payment/approve";

        kakaoPayApproveVO = restTemplate.postForObject(url, body, KakaoPayApproveVO.class);
        log.info("--------------------------------------------------------------------------------------");
        log.info("결제 승인 요청 바디 : " + body);
        log.info("결제 승인 정보 : " + kakaoPayApproveVO);
        log.info("--------------------------------------------------------------------------------------");

        kakaoApproveMapper.insertApprove(kakaoPayApproveVO);
        return kakaoPayApproveVO;


    }
}
