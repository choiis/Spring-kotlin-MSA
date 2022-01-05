package org.appkey.data;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table("sm01")
public class Sm01vo {

    @PrimaryKey
    private String appkey;

    private String userid;
    private int usertype;

    public Sm01vo() {
    }

    public Sm01vo(String appkey, String userid, int usertype) {
        this.appkey = appkey;
        this.userid = userid;
        this.usertype = usertype;
    }

    public String getAppkey() {
        return appkey;
    }

    public String getUserid() {
        return userid;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sm01vo sm01Vo = (Sm01vo) o;
        return usertype == sm01Vo.usertype && Objects.equals(appkey, sm01Vo.appkey) && Objects.equals(userid, sm01Vo.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appkey, userid, usertype);
    }

    @Override
    public String toString() {
        return "SM01Vo{" +
                "appkey='" + appkey + '\'' +
                ", userid='" + userid + '\'' +
                ", usertype=" + usertype +
                '}';
    }
}
