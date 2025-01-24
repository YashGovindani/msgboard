package com.yash.govindani.msgboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import com.google.gson.*;
import com.yash.govindani.msgboard.beans.*;
import java.io.*;
import java.util.*;
import com.yash.govindani.msgboard.dao.*;
import com.yash.govindani.msgboard.dto.*;

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

	@Bean
	@DependsOn("databaseBean")
	public MessageBoardBean getMessageBoardBean() {
		MessageBoardBean messageBoardBean = new MessageBoardBean();
		try {
			Vector<Branch> branches = BranchDAO.getAll();
			Vector<Semester> semesters = SemesterDAO.getAll();
			for(int i = 0; i < branches.size(); i++) {
				Branch branch = branches.get(i);
				BranchBean branchBean = new BranchBean();
				branchBean.setCode(branch.getCode());
				branchBean.setTitle(branch.getTitle());
				messageBoardBean.addBranch(branchBean);
			}
			for(int i = 0; i < semesters.size(); i++) {
				Semester semester = semesters.get(i);
				SemesterBean semesterBean = new SemesterBean();
				semesterBean.setCode(semester.getCode());
				semesterBean.setTitle(semester.getTitle());
				messageBoardBean.addSemester(semesterBean);
			}
		} catch(Exception exception) {
			System.out.println(exception.getMessage()); // later it should be logged somewhere
		}
		return messageBoardBean;
	}
}
