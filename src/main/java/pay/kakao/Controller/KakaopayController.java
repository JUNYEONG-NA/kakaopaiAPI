package pay.kakao.Controller;

import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pay.kakao.Service.KakaopayService;

import pay.kakao.VO.KakaoPayReadyVO;

@Controller
@Log
@RestController
@RequestMapping("/payment")
public class KakaopayController {
    @Setter(onMethod_ = @Autowired)
    private KakaopayService kakaoPay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet(){
    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(KakaoPayReadyVO kakaoPayReadyVO){
        log.info("kakaoPay post...................");

        return kakaoPay.kakaoPayReady();

    }


    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model){

        log.info("kakaoPaySuccess get....................");
        log.info("kakaoPaySuccess pg_token : "+ pg_token);

        model.addAttribute("info", kakaoPay.kakaoPayInfo(pg_token));

    }
    @GetMapping("/kakaoPayCancel")
    public void kakaoPayCancel(){

    }
    @GetMapping("/kakaoPaySuccessFail")
    public void kakaoPaySuccessFail(){
    }

}