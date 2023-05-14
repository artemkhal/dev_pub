package com.khaliullov.dev_pub.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "moderator_id")
    int moderatorId;
    @Column(name = "user_id")
    int userId;
    LocalDateTime time;
    String title;
    String text;
    @Column(name = "view_count")
    int viewCount;
}
