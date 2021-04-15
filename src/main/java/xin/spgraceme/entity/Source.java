package xin.spgraceme.entity;

import java.io.Serializable;

public class Source implements Serializable {
    private String sid;

    private String sname;

    private String description;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "Source{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}