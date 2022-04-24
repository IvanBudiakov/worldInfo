package data.entities;

public class User {
    Integer id;
    String username;
    String enc_pwd;

    public String getUserName() {
        return username;
    }

    public void setUserName(String name) {
        username = name;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }

    
    public void setPwd(String newPwd){
        this.enc_pwd = newPwd;
    }
    
    public String getPwd(){
        return enc_pwd;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", enc_pwd=" + enc_pwd + "]";
    }
}
