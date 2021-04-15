package xin.spgraceme.entity;

import java.io.Serializable;

public class Industry implements Serializable {
    private String iid;

    private String iname;

    private String description;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname == null ? null : iname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "Industry{" +
                "iid='" + iid + '\'' +
                ", iname='" + iname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
