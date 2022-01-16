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
public class UserHasAlarm {


    @Id
    @ManyToOne
    @JoinColumn(name = "user_idx")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "alram_alram_idx")
    private Alarm alarm;

}
