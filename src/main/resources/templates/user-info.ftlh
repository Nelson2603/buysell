<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>BUYSELL</title>
    <!-- Подключение Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <!-- Стили -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        h1, h3, h4 {
            color: #333;
        }

        h1 {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            text-align: center;
            margin-bottom: 0;
        }

        hr {
            border: 1px solid #ccc;
            margin-bottom: 20px;
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        .user-info, .product-list {
            background-color: white;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            border-radius: 10px;
        }

        b {
            color: #555;
        }

        .product-list div {
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }

        .product-list div:last-child {
            border-bottom: none;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            margin-top: 10px;
        }

        .btn:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
            font-size: 18px;
            text-align: center;
            padding: 10px;
            background-color: #ffe6e6;
            border-radius: 10px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<h1>BUYSELL</h1>
<hr>

<div class="container">
    <div class="user-info">
        <h3>Подробная информация о пользователе: ${user.name}</h3>
        <p><b>Номер телефона:</b> ${user.phoneNumber}</p>
        <p><b>Email:</b> ${user.email}</p>
    </div>

    <div class="product-list">
        <h4>Товары пользователя: ${user.name}</h4>
        <#list products as product>
            <div>
                <p><b>${product.title}</b> - ${product.price} руб.</p>
                <a class="btn" href="/product/${product.id}">Посмотреть</a>
            </div>
        <#else>
            <h3>Товаров нет</h3>
        </#list>
    </div>

    <#if errorMessage??>
        <div class="error-message">${errorMessage}</div>
    </#if>
</div>

</body>
</html>
