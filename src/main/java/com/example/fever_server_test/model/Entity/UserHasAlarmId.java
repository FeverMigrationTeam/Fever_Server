package com.example.fever_server_test.model.Entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class UserHasAlarmId implements Serializable {
//    @JoinColumn(referencedColumnName = "id", name = "dog_id")

    @OneToOne
//    @JoinColumn(referencedColumnName = "userIdx",name = "user_user_idx") // referencedColumnName : db table이 아닌 Entity 상의 칼럼 이름
    @JoinColumn(name = "user_idx")
    private Member userUserIdx;

    @OneToOne
//    @JoinColumn(referencedColumnName = "alarmIdx",name = "alram_alram_idx")
    @JoinColumn(name = "alram_idx")
    private Alarm alarmIdx;

}
