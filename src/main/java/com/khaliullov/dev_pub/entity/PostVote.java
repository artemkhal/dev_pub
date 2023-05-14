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
    int id;// INT NOT NULL AUTO_INCREMENT user_id INT NOT NULL
    @Column(name = "post_id")
    int postId;// INT NOT NULL
    LocalDateTime time;// DATETIME NOT NULL
    byte value;// TINYINT NOT NULL
}
