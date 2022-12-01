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
    <link rel="stylesheet" href="../static/css/searchPost.css">
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
        <main class="main-content bgc-grey-100">
            <form action="/haber/filter" method="post">
                <div class="row">
                        <div class="col-md-6">

                            <select name="time-filter" id="time-filter" class="ui selection dropdown fluid">
                                <option value="current">En son eklenen üstte</option>
                                <option value="dated">En son eklenen altta</option>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <select name="post-group-filter" id="post-group-filter" class="ui selection dropdown fluid multiple" required>
                                <c:forEach var="group" items="${postGroups}">
                                    <option value="">Lütfen kategori seçiniz</option>
                                    <option value="${group.id}">${group.name}</option>
                                </c:forEach>
                            </select>

                        </div>
                </div>
                <div class="row">
                    <button type="submit" class="btn" id="filter-button">Filtrele</button>
                </div>
            </form>
            <%@include file="static/commonPost.jsp"%>



        </main>

    </div>
</div>


<script type="text/javascript" src="../static/js/vendor.js"></script>
<script type="text/javascript" src="../static/js/bundle.js"></script>
<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../static/semantic/semantic.min.js"></script>
</body>

<script>
    <c:if test="${timeFilter != null}">
        $("#time-filter").dropdown('set selected', '${timeFilter}');
    </c:if>
    <c:if test="${timeFilter == null}">
        $("#time-filter").dropdown();
    </c:if>

    const selectedPostGroups = ${postGroupFilter};
    let selectedPostGroupsAsString = [];
    for(const group of selectedPostGroups)
        selectedPostGroupsAsString.push(group.toString());

    $("#post-group-filter").dropdown('set selected',  selectedPostGroupsAsString);

</script>
</html>
