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
    @JoinColumn(name = "user_user_idx", referencedColumnName = "user_idx")
    private Member userUserIdx;

    @Id
    @OneToOne
    @JoinColumn(name = "alram_alram_idx",referencedColumnName = "alram_idx")//
    private Alarm alarmIdx;

}
