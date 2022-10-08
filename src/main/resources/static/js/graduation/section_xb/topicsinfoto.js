//1、使用 window.locaion.href 获得项目的根路径
var curWwwPath = window.location.href;
//2、获得主机地址之后 的目录
var pathname = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathname);
//3、获得主机地址
var localhostPath = curWwwPath.substring(0, pos);
//4、获取项目名称
var projectName = pathname.substring(0, pathname.substr(1).indexOf('/') + 1);

function projectXSYLB(projectId,project_name){
    // console.log(Myproject);
    if(project_name==""||project_name==null){
        alert("课题信息有误，生成失败")
    }else{
        window.location.href = localhostPath + projectName + "/excel/projectXSYLB/"+projectId+"?projectName="+project_name;
    }

}

function studAjax(ojb, projectId) {
    var a = $("button").index(ojb);
    var b = $("button").eq(a);
    var c = $("table").find("tr").eq(a).find("td").eq(0).html();
    var fb;
    if ($.trim($(b).html()) === "选取") fb = 3;
    else fb = 1;
    $.ajax({
        url: localhostPath + projectName + '/static/QRstudentXB',
        type: 'POST',
        data: {'studet_id': c, 'zt': fb, 'project_id': projectId},
        async: false,
        contentType: 'application/x-www-form-urlencoded',
        dataType: 'json',
        success: function (PD) {
            if ($.trim($(b).html()) === "选取") {
                $(b).text("取消");
            } else {
                $(b).text("选取");
            }
        },
        error: function () {
            alert("选取失败！");
        }
    })
}