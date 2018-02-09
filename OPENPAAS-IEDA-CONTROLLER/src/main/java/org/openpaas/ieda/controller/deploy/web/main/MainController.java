package org.openpaas.ieda.controller.deploy.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 메인 화면을 호출하 이동
     * @title : goLayout
     * @return : String
    ***************************************************/
    @RequestMapping(value="/platform", method=RequestMethod.GET)
    public String goLayout(){ 
        return "/deploy/main/layout";
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 메인의 top 화면 호출
     * @title : goTop
     * @return : String
    ***************************************************/
    @RequestMapping(value="/top", method=RequestMethod.GET)
    public String goTop() {
        return "/deploy/main/top";
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 메인의 menu 화면 호출 
     * @title : goMenu
     * @return : String
    ***************************************************/
    @RequestMapping(value="/menu", method=RequestMethod.GET)
    public String goMenu() {
        return "/deploy/main/menu";
    }

}
