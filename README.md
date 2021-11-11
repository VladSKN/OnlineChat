# Курсовой проект "Сетевой чат" от Нетологии

## Разработка двух приложений для обмена текстовыми сообщениями по сети с помощью консоли (терминала) между двумя и более пользователями.

**Первое приложение** - сервер чата, должно ожидать подключения пользователей.

**Второе приложение** - клиент чата, подключается к серверу чата и осуществляет доставку и получение новых сообщений.

Все сообщения должны записываться в **file.log** как на сервере, так и на клиентах. 
**file.log** должен дополняться при каждом запуске, а также при отправленном или полученном сообщении. 
Выход из чата должен быть осуществлен по команде **exit**. Установка порта для подключения клиентов через файл настроек **(settings.txt)**;

## Использованные технологии в проекте
- Сборщик проектов **Maven**;
- Библиотека логгирования **log4j**;
- Библиотека тестировнания **JUnit**;
