package xin.spgraceme.entity;

import java.io.Serializable;

public class Permission  implements Serializable {
    private String pid;

    private String parentId;

    private String pname;

    private String description;

    private String url;

    private String operation;

    private Byte level;

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {

        this.operation = operation == null ? null : operation.trim();
    }

    @Override
    public String toString() {
        return "Permission{" +
                "pid='" + pid + '\'' +
                ", parentId='" + parentId + '\'' +
                ", pname='" + pname + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", operation='" + operation + '\'' +
                ", level=" + level +
                '}';
    }
}