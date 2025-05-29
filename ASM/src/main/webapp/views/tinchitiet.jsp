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
            text-align: left;
        }

        .b1 {
            display: flex;
            gap: 10px;
            margin-bottom: 10px;
        }

        .b1 .img {
            flex: 1;
        }

        .b1 p {
            flex: 4;
        }

        .b2 div {
            text-align: right;
        }
    </style>
</head>

<body>
    <h1>Tieu de ban tin</h1>
    <div class="b1">
        <div class="img">Ảnh</div>
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Nesciunt perspiciatis eos necessitatibus
            nostrum recusandae accusantium dicta sequi sed, laborum, sunt, enim eius odio labore provident ipsa
            incidunt possimus quas fugiat! Nostrum officiis earum maiores repellendus eligendi praesentium vitae
            illum sequi error. Hic accusantium quaerat praesentium incidunt esse ea magni ipsam.</p>
    </div>
    <div class="b2">
        <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Culpa optio recusandae ipsam dolore
            necessitatibus eius eos eveniet sunt, veniam pariatur minima quidem numquam qui delectus a suscipit.
            Aut ipsum illum reprehenderit laboriosam necessitatibus vitae accusamus?</p>
        <div>tac gia / ngay dang</div>
    </div>
    <div class="b3">
        <h2>Tin cung loai</h2>
        <ol type="1">
            <li><a href="#">Tieu de ban tin cung loai 1</a></li>
            <li><a href="#">Tieu de ban tin cung loai 2</a></li>
            <li><a href="#">Tieu de ban tin cung loai 3</a></li>
        </ol>
    </div>
</body>

</html>