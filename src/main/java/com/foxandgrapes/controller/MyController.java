package com.foxandgrapes.controller;

import com.foxandgrapes.domain.Chat;
import com.foxandgrapes.domain.ChatAndUser;
import com.foxandgrapes.domain.User;
import com.foxandgrapes.service.ChatAndUserService;
import com.foxandgrapes.service.ChatService;
import com.foxandgrapes.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
public class MyController {

    @Resource
    private UserService userService;
    @Resource
    private ChatService chatService;
    @Resource
    private ChatAndUserService chatAndUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String view = "";
        if (userService.existence(user)) {
            view = "chating";
            HttpSession session = request.getSession();
            Integer userId = userService.getUserId(user);
            session.setAttribute("userId", userId);
            session.setAttribute("nowChatId", chatService.queryLastChatId());
            response.addCookie(new Cookie("userId", userId.toString()));
        } else {
            view = "login";
        }
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping("/to_register")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @RequestMapping("/check_name")
    @ResponseBody
    public String checkName(User user) {
        if (userService.existence(user.getName())) {
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestPart("imageFile") MultipartFile imageFile, User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        String view = "";
        if (!imageFile.isEmpty()) {
            user.setImage(user.getName() + ".jpg");
            String path = request.getSession().getServletContext().getRealPath("/static/images/" + user.getImage());
            imageFile.transferTo(new File(path));
        } else {
            user.setImage("default.jpg");
        }
        if (userService.addUser(user)) {
            view = "chating";
            HttpSession session = request.getSession();
            Integer userId = userService.getUserId(user);
            session.setAttribute("userId", userId);
            session.setAttribute("nowChatId", chatService.queryLastChatId());
            response.addCookie(new Cookie("userId", userId.toString()));
        } else {
            view = "register";
        }
        mv.setViewName(view);
        return mv;
    }

    // 拦截
    @RequestMapping("/refresh")
    @ResponseBody
    public List<ChatAndUser> refresh(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ChatAndUser> list = chatAndUserService.queryChatAndUser((Integer) session.getAttribute("nowChatId"));
        int len = list.size();
        if (len > 0) {
            session.setAttribute("nowChatId", list.get(len - 1).getId());
        }
        return list;
    }

    // 拦截
    @RequestMapping("/sendChat")
    @ResponseBody
    public String getChat(Chat chat, HttpServletRequest request) {
        chat.setUserId((Integer) request.getSession().getAttribute("userId"));
        if (chatService.addChat(chat)) {
            return "true";
        }
        return "false";
    }

    // 拦截
    @RequestMapping("/exit")
    public ModelAndView getChat(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 清楚登录信息
        session.removeAttribute("userId");
        session.removeAttribute("nowChatId");
        ModelAndView mv = new ModelAndView();
        // 转发
        mv.setViewName("forward:/index.jsp");
        return mv;
    }
}
