// 设置填写年份
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

//为教学任务添加单元格
$("#add1").click(function () {
    var s = $("#row1").attr("rowspan")
    $("#row1").attr("rowspan", Number(s) + 1)
    $("#a").before($("<tr></tr>").append($("<td></td>").append(Number(s) + 1)).append($("<td><input type='text'></td>").attr("colspan", 2)))
})

//为科研及成果添加单元格
$("#add2").click(function () {
    var s = $("#row2").attr("rowspan")
    $("#row2").attr("rowspan", Number(s) + 1)
    $("#b").before($("<tr></tr>").append($("<td></td>").append(Number(s) + 1)).append($("<td><input type='text'></td>").attr("colspan", 2)))
})

//为其它方面工作添加单元格
$("#add3").click(function () {
    var s = $("#row3").attr("rowspan")
    $("#row3").attr("rowspan", Number(s) + 1)
    $("#c").before($("<tr></tr>").append($("<td></td>").append(Number(s) + 1)).append($("<td><input type='text'></td>").attr("colspan", 2)))
})

//为获奖情况添加单元格
$("#add4").click(function () {
    var s = $("#row4").attr("rowspan")
    $("#row4").attr("rowspan", Number(s) + 1)
    $("#d").before($("<tr></tr>").append($("<td></td>").append(Number(s) + 1)).append($("<td><input type='text'></td>").attr("colspan", 2)))
})

$("#summary").blur(function () {
    var info = $(this).val()
    if(info.length>100){
        alert("字数超限")
    }
})

$("#sub").click(function () {
    // 获取教学任务模块的值
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

    if(teachingTask == "" || scientificResearch == "" || otherWork == ""|| winAward == "" || summary == ""){
        alert("请填写完整的年度述职")
    }else if(summary.length>100){
        alert("工作亮点小结字数超限")
    }else{
        $.ajax({
            url: "/edumanagement/teacher/upload_year_debriefing",
            type: "post",
            data: {
                year: year, teachingTask: teachingTask, scientificResearch: scientificResearch,
                otherWork: otherWork, winAward: winAward, summary: summary
            },
            async: false,
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json',
            success: function (result) {
                alert("提交成功")
                window.location.href = "/edumanagement/teacher/year_debriefing"
            }
        })
    }
})

$("#reset").click(function () {
    window.location.reload()
})