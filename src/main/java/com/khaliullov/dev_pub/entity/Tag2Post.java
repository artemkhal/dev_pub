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
    @JoinColumn(name = "post_id")
    @OneToOne(cascade = CascadeType.ALL)
    Post postId;// INT NOT NULL
    @JoinColumn(name = "tag_id")
    @OneToOne(cascade = CascadeType.ALL)
    Tag tagId;// INT NOT NULL

}
