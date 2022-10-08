//1、使用 window.locaion.href 获得项目的根路径
var curWwwPath = window.location.href;
//2、获得主机地址之后 的目录
var pathname = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathname);
//3、获得主机地址
var localhostPath = curWwwPath.substring(0, pos);
//4、获取项目名称
var projectName = pathname.substring(0, pathname.substr(1).indexOf('/') + 1);


$("#href").click(function () {
    window.location.href = localhostPath + projectName + "/excel/BYSJSCZB";
})