package org.appkey.data;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.*;

@Table("sm01")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserAppkey {

    @PrimaryKey
    private String appkey;

    private String userid;
    private int usertype;


}
