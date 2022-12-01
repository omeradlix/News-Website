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
    <link rel="stylesheet" href="../static/css/allPostGroups.css">
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
                       <th>Kategori</th>
                       <th>Açıklama</th>
                       <th>Oluşturulma Tarihi</th>
                       <th>Son Güncelleme Tarihi</th>
                       <th>Güncelle</th>
                   </tr>
                </thead>
                <tbody>
                   <c:forEach var="group" items="${postGroups}">
                       <tr>
                           <td style="text-align: center;" class="del-column" data-toggle="modal" data-target="#delete-group-${group.id}">
                               <i class="far fa-trash-alt"></i>
                           </td>
                           <div class="modal fade" id="delete-group-${group.id}" tabindex="-1" role="dialog" aria-labelledby="delete-group-${group.id}" style="display: none;" aria-hidden="true">
                               <div class="modal-dialog" role="document">
                                   <div class="modal-content">
                                       <form action="/kategori/delete/${group.id}" method="post">
                                           <div class="modal-header"><h5 class="modal-title" id="exampleModalLabel">Kategori Sil</h5>
                                               <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                           </div>
                                           <div class="modal-body">'${group.name}' isimli kategoriyi silmek istiyor musunuz ?</div>
                                           <div class="modal-footer">
                                               <button type="button" class="btn btn-secondary" data-dismiss="modal">Vazgeç</button>
                                               <button type="submit" class="btn btn-danger">&nbsp;	&nbsp;	Sil &nbsp;	&nbsp;	</button>
                                           </div>
                                       </form>

                                   </div>
                               </div>
                           </div>

                           <td>${group.name}</td>
                           <td>${group.description}</td>
                           <td>${group.getTimestampsAsReadable(group.created_at)}</td>
                           <td>${group.getTimestampsAsReadable(group.updated_at)}</td>
                           <td style="text-align: center;" class="edit-column" data-toggle="modal" data-target="#edit-group-${group.id}">
                               <i class="far fa-edit"></i>
                           </td>
                           <div class="modal fade" id="edit-group-${group.id}" tabindex="-1" role="dialog" aria-labelledby="edit-group-${group.id}" aria-hidden="true">
                               <div class="modal-dialog" role="document">
                                   <form action="/kategori/edit/${group.id}" method="post">
                                       <div class="modal-content">
                                           <div class="modal-header">
                                               <h5 class="modal-title">Kategori Güncelle</h5>
                                               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                   <span aria-hidden="true">&times;</span>
                                               </button>
                                           </div>
                                           <div class="modal-body">

                                               <div class="form-group">
                                                   <label for="category-name">Kategori Adı</label>
                                                   <input name="category-name" type="text" class="form-control" required value="${group.name}">
                                               </div>
                                               <div class="form-group">
                                                   <label for="category-description">Açıklama</label>
                                                   <textarea name="category-description" type="text" class="form-control" rows="4" required>${group.description}</textarea>
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

                       </tr>
                   </c:forEach>
                </tbody>
                <tfoot class="full-width">
                    <tr>

                        <th colspan="6">
                            <div class="ui right floated small icon button" type="button" data-toggle="modal" data-target="#create-category" style="color: white; background-color: #FA906E;">
                                Kategori Ekle
                            </div>

                        </th>
                    </tr>
                </tfoot>
            </table>
        </main>
        <div class="modal fade" id="create-category" tabindex="-1" role="dialog" aria-labelledby="create-category" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <form action="/kategori/create" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Yeni Kategori</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="form-group">
                                <label for="category-name">Kategori Adı</label>
                                <input name="category-name" type="text" class="form-control" id="category-name" required>
                            </div>
                            <div class="form-group">
                                <label for="category-description">Açıklama</label>
                                <textarea name="category-description" type="text" class="form-control" id="category-description" rows="4" required></textarea>
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


</script>
</body>
</html>
