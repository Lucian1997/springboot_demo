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
        <div style="padding: 15px" class="layui-form">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                <legend>员工列表</legend>
            </fieldset>
            <div class="layui-form-item">
                <form method="post" action="/queryEmployByAreaPosition">
                    <label class="layui-form-label">地区</label>
                    <div class="layui-input-inline">
                        <select  name="area" lay-verify="required">
                            <option value="全部地区">请选择地区</option>
                            <#list areas as area>
                                <option value="${area}">${area}</option>
                            </#list>
                        </select>
                    </div>
                    <label class="layui-form-label">职位</label>
                    <div class="layui-input-inline">
                        <select  name="work" lay-verify="required">
                            <option value="全部职位">请选择职位</option>
                            <#list work as w>
                                <option value="${w}">${w}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <button type="submit" class="layui-btn">搜索</button>
                    </div>
                </form>
            </div>
            <div>
                <form name="employeeForm" method="post">
                    <table class="layui-table">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>职位</th>
                            <th>所在地区</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <#list employees.list as employee>
                            <tr>
                                <td>${employee.name}</td>
                                <td>${employee.work}</td>
                                <td>${employee.area}</td>
                                <td>${employee.position}</td>
                                <td>
                                    <input class="layui-btn layui-btn-xs" type="button" name="btnAddProject" value="发布任务" id="${employee.accountId}">
                                    <input class="layui-btn layui-btn-xs" type="button" name="btnModify" value="修改" id="${employee.id}">
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <br>
                    <div class="message">
                        共<i class="blue">${employees.total}</i>条记录，当前显示第&nbsp;<i
                                class="blue">${employees.pageNum}/${employees.pages}</i>&nbsp;页
                    </div>
                    <br>
                    <#if EmpInfo=="all">
                    <div style="text-align:center;" id="nav">
                        <ul>
                            <li><a href="/showAllEmployee">首页</a></li>
                            <#if employees.isFirstPage==false>
                                <li><a href="/showAllEmployee?pageNum=${employees.prePage}">上一页</a></li>
                            </#if>
                            <#list employees.navigatepageNums as element>
                                <#if element==employees.pageNum>
                                    <li class="active"><a href="/showAllEmployee?pageNum=${element}">${element}</a></li>
                                </#if>
                                <#if element!=employees.pageNum>
                                    <li><a href="/showAllEmployee?pageNum=${element}">${element}</a></li>
                                </#if>
                            </#list>
                            <#if employees.isLastPage==false>
                                <li><a href="/showAllEmployee?pageNum=${employees.nextPage}">下一页</a></li>
                            </#if>
                            <li><a href="/showAllEmployee?pageNum=${employees.pages}">尾页</a></li>
                        </ul>
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

    $(function(){
        $("input[name='btnModify']").click(function(){
            $("form[name='employeeForm']").attr("action","/modifyEmployeeMes?id="+$(this).attr("id"));
            $("form[name='employeeForm']").submit();
        });
    })

    $(function(){
        $("input[name='btnAddProject']").click(function(){
            $("form[name='employeeForm']").attr("action","/addProjectPage?id="+$(this).attr("id"));
            $("form[name='employeeForm']").submit();
        });
    })
</script>
</body>
</html>