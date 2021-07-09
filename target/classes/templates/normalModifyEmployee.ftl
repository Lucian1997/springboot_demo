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
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                <legend>修改个人信息</legend>
            </fieldset>
            <div class="layui-form">
                <form name="Employee_modify_Form" method="post">
                    <div class="layui-form-item">
                        <label class="layui-form-label">姓名</label>
                        <label class="layui-form-label">${name}</label>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">业务</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${work}" name="work" lay-verify="required" placeholder="请输入职位" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">职位</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${position}" name="position" lay-verify="required" placeholder="请输入职位" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">地区</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${area}" name="area" lay-verify="required" placeholder="请输入地区" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <label class="layui-form-label">&emsp;</label>
                    <input class="layui-btn" type="button" name="modify" value="修改" id="${account.id}">
                </form>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © arshcoo
    </div>
</div>
<script src="/static/layui/layui.js"></script>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("input[name='btnExit']").click(function(){if(confirm("是否退出系统？")){
            $("form[name='exitForm']").attr("action","/exit");
            $("form[name='exitForm']").submit();}
        });
    })

    $(function(){
        $("input[name='modify']").click(function(){if(confirm("是否确认修改？")){
            $("form[name='Employee_modify_Form']").attr("action","/modifyEmployee2?id="+$(this).attr("id"));
            $("form[name='Employee_modify_Form']").submit();}
        });
    })
</script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;
    })
</script>
</body>
</html>