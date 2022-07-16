package user;

public class UserError {

    private String userID;
    private String fullName;
    private String address;
    private String phone;
    private String password;
    private String confirm;
    private String roleID;

    public UserError(String userID, String fullName, String address, String phone, String password, String confirm, String roleID) {
        this.userID = userID;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.confirm = confirm;
        this.roleID = roleID;
    }

    public UserError() {
        this.userID = "";
        this.fullName = "";
        this.address = "";
        this.phone = "";
        this.password = "";
        this.confirm = "";
        this.roleID = "";
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

}
