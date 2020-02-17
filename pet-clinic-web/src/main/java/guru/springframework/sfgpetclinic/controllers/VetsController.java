package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/vet","/vets"})
@Controller
public class VetsController {

    @RequestMapping({"","/vet","/index","/index.html"})
    public String listVets(){

        return "vets/index";

    }
}
