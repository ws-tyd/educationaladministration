
// 设置填写年份
$(function () {

    build_select();

})

function build_select() {
    $.ajax({
        url: "select_debriefing_year",
        type: "get",
        success: function (result) {
            console.log(result)
            var year = result.extend.year;
            //默认页面年份查询
            a(year[0].year)
            $.each(year, function (index, item) {
                $("<option></option>").append(item.year)
                    .appendTo("#year");
            })
        }
    })
}

$("#year").on("change", function () {
    var year = this.value
    a(year)
})

function a(year) {
    $.ajax({
        url: "select_debriefing_year_info",
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
    var debriefingInfo = result.extend.debriefingInfo;

    var teachingTask = debriefingInfo.teachingtask;
    var achievementsInScientific = debriefingInfo.achievementsinscientificresearch;
    var otherWork = debriefingInfo.otherwork;
    var winAward = debriefingInfo.winaward;
    var summary = debriefingInfo.summary;


    $("#content").empty()
    var s = teachingTask.split("/")
    $("#content").append($("<tr></tr>").append($("<td></td>").attr("rowspan", s.length).append("教学任务"))
        .append($("<td></td>").append(1)).append($("<td></td>").attr("colspan", 2).append(s[0])))
    $.each(s, function (index, item) {
        if (index != 0) {
            var a = $("<td></td>").append(index + 1);
            var b = $("<td></td>").attr("colspan", 2).append(item);
            $("#content").append($("<tr></tr>").append(a).append(b))
        }
    })


    s = achievementsInScientific.split("/")
    $("#content").append($("<tr></tr>").append($("<td></td>").attr("rowspan", s.length).append("科研及成果"))
        .append($("<td></td>").append(1)).append($("<td></td>").attr("colspan", 2).append(s[0])))
    $.each(s, function (index, item) {
        if (index != 0) {
            var a = $("<td></td>").append(index + 1);
            var b = $("<td></td>").attr("colspan", 2).append(item);
            $("#content").append($("<tr></tr>").append(a).append(b))
        }
    })

    s = otherWork.split("/")
    $("#content").append($("<tr></tr>").append($("<td></td>").attr("rowspan", s.length).append("其它方面工作"))
        .append($("<td></td>").append(1)).append($("<td></td>").attr("colspan", 2).append(s[0])))
    $.each(s, function (index, item) {
        if (index != 0) {
            var a = $("<td></td>").append(index + 1);
            var b = $("<td></td>").attr("colspan", 2).append(item);
            $("#content").append($("<tr></tr>").append(a).append(b))
        }
    })

    s = winAward.split("/")
    $("#content").append($("<tr></tr>").append($("<td></td>").attr("rowspan", s.length).append("获奖情况"))
        .append($("<td></td>").append(1)).append($("<td></td>").attr("colspan", 2).append(s[0])))
    $.each(s, function (index, item) {
        if (index != 0) {
            var a = $("<td></td>").append(index + 1);
            var b = $("<td></td>").attr("colspan", 2).append(item);
            $("#content").append($("<tr></tr>").append(a).append(b))
        }
    })

    $("#content").append($("<tr></tr>").append($("<td></td>").attr("rowspan", 3).append("工作亮点小结<br>(不超过100字)"))
        .append($("<td></td>").attr("colspan", 3).attr("rowspan", 3).append(summary)))
    $("#content").append($("<tr></tr>"))
    $("#content").append($("<tr></tr>"))

}

//1、使用 window.locaion.href 获得项目的根路径
var curWwwPath = window.location.href;
//2、获得主机地址之后 的目录
var pathname = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathname);
//3、获得主机地址
var localhostPath = curWwwPath.substring(0, pos);

$("#herf").click(function () {
    var year = $("#year").val()
    window.location.href = localhostPath + "/static/down_year_debriefing?year="+year;
})

$("#dayin").click(function () {
    $("#dayin").css('display', 'none')
    $("#herf").css("display", "none")

    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint-->"; //开始打印标识字符串有17个字符
    eprnstr="<!--endprint-->"; //结束打印标识字符串
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); //从开始打印标识之后的内容
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); //截取开始标识和结束标识之间的内容
    window.print(); //调用浏览器的打印功能打印指定区域
    window.document.body.innerHTML=bdhtml;//重新给页面内容赋值；
    window.location.reload();
    return false;
})