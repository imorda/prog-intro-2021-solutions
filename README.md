Этот репозиторий будет склонирован для каждого студента и доступен по адресу
`https://www.kgeorgiy.info/git-students/year2021/<USER>/prog-intro`,
где `<USER>` — имя пользователя в PCMS (пароль так же используется из PCMS).

Для сдачи домашних заданий
 * Клонируйте ваш личный репозиторий
    * `git clone https://www.kgeorgiy.info/git-students/year2021/<USER>/prog-intro`
    * У личных репозиториев __нет__ web-интерфейса, используйте инструменты командной строки.
 * Добавьте ссылку на исходный репозиторий
    * `git remote add source https://www.kgeorgiy.info/git/geo/prog-intro-2021-solutions`
    * Теперь можно будет забирать обновления командной `git pull source master`.
 * Добавляйте _только_ исходные файлы решений
    * Сохраняйте текущую структуру каталогов и имена файлов.
    * Не добавляйте исходный код тестов, `.class`-файлы и файлы проектов.
    * Если структура репозитория не соответствует исходной, преподаватель не будет проверять решение.
    * Вы можете редактировать `.gitignore` как вам удобно.
 * Перед отправкой решения на проверку
    * Проверьте, что все исходники на Java компилируются
    * Проверьте, что тесты сдаваемого ДЗ проходят
    * Закоммитьте все изменения в `master`
    * Запушите все изменения
    * Запросите проверку решения, заполнив форму
 * После проверки преподаватель либо укажет найденные недостатки в `NOTES.md`,
   либо укажет их в виде комментариев в исходном коде, пометив их как `:NOTE:`
