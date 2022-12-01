<c:forEach var="post" items="${posts}">
    <div class="row post-row">
        <div class="col-md-9">
            <div class="row">
                <div class="col-md-8">
                    <h4>
                        <a href="/haber/${post.id}">${post.title} </a>
                    </h4>
                </div>
                <div class="col-md-4">
                                <span class="post-time">
                                        ${post.getLocalDateTimeAsReadable(post.created_at)}
                                </span>
                </div>

            </div>
            <div class="row">
                <c:choose>
                    <c:when test="${post.content.length() > 460}">
                        <p> ${post.content.substring(0, 460)} ...</p>
                    </c:when>
                    <c:otherwise>
                        <p> ${post.content}</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-md-3">
            <img src="${post.imageUrl}" alt="" class="post-img" style="float: right;">
        </div>
    </div>
    <div class="row post-group-name-row">

        <span class="post-group-name">${post.postGroup.name}</span>


    </div>

</c:forEach>
