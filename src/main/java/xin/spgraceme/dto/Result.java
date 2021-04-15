package xin.spgraceme.dto;

/**
 * @author 沈鹏
 * @date 2020/5/19 -19:34
 */
public class Result<T> {

    private int state;
    private String msg;
    private T data;

    public static final int OK = 0;
    public static final int ERROR =1;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
