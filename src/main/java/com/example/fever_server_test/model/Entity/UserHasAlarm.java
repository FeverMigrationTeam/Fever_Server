package com.example.fever_server_test.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_has_alram")
@IdClass(UserHasAlarmId.class)
public class UserHasAlarm {


    @Id
    @OneToOne
//    @JoinColumn(referencedColumnName = "userIdx",name = "user_user_idx")
    @JoinColumn(name = "user_idx")
    private Member userUserIdx;

    @Id
    @OneToOne
//    @JoinColumn(referencedColumnName = "alarmIdx",name = "alram_alram_idx")
    @JoinColumn(name = "alram_idx")
    private Alarm alarmIdx;

}
