package sales.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sales.api.dto.UserDTO;
import sales.api.service.UserService;

@Controller
@ResponseBody

public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/join")
    public String joinMember(UserDTO userDTO){
        System.out.println("UserID : "+ userDTO.getUserId() + " / UserNM : " + userDTO.getUserNm());
        String rsltMsg = userService.JoinMember(userDTO);
        System.out.println(">>>>>>>>>>>> joinMember Result >>>>>>>>> " + rsltMsg);
        String rslt = rsltMsg.substring(0, 1);
        String msg = rsltMsg.substring(2, rsltMsg.length());

//        System.out.println(rslt);
//        System.out.println(msg);
        return rsltMsg;
    }

    @PostMapping("/update")
    public String updateUserInfo(UserDTO userDTO){

        String rsltMsg = userService.updateUserInfo(userDTO);
        return rsltMsg;
    }

    @PostMapping("/login")
    public String userLogin(UserDTO userDTO){
        System.out.println("UserID : "+ userDTO.getUserId() + " / UserNM : " + userDTO.getUserNm());
//        System.out.println(">>>>>>>>>>>> login Result >>>>>>>>> " + rsltMsg);
//        String rslt = rsltMsg.substring(0, 1);
//        String msg = rsltMsg.substring(2, rsltMsg.length());
//
//        System.out.println(rslt);
//        System.out.println(msg);
        return "test";
    }

    @GetMapping("/login")
    public String userLogin2(UserDTO userDTO){
        System.out.println("UserID : "+ userDTO.getUserId() + " / UserNM : " + userDTO.getUserNm());
        return "test";
    }

}
