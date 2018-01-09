<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">


</head>
<body>
<div class="container">

    <div class="currentYen">


        <div class="nominal">

        </div>


        <div class="value">

        </div>


    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-secondary" type="button" onclick="buy()">Buy</button>
      </span>
                <input type="text" id="buyInput" class="form-control" placeholder="Value for buy" value="">
            </div>
            <div id="buyResult" class="result">

            </div>
        </div>
        <div class="col-md-6">
            <div class="input-group">
                <input type="text" id="sellInput" class="form-control" placeholder="Value for sell" value="">
                <span class="input-group-btn">
        <button class="btn btn-secondary" type="button" onclick="sell()">Sell</button>
      </span>
            </div>
            <div id="sellResult" class="result">

            </div>
        </div>
    </div>
</div>
<div class="footer">
    <script type="text/javascript" src="webjars/jquery/3.2.1/jquery.min.js" defer></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="static/js/main.js" defer></script>
</div>
</body>
</html>