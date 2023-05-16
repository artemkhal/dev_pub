package com.khaliullov.dev_pub.controller;

/*
 * обрабатывает все запросы /api/auth/*
 * */

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/auth")
public class ApiAuthController {


    @GetMapping("/check")
    public ResponseEntity<String> getCheck() {
        return ResponseEntity.ok("{\"result\": false}");
    }

    /**
     * Метод возвращает информацию о текущем авторизованном пользователе, если он авторизован. Он
     * должен проверять, сохранён ли идентификатор текущей сессии в списке авторизованных.
     * Значение moderationCount содержит количество постов необходимых для проверки модераторами.
     * Считаются посты имеющие статус NEW и не проверерны модератором.
     * Если пользователь не модератор возращать 0 в moderationCount и false в moderation.
     * Возвращайте result:false пока у вас не реализована авторизация пользователей.
     * <p>
     * AUTH
     * {
     * "result": true,
     * "user": {
     * "id": 576,
     * "name": "Дмитрий Петров",
     * "photo": "/avatars/ab/cd/ef/52461.jpg",
     * "email": "petrov@petroff.ru",
     * "moderation": true,
     * "moderationCount": 56,
     * "settings": true
     * } }
     * <p>
     * NoAUTH
     **/

    @GetMapping("/captcha")
    public ResponseEntity<String> getCaptcha() {
        return ResponseEntity.ok("{\n" +
                "  \"secret\": \"car4y8cryaw84cr89awnrc\",\n" +
                "  \"image\": \"data:image/png;base64, код_изображения_в_base64\"\n" +
                "}");
    }

    /**
     * Метод генерирует коды капчи, - отображаемый и секретный, - сохраняет их в базу данных
     * (таблица captcha_codes) и возвращает секретный код secret (поле в базе данныхsecret_code)
     * и изображение размером 100х35 с отображённым на ней основным кодом капчи image (поле базе данных code).
     * Также метод должен удалять устаревшие капчи из таблицы. Время устаревания должно быть задано в конфигурации
     * приложения (по умолчанию, 1 час).
     **/

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("{\"result\": true}");
    }

    /**
     * Метод создаёт пользователя в базе данных, если введённые данные верны. Если данные неверные -
     * пользователь не создаётся, а метод возвращает соответствующую ошибку.
     * {
     * "result": false,
     * "errors": {
     * "email": "Этот e-mail уже зарегистрирован",
     * "name": "Имя указано неверно",
     * "password": "Пароль короче 6-ти символов",
     * "captcha": "Код с картинки введён неверно"
     * } }
     **/
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam(name = "e_mail") String email, @RequestParam String password) {
        return ResponseEntity.ok("{\n" +
                "  \"result\": false\n" +
                "}");
    }

    /**
     * Метод проверяет введенные данные и производит авторизацию пользователя, если введенные данные верны.
     * Если пользователь авторизован, идентификатор его сессии должен запоминаться в Map<String, Integer> со значением,
     * равным ID пользователя, которому принадлежит данная сессия.
     * В параметрах объекта user выводятся имя пользователя, ссылка на его аватар, e-mail, параметры moderation
     * (если равен true, то у пользователя есть права на модерацию и в выпадающем меню справа будет отображаться пункт
     * меню Модерация с цифрой, указанной в параметре moderationCount) и settings (если равен true, то пользователю
     * доступны настройки блога). Оба параметра - moderation и settings - должны быть равны true, если пользователь
     * является модератором.
     * Значение moderationCount содержит количество постов необходимых для проверки модераторами.
     * Считаются посты имеющие статус NEW и не проверерны модератором. Если пользователь не модератор возращать 0 в
     * moderationCount.
     * Авторизация: не требуется
     * <p>
     * {
     * "result": true,
     * "user": {
     * "id": 576,
     * "name": "Дмитрий Петров",
     * "photo": "/avatars/ab/cd/ef/52461.jpg",
     * "email": "my@email.com",
     * "moderation": true,
     * "moderationCount": 0,
     * "settings": true
     * } }
     **/

    @PostMapping("/profile/my")
    public ResponseEntity<String> updateProfile() {
        return ResponseEntity.ok("{\n" +
                " \"result\": false,\n" +
                " \"errors\": {\n" +
                "   \"email\": \"Этот e-mail уже зарегистрирован\",\n" +
                "   \"photo\": \"Фото слишком большое, нужно не более 5 Мб\",\n" +
                "   \"name\": \"Имя указано неверно\",\n" +
                "   \"password\": \"Пароль короче 6-ти символов\",\n" +
                "} }");
    }

    /**
     * Метод обрабатывает информацию, введённую пользователем в форму редактирования своего профиля.
     * Если пароль не введён, его не нужно изменять. Если введён, должна проверяться его корректность: достаточная длина.
     * Одинаковость паролей, введённых в двух полях, проверяется на frontend - на сервере проверка не требуется.
     * Авторизация: требуется
     * photo - файл с фото или пустое значение (если его требуется удалить)
     * removePhoto - параметр, который указывает на то, что фотографию нужно удалить (если значение равно 1)
     * name - новое имя
     * email - новый e-mail
     * password - новый пароль
     **/

    @PostMapping("/restore")
    public ResponseEntity<String> restore(@RequestParam(name = "e_mail") String email) {
        return ResponseEntity.ok("{\n" +
                "  \"result\": false\n" +
                "}");
    }
    /**
     Метод проверяет наличие в базе пользователя с указанным e-mail.
     Если пользователь найден, ему должно отправляться письмо со ссылкой на восстановление пароля следующеговида -
     /login/change-password/HASH, где HASH - сгенерированный код вида b55ca6ea6cb103c6384cfa366b7ce0bdcac092be26bc0
     (код должен генерироваться случайным образом и сохраняться в базе данных в поле users.code).
     Авторизация: не требуется
     **/

    @PostMapping("/password")
    public ResponseEntity<String> checkPassword(@RequestParam String code,
                                                @RequestParam String password,
                                                @RequestParam String captcha,
                                                @RequestParam(name = "captcha_secret") String captchaSecret){
        return ResponseEntity.ok("{\n" +
                "  \"result\": false,\n" +
                "  \"errors\": {\n" +
                "    \"code\": \"Ссылка для восстановления пароля устарела.\n" +
                "        <a href=\n" +
                "        \\\"/auth/restore\\\">Запросить ссылку снова</a>\",\n" +
                "    \"password\": \"Пароль короче 6-ти символов\",\n" +
                "    \"captcha\": \"Код с картинки введён неверно\"\n" +
                "} }");
    }
    /**
     Метод проверяет корректность кода восстановления пароля (параметр code) и корректность кодов капчи:
     введённый код (параметр captcha) должен совпадать со значением в поле code таблицы captcha_codes,
     соответствующем пришедшему значению секретного кода (параметр captcha_secret и поле secret_code в таблице базы
     данных).
     Авторизация: не требуется
     code - код восстановления пароля
     password - новый пароль
     captcha - код капчи
     captcha_secret - секретный код капчи
    **/


    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok("{\"result\": true}");
    }
    /**
     Метод разлогинивает пользователя: удаляет идентификатор его сессии из списка авторизованных.
     Всегда возвращает true, даже если идентификатор текущей сессии не найден в списке авторизованных.
     Авторизация: требуется
    **/

}
