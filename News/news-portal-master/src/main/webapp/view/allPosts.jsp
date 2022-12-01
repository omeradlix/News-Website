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
    <link rel="stylesheet" href="../static/css/allPosts.css">
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
            <table class="ui celled striped table">
                <thead class="full-width">
                    <tr>
                        <c:if test="${edit_or_delete_post_permission}">
                            <th>Sil</th>
                        </c:if>
                        <th>Başlık</th>
                        <th>Tür</th>
                        <th>Yazar</th>
                        <th>Tarih</th>
                        <th>Görünebilirlik (Herkes)</th>
                        <th>Onaylandı</th>
                        <c:if test="${edit_or_delete_post_permission}">
                            <th>Güncelle</th>
                        </c:if>
                        <c:if test="${approve_post_permission}">
                            <th>Önizle</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="post" items="${posts}">
                        <tr>
                            <c:if test="${edit_or_delete_post_permission}">
                                <td style="text-align: center;">
                                    <i class="fas fa-trash" data-toggle="modal" data-target="#delete-post-${post.id}"></i>
                                </td>
                                <div class="modal fade" id="delete-post-${post.id}" tabindex="-1" role="dialog" aria-labelledby="delete-post-${post.id}" style="display: none;" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <form action="/haber/delete/${post.id}" method="post">
                                                <div class="modal-header"><h5 class="modal-title" id="exampleModalLabel">Haber Sil</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                </div>
                                                <div class="modal-body">'${post.title}' başlıklı haberi silmek istiyor musunuz ?</div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Vazgeç</button>
                                                    <button type="submit" class="btn btn-danger">&nbsp;	&nbsp;	Sil &nbsp;	&nbsp;	</button>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>

                            </c:if>
                            <td>${post.title}</td>
                            <td>${post.postGroup.name}</td>
                            <td>${post.getAuthorNameAndLastname()}</td>
                            <td>${post.getLocalDateTimeAsReadable(post.created_at)}</td>
                            <c:choose>
                                <c:when test="${!post.privacy}">
                                    <td class="center aligned">
                                        <i class="large green checkmark icon"></i>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${post.verified}">
                                    <td class="center aligned">
                                        <i class="large green checkmark icon"></i>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${edit_or_delete_post_permission}">
                                <td style="text-align: center;">
                                    <a href="/haber/duzenle/${post.id}"><i class="far fa-edit"></i></a>
                                </td>
                            </c:if>
                            <c:if test="${approve_post_permission}">
                               <td style="text-align: center;">
                                   <a href="/haber/onizle/${post.id}">
                                       <i class="fas fa-search"></i>
                                   </a>
                               </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>

    </div>
</div>


<script type="text/javascript" src="../static/js/vendor.js"></script>
<script type="text/javascript" src="../static/js/bundle.js"></script>
<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../static/semantic/semantic.min.js"></script>


<script>


</script>
</body>
</html>
