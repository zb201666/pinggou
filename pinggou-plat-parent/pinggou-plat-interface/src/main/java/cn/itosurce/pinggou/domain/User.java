package cn.itosurce.pinggou.domain;

/**
 * @author zb
 * @version 1.0
 * @classname User
 * @description 用户实体类
 * @date 2019/5/11
 */
public class User {

    private Long id;
    private String username;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
