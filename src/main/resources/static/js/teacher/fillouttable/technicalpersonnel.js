$(function () {
    var myDate = new Date;
    var year = myDate.getFullYear(); //获取当前年

    var lastYear = year - 1
    var thisYear = year
    var nextYear = year + 1

    $("#year").append($("<option>" + lastYear + "</option>").attr("value", lastYear))
        .append($("<option>" + thisYear + "</option>").attr("selected", "selected").attr("value", thisYear))
        .append($("<option>" + nextYear + "</option>").attr("value", nextYear))
})

$("#sub").click(function () {
    var year = $("#year").val()
    var mainAchievements = $("#mainAchievements").val()
    var attendance = $("#attendance").val()
    var rewardsAndPunishments = $("#rewardsAndPunishments").val()

    $.ajax({
        url: "/edumanagement/teacher/upload_technical_personnel",
        type: "post",
        async: false,
        data: {
            year: year, mainAchievements: mainAchievements, attendance: attendance, rewardsAndPunishments: rewardsAndPunishments
        },
        contentType: 'application/x-www-form-urlencoded',
        dataType: 'json',
        success:function (result) {
            alert("提交成功")
            window.location.reload()
        }
    })
})

$("#reset").click(function () {
    window.location.reload()
})