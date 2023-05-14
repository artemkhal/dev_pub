package com.khaliullov.dev_pub.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_votes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class PostVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;// INT NOT NULL AUTO_INCREMENT
    @OneToOne(cascade = CascadeType.ALL)
    User user_id;// INT NOT NULL
    @JoinColumn(name = "post_id")
    @OneToOne(cascade = CascadeType.ALL)
    Post postId;// INT NOT NULL
    LocalDateTime time;// DATETIME NOT NULL
    byte value;// TINYINT NOT NULL
}
