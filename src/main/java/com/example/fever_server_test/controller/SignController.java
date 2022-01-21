package com.example.fever_server_test.controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sign/")
public class SignController {
/*
    private final CustomMemberDetailService customMemberDetailService;

    private final CommonEncoder passwordEncoder = new CommonEncoder();

    private final JwtTokenProvider jwtTokenProvider;
    private final Status status;
    private final ResponseMessage message;

    @Autowired
    public SignController(ResponseMessage message, Status status, CustomMemberDetailService customMemberDetailService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.customMemberDetailService = customMemberDetailService;
//        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider  = jwtTokenProvider;
        this.message  = message;
        this.status =status;
    }

    // A02 : 로그인 -- Tony
    @PostMapping("signin")
    @ResponseBody
    public SignInRespDto signInUser(HttpServletRequest request, @RequestBody SignInReqDto signReqDto) {
        Member member = (Member) customMemberDetailService.findByEmail(signReqDto.getEmail());
        SignInRespDto signInRespDto = new SignInRespDto();

        try {
            if (passwordEncoder.matches(signReqDto.getPassword(), member.getPassword())) {
                System.out.println("비밀번호 일치");
                List<String > roleList = Arrays.asList(member.getRoles().split(","));
                signInRespDto.setResult("success");
                signInRespDto.setAccessToken(jwtTokenProvider.createToken(member.getEmail(), roleList)); // access token 만들기
                String tmpRefreshToken = jwtTokenProvider.createRefreshToken(member.getEmail());
                signInRespDto.setRefreshToken(tmpRefreshToken); // refresh token 만들기

                // 새롭게 DB에 refresh token 변경
                member.setRefreshToken(tmpRefreshToken);
                customMemberDetailService.save(member);

                return signInRespDto;
            }else {
                signInRespDto.setResult("fail");
                signInRespDto.setMessage(" ID or Password is invalid.");
                return signInRespDto;
            }
        }catch (NullPointerException e){
            signResultRepDto.setResult("fail");
            signResultRepDto.setMessage("NullPointerException");
            return signResultRepDto;
        }
    }


    // A01 : 회원가입 -- Tony
    @PostMapping("signup")
    @ResponseBody
    public SignResultRepDto addUser(HttpServletRequest request, @RequestBody UserJoinDto userJoinDto) {
        User user = new User(userJoinDto);

        user.setRoles("USER"); // 역할(권한) 부여
        user.setPassword(passwordEncoder.encode(userJoinDto.getPassword())); // 패스워드 암호화
        SignResultRepDto signResultRepDto = new SignResultRepDto();
        int result = customUserDetailService.signUpUser(user);
        if (result == 1) {
            signResultRepDto.setResult("success");
            signResultRepDto.setMessage("회원 가입 성공 !");
            return signResultRepDto;
        } else if (result == -1) {
            signResultRepDto.setResult("fail");
            signResultRepDto.setMessage("이메일이 존재합니다. 비밀번호 찾기를 진행해주세요 !");
            return signResultRepDto;
        } else {
            signResultRepDto.setResult("fail");
            signResultRepDto.setMessage("Ask system admin");
            return signResultRepDto;
        }

    }

    @PostMapping("reaccess")
    @ResponseBody
    public ResponseEntity refreshAccessToken(HttpServletRequest request, @RequestBody RefreshTokenReqDto refreshTokenReqDto) {

        HashMap<String, String> tokens = new HashMap<>();

        Optional<Member> isExist = customMemberDetailService.findById(refreshTokenReqDto.getUserId());
        if (isExist.isPresent()) { // 해당 유저 존재해야됨
            Member member = isExist.get();
            String userRefreshToken = member.getRefreshToken();
            String reqToken = refreshTokenReqDto.getRefreshToken();
            if (userRefreshToken != null && userRefreshToken.equals(reqToken)) { // refreshToken 유효해야
                List<String> roleList = Arrays.asList(member.getRoles().split(","));
                tokens.put("accesstoken", jwtTokenProvider.createToken(member.getEmail(), roleList));
                userRefreshToken = jwtTokenProvider.createRefreshToken(member.getEmail());
                tokens.put("refreshtoken", userRefreshToken);
                member.setRefreshToken(userRefreshToken); // refreshToken 업데이트

                customMemberDetailService.save(member); // 새롭게 refreshToken 업데이트 된 User DB에 업데이트
                return new ResponseEntity(DataResponse.response(status.SUCCESS,
                        "access token 재발급 " + message.SUCCESS, tokens), HttpStatus.OK);

            }
        }

        return new ResponseEntity(NoDataResponse.response(status.EXPIRED_TOKEN, message.EXPIRED_TOKEN), HttpStatus.OK);
    }
*/
}

