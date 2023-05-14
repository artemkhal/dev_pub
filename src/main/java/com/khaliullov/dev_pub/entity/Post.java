package com.khaliullov.dev_pub.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "is_active")
    short isActive;
    @Column(name = "moderation_status")
    ModerationStatus moderationStatus;
    @JoinColumn(name = "moderator_id")
    @OneToOne(cascade = CascadeType.ALL)
    User moderatorId;
    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    User userId;
    LocalDateTime time;
    String title;
    String text;
    @Column(name = "view_count")
    int viewCount;

    @Transient
    @OneToMany
    Set<PostComment> postComments;
    @Transient
    @OneToMany
    Set<PostVote> postVotes;


}
