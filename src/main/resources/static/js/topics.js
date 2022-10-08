$(function () {
    $('#xb').click(function () {
        /*$("table").find("tr").eq(1).find("td").eq(4).text();*/
        console.log($('#xb').val());
        if ($('#xb').val() == "0" || $('#xb').val() == "2") {
            var projectName = $("#project_Name").html();
            $.ajax({
                    type: 'GET',
                    url: 'enroll',
                    dataType: 'json', //表示返回值的数据类型
                    contentType: 'application/json;charset=UTF-8', //内容类型
                    data: {"val": "1", "projectName": projectName},
                    success: function (res) {
                        if (res == 200) {
                            alert("选报成功，请耐心等待导师回复！");
                            $('#xb').html("取消选报");
                            window.location.reload();//偷懒做的
                        }
                        if (res == 201) {
                            alert("选报失败！")
                        }
                    }
                }
            );
        }
        else if ($('#xb').val() == "1") {
            $.ajax({
                    type: 'GET',//DELETE
                    url: 'cancel',
                    dataType: 'json', //表示返回值的数据类型
                    contentType: 'application/json;charset=UTF-8', //内容类型
                    data: {"val": "2"},
                    success: function (res) {
                        if (res == 200) {
                            alert("取消成功！");
                            $('#xb').html("选报");
                            window.location.reload();//偷懒做的
                        }
                        if (res == 201) {
                            alert("取消失败!")
                        }
                        if (res == 203) {
                            alert("操作失败！");
                        }
                    }
                }
            );
        } else if ($('#xb').val() == "3") {
            alert("您已选题成功，如需重选请联系该课题导师，取消您的选择");
        }else {
            alert("你已选择其他课题，如想选择本课题，请先取消已报名课题");
        }
    });
})
