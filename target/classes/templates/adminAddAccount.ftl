<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>主页</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/css/page.css">
</head>
<body class="layui-layout-body" >
<#import "spring.ftl" as spring>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <a href="/returnAdminMain"><div class="layui-logo">全国销售管理系统</div></a>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/adminShowAllNotices">通知</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    ${account.name}
                </a>
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
                    <dl class="layui-nav-child">
                        <dd><a href="/addNoticePage">发布通知</a></dd>
                        <dd><a href="/showAllProjects">任务管理</a></dd>
                        <dd><a href="/showAllEmployee">销售名单</a></dd>
                        <dd><a href="/showAllAccounts">销售管理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                    <legend>新增账号</legend>
                </fieldset>
                <form action="/register" method="post" id="account" class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">员工姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="name"  lay-verify="required" placeholder="请输入员工姓名" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="username"  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">密码</label>
                        <div class="layui-input-inline">
                            <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">权限</label>
                            <div class="layui-input-block">
                                <select name="power">
                                    <option value="saler" selected="">saler</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <label class="layui-form-label">&emsp;</label>
                    <input class="layui-btn" name='btn' value='确定' type='submit'>
                    <#if errlist??&&(errlist?size > 0)>
                        <div id="mydiv" class="title">
                            <#list errlist as err>
                                <span style="color:#555555;">${err}<br></span>
                            </#list>
                        </div>
                    </#if>
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

    $(function () {
        setTimeout(function () {
            $("#mydiv").css("display","none");
        },2500)
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