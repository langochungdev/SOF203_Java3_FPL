<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .item {
            display: flex;
            margin-bottom: 16px;
        }

        .item img {
            flex: 2;
            padding-right: 16px;
        }

        .item .noidung {
            flex: 8;
            text-align: left;
        }

        a {
            color: black;
            text-decoration: none;
        }

        .subtext {
            font-size: 10px;
        }
    </style>
</head>

<body>
    <a href="#" class="item">
		<div class="img">Ảnh</div>
        <div class="noidung">
            <h2 class="tieude">Tieu de ban tin</h2>
            <p class="tomtat">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Nemo, doloremque?
            </p>
            <p class="subtext">ngay dang / tac gia</p>
        </div>
    </a>
    <a href="#" class="item">
        <div class="img">Ảnh</div>
        <div class="noidung">
            <h2 class="tieude">Tieu de ban tin</h2>
            <p class="tomtat">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Nemo, doloremque?
            </p>
            <p class="subtext">ngay dang / tac gia</p>
        </div>
    </a>
    <a href="#" class="item">
        <div class="img">Ảnh</div>
        <div class="noidung">
            <h2 class="tieude">Tieu de ban tin</h2>
            <p class="tomtat">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Nemo, doloremque?
            </p>
            <p class="subtext">ngay dang / tac gia</p>
        </div>
    </a>
</body>

</html>