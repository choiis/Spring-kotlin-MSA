package org.api.data;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserAppkey {

    private String appkey;

    private String userid;
    private int usertype;

    @Override
    public String toString() {
        return "UserAppkey{" +
                "appkey='" + appkey + '\'' +
                ", userid='" + userid + '\'' +
                ", usertype=" + usertype +
                '}';
    }
}
