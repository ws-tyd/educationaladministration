// 设置填写年份
$(function () {

    build_select();

})

function build_select() {
    $.ajax({
        url: "/edumanagement/teacher/select_debriefing_term",
        type: "get",
        success: function (result) {
            var year = result.extend.year;
            //默认页面年份查询
            a(year[0], "上")
            $.each(year, function (index, item) {
                $("<option></option>").append(item)
                    .appendTo("#year");
            })
        }
    })
}

$("#year").on("change", function () {
    var year = this.value
    var term = $("#term").val()
    a(year, term)
})

$("#term").on("change", function () {
    var term = this.value
    var year = $("#year").val()
    a(year, term)
})

function a(year, term) {
    $.ajax({
        url: "/edumanagement/teacher/select_debriefing_term_info",
        type: "get",
        async: false,
        dataType: 'json',
        data: {
            year: year, term: term
        },
        success: function (result) {
            build_table(result);
        }
    })
}

function build_table(result) {
    var debriefingInfo = result.extend.debriefingInfo;
    if (debriefingInfo != null) {
        $("#herf").css({display: "block"});
        $("#dayin").css({display: "block"});
        $("#submit").css({visibility: "visible"});
        var teachingTask = debriefingInfo.teachingtask;
        var achievementsInScientific = debriefingInfo.achievementsinscientificresearch;
        var otherWork = debriefingInfo.otherwork;
        var winAward = debriefingInfo.winaward;
        var summary = debriefingInfo.summary;


        $("#content").empty()
        var s = teachingTask.split("/")
        $("#content").append($("<tr></tr>").append($("<td  id='row1'></td>").attr("rowspan", s.length).append("教学任务"))
            .append($("<td></td>").append(1)).append($("<td class='aa'></td>").attr("colspan", 2).append($("<input type='text'></input>").attr("value", s[0]).attr("readonly", "readonly"))))
        $.each(s, function (index, item) {
            if (index != 0) {
                var a = $("<td></td>").append(index + 1);
                var b = $("<td class='aa'></td>").attr("colspan", 2).append($("<input type='text'></input>").attr("value", item).attr("readonly", "readonly"));
                if(index == s.length - 1){
                    $("#content").append($("<tr class='aaa'></tr>").append(a).append(b))
                }else{
                    $("#content").append($("<tr></tr>").append(a).append(b))
                }
            }
        })


        s = achievementsInScientific.split("/")
        $("#content").append($("<tr></tr>").append($("<td  id='row2'></td>").attr("rowspan", s.length).append("科研及成果"))
            .append($("<td></td>").append(1)).append($("<td class='bb'></td>").attr("colspan", 2).append($("<input type='text'></input>").attr("value", s[0]).attr("readonly", "readonly"))))
        $.each(s, function (index, item) {
            if (index != 0) {
                var a = $("<td></td>").append(index + 1);
                var b = $("<td class='bb'></td>").attr("colspan", 2).append($("<input type='text'></input>").attr("value", item).attr("readonly", "readonly"));
                if(index == s.length - 1){
                    $("#content").append($("<tr class='bbb'></tr>").append(a).append(b))
                }else{
                    $("#content").append($("<tr></tr>").append(a).append(b))
                }
            }
        })

        s = otherWork.split("/")
        $("#content").append($("<tr></tr>").append($("<td  id='row3'></td>").attr("rowspan", s.length).append("其它方面工作"))
            .append($("<td></td>").append(1)).append($("<td class='cc'></td>").attr("colspan", 2).append($("<input type='text'></input>").attr("value", s[0]).attr("readonly", "readonly"))))
        $.each(s, function (index, item) {
            if (index != 0) {
                var a = $("<td></td>").append(index + 1);
                var b = $("<td class='cc'></td>").attr("colspan", 2).append($("<input type='text'></input>").attr("value", item).attr("readonly", "readonly"));
                if(index == s.length - 1){
                    $("#content").append($("<tr class='ccc'></tr>").append(a).append(b))
                }else{
                    $("#content").append($("<tr></tr>").append(a).append(b))
                }
            }
        })

        s = winAward.split("/")
        $("#content").append($("<tr></tr>").append($("<td  id='row4'></td>").attr("rowspan", s.length).append("获奖情况"))
            .append($("<td></td>").append(1)).append($("<td class='dd'></td>").attr("colspan", 2).append($("<input type='text'></input>").attr("value", s[0]).attr("readonly", "readonly"))))
        $.each(s, function (index, item) {
            if (index != 0) {
                var a = $("<td></td>").append(index + 1);
                var b = $("<td class='dd'></td>").attr("colspan", 2).append($("<input type='text'></input>").attr("value", item).attr("readonly", "readonly"));
                if(index == s.length - 1){
                    $("#content").append($("<tr class='ddd'></tr>").append(a).append(b))
                }else{
                    $("#content").append($("<tr></tr>").append(a).append(b))
                }
            }
        })

        $("#content").append($("<tr></tr>").append($("<td></td>").attr("rowspan", 3).append("工作亮点小结<br>(不超过100字)"))
            .append($("<td></td>").attr("colspan", 3).attr("rowspan", 3).append($("<input id='summary'></input>").attr("value", summary).attr("readonly", "readonly"))))
        $("#content").append($("<tr></tr>"))
        $("#content").append($("<tr></tr>"))
    } else {
        $("#content").empty()
        $("#herf").css({display: "none"});
        $("#dayin").css({display: "none"});
        $("#submit").css({visibility: "hidden"});
        alert("当前年份或学期还未填写，请前往填写")
        // window.location.href = "term_debriefing"
    }
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
    var term = $("#term").val()
    window.location.href = localhostPath + projectName + "/static/down_debriefing?year=" + year + "&term=" + term;
})


$("#dayin").click(function () {
    var year = $("#year").val()
    var term = $("#term").val()
    window.open('/edumanagement/teacher/print_term_debriefing?year=' + year + '&term=' + term, '_blank', 'height=800,width=700,left=0,top=0,menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=yes,resizable=yes')
})

//单击修改按钮
$("#submit").click(function () {
    if ($(this).attr("flag" ) == 1) {       //判断修改按钮属性值 1：修改   2：保存
        $("input").each(function () {
            $(this).removeAttr("readonly");
        })

        //为教学任务框架添加按钮
        $(".aaa").after($("<tr id='a'></tr>").append($("<td></td>").attr("colspan", 4).append($("<button type='button' class='btn btn-primary' id='add1'>添加</button>"))))
        $(".bbb").after($("<tr id='b'></tr>").append($("<td></td>").attr("colspan", 4).append($("<button type='button' class='btn btn-primary' id='add2'>添加</button>"))))
        $(".ccc").after($("<tr id='c'></tr>").append($("<td></td>").attr("colspan", 4).append($("<button type='button' class='btn btn-primary' id='add3'>添加</button>"))))
        $(".ddd").after($("<tr id='d'></tr>").append($("<td></td>").attr("colspan", 4).append($("<button type='button' class='btn btn-primary' id='add4'>添加</button>"))))

        $(this).html("保存")
        $(this).attr("flag", 2)
    }else{

        var teachingTask = '';
        $(".aa input[type=text]").each(function (it) {
            if (this.value != "") {
                teachingTask += this.value;
                teachingTask += "/"
            }
        })
        teachingTask = teachingTask.substring(0, teachingTask.length - 1)

        // 获取科研及成果模块的值
        var scientificResearch = '';
        $(".bb input[type=text]").each(function () {
            if (this.value != "") {
                scientificResearch += this.value;
                scientificResearch += "/"
            }
        })
        scientificResearch = scientificResearch.substring(0, scientificResearch.length - 1)


        // 获取其它方面工作模块的值
        var otherWork = ''
        $(".cc input[type=text]").each(function () {
            if (this.value != "") {
                otherWork += this.value;
                otherWork += "/"
            }
        })
        otherWork = otherWork.substring(0, otherWork.length - 1)

        // 获取获奖情况模块的值
        var winAward = ''
        $(".dd input[type=text]").each(function () {
            if (this.value != "") {
                winAward += this.value;
                winAward += "/"
            }
        })
        winAward = winAward.substring(0, winAward.length - 1)

        // 获取总结模块的值
        var summary = $("#summary").val()

        var year = $("#year").val()
        var term = $("#term").val()

        $.ajax({
            url: "/edumanagement/teacher/upload_term_debriefing",
            type: "post",
            data: {
                year: year, term: term, teachingTask: teachingTask, scientificResearch: scientificResearch,
                otherWork: otherWork, winAward: winAward, summary: summary
            },
            async: false,
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json',
            success: function () {
                alert("修改成功")
                window.location.reload(location.href)
            }
        })
        $(this).html("修改")
        $(this).attr("flag", 1)
    }
})

//为科研及成果添加单元格
$(document).on("click", "#add1", function () {
    var s = $("#row1").attr("rowspan")
    $("#row1").attr("rowspan", Number(s) + 1)
    $("#a").before($("<tr></tr>").append($("<td></td>").append(Number(s) + 1)).append($("<td class='aa'><input type='text'></td>").attr("colspan", 2)))
})

//为科研及成果添加单元格
$(document).on("click", "#add2", function () {
    var s = $("#row2").attr("rowspan")
    $("#row2").attr("rowspan", Number(s) + 1)
    $("#b").before($("<tr></tr>").append($("<td></td>").append(Number(s) + 1)).append($("<td class='bb'><input type='text'></td>").attr("colspan", 2)))
})

//为其它方面工作添加单元格
$(document).on("click", "#add3", function () {
    var s = $("#row3").attr("rowspan")
    $("#row3").attr("rowspan", Number(s) + 1)
    $("#c").before($("<tr></tr>").append($("<td></td>").append(Number(s) + 1)).append($("<td class='cc'><input type='text'></td>").attr("colspan", 2)))
})

//为获奖情况添加单元格
$(document).on("click", "#add4", function () {
    var s = $("#row4").attr("rowspan")
    $("#row4").attr("rowspan", Number(s) + 1)
    $("#d").before($("<tr></tr>").append($("<td></td>").append(Number(s) + 1)).append($("<td class='dd'><input type='text'></td>").attr("colspan", 2)))
})
