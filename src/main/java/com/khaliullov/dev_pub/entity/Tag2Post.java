package com.khaliullov.dev_pub.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class Tag2Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;// INT NOT NULL AUTO_INCREMENT
    @Column(name = "post_id")
    int postId;// INT NOT NULL
    @Column(name = "tag_id")
    int tagId;// INT NOT NULL

}
