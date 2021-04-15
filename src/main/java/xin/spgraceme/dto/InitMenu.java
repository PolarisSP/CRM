package xin.spgraceme.dto;

import java.util.List;

public class InitMenu {

    private Menu homeInfo;

    private Menu logoInfo;

    private List<Menu> menuInfo;

    public Menu getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(Menu homeInfo) {
        this.homeInfo = homeInfo;
    }

    public Menu getLogoInfo() {
        return logoInfo;
    }

    public void setLogoInfo(Menu logoInfo) {
        this.logoInfo = logoInfo;
    }


    public List<Menu> getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(List<Menu> menuInfo) {
        this.menuInfo = menuInfo;
    }

    @Override
    public String toString() {
        return "InitMenu{" +
                "homeInfo=" + homeInfo +
                ", logoInfo=" + logoInfo +
                ", menuInfo=" + menuInfo +
                '}';
    }
}
