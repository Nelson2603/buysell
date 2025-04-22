<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>BUYSELL</title>
    <style>
        /* Стили для изображений */
        .product-image {
            max-width: 100%; /* Ограничивает ширину изображения до 100% от контейнера */
            height: auto; /* Сохраняет пропорции изображения */
            display: block; /* Убирает лишние отступы под изображением */
            margin-bottom: 10px; /* Отступ снизу между изображениями */
        }
    </style>
</head>
<body>
<h1>ПОДРОБНАЯ ИНФОРМАЦИЯ О ТОВАРЕ</h1>

<!-- Отображение изображений товара -->
<#list images as img>
    <img src="/images/${img.id}" alt="Изображение товара ${product.title}" class="product-image"/>
</#list>

<b>Название товара:</b> ${product.title}<br>
<b>Описание товара:</b> ${product.description}<br>
<b>Цена:</b> ${product.price} руб.<br>
<b>Город:</b> ${product.city}<br>
<b>Автор:</b> <a href="/user/${product.user.id}">${product.user.name}</a><br>

<!-- Если нужно включить форму удаления товара, раскомментируйте этот блок -->
<!-- <form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Удалить товар"/>
</form> -->

</body>
</html>
