<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
    <title>Yönetici Girişi</title>

    <link rel="stylesheet" href="../static/css/style.css">
    <link rel="stylesheet" href="../static/css/signin.css">
</head>
<body class="app">
<div id="loader">
    <div class="spinner"></div>
</div>

<script>
    window.addEventListener('load', function load() {
        const loader = document.getElementById('loader');
        setTimeout(function () {
            loader.classList.add('fadeOut');
        }, 300);

    });
</script>

<div class="peers ai-s fxw-nw h-100vh">
    <div class="d-n@sm- peer peer-greed h-100 pos-r bgr-n bgpX-c bgpY-c bgsz-cv" id="login-image">
    </div>

    <div class="col-12 col-md-4 pX-40 pY-80 h-100 bgc-white pos-r" id="form-div">
        <h2 class="fw-300 c-grey-900 mB-40 centered" style="border-bottom: 2px solid black;">
            <img src="../static/img/logo.png" alt="" id="logo-image">
        </h2>
        <form action="/login" method="post">
            <div class="form-group">
                <label class="text-normal text-dark">Kullanıcı Adı</label>
                <input type="text" name="username" class="form-control" style="border-left: 3px solid #FA906E;" required>
            </div>
            <div class="form-group"><label class="text-normal text-dark">Parola</label>
                <input type="password" name="password" class="form-control" style="border-left: 3px solid #FA906E;" required>
            </div>
            <c:if test="${wrongUsernameOrPassword}">
                <u id="wrong-username-or-password" style="color: red;">Hatalı kullanıcı adı yada parola.</u>
            </c:if>
            <hr>
            <div class="form-group">
                <div class="peers ai-c jc-sb fxw-nw">
                    <button class="btn btn-primary centered-by-margin" id="login-button" type="submit">Giriş</button>
                </div>
            </div>
        </form>
        <br>
        <hr>
        <a class="links" href="/kayit-ol" style="float: left;">Hesabınız yok mu? Hemen kaydolun</a>
        <a class="links" href="/" style="float: right;">Anasayfaya Dön</a>
        <br>
    </div>

</div>
<script type="text/javascript" src="../static/js/vendor.js"></script>
<script type="text/javascript" src="../static/js/bundle.js"></script>
<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script>
    window.onload = function () {
        setTimeout(function () {
            $("#wrong-username-or-password").fadeOut();
        }, 7000)
    }
</script>

</body>
</html>
