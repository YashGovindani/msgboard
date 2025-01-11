package com.yash.govindani.msgboard.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.yash.govindani.msgboard.beans.*;

@Controller
public class Administration {
    @Autowired
    DatabaseBean databaseBean;
    @GetMapping("/admin")
    public String adminIndex() {
        if(databaseBean.getDriver() == null) return "Installer";
        return "AdminIndex";
    }
}
