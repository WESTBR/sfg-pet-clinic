package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/owner","/owners"})
@Controller
public class OwnerController {

    @RequestMapping({"","/","/index","/index.html"})
    public String listOwner(){

        return "owners/index";
    }
}
