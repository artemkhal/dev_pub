package com.khaliullov.dev_pub.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;// INT NOT NULL AUTO_INCREMENT
    @Column(name = "parent_id")
    int parentId;// INT
    @JoinColumn(name = "post_id")
    @OneToOne(cascade = CascadeType.ALL)
    Post postId;// INT NOT NULL
    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    User userId;// INT NOT NULL
    LocalDateTime time;// DATETIME NOT NULL
    String text;// TEXT NOT NULL
}
