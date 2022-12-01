<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
    <title>Yönetici Girişi</title>

    <link rel="stylesheet" href="../static/css/style.css">
    <link rel="stylesheet" href="../static/css/register.css">
    <link rel="stylesheet" href="../static/semantic/semantic.css">
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
        <form action="/register" method="post">
            <div class="form-group">
                <label class="text-normal text-dark">İsim</label>
                <input type="text" name="name" class="form-control" style="border-left: 3px solid #FA906E;" required>
            </div>
            <div class="form-group">
                <label class="text-normal text-dark">Soyisim</label>
                <input type="text" name="lastname" class="form-control" style="border-left: 3px solid #FA906E;" required>
            </div>
            <div class="form-group">
                <label class="text-normal text-dark">Adres</label>
                <textarea type="text" name="address" class="form-control" style="border-left: 3px solid #FA906E;" required rows="3"></textarea>
            </div>
            <div class="form-group">
                <label class="text-normal text-dark">Telefon Numarası</label>
                <input type="text" name="phone-number" class="form-control" style="border-left: 3px solid #FA906E;" placeholder="5XX-XXX-XX-XX" required>
            </div>
            <div class="form-group">
                <label class="text-normal text-dark">Kullanıcı Adı</label>
                <input type="text" name="username" class="form-control" style="border-left: 3px solid #FA906E;" required>
            </div>

            <div class="form-group">
                <label class="text-normal text-dark">Parola</label>
                <input type="password" name="password1" class="form-control" style="border-left: 3px solid #FA906E;" required>
            </div>
            <div class="form-group">
                <label class="text-normal text-dark">Parola (Tekrar)</label>
                <input type="password" name="password2" class="form-control" style="border-left: 3px solid #FA906E;" required>
            </div>

            <div class="form-group">
                <label class="text-normal text-dark">Bir güvenlik sorusu seçiniz</label>
                <select name="security-question" id="security-question" class="ui selection dropdown fluid">
                    <c:forEach var="question" items="${securityQuestions}">
                        <option value="${question.id}">${question.question}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label class="text-normal text-dark">Güvenlik sorusu cevabınız</label>
                <input type="text" name="security-question-answer" class="form-control" style="border-left: 3px solid #FA906E;" required>

            </div>


            <hr>
            <div class="form-group">
                <div class="peers ai-c jc-sb fxw-nw">
                    <button class="btn btn-primary centered-by-margin" id="register-button" type="submit">Kayıt Ol</button>
                </div>
            </div>
        </form>
        <br>
        <hr>
        <a class="links" href="/giris" style="float: left;">Zaten bir hesabım var</a>
        <a class="links" href="/" style="float: right;">Anasayfaya Dön</a>
        <br>
    </div>

</div>
<script type="text/javascript" src="../static/js/vendor.js"></script>
<script type="text/javascript" src="../static/js/bundle.js"></script>
<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../static/semantic/semantic.min.js"></script>
<script>
    $("#security-question").dropdown();
</script>

</body>
</html>
