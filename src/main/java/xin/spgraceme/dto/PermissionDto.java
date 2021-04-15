package xin.spgraceme.dto;

import java.util.List;

public class PermissionDto {

    private String id;

    private String title;

    private String field;

    private boolean checked;

    private boolean spread = true;

    private List<PermissionDto> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<PermissionDto> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PermissionDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", field='" + field + '\'' +
                ", checked=" + checked +
                ", spread=" + spread +
                ", children=" + children +
                '}';
    }
}
