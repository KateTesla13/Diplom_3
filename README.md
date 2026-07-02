# Diplom_2
UI-тесты для веб-приложения **Stellar Burgers**.
## 📋 Описание

Автотесты для проверки функциональности веб-приложения Stellar Burgers:
- Регистрация пользователя
- Вход в систему (4 способа)
- Разделы конструктора

---

## 🛠️ Технологии
| Технология       | Версия |
|------------------|--------|
| Java             | 11     |
| JUnit            | 4.13.2 |
| Selenium         | 4.15.0 |
| WebDriverManager | 5.6.2  |
| Allure           | 2.21.0 |

---
## 📁 Структура проекта
```bash

src/test/java/com/stellar/burgers/
├── config/
│ └── BaseTest.java 
├── pageobject/
│ ├── MainPage.java
│ ├── LoginPage.java 
│ ├── RegisterPage.java
│ └── ForgotPasswordPage.java
└── test/
├── RegistrationTest.java 
├── LoginTest.java 
└── ConstructorTest.java
``` 
---

## 🧪 Запуск тестов

### Chrome (по умолчанию)
```bash

mvn clean test
``` 
### Яндекс Браузер
```bash

mvn clean test -Dbrowser=yandex
``` 
## 📊 Allure-отчёт
### Сгенерировать и открыть отчёт
```bash

allure serve target/surefire-reports/
``` 
### Сгенерировать в папку проекта
```bash

mvn allure:report
``` 
## 📋 Что проверяется

### Регистрация
✅ Успешная регистрация

✅ Ошибка для пароля менее 6 символов

### Вход
✅ По кнопке «Войти в аккаунт» на главной

✅ Через «Личный кабинет»

✅ Через форму регистрации

✅ Через форму восстановления пароля

### Конструктор
✅ Переход к разделу «Булки»

✅ Переход к разделу «Соусы»

✅ Переход к разделу «Начинки»

## 👩‍💻 Автор
KateTesla13