package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {

    @RequestMapping({"/owners","/owner","/owner/index","/owner/index.html"})
    public String listOwner(){

        return "owners/index";
    }
}
