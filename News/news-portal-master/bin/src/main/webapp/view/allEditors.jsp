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
    <link rel="stylesheet" href="../static/css/allEditors.css">
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
                    <th>Sil</th>
                    <th>İsim</th>
                    <th>Soyisim</th>
                    <th>Address</th>
                    <th>Telefon Numarası</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="editor" items="${editors}">
                    <tr>
                        <td style="text-align: center;" class="del-column" data-toggle="modal" data-target="#delete-editor-${editor.id}">
                            <i class="far fa-trash-alt"></i>
                        </td>
                        <div class="modal fade" id="delete-editor-${editor.id}" tabindex="-1" role="dialog" aria-labelledby="delete-editor-${editor.id}" style="display: none;" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <form action="/editor/delete/${editor.id}" method="post">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Editör Sil</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                        </div>
                                        <div class="modal-body">'${editor.name} &nbsp; ${editor.lastname}' isimli kategoriyi silmek istiyor musunuz ?</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Vazgeç</button>
                                            <button type="submit" class="btn btn-danger">&nbsp;	&nbsp;	Sil &nbsp;	&nbsp;	</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>

                        <td>${editor.name}</td>
                        <td>${editor.lastname}</td>
                        <td>${editor.address}</td>
                        <td>${editor.phoneNumber}</td>


                    </tr>
                </c:forEach>
                </tbody>
                <tfoot class="full-width">
                <tr>
                    <th colspan="7">
                        <div class="ui right floated small primary icon button" type="button" data-toggle="modal" data-target="#create-editor" style="color: white; background-color: #FA906E;">
                            Editör Ekle
                        </div>

                    </th>
                </tr>
                </tfoot>
            </table>
        </main>
        <div class="modal fade" id="create-editor" tabindex="-1" role="dialog" aria-labelledby="create-editor" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <form action="/editor/create" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Yeni Editör</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="form-group">
                                <label for="editor-name">İsim</label>
                                <input name="editor-name" type="text" class="form-control" id="editor-name" required>
                            </div>
                            <div class="form-group">
                                <label for="editor-lastname">Soyisim</label>
                                <input name="editor-lastname" type="text" class="form-control" id="editor-lastname" required>
                            </div>
                            <div class="form-group">
                                <label for="editor-username">Kullanıcı Adı</label>
                                <input name="editor-username" type="text" class="form-control" id="editor-username" required>
                            </div>
                            <div class="form-group">
                                <label for="editor-password1">Parola</label>
                                <input name="editor-password1" type="password" class="form-control" id="editor-password1" required>
                            </div>
                            <div class="form-group">
                                <label for="editor-username">Parola (Tekrar)</label>
                                <input name="editor-password2" type="password" class="form-control" id="editor-password2" required>
                            </div>

                            <div class="form-group">
                                <label for="editor-phone-number">Telefon Numarası</label>
                                <input name="editor-phone-number" type="text" class="form-control" id="editor-phone-number" required>
                            </div>
                            <div class="form-group">
                                <label for="editor-address">Address</label>
                                <textarea name="editor-address" type="text" class="form-control" id="editor-address" rows="3" required></textarea>
                            </div>

                            <div class="form-group">
                                <label class="text-normal text-dark">Kategoriler</label>
                                <select name="editor-post-groups" id="editor-post-groups" class="ui selection dropdown fluid multiple" required>
                                    <option value="">Kategori seçiniz</option>
                                    <c:forEach var="group" items="${postGroups}">
                                        <option value="${group.id}">${group.name}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="form-group">
                                <label class="text-normal text-dark">Bir güvenlik sorusu seçiniz</label>
                                <select name="editor-security-question" id="security-question" class="ui selection dropdown fluid">
                                    <c:forEach var="question" items="${securityQuestions}">
                                        <option value="${question.id}">${question.question}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="text-normal text-dark">Güvenlik sorusu cevabınız</label>
                                <input type="text" name="editor-security-question-answer" class="form-control" required>

                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Vazgeç</button>
                            <button type="submit" class="btn btn-primary">Kaydet</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript" src="../static/js/vendor.js"></script>
<script type="text/javascript" src="../static/js/bundle.js"></script>
<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../static/semantic/semantic.min.js"></script>


<script>
    $("#security-question").dropdown();
    $("#editor-post-groups").dropdown();


</script>
</body>
</html>
