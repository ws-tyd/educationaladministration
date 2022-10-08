$(function () {

    build_select();

})

function build_select() {
    $.ajax({
        url: "/edumanagement/teacher/select_technical_personnel_year",
        type: "get",
        success: function (result) {
            if (result.code == 100) {
                var year = result.extend.year;
                // 默认页面年份查询
                a(year[0].year)
                $.each(year, function (index, item) {
                    $("<option></option>").append(item.year)
                        .appendTo("#year");
                })
            } else {
                alert("当前尚未填写")
                $("#herf").css("display","none")
            }
        }
    })
}

$("#year").on("change", function () {
    var year = this.value
    a(year)
})

function a(year) {
    $.ajax({
        url: "/edumanagement/teacher/select_technicalpersonnel_year_info",
        type: "get",
        async: false,
        dataType: 'json',
        data: {
            year: year
        },
        success: function (result) {
            console.log(result);
            build_table(result);
        }
    })
}

function build_table(result) {
    var info = result.extend.technicalPersonnel
    $("#mainAchievements").val(info.mainachievements)
    $("#attendance").val(info.attendance)
    $("#rewardsAndPunishments").val(info.rewardsandpunishments)
}

//1、使用 window.locaion.href 获得项目的根路径
var curWwwPath = window.location.href;
//2、获得主机地址之后 的目录
var pathname = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathname);
//3、获得主机地址
var localhostPath = curWwwPath.substring(0, pos);
//4、获取项目名称
var projectName = pathname.substring(0, pathname.substr(1).indexOf('/') + 1);

$("#herf").click(function () {
    var year = $("#year").val()
    window.location.href = localhostPath + projectName + "/static/down_technical_personnel?year=" + year;
})

$("#submit").click(function () {

    if ($(this).attr("flag" ) == 1) {       //判断修改按钮属性值 1：修改   2：保存
        $("#mainAchievements").removeAttr("readonly")
        $("#attendance").removeAttr("readonly")
        $("#rewardsAndPunishments").removeAttr("readonly")
        $(this).html("保存")
        $(this).attr("flag", 2)
    }else{
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
                alert("修改成功")
                window.location.reload(location.href)
            }
        })
        $(this).html("修改")
        $(this).attr("flag", 1)
    }
})
