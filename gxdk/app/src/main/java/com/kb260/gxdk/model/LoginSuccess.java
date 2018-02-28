package com.kb260.gxdk.model;

/**
 * @author KB260
 *         Created on  2017/11/1
 */

public class LoginSuccess {

    /**
     * id : 14
     * type : 0
     * username : 14727002600
     * token : 5f61ec21c22a4dd689b802025881db8b
     */

    private int id;
    private String type;
    private String username;
    private String token;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
