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
@RequestMapping("/branch")
public class BranchMaster {
    @Autowired
    MessageBoardBean messageBoardBean;
    @PostMapping("/add")
    public ActionResponse<Integer> add(BranchBean branchBean) {
        ActionResponse<Integer> actionResponse = new ActionResponse<>();
        try {
            Branch branch = new Branch();
            branch.setTitle(branchBean.getTitle());
            int code = BranchDAO.add(branch);
            actionResponse.setSuccess(true);
            actionResponse.setValue(code);
        } catch(DAOException exception) {
            actionResponse.setSuccess(false);
            actionResponse.setError(exception.getMessage());
        }
        return actionResponse;
    }
    @PostMapping("/update")
    public ActionResponse<Integer> update(BranchBean branchBean) {
        ActionResponse<Integer> actionResponse = new ActionResponse<>();
        try {
            Branch branch = new Branch();
            branch.setCode(branchBean.getCode());
            branch.setTitle(branchBean.getTitle());
            BranchDAO.update(branch);
            actionResponse.setSuccess(true);
            actionResponse.setValue(branch.getCode());
        } catch(DAOException exception) {
            actionResponse.setSuccess(false);
            actionResponse.setError(exception.getMessage());
        }
        return actionResponse;
    }
    @PostMapping("/delete")
    public ActionResponse<String> delete(BranchBean branchBean) {
        ActionResponse<String> actionResponse = new ActionResponse<>();
        try {
            Branch branch = new Branch();
            branch.setCode(branchBean.getCode());
            BranchDAO.delete(branch);
            actionResponse.setSuccess(true);
        } catch(DAOException exception) {
            actionResponse.setSuccess(false);
            actionResponse.setError(exception.getMessage());
        }
        return actionResponse;
    }
    @PostMapping("/getAll")
    public ActionResponse<List<BranchBean>> getAll() {
        ActionResponse<List<BranchBean>> actionResponse = new ActionResponse<>();
        try {
            List<Branch> branches = BranchDAO.getAll();
            List<BranchBean> branchBeans = new Vector<>();
            for(int i = 0; i < branches.size(); i++) {
                Branch branch = branches.get(i);
				BranchBean branchBean = new BranchBean();
				branchBean.setCode(branch.getCode());
				branchBean.setTitle(branch.getTitle());
				branchBeans.add(branchBean);
            }
            actionResponse.setSuccess(true);
            actionResponse.setValue(branchBeans);
        } catch(DAOException exception) {
            actionResponse.setSuccess(false);
            actionResponse.setError(exception.getMessage());
        }
        return actionResponse;
    }
}