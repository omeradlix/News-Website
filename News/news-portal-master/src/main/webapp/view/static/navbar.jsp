<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<style>
    .nav-item{
        margin-right: 15px;
        margin-left: 15px;
        font-size: 18px;
    }

</style>
<link rel="stylesheet" href="../webjars/font-awesome/5.12.0/css/all.css">
<nav class="navbar navbar-expand-lg navbar-light bg-light" style="border-bottom: 2px solid lightslategray;">
    <a class="navbar-brand" href="/">
        <img src="../static/img/logo.png" alt="" style="width: 246px; border-radius: 5px;">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse nav-right" id="navbarNavDropdown" style="border-radius: 8px; background-color: #FCB694;">
        <ul class="navbar-nav">
            <li class="nav-item ${homeActive}">
                <a class="nav-link" href="/">Anasayfa</a>
            </li>
            <li class="nav-item ${filterActive}">
                <a class="nav-link" href="/haber/filtre">Filtre</a>
            </li>
            <c:choose>
                <c:when test="${sessionScope.isLoggedIn}">
                    <c:choose>
                        <c:when test="${sessionScope.groupId == 1}">
                            <li class="nav-item ${postActive}">
                                <a class="nav-link" href="/haber/listele">Haber Onayla</a>
                            </li>
                            <li class="nav-item ${categoryActive}">
                                <a class="nav-link" href="/kategori/listele">Kategori</a>
                            </li>
                            <li class="nav-item ${editorActive}">
                                <a class="nav-link" href="/editor/listele">Editör</a>
                            </li>
                        </c:when>
                        <c:when test="${sessionScope.groupId == 2}">
                            <li class="nav-item dropdown ${newPostActive}" >
                                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Haber
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <a class="dropdown-item" href="/haber/yeni">Haber Ekle</a>
                                    <a class="dropdown-item" href="/haber/listele">Haber Güncelle / Sil</a>
                                </div>
                            </li>
                        </c:when>
                    </c:choose>

                </c:when>
            </c:choose>





<%--            <li class="nav-item dropdown">--%>
<%--                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                    Dropdown link--%>
<%--                </a>--%>
<%--                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">--%>
<%--                    <a class="dropdown-item" href="#">Action</a>--%>
<%--                    <a class="dropdown-item" href="#">Another action</a>--%>
<%--                    <a class="dropdown-item" href="#">Something else here</a>--%>
<%--                </div>--%>
<%--            </li>--%>
        </ul>

        <ul class="navbar-nav ml-auto">

            <c:choose>
                <c:when test="${sessionScope.isLoggedIn}">
                    <li class="nav-item ${profileActive}">
                        <a class="nav-link" href="/profil">
                            <i class="fas fa-user-tag"></i> &nbsp;Profil
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">
                            <i class="fas fa-power-off"></i> &nbsp;Çıkış
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/giris">Giriş Yap</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/kayit-ol">Kayıt Ol</a>
                    </li>
                </c:otherwise>
            </c:choose>


        </ul>
    </div>
</nav>
