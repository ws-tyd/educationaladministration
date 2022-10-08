$(function () {

    build_select();

})

function build_select() {
    $.ajax({
        url: "/edumanagement/teacher/select_annual_assessment",
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
                $("#herf").css("display","none")
                alert("当前尚未填写")
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
        url: "/edumanagement/teacher/select_annualassessment_year_info",
        type: "get",
        async: false,
        dataType: 'json',
        data: {
            year: year
        },
        success: function (result) {
            build_table(result);

        }
    })
}

function build_table(result) {
    var assessmentInfo = result.extend.assessmentInfo;
    $("#persona").val(assessmentInfo.personalsummary)
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
    window.location.href = localhostPath + projectName + "/static/down_annual_assessment?year=" + year;
})

$("#submit").click(function () {

    if ($(this).attr("flag" ) == 1) {       //判断修改按钮属性值 1：修改   2：保存
        $("#persona").removeAttr("readonly")
        $(this).html("保存")
        $(this).attr("flag", 2)
    }else{
        var year = $("#year").val()
        var personalSummary = $("#persona").val()
        var remark = $("#remark").val()

        $.ajax({
            url:"/edumanagement/teacher/upload_annual_assessment",
            type:"post",
            async: false,
            data:{
                personalSummary: personalSummary, year: year, remark: remark
            },
            dataType: 'json',
            success:function () {
                alert("修改成功")
                window.location.reload(location.href)
            }
        })
        $(this).html("修改")
        $(this).attr("flag", 1)
    }
})
