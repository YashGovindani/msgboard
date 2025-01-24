package com.yash.govindani.msgboard.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.yash.govindani.msgboard.beans.*;
import com.yash.govindani.msgboard.dao.*;
import com.yash.govindani.msgboard.dto.*;
import com.yash.govindani.msgboard.utils.*;
import java.io.*;
import com.google.gson.*;

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
            DAOConnection.setDriver(installationBean.getDriver());
            DAOConnection.setConnectionString(installationBean.getConnectionString());
            DAOConnection.setUsername(installationBean.getUsername());
            DAOConnection.setPassword(installationBean.getPassword());
            databaseBean.setDriver(installationBean.getDriver());
            databaseBean.setConnectionString(installationBean.getConnectionString());
            databaseBean.setUsername(installationBean.getUsername());
            databaseBean.setPassword(installationBean.getPassword());
            Gson gson = new Gson();
            String jsonString = gson.toJson(databaseBean);
            File file = new File("conf");
            file.mkdir();
            file = new File("conf" + File.separator + "db.json");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonString);
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
        } catch (IOException exception) {
            System.out.println(exception.getMessage()); //  add to logs
            return "InstallationFailed";
        } catch (Exception exception) {
            System.out.println(exception.getMessage()); // add to logs
            return "InstallationFailed";
        }
    }
}
