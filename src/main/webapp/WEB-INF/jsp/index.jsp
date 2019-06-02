<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row mt-5">
        <h1 id="promo" data-promo-id="${promoId}">Акция "${promoName}"!</h1>
    </div>
    <div class="row mt-3 d-none promo-empty">
        К сожалению, подарки для данной акции закончились.
    </div>
    <div id="bonus-message" class="font-weight-bold text-success row mt-3 d-none">
        Вы забрали подарок: <span class="ml-2" id="bonus-name"></span>
    </div>
    <c:choose>
        <c:when test="${empty bonuses && totalBonusCounts == 0}">
            <div class="row mt-3 promo-empty">
                К сожалению, подарки для данной акции закончились.
            </div>
        </c:when>
        <c:otherwise>
            <div class="wrapper">
                <div class="row mt-3">
                    Осталось подарков: <span class="ml-2" id="total-bonus-count">${totalBonusCounts}</span>
                </div>
                <div class="row mt-3">
                    <form>
                        <div class="form-row align-items-center">
                            <div class="col-auto">
                                <label class="sr-only" for="inlineFormInput">Name</label>
                                <input type="text" class="form-control mb-2" id="filter" name="filter"
                                       placeholder="" value="${filter}">
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-outline-primary mb-2">Фильтровать
                                </button>
                            </div>
                            <div class="col-auto">
                                <button type="submit" id="filter-reset" class="btn btn-outline-danger mb-2">Показать
                                    все
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row mt-3">
                    <ul class="list-group col-7">
                        <c:forEach var="b" items="${bonuses}">
                            <li class="list-group-item d-flex justify-content-between align-items-center bonus-row"
                                data-id="${b.id}">
                                    ${b.bonusName}
                                <span class="badge">
                                <button type="button" class="btn btn-outline-success get-bonus">Забрать</button>
                            </span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        var promoId = $('#promo').attr('data-promo-id');
        $('#filter-reset').on('click', function (e) {
            e.preventDefault();
            window.location.href = window.location.origin + "/" + promoId + "?filter=";
        });
        $(".get-bonus").on('click', function () {
            //var filter = getFilter("filter");
            var bonusId = $(this).closest('li').attr('data-id');
            //var promoId = $('#promo').attr('data-promo-id');
            //, filter: filter
            $.post("/update", {promoId: promoId, bonusId: bonusId}, function (data, status) {
                if (parseInt(data.totalBonusCount) > 0) {
                    $('#total-bonus-count').html(data.totalBonusCount);
                    $('#bonus-name').html(data.bonusName);
                    $('#bonus-message').removeClass('d-none');
                    if (parseInt(data.bonusCount) === 0) {
                        $(".bonus-row[data-id='" + bonusId + "']").remove();
                    }
                }
                else {
                    $('.promo-empty').removeClass('d-none');
                    $('.wrapper').remove();
                }
            });
        });
    });

    // function getFilter(filterParamName) {
    //     var url_string = window.location.href;
    //     var url = new URL(url_string);
    //     return url.searchParams.get(filterParamName);
    // }
</script>
</body>
</html>