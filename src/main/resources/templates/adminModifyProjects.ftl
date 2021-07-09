<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>主页</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/css/page.css">

</head>
<body class="layui-layout-body">
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
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
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
                    <legend>修改任务信息</legend>
                </fieldset>
                <form name="Projects_Form" method="post" id="account" class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">负责人</label>
                        <label class="layui-form-label">${proj.principal}</label>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">任务</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${proj.project}" name="project" lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">内容</label>
                        <div class="layui-input-block">
                            <textarea name="context" value="${proj.context}" placeholder="请输入任务内容" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <label class="layui-form-label">&emsp;</label>
                    <input type="button" name="updateProject" value="修改" id="${proj.id}" class="layui-btn">
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
</div>


</body>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("input[name='updateProject']").click(function () {if(confirm("是否确认修改？")){
            $("form[name='Projects_Form']").attr("action", "/updateProject?id=" + $(this).attr("id"));
            $("form[name='Projects_Form']").submit();}
        });
    })
</script>
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
    layui.use('element', function () {
        var element = layui.element;

    });
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;
    })
</script>

</html>