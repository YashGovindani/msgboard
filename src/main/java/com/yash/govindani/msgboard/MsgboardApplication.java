package com.yash.govindani.msgboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import com.google.gson.*;
import com.yash.govindani.msgboard.beans.*;
import java.io.*;

@SpringBootApplication
public class MsgboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgboardApplication.class, args);
	}
	
	@Bean
	public DatabaseBean getDatabaseBean() {
		DatabaseBean databaseBean = null;
		try {
			File file = new File("conf" + File.separator + "db.json");
			if(file.exists()) {
				Gson gson = new Gson();
				databaseBean = gson.fromJson(new FileReader(file.getAbsolutePath()), DatabaseBean.class);
			} else {
				databaseBean = new DatabaseBean();
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage()); // later it should be logged somewhere
		}
		return databaseBean;
	}

}
