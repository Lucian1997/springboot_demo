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
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                <legend>通知列表</legend>
            </fieldset>
            <div class="layui-form">
                <form name="noticesForm" method="post">
                    <table class="layui-table">
                        <thead>
                        <tr>
                            <th>通知id</th>
                            <th>标题</th>
                            <th>发布日期</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <#list notcies.list as notice>
                            <tr>
                                <td width="5%">${(notice.id)}</td>
                                <td>${(notice.title)}</td>
                                <td>${(notice.date?string('yyyy-MM-dd'))}</td>
                                <td>
                                    <input class="layui-btn layui-btn-primary layui-btn-xs" type="button" name="btnSelect" value="查看" id="${notice.id}">
                                    <input class="layui-btn layui-btn-danger layui-btn-xs" type="button" name="btnDelete" value="删除" id="${notice.id}">
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <br>
                    <div class="message">
                        共<i class="blue">${notcies.total}</i>条记录，当前显示第&nbsp;<i
                                class="blue">${notcies.pageNum}/${notcies.pages}</i>&nbsp;页
                    </div>
                    <br>
                    <div style="text-align:center;" id="nav">
                        <ul>
                            <li><a href="/adminShowAllNotices">首页</a></li>
                            <#if notcies.isFirstPage==false>
                                <li><a href="/adminShowAllNotices?pageNum=${notcies.prePage}">上一页</a></li>
                            </#if>
                            <#list notcies.navigatepageNums as element>
                                <#if element==notcies.pageNum>
                                    <li class="active"><a href="/adminShowAllNotices?pageNum=${element}">${element}</a></li>
                                </#if>
                                <#if element!=notcies.pageNum>
                                    <li><a href="/adminShowAllNotices?pageNum=${element}">${element}</a></li>
                                </#if>
                            </#list>
                            <#if notcies.isLastPage==false>
                                <li><a href="/adminShowAllNotices?pageNum=${notcies.nextPage}">下一页</a></li>
                            </#if>
                            <li><a href="/adminShowAllNotices?pageNum=${notcies.pages}">尾页</a></li>
                        </ul>
                    </div>
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
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("input[name='btnExit']").click(function(){if(confirm("是否退出系统？")){
            $("form[name='exitForm']").attr("action","/exit");
            $("form[name='exitForm']").submit();}
        });
    })
</script>
<script type="text/javascript">
    $(function(){
        $("input[name='btnDelete']").click(function(){if(confirm("是否删除?")){
            $("form[name='noticesForm']").attr("action","/deleteNotice?id="+$(this).attr("id"));
            $("form[name='noticesForm']").submit();}
        });
    })
    $(function(){
        $("input[name='btnSelect']").click(function(){
            $("form[name='noticesForm']").attr("action","/adminSelectNotice?id="+$(this).attr("id"));
            $("form[name='noticesForm']").submit();
        });
    })

</script>
</body>
</html>