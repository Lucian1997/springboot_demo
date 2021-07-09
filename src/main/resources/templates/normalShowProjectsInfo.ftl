<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>主页</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/css/page.css">
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
                <legend>任务列表</legend>
            </fieldset>
            <form name="projectInfoForm" method="post">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>任务</th>
                        <th>日期</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list projects.list as project>
                            <tr>
                                <td width="5%">${(project.id)}</td>
                                <td>${(project.project)}</td>
                                <td>${(project.date?string('yyyy-MM-dd'))}</td>
                                <td>
                                    <input class="layui-btn layui-btn-primary layui-btn-xs" type="button" name="btnSelect" value="查看" id="${project.id}">
                                </td>
                             </tr>
                        </#list>
                    </tbody>
                </table>
                <br>
                <div class="message">
                    共<i class="blue">${projects.total}</i>条记录，当前显示第&nbsp;<i
                            class="blue">${projects.pageNum}/${projects.pages}</i>&nbsp;页
                </div>
                <br>
                <div style="text-align:center;" id="nav">
                    <ul>
                        <li><a href="/showProjectsInfo">首页</a></li>
                        <#if projects.isFirstPage==false>
                            <li><a href="/showProjectsInfo?pageNum=${projects.prePage}">上一页</a></li>
                        </#if>
                        <#list projects.navigatepageNums as element>
                            <#if element==projects.pageNum>
                                <li class="active"><a href="/showProjectsInfo?pageNum=${element}">${element}</a></li>
                            </#if>
                            <#if element!=projects.pageNum>
                                <li><a href="/showProjectsInfo?pageNum=${element}">${element}</a></li>
                            </#if>
                        </#list>
                        <#if projects.isLastPage==false>
                            <li><a href="/showProjectsInfo?pageNum=${projects.nextPage}">下一页</a></li>
                        </#if>
                        <li><a href="/showProjectsInfo?pageNum=${projects.pages}">尾页</a></li>
                    </ul>
                </div>
            </form>
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

    $(function(){
        $("input[name='btnSelect']").click(function(){
            $("form[name='projectInfoForm']").attr("action","/normalSelectProject?id="+$(this).attr("id"));
            $("form[name='projectInfoForm']").submit();
        });
    })
</script>
</body>
</html>