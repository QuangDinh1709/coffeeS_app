package gvn.project311.coffeeS;

/**
 * Created by admin on 10/5/17.
 */

public class MenuModel {

    private String id = "";
    private String name;
    private String price;
    private boolean special = false;
    private int orderMenu;
    private boolean is_vaild = true;

    public MenuModel() {
    }

    public MenuModel(String id, String name, String price, boolean special, int orderMenu) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.special = special;
        this.orderMenu = orderMenu;
        this.is_vaild = true;
    }

    public MenuModel(int orderMenu) {
        this.orderMenu = orderMenu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public int getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(int orderMenu) {
        this.orderMenu = orderMenu;
    }

    public void setIs_vaild(boolean is_vaild) {
        this.is_vaild = is_vaild;
    }
}
