package com.example.springRedis.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
//    HashMap<String, String> sessionMap = new HashMap<>();

    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
//        sessionMap.put(session.getId(), name);
        session.setAttribute("name", name);
        return "saved";
    }

    @GetMapping("/authorization")
    public String authorization(HttpSession session) {
//        return sessionMap.get(session.getId());
        String myName = (String) session.getAttribute("name");
        return myName;
    }
}
