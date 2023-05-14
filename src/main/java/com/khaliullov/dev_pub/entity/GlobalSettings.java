package com.khaliullov.dev_pub.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "global_settings")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class GlobalSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;// INT NOT NULL AUTO_INCREMENT
    String code;// VARCHAR(255) NOT NULL
    String name;// VARCHAR(255) NOT NULL
    String value;// VARCHAR(255) NOT NULL
}

/**
 * Значения глобальных настроек
 *
 * code                         name                                        value
 * MULTIUSER_MODE               Многопользовательский режим                 YES / NO
 * POST_PREMODERATION           Премодерация постов                         YES / NO
 * STATISTICS_IS_PUBLIC         Показывать всем статистику блога            YES / NO
 **/
