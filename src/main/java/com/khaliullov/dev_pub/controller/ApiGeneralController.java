package com.khaliullov.dev_pub.controller;

/*
 * для прочих запросов к API
 * */


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ApiGeneralController {


    @GetMapping("/init")
    public ResponseEntity<String> getInit() {
        return ResponseEntity.ok("{\n" +
                "     \"title\": \"DevPub\",\n" +
                "     \"subtitle\": \"Рассказы разработчиков\",\n" +
                "     \"phone\": \"+7 903 666-44-55\",\n" +
                "     \"email\": \"mail@mail.ru\",\n" +
                "     \"copyright\": \"Дмитрий Сергеев\",\n" +
                "     \"copyrightFrom\": \"2005\"\n" +
                "     }");
    }

    /**
     * Метод возвращает общую информацию о блоге: название блога и подзаголовок для размещения в хэдере сайта,
     * а также номер телефона, e-mail и информацию об авторских правах для размещения в футере.
     * {
     * "title": "DevPub",
     * "subtitle": "Рассказы разработчиков",
     * "phone": "+7 903 666-44-55",
     * "email": "mail@mail.ru",
     * "copyright": "Дмитрий Сергеев",
     * "copyrightFrom": "2005"
     * }
     **/

    @GetMapping("/settings")
    public ResponseEntity<String> getSettiings() {
        return ResponseEntity.ok("{\n" +
                "     \"MULTIUSER_MODE\": false,\n" +
                "     \"POST_PREMODERATION\": true,\n" +
                "     \"STATISTICS_IS_PUBLIC\": true\n" +
                "     }");
    }

    /**
     * Метод возвращает глобальные настройки блога из таблицы global_settings. Если значение
     * параметра YES - возвращаем true, если NO - возвращаем false
     * {
     * "MULTIUSER_MODE": false,
     * "POST_PREMODERATION": true,
     * "STATISTICS_IS_PUBLIC": true
     * }
     **/
    @GetMapping("/tag")
    public ResponseEntity<String> getTag() {
        return ResponseEntity.ok("{ \"tags\":\n" +
                "  [\n" +
                "   {\"name\":\"Java\", \"weight\":1},\n" +
                "   {\"name\":\"Spring\", \"weight\":0.56},\n" +
                "   {\"name\":\"Hibernate\", \"weight\":0.22},\n" +
                "   {\"name\":\"Hadoop\", \"weight\":0.17},\n" +
                "] }");
    }

    /**
     * Метод выдаёт список тэгов, начинающихся на строку, заданную в параметре query. В случае,
     * если она не задана, выводятся все тэги. В параметре weight должен быть указан относительный нормированный вес
     * тэга от 0 до 1, соответствующий частоте его встречаемости. Значение 1 означает, что этот тэг встречается чаще всего.
     **/
    @GetMapping("/calendar")
    public ResponseEntity<String> getCalendar(@RequestParam int year) {
        return ResponseEntity.ok("{\n" +
                "     \"years\": [2017, 2018, 2019, 2020],\n" +
                "     \"posts\":{\n" +
                "     \"2019-12-17\":56,\n" +
                "     \"2019-12-14\":11,\n" +
                "     \"2019-06-17\":1,\n" +
                "     \"2020-03-12\":6\n" +
                "     }\n" +
                "     }");
    }

    /**
     * Метод выводит количества публикаций на каждую дату переданного в параметре year года или текущего года,
     * если параметр year не задан. В параметре years всегда возвращается список всех годов,
     * за которые была хотя бы одна публикация, в порядке возрастания.
     * Должны учитываться только активные посты (поле is_active в таблице posts равно 1),
     * утверждённые модератором (поле moderation_status равно ACCEPTED) посты с датой публикации не позднее
     * текущего момента.
     * {
     * "years": [2017, 2018, 2019, 2020],
     * "posts":{
     * "2019-12-17":56,
     * "2019-12-14":11,
     * "2019-06-17":1,
     * "2020-03-12":6
     * }
     * }
     **/


    @PostMapping("/image")
    public ResponseEntity<String> setImg() {

        return ResponseEntity.badRequest().body("{\n" +
                "  \"result\": false,\n" +
                "  \"errors\": {\n" +
                "    \"image\": \"Размер файла превышает допустимый размер\"\n" +
                "  }\n" +
                "}");
    }

    /**
     * Метод загружает на сервер изображение в папку upload и три случайные подпапки. Имена подпапок и
     * изображения можно формировать из случайного хэша, разделяя его на части.
     * Метод возвращает путь до изображения. Этот путь затем будет вставлен в HTML-код публикации,
     * в которую вставлено изображение.
     * Авторизация: требуется
     **/

    @PostMapping("/comment")
    public ResponseEntity<String> sendComment(@RequestParam(name = "parent_id") int commentId,
                                              @RequestParam(name = "post_id") int postId,
                                              @RequestParam String text) {
        return ResponseEntity.badRequest().body("{\n" +
                "  \"result\": false,\n" +
                "  \"errors\": {\n" +
                "    \"text\": \"Текст комментария не задан или слишком короткий\"\n" +
                "  }\n" +
                "}");
    }

    /**
     * Метод добавляет комментарий к посту. Должны проверяться все три параметра. Если параметры parent_id и/или
     * post_id неверные (соответствующие комментарий и/или пост не существуют), должна
     * выдаваться ошибка 400 (см. ниже раздел “Обработка ошибок”). В случае, если текст комментария отсутствует (пустой)
     * или слишком короткий, необходимо выдавать ошибку в JSON-формате.
     * Авторизация: требуется
     * parent_id - ID комментария, на который пишется ответ
     * post_id - ID поста, к которому пишется ответ
     * text - текст комментария (формат HTML)
     **/

    @PostMapping("/moderation")
    public ResponseEntity<String> moderation(@RequestParam(name = "post_id") int postIs, @RequestParam String decision) {
        return ResponseEntity.ok("{\n" +
                "  \"result\":true\n" +
                "}");
    }

    /**
     * Метод фиксирует действие модератора по посту: его утверждение или отклонение.
     * Кроме того, фиксируется moderator_id - идентификатор пользователя, который отмодерировал пост.
     * Посты могут модерировать только пользователи с is_moderator = 1
     * Авторизация: требуется
     * post_id - идентификатор поста
     * decision - решение по посту: accept или decline.
     **/

    @GetMapping("/statistics/my")
    public ResponseEntity<String> getStatistics() {
        return ResponseEntity.ok("{\n" +
                "\"postsCount\":7,\n" +
                "\"likesCount\":15,\n" +
                "\"dislikesCount\":2,\n" +
                "\"viewsCount\":58,\n" +
                "\"firstPublication\":1590217200\n" +
                "}");
    }

    /**
     Метод возвращает статистику постов текущего авторизованного пользователя: общие количества
     параметров для всех публикаций, у который он является автором и доступные для чтения.
     Авторизация: требуется
     **/

    @GetMapping("/statistics/all")
    public ResponseEntity<String> getAllStatistic(){
        return ResponseEntity.ok("{\n" +
                "\"postsCount\":7,\n" +
                "\"likesCount\":15,\n" +
                "\"dislikesCount\":2,\n" +
                "\"viewsCount\":58,\n" +
                "\"firstPublication\":1590217200\n" +
                "}");
    }
    /**
     Метод выдаёт статистику по всем постам блога.
     В случае, если публичный показ статистики блога запрещён (см. соответствующий параметр в global_settings)
     и текущий пользователь не модератор, должна выдаваться ошибка 401 (см. ниже Обработка ошибок).
     Авторизация: не требуется
    **/


    @PutMapping("/settings")
    public ResponseEntity<String> settings(@RequestParam(name = "MULTIUSER_MODE") boolean multiuserMode,
                                           @RequestParam(name = "POST_PREMODERATION") boolean postPremoderation,
                                           @RequestParam(name = "STATISTICS_IS_PUBLIC") boolean statisticIsPublic){
        return ResponseEntity.ok("{\"result\":false}");
    }
}
