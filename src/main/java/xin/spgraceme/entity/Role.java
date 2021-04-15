package xin.spgraceme.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private String rid;

    private String rname;

    private String description;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid='" + rid + '\'' +
                ", rname='" + rname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}