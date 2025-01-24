package com.yash.govindani.msgboard.beans;

import java.util.HashMap;

public class MessageBoardBean implements java.io.Serializable {
    private HashMap<Integer, BranchBean> branchMap;
    private HashMap<Integer,SemesterBean> semesterMap;
    public MessageBoardBean() {
        this.branchMap = new HashMap<>();
        this.semesterMap = new HashMap<>();
    }
    public void addBranch(BranchBean branchBean) {
        this.branchMap.put(branchBean.getCode(), branchBean);
    }
    public void addSemester(SemesterBean semesterBean) {
        this.semesterMap.put(semesterBean.getCode(), semesterBean);
    }
}