package com.yash.govindani.msgboard.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.yash.govindani.msgboard.beans.*;
import com.yash.govindani.msgboard.ActionResponse;
import com.yash.govindani.msgboard.dao.*;
import com.yash.govindani.msgboard.dto.*;
import java.util.*;

@RestController
@RequestMapping("/semester")
public class SemesterMaster {
    @Autowired
    MessageBoardBean messageBoardBean;
    @PostMapping("/add")
    public ActionResponse<Integer> add(SemesterBean semesterBean) {
        ActionResponse<Integer> actionResponse = new ActionResponse<>();
        try {
            Semester semester = new Semester();
            semester.setTitle(semesterBean.getTitle());
            int code = SemesterDAO.add(semester);
            actionResponse.setSuccess(true);
            actionResponse.setValue(code);
        } catch(DAOException exception) {
            actionResponse.setSuccess(false);
            actionResponse.setError(exception.getMessage());
        }
        return actionResponse;
    }
    @PostMapping("/update")
    public ActionResponse<Integer> update(SemesterBean semesterBean) {
        ActionResponse<Integer> actionResponse = new ActionResponse<>();
        try {
            Semester semester = new Semester();
            semester.setCode(semesterBean.getCode());
            semester.setTitle(semesterBean.getTitle());
            SemesterDAO.update(semester);
            actionResponse.setSuccess(true);
            actionResponse.setValue(semester.getCode());
        } catch(DAOException exception) {
            actionResponse.setSuccess(false);
            actionResponse.setError(exception.getMessage());
        }
        return actionResponse;
    }
    @PostMapping("/delete")
    public ActionResponse<String> delete(SemesterBean semesterBean) {
        ActionResponse<String> actionResponse = new ActionResponse<>();
        try {
            Semester semester = new Semester();
            semester.setCode(semesterBean.getCode());
            SemesterDAO.delete(semester);
            actionResponse.setSuccess(true);
        } catch(DAOException exception) {
            actionResponse.setSuccess(false);
            actionResponse.setError(exception.getMessage());
        }
        return actionResponse;
    }
    @PostMapping("/getAll")
    public ActionResponse<List<SemesterBean>> getAll() {
        ActionResponse<List<SemesterBean>> actionResponse = new ActionResponse<>();
        try {
            List<Semester> semesters = SemesterDAO.getAll();
            List<SemesterBean> semesterBeans = new Vector<>();
            for(int i = 0; i < semesters.size(); i++) {
                Semester semester = semesters.get(i);
				SemesterBean semesterBean = new SemesterBean();
				semesterBean.setCode(semester.getCode());
				semesterBean.setTitle(semester.getTitle());
				semesterBeans.add(semesterBean);
            }
            actionResponse.setSuccess(true);
            actionResponse.setValue(semesterBeans);
        } catch(DAOException exception) {
            actionResponse.setSuccess(false);
            actionResponse.setError(exception.getMessage());
        }
        return actionResponse;
    }
}