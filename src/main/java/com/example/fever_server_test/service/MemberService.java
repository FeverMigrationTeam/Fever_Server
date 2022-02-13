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

    public UserId verifyUser(String loginType, String token) throws Exception {
        UserId userId;
        if (!loginType.equals("NORMAL")) {
            SocialLoginType socialLoginType = SocialLoginType.valueOf(loginType);
            String body = oauthService.requestSocialData(socialLoginType, token);
            JSONObject jsonObject = new JSONObject(body);
            userId = new UserId(loginType, jsonObject.get("id"));
            Member member = memberRepository.findByUserSocialIdx(userId.getValue())
                    .orElseThrow(Exception::new);
            if (member.getUserSocialIdx().equals(userId.getValue())) return userId;
            throw new Exception("UNAUTHORIZED");
        } else { userId = new UserId(loginType, 0); }

        return userId;
    }
}
