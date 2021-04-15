package xin.spgraceme.dto;

public class RoleDto {

    private String id;

    private String field = "";

    private boolean checked;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "RoleDto{" +
                "id='" + id + '\'' +
                ", field='" + field + '\'' +
                ", checked=" + checked +
                ", title='" + title + '\'' +
                '}';
    }
}
