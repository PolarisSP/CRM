package xin.spgraceme.entity;

import java.io.Serializable;
import java.util.Date;

public class Visit implements Serializable {
    private String vid;

    private String uid;

    private String cid;

    private Date visittime;

    private Date endtime;

    private String record;

    private User user;

    private Customer customer;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid == null ? null : vid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public Date getVisittime() {
        return visittime;
    }

    public void setVisittime(Date visittime) {
        this.visittime = visittime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    @Override
    public String toString() {
        return "Visit{" +
                "vid='" + vid + '\'' +
                ", uid='" + uid + '\'' +
                ", cid='" + cid + '\'' +
                ", visittime=" + visittime +
                ", endtime=" + endtime +
                ", record='" + record + '\'' +
                ", user=" + user +
                ", customer=" + customer +
                '}';
    }
}