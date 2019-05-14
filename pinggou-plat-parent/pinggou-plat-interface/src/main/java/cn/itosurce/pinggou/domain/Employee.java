package cn.itosurce.pinggou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zb
 * @since 2019-05-14
 */
@TableName("t_employee")
public class Employee implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    @TableField("realName")
    private String realName;

    @TableField("cardId")
    private String cardId;

    /**
     * 关联部门
     */
    @TableField("departmentId")
    private Long departmentId;

    private LocalDateTime hiredate;

    private Long phone;

    /**
     * 关联数据字典
     */
    private String education;

    private String address;

    /**
     * 关联数据字典
     */
    private String gender;

    private LocalDateTime birthday;

    private String remarks;

    /**
     * 0表示在职，-1表示离职
     */
    private Integer status;

    /**
     * 微信账号的标识
     */
    private String unionid;

    /**
     * github账号唯一标识
     */
    private String nodeid;


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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDateTime getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDateTime hiredate) {
        this.hiredate = hiredate;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    @Override
    public String toString() {
        return "Employee{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", realName=" + realName +
        ", cardId=" + cardId +
        ", departmentId=" + departmentId +
        ", hiredate=" + hiredate +
        ", phone=" + phone +
        ", education=" + education +
        ", address=" + address +
        ", gender=" + gender +
        ", birthday=" + birthday +
        ", remarks=" + remarks +
        ", status=" + status +
        ", unionid=" + unionid +
        ", nodeid=" + nodeid +
        "}";
    }
}
