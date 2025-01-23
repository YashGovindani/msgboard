package com.yash.govindani.msgboard.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.yash.govindani.msgboard.beans.*;
import com.yash.govindani.msgboard.dao.*;
import com.yash.govindani.msgboard.dto.*;
import com.yash.govindani.msgboard.utils.*;

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
        try {
        DatabaseUtility.createTables(installationBean.getDriver(), installationBean.getConnectionString(), installationBean.getUsername(), installationBean.getPassword());
        } catch (DAOException exception) {
            System.out.println("Unable to create tables"); // add to logs
            return "InstallationFailed";
        }
        DAOConnection.setDriver(installationBean.getDriver());
        DAOConnection.setConnectionString(installationBean.getConnectionString());
        DAOConnection.setUsername(installationBean.getUsername());
        DAOConnection.setPassword(installationBean.getPassword());
        try {
            Administrator administrator = new Administrator();
            administrator.setUsername(installationBean.getUsername());
            String passwordKey = EncryptionUtility.getKey();
            String encryptedPassword = EncryptionUtility.encrypt(installationBean.getPassword(), passwordKey);
            administrator.setPassword(encryptedPassword);
            administrator.setPasswordKey(passwordKey);
            AdministratorDAO.add(administrator);
            return "InstallationSuccessful";
        } catch (DAOException exception) {
            System.out.println(exception.getMessage()); //  add to logs
            return "InstallationFailed";
        } catch (Exception exception) {
            System.out.println(exception.getMessage()); // add to logs
            return "InstallationFailed";
        }
    }
}
