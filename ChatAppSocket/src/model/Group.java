/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Hi
 */
public class Group implements Serializable{
    private static final long serialVersionUID = 2L;
    private int groupId;
    private String nameGroup;
    private ArrayList<Member> listMembers;
    private ArrayList<User> listUser;

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }
    private ArrayList<Message> listMessages;

    public Group() {
    }

    public Group(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Group(int groupId, String nameGroup) {
        this.groupId = groupId;
        this.nameGroup = nameGroup;
    }
    
    public Group(int groupId, String nameGroup, ArrayList<Member> listMembers) {
        this.groupId = groupId;
        this.nameGroup = nameGroup;
        this.listMembers = listMembers;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setListMessages(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public ArrayList<Member> getListMembers() {
        return listMembers;
    }

    @Override
    public String toString() {
        return "Group{" + "groupId=" + groupId + ", nameGroup=" + nameGroup + '}';
    }

    public void setListMembers(ArrayList<Member> listMembers) {
        this.listMembers = listMembers;
    }

}
