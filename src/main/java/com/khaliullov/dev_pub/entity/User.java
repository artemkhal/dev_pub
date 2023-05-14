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
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;//id пользователя
    @Column(name = "is_moderator")
    short isModerator;// является ли пользователь модератором (может ли править глобальные настройки сайта и модерировать посты)
    @Column(name = "reg_time")
    LocalDateTime regTime;//дата и время регистрации пользователя
    String name;//имя пользователя
    String email;//e-mail пользователя
    String password;//хэш пароля пользователя
    String code;//код для восстановления пароля, может быть NULL
    @Column(name = "photo")
    String photoPath;//фотография (ссылка на файл), может быть NULL

    @Transient
    @OneToMany
    Set<Post> posts;
}
