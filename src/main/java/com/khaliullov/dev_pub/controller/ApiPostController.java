package com.khaliullov.dev_pub.controller;
/*
 * обрабатывает все запросы /api/post/*
 * */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/api/post")
public class ApiPostController {

    @GetMapping
    public ResponseEntity<String> getPosts(@RequestParam int offset, @RequestParam int limit, @RequestParam String mode) {
        return ResponseEntity.ok("{\n" + "     \"count\": 390,\n" + "     \"posts\": [\n" + "     {\n" + "     \"id\": 345,\n" + "     \"timestamp\": 1592338706,\n" + "     \"user\":\n" + "     {\n" + "     \"id\": 88,\n" + "     \"name\": \"Дмитрий Петров\"\n" + "     },\n" + "     \"title\": \"Заголовок поста\",\n" + "     \"announce\": \"Текст анонса поста без HTML-тэгов\",\n" + "     \"likeCount\": 36,\n" + "     \"dislikeCount\": 3,\n" + "     \"commentCount\": 15,\n" + "     \"viewCount\": 55\n" + "     },\n" + "     {...} ]\n" + "     }");
    }


    @GetMapping("/search")
    public ResponseEntity<String> getPosts() {
        return ResponseEntity.ok("{\n" + "  \"count\":0,\n" + "  \"posts\":[]\n" + "}");
    }

    /**
     * Метод возвращает посты, соответствующие поисковому запросу - строке query. В случае,
     * если запрос пустой или содержит только пробелы, метод должен выводить все посты
     * (запрос GET /api/post c параметров mode=recent).
     * <p>
     * Параметр count содержит общее количество постов, которые найдены по переданному в параметре query запросу.
     * Массив posts - только то количество, которое было передано при запросе в параметре limit, или меньшее,
     * если это последняя порция постов.
     * <p>
     * Должны выводиться только активные (поле is_active в таблице posts равно 1), утверждённые модератором
     * (поле moderation_status равно ACCEPTED) посты с датой публикации не позднее текущего момента
     * (движок должен позволять откладывать публикацию).
     * {
     * "count": 20,
     * "posts": [
     * {
     * "id": 345,
     * "timestamp": 1592338706,
     * "user":
     * {
     * "id": 88,
     * "name": "Дмитрий Петров"
     * },
     * "title": "Заголовок поста",
     * "announce": "Текст анонса поста без HTML-тэгов",
     * "likeCount": 36,
     * "dislikeCount": 3,
     * "commentCount": 15,
     * "viewCount": 55
     * },
     * {...} ]
     * }
     **/

    @GetMapping("/byDate")
    public ResponseEntity<String> getPostsByDate(@RequestParam int offset, @RequestParam int limit, @RequestParam LocalDate date) {

        return ResponseEntity.ok("{\n" + "  \"count\":0,\n" + "  \"posts\":[]\n" + "}");
    }

    /**
     * Выводит посты за указанную дату, переданную в запросе в параметре date. Параметры offset и limit,
     * а также выдаваемое значение count аналогичны таковым в методе GET /api/post, выдающем список постов.
     * Должны выводиться только активные (поле is_active в таблице posts равно 1), утверждённые модератором
     * (поле moderation_status равно ACCEPTED) посты с датой публикации не позднее текущего момента
     * (движок должен позволять откладывать публикацию).
     * {
     * "count": 2,
     * "posts": [
     * {
     * "id": 345,
     * "timestamp": 1592338706,
     * "title": "Заголовок поста",
     * "announce": "Текст анонса (часть основного текста) поста без HTML-тэгов",
     * "likeCount": 36,
     * "dislikeCount": 3,
     * "commentCount": 15,
     * "viewCount": 55,
     * "user":
     * {
     * "id": 88,
     * "name": "Дмитрий Петров"
     * } },
     * {...}
     * ] }
     **/

    @GetMapping("/byTag")
    public ResponseEntity<String> getPostsByTag(@RequestParam int offset, @RequestParam int limit, @RequestParam String tag) {
        return ResponseEntity.ok("{\n" + "  \"count\":0,\n" + "  \"posts\":[]\n" + "}");
    }

    /**
     * Метод выводит список постов, привязанных к тэгу, который был передан методу в качестве параметра
     * tag.
     * Параметры offset и limit, а также выдаваемое значение count аналогичны таковым в методе GET
     * /api/post, выдающем список постов.
     * Должны выводиться только активные (поле is_active в таблице posts равно 1), утверждённые модератором
     * (поле moderation_status равно ACCEPTED) посты с датой публикации не позднее текущего момента
     * (движок должен позволять откладывать публикацию).
     * {
     * "count": 2,
     * "posts": [
     * {
     * "id": 345,
     * "timestamp": 1592338706,
     * "title": "Заголовок поста",
     * "announce": "Текст анонса (часть основного текста) поста без HTML-тэгов",
     * "likeCount": 36,
     * "dislikeCount": 3,
     * "commentCount": 15,
     * "viewCount": 55,
     * "user":
     * {
     * "id": 88,
     * "name": "Дмитрий Петров"
     * } },
     * {...}
     * ] }
     **/
    @GetMapping("/{ID}")
    public ResponseEntity<String> getPost(@PathVariable int id) {
        return ResponseEntity.badRequest().body("");
    }

    /**
     * Метод выводит данные конкретного поста для отображения на странице поста, в том числе,
     * список комментариев и тэгов, привязанных к данному посту. Выводит пост в любом случае,
     * если пост активен (параметр is_active в базе данных равен 1), принят модератором
     * (параметр moderation_status равен ACCEPTED) и время его публикации (поле timestamp) равно текущему
     * времени или меньше его формата UTC.
     * При успешном запросе необходимо увеличивать количество просмотров поста на 1 (поле view_count), кроме случаев:
     * Если модератор авторизован, то не считаем его просмотры вообще Если автор авторизован, то не считаем просмотры
     * своих же публикаций
     * Параметр active в ответе используется админ частью фронта, должно быть значение true если
     * пост опубликован и false если скрыт (при этом модераторы и автор поста будет его видеть)
     **/

    @GetMapping("/moderation")
    public ResponseEntity<String> getPostsOnMod(@RequestParam int offset, @RequestParam int limit,
                                                @RequestParam String status) {
        return ResponseEntity.ok("{\n" +
                "  \"count\":0,\n" +
                "  \"posts\":[]\n" +
                "}");
    }

    /**
     * Метод выводит все посты, которые требуют модерационных действий (которые нужно утвердить или отклонить) или
     * над которыми мною были совершены модерационные действия: которые я отклонил или утвердил (это определяетс
     * полями moderation_status и moderator_id в таблице posts базы данных).
     * Параметры offset и limit, а также выдаваемое значение count аналогичны таковым в методе GET /api/post,
     * выдающем список постов.
     * Должны выводиться только активные (поле is_active в таблице posts равно 1) посты. Скрытые посты - это черновики,
     * которые пользователь создал, но ещё не решил опубликовать.
     * Авторизация: требуется
     * {
     * "count": 3,
     * "posts": [
     * {
     * "id": 31,
     * "timestamp": 1592338706,
     * "title": "Заголовок поста",
     * "announce": "Текст анонса (часть основного текста) поста без HTML-тэгов (HTML
     * тэги необходимо удалить из текста анонса)",
     * "likeCount": 36,
     * "dislikeCount": 3,
     * "commentCount": 15,
     * "viewCount": 55,
     * "user":
     * {
     * "id": 88,
     * "name": "Дмитрий Петров"
     * } },
     * {...}
     * ] }
     **/

    @GetMapping("/my")
    public ResponseEntity<String> getMyPosts(@RequestParam int offset, @RequestParam int limit,
                                             @RequestParam String status) {
        return ResponseEntity.ok("{\n" +
                "  \"count\":0,\n" +
                "  \"posts\":[]\n" +
                "}");
    }

    /**
     * Метод выводит только те посты, которые создал я (в соответствии с полем user_id в таблице posts
     * базы данных). Возможны 4 типа вывода (см. ниже описания значений параметра status). Параметры offset и limit, а также выдаваемое значение count аналогичны таковым в методе GET
     * /api/post, выдающем список постов.
     * Авторизация: требуется
     * {
     * "count": 3,
     * "posts": [
     * {
     * "id": 31,
     * "timestamp": 1592338706,
     * "title": "Заголовок поста",
     * "announce": "Текст анонса (часть основного текста) поста без HTML-тэгов (HTML
     * тэги необходимо удалить из текста анонса)",
     * "likeCount": 36,
     * "dislikeCount": 3,
     * "commentCount": 15,
     * "viewCount": 55,
     * "user":
     * {
     * "id": 88,
     * "name": "Дмитрий Петров"
     * } },
     * {...}
     * ] }
     **/

    @PostMapping("/")
    public ResponseEntity<String> createPost() {
        return ResponseEntity.ok("{\n" +
                "  \"result\": false,\n" +
                "  \"errors\": {\n" +
                "    \"title\": \"Заголовок не установлен\",\n" +
                "    \"text\": \"Текст публикации слишком короткий\"\n" +
                "  }\n" +
                "}");
    }

    /**
     * Метод отправляет данные поста, которые пользователь ввёл в форму публикации. В случае, если заголовок или
     * текст поста не установлены и/или слишком короткие (короче 3 и 50 символов соответственно), метод должен выводить
     * ошибку и не добавлять пост.
     * Время публикации поста также должно проверяться: в случае, если время публикации раньше текущего времени,
     * оно должно автоматически становиться текущим. Если позже текущего - необходимо устанавливать введенное значение.
     * Пост должен сохраняться со статусом модерации NEW.
     * Авторизация: требуется
     **/

    @PutMapping("/{ID}")
    public ResponseEntity<String> updatePost(@PathVariable int id) {
        return ResponseEntity.ok("{\n" +
                "  \"result\": false,\n" +
                "  \"errors\": {\n" +
                "    \"title\": \"Заголовок не установлен\",\n" +
                "    \"text\": \"Текст публикации слишком короткий\"\n" +
                "  }\n" +
                "}");
    }

    /**
     * Метод изменяет данные поста с идентификатором ID на те, которые пользователь ввёл в форму публикации.
     * В случае, если заголовок или текст поста не установлены и/или слишком короткие
     * (короче 3 и 50 символов соответственно), метод должен выводить ошибку и не изменять пост.
     * Время публикации поста также должно проверяться: в случае,
     * если время публикации раньше текущего времени, оно должно автоматически становиться текущим.
     * Если позже текущего - необходимо устанавливать указанное значение.
     * Пост должен сохраняться со статусом модерации NEW, если его изменил автор, и статус модерации не должен изменяться,
     * если его изменил модератор.
     * Авторизация: требуется
     **/

    @PostMapping("/like")
    public ResponseEntity<String> setLike(@RequestParam(name = "post_id") int postId) {
        return ResponseEntity.ok("{\n" +
                "  \"result\": true\n" +
                "}");
    }

    /**
     * Метод сохраняет в таблицу post_votes лайк текущего авторизованного пользователя.
     * В случае повторного лайка возвращает {result: false}.
     * Если до этого этот же пользователь поставил на этот же пост дизлайк, этот дизлайк должен быть заменен
     * на лайк в базе данных.
     * Авторизация: требуется
     */

    @PostMapping("/dislike")
    public ResponseEntity<String> setDislike(@RequestParam(name = "post_id") int postId) {
        return ResponseEntity.ok("{\n" +
                "  \"result\": true\n" +
                "}");
    }
    /**
     Метод сохраняет в таблицу post_votes дизлайк текущего авторизованного пользователя. В случае
     повторного дизлайка возвращает {result: false}.
     Если до этого этот же пользователь поставил на этот же пост лайк, этот лайк должен заменен на дизлайк
     в базе данных.
     Авторизация: требуется
     */

}
