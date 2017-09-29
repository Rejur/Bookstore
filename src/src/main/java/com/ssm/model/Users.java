package com.ssm.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Users")
public class Users {


    public Users() {}
    public Users(Users user) {
        this.id = user.getId();
        this.name = user.getName();
        this.loginid = user.getLoginid();
        this.loginpwd = user.getLoginpwd();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.mail = user.getMail();
        this.birthday = user.getBirthday();
        this.userroleid = user.getUserroleid();
        this.userstateid = user.getUserstateid();
        this.registerip = user.getRegisterip();
        this.registertime = user.getRegistertime();
    }

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LoginId")
    private String loginid;

    @Column(name = "LoginPwd")
    private String loginpwd;

    @Column(name = "Name")
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Mail")
    private String mail;

    @Column(name = "Birthday")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @Column(name = "UserRoleId")
    private Integer userroleid;

    @Column(name = "UserStateId")
    private Integer userstateid;

    @Column(name = "RegisterIp")
    private String registerip;

    @Column(name = "RegisterTime")
    private Date registertime;

    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return LoginId
     */
    public String getLoginid() {
        return loginid;
    }

    /**
     * @param loginid
     */
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    /**
     * @return LoginPwd
     */
    public String getLoginpwd() {
        return loginpwd;
    }

    /**
     * @param loginpwd
     */
    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return Phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return Mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return Birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return UserRoleId
     */
    public Integer getUserroleid() {
        return userroleid;
    }

    /**
     * @param userroleid
     */
    public void setUserroleid(Integer userroleid) {
        this.userroleid = userroleid;
    }

    /**
     * @return UserStateId
     */
    public Integer getUserstateid() {
        return userstateid;
    }

    /**
     * @param userstateid
     */
    public void setUserstateid(Integer userstateid) {
        this.userstateid = userstateid;
    }

    /**
     * @return RegisterIp
     */
    public String getRegisterip() {
        return registerip;
    }

    /**
     * @param registerip
     */
    public void setRegisterip(String registerip) {
        this.registerip = registerip;
    }

    /**
     * @return RegisterTime
     */
    public Date getRegistertime() {
        return registertime;
    }

    /**
     * @param registertime
     */
    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }
}