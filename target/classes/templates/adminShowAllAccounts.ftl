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
                <legend>销售账号列表</legend>
            </fieldset>
            <form method="post" action="/registerPage">
                <div class="layui-input-inline">
                    <div style="width: 216px; margin: 0;">
                        <button type="submit" class="layui-btn layui-btn-fluid">添加账号</button>
                    </div>
                </div>
            </form>
            <div class="layui-form">
                <form name="accountsForm" method="post">
                    <table class="layui-table">
                        <thead>
                        <tr>
                            <th>员工</th>
                            <th>用户名</th>
                            <th>密码</th>
                            <th>权限</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <#list accounts.list as account>
                            <tr>
                                <td>${(account.name)}</td>
                                <td>${(account.username)}</td>
                                <td>${(account.password)}</td>
                                <td>${(account.power)}</td>
                                <td>
                                    <input class="layui-btn layui-btn-xs" type="button" name="btnModify" value="修改" id="${account.id}">
                                    <input class="layui-btn layui-btn-danger layui-btn-xs" type="button" name="btnDelete" value="删除" id="${account.id}">
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <br>
                    <div class="message">
                        共<i class="blue">${accounts.total}</i>条记录，当前显示第&nbsp;<i
                                class="blue">${accounts.pageNum}/${accounts.pages}</i>&nbsp;页
                    </div>
                    <br>
                    <div style="text-align:center;" id="nav">
                        <ul>
                            <li><a href="/showAllAccounts">首页</a></li>
                            <#if accounts.isFirstPage==false>
                                <li><a href="/showAllAccounts?pageNum=${accounts.prePage}">上一页</a></li>
                            </#if>
                            <#list accounts.navigatepageNums as element>
                                <#if element==accounts.pageNum>
                                    <li class="active"><a href="/showAllAccounts?pageNum=${element}">${element}</a></li>
                                </#if>
                                <#if element!=accounts.pageNum>
                                    <li><a href="/showAllAccounts?pageNum=${element}">${element}</a></li>
                                </#if>
                            </#list>
                            <#if accounts.isLastPage==false>
                                <li><a href="/showAllAccounts?pageNum=${accounts.nextPage}">下一页</a></li>
                            </#if>
                            <li><a href="/showAllAccounts?pageNum=${accounts.pages}">尾页</a></li>
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
            $("form[name='accountsForm']").attr("action","/deleteAccount?id="+$(this).attr("id"));
            $("form[name='accountsForm']").submit();}
        });
    })

    $(function(){
        $("input[name='btnModify']").click(function(){
            $("form[name='accountsForm']").attr("action","/modifyAccountMes?id="+$(this).attr("id"));
            $("form[name='accountsForm']").submit();
        });
    })
</script>
</body>
</html>