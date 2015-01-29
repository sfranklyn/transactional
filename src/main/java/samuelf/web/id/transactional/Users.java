/*
 * Copyright 2013 Samuel Franklyn <sfranklyn@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package samuelf.web.id.transactional;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Samuel Franklyn <sfranklyn@gmail.com>
 */
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_name"})})
@NamedQueries({
    @NamedQuery(name = "Users.selectByUserName",
            query = "SELECT u FROM Users u WHERE u.userName = :userName")
})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_password", nullable = false, length = 128)
    private String userPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_full_name", nullable = false, length = 200)
    private String userFullName;
    @Column(name = "user_phone", length = 50)
    private String userPhone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_version", nullable = false)
    @Version
    private int userVersion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_last_pwd_change", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date userLastPwdChange;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserVersion() {
        return userVersion;
    }

    public void setUserVersion(int userVersion) {
        this.userVersion = userVersion;
    }

    public Date getUserLastPwdChange() {
        return userLastPwdChange;
    }

    public void setUserLastPwdChange(Date userLastPwdChange) {
        this.userLastPwdChange = userLastPwdChange;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return (this.userId != null || other.userId == null) && (this.userId == null || this.userId.equals(other.userId));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
