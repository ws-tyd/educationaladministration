//1、使用 window.locaion.href 获得项目的根路径
var curWwwPath = window.location.href;
//2、获得主机地址之后 的目录
var pathname = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathname);
//3、获得主机地址
var localhostPath = curWwwPath.substring(0, pos);
//4、获取项目名称
var projectName = pathname.substring(0, pathname.substr(1).indexOf('/') + 1);

function fun(ojb, projectid) {
    var a = $("button").index(ojb);
    var b = $("button").eq(a);
    var c = $("table").find("tr").eq(a).find("th").eq(7);
    var fb;
    if ($.trim($(b).html()) === "关闭课题") fb = 1;
    else fb = 0;
    $.ajax({
        url: localhostPath + projectName + '/static/DK_project',
        type: 'POST',
        data: {'zt': fb, 'project_id': projectid},
        async: false,
        contentType: 'application/x-www-form-urlencoded',
        dataType: 'json',
        success: function (PD) {


//                if ($(b).html() === "关闭课题") {
//                    $(b).text("打开课题");
//                } else {
//                    $(b).text("关闭课题");
//                }
//                if($(c).html()=="可选"){
//                    $(c).text("不可选");
//                } else {
//                    $(c).text("可选");
//                }


            // alert("成功！");
            window.location.href = localhostPath + projectName +"/static/sectionProject";
        },
        error: function () {
            alert("失败！");
        }
    })
}