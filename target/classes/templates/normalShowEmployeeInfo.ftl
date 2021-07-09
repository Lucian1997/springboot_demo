<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>主页</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <a href="/returnNormalMain"><div class="layui-logo">全国销售管理系统</div></a>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/normalShowAllNotices">&ensp;通知&ensp;<#if (account.noticeNum>0)><span class="layui-badge">${account.noticeNum}</span></#if></a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    ${account.name}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/showEmployeeInfo">个人信息</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <form name="exitForm" method="post">
                    <input class="layui-btn layui-btn-radius layui-btn-normal" type="button" name="btnExit" value="退出">
                </form>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <dd><a href="/showProjectsInfo">&ensp;任务信息&ensp;<#if (account.projectNum>0)><span class="layui-badge">${account.projectNum}</span></#if></a></dd>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>个人信息</legend>
            </fieldset>
            <form method="post" action="/modifyEmployeeMes2">
                <div class="layui-input-inline">
                    <div style="width: 216px; margin: 0;">
                        <button type="submit" class="layui-btn layui-btn-fluid">修改个人信息</button>
                    </div>
                </div>
            </form>
            <form name="personlInfoForm" method="post">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>业务</th>
                        <th>职位</th>
                        <th>地区</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                            <td>${personal.name}</td>
                            <td>${personal.work}</td>
                            <td>${personal.position}</td>
                            <td>${personal.area}</td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <div align="center"><#if Msg??><h3>${Msg}</h3></#if></div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © arshcoo
    </div>
</div>
<script src="/static/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("input[name='btnExit']").click(function(){if(confirm("是否退出系统？")){
            $("form[name='exitForm']").attr("action","/exit");
            $("form[name='exitForm']").submit();}
        });
    })
</script>
</body>
</html>