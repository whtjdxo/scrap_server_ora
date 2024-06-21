package sales.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sales.api.dto.UserDTO;
import sales.api.entity.TscUser;
import sales.api.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String JoinMember(UserDTO userDTO){
        String userId = userDTO.getUserId();
        System.out.println(userId);
        boolean dupChk = userRepository.existsById(userId);
        System.out.println(dupChk);
        if(dupChk){
            return "0, Duplicate Member ID";
        }

        String userNm = userDTO.getUserNm();
        String userPwd = bCryptPasswordEncoder.encode(userDTO.getUserPwd());
        String userAuth = "ROLE_ADMIN";     // joinDTO.getUserAuth();

        TscUser userData = new TscUser();
        userData.setUserId(userId);
        userData.setUserNm(userNm);
        userData.setUserPwd(userPwd);
        userData.setUserAuth(userAuth);
        try {
            userRepository.save(userData);
            System.out.println("사용자가 성공적으로 저장되었습니다.");
            return "1, Hola~!";
        } catch (Exception e) {
            System.out.println("0, Error: " + e.getMessage());
            return "0, Error : " + e.getMessage();
        }

    }

    public String updateUserInfo(UserDTO userDTO){
        String userId = userDTO.getUserId();
        System.out.println(userId);
        boolean dupChk = userRepository.existsById(userId);
        System.out.println(dupChk);
        if(!dupChk){
            return "0, Check User ID";
        }

        String userNm = userDTO.getUserNm();
        String userPwd = bCryptPasswordEncoder.encode(userDTO.getUserPwd());
        String userAuth = "ROLE_ADMIN";     // joinDTO.getUserAuth();

        TscUser userData = new TscUser();
        userData.setUserId(userId);
        userData.setUserNm(userNm);
        userData.setUserPwd(userPwd);
        userData.setUserAuth(userAuth);
        try {
            userRepository.save(userData);
            System.out.println("Update User Info Complete.");
            return "1, sucess!";
        } catch (Exception e) {
//            System.out.println("0, Error: " + e.getMessage());
            return "0, Error : " + e.getMessage();
        }
    }
}
