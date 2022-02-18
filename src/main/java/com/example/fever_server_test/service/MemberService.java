package com.example.fever_server_test.service;

import com.example.fever_server_test.model.Entity.Equipment;
import com.example.fever_server_test.objects.UserId;
import com.example.fever_server_test.model.Entity.Member;
import com.example.fever_server_test.repository.EquipmentRepository;
import com.example.fever_server_test.repository.MemberRepository;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import com.example.fever_server_test.social.SocialLoginType;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final Status status;
    private final ResponseMessage message;
    private final MemberRepository memberRepository;

    @Autowired
    private OauthService oauthService;

    // 2.18 수정: return값을 UserId가 아닌 Long type userIdx로 반환
    public Long verifyUser(String loginType, String token) throws Exception {
        UserId userId;
        if (!loginType.equals("NORMAL")) {
            SocialLoginType socialLoginType = SocialLoginType.valueOf(loginType);
            String body = oauthService.requestSocialData(socialLoginType, token);
            JSONObject jsonObject = new JSONObject(body);
            userId = new UserId(loginType, jsonObject.get("id"));
            Member member = memberRepository.findByUserSocialIdx(userId.getValue())
                    .orElseThrow(Exception::new);
            if (member.getUserSocialIdx().equals(userId.getValue())) return member.getUserIdx();
            throw new Exception("UNAUTHORIZED");
        }
        // 요건 추후에 spring security로 자체 로그인 토큰 생성 시 사용할 예정
        else { throw new Exception("Not verify userIdx yet"); }
    }
}
