function CXAjax() {
    var a = document.getElementById('sel1').options[document.getElementById('sel1').selectedIndex].value;
    var b = document.getElementById('sel2').options[document.getElementById('sel2').selectedIndex].value;
    if (a == "" && b == "") {
        alert("条件不能为空！")
    } else {
        $.ajax({
            url: "/edumanagement/static/SelectAll",
            type: "POST",
            async: false,
            data: {"Spercialty": a, "csa": b},
            contentType: 'application/x-www-form-urlencoded',
            dataType: "json",
            success: function (message) {
                var datas = message;
                $("#CR").empty();
                var str = "";
                $.each(datas, function (index, item) {
                    str += "<tr><td>" + (index+1) + "</td><td>" + item.class_name + "</td><td>" + item.stuNum + "</td><td>" + item.name + "</td><td>" + item.projectname + "</td><td>" + item.stuselectFlag + "</td></tr>";
                })
                $("#CR").html(str);
            }
        })
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


function classKTYLB() {
    var b = document.getElementById('sel2').options[document.getElementById('sel2').selectedIndex].value;
    if (b != null && b != "") {
        window.location.href = localhostPath + projectName + "/excel/classKTYLB?class_name=" + b;
    } else {
        alert("请选择班级");
    }
}


function ZYKTYLB() {
    var a = document.getElementById('sel1').options[document.getElementById('sel1').selectedIndex].value;
    if (a != null && a != "") {
        window.location.href = localhostPath + projectName + "/excel/ZYKTYLB?specialty_name=" + a;
    } else {
        alert("请选择专业");
    }
}