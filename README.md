# AuctionBackEnd

Для класса модели Account юзал Spring Data интерфейс AccountRepositury
Для класса модели Product юзал интерфейс ProductRepository

Базу данных я использовал MySql на localhost
В application.proporties прописывал нужную инфу для коннекта
 

Для отправки запросов использовал Postman

Авторизация и Регистрация:

Регистрация:
Класс RegisterController
Метод реализует Post запрос: в теле запроса передается данные класса модели Account: email, password используя зависимость AccountRepository, проверяем данные в классе RegisterProcessor: если такого человека не существует то сохраняем данные аккаунта


Авторизация
Класс LoginController
Метод реализует Post запрос: в теле запроса получает данные модели Account: email, password используя зависимость accountRepository проверяет на наличие такого аккаунта в классе LoginProcessor так же  проверяет правильномть введенных данных в классе Сервиса
Если все верно то выделяется бин сессии

Класс MainController
Запрос для Главного меню
post запрос пути “/main” 
Выводит все активные аукционные товары со статусом active

post запрос по пути “/sell”
Клиент который хочет продать тгвар на аукционе делает запрос по пути “/sell”
В теле запроса принимаются данные класса модели Product с аттрибутами not null, то есть 
end date, buyer не отправлять

post запрос путр “/buy”
Пользователь который хочет купить товар ставит ставку отправляя в параметрах запроса id товара и сумму(ставочную). Далее проверяется сумма не должна быть меньше начальной суммы если все верно сохраняем в базе данных этого продукта покупателя(buyer). Если впервые делается ставка на этот товар:проверяем наличием в этой column на null: если впервые то сохраняется end date в базе данных: System.currentTime() + 5 часов(столько у меня идет аукцион на один товар)


Класс ThreadTimerService создает новый поток и каждую секунду ищет товары у которых истекло время. Если находит ставит ему в датабейс статус inactive.


Не успел сделать все пункты так как заранее я не знал что вышлют тз и на выходние дни были планы. В любом случае неплохой опыт. 
