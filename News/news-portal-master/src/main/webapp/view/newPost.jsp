<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
    <title>Dashboard</title>
    <style>
        #loader {
            transition: all .3s ease-in-out;
            opacity: 1;
            visibility: visible;
            position: fixed;
            height: 100vh;
            width: 100%;
            background: #fff;
            z-index: 90000
        }

        #loader.fadeOut {
            opacity: 0;
            visibility: hidden
        }

        .spinner {
            width: 40px;
            height: 40px;
            position: absolute;
            top: calc(50% - 20px);
            left: calc(50% - 20px);
            background-color: #333;
            border-radius: 100%;
            -webkit-animation: sk-scaleout 1s infinite ease-in-out;
            animation: sk-scaleout 1s infinite ease-in-out
        }

        @-webkit-keyframes sk-scaleout {
            0% {
                -webkit-transform: scale(0)
            }
            100% {
                -webkit-transform: scale(1);
                opacity: 0
            }
        }

        @keyframes sk-scaleout {
            0% {
                -webkit-transform: scale(0);
                transform: scale(0)
            }
            100% {
                -webkit-transform: scale(1);
                transform: scale(1);
                opacity: 0
            }
        }</style>
    <link rel="stylesheet" href="../static/css/style.css">
    <link rel="stylesheet" href="../static/css/newPost.css">
    <link rel="stylesheet" href="../static/semantic/semantic.css">
</head>
<body class="app" style="background-image: url(../static/img/gazette.jpg)">
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
<div style="border-radius: 10px;">

    <div class="container">
        <!-- NAVBAR -->
        <%@include file="static/navbar.jsp"%>

        <main class="main-content bgc-grey-100" style="padding-left: 20px;">

            <c:choose>
                <c:when test="${updatePost == true}">

                    <form class="ui form" action="/haber/update/${post.id}" method="post">
                        <div class="field">
                            <div class="two fields">
                                <div class="field">
                                    <input type="text" name="title" placeholder="Başlık" value="${post.title}" required>
                                </div>
                                <div class="field">
                                    <select name="post-group" id="post-update-group" class="ui selection dropdown fluid">
                                        <c:forEach var="group" items="${groups}">
                                            <option value="${group.id}">${group.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-9">
                                <div class="field">
                                    <textarea name="content" rows="20" placeholder="İçerik" required>${post.content}</textarea>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <img src="${post.imageUrl}" alt="Resim Bulunamadı" id="post-img">
                                <br>
                                <div class="field">
                                    <input type="text" name="img-url" id="img-url" placeholder="Resim Urlsi" value="${post.imageUrl}" required>
                                </div>
                                <br>
                                <div class="ui slider checkbox privacy-div">
                                    <c:if test="${!post.privacy}">
                                        <input type="checkbox" name="privacy" checked>
                                        <label>Herkese Açık</label>
                                    </c:if>
                                    <c:if test="${post.privacy}">
                                        <input type="checkbox" name="privacy">
                                        <label>Herkese Açık</label>
                                    </c:if>

                                </div>
                            </div>

                        </div>

                        <button class="ui button" type="submit" id="update-post-btn">Oluştur</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="ui form" action="/haber/create" method="post">
                        <div class="field">
                            <div class="two fields">
                                <div class="field">
                                    <input type="text" name="title" id="title" placeholder="Başlık" required>
                                </div>
                                <div class="field">
                                    <select name="post-group" id="post-group" class="ui selection dropdown fluid">
                                        <c:forEach var="group" items="${groups}">
                                            <option value="${group.id}">${group.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-9">
                                <div class="field">
                                    <textarea name="content" rows="20" placeholder="İçerik" required></textarea>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <img src="../static/img/questionMark.png" alt="Resim Bulunamadı" id="post-img">
                                <br>
                                <div class="field">
                                    <input type="text" name="img-url" id="img-url" placeholder="Resim Urlsi" required>
                                </div>
                                <br>
                                <div class="ui slider checkbox privacy-div">
                                    <input type="checkbox" name="privacy" checked>
                                    <label>Herkese Açık</label>
                                </div>
                            </div>
                        </div>



                        <button class="ui button" type="submit" id="new-post-btn">Oluştur</button>
                    </form>

                </c:otherwise>
            </c:choose>

        </main>

    </div>
</div>


<script type="text/javascript" src="../static/js/vendor.js"></script>
<script type="text/javascript" src="../static/js/bundle.js"></script>
<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../static/semantic/semantic.min.js"></script>


<script>
    $("#post-group").dropdown();
    document.getElementById("img-url").addEventListener("input", function () {
        if(this.value == ""){
            document.getElementById("post-img").src = "../static/img/questionMark.png";
        }else{
            document.getElementById("post-img").src = this.value;
        }
    });
    $('#post-update-group').dropdown('set selected', '${post.postGroup.id}');


</script>
</body>
</html>
