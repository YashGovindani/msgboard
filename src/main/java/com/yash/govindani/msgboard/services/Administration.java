package com.yash.govindani.msgboard.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/install")
    public String installMessageBoard(InstallationBean installationBean) {
        System.out.println(installationBean.getDriver());
        System.out.println(installationBean.getConnectionString());
        System.out.println(installationBean.getUsername());
        System.out.println(installationBean.getPassword());
        System.out.println(installationBean.getAdministratorUsername());
        System.out.println(installationBean.getAdministratorPassword());
        // You need to write code to get tables created 
        // If created, then set driver etc. in DAOConnection
        // call add of AdministratorDAO
        // if all is well return InstallationSuccessful
        return "InstallationFailed";
    }
}
