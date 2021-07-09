<html>
<head>
    <meta charset="utf-8">
    <title>全国销售管理系统——登录</title>
    <style type="text/css">
        body {
            background-image: url("static/images/login_bg.jpg");
            background-size: 100% 100%;
            background-repeat: no-repeat;
        }

        .login_frame {
            width: 450px;
            height: 330px;


            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -200px;

            background-color: rgba(240, 255, 255, 255);
            border-radius: 1em ;
            text-align: center;
        }

        form p > * {
            display: inline-block;
            vertical-align: middle;
        }

        .btn_login {
            font-size: 14px;
            font-family: 宋体;
            font-weight: 800;

            width: 120px;
            height: 28px;
            line-height: 28px;
            text-align: center;
            margin-top: 12px;

            color: white;
            background-color: #005faf;
            border-radius: 6px;
            border: 0;
        }
        .login_title {
            text-align: center;
            padding: 15px;
            color: white;
            font-size: 30px;
            font-weight: 800;
            background-color: #2976d6;
            border-top-left-radius: 0.5em ;
            border-top-right-radius: 0.5em;
        }
        .image_logo {
            width: 110px;
            height: 50px;
            margin-top: 22px;
        }

        .label_input {
            font-size: 14px;
            font-family: 宋体;
            font-weight: 800;

            width: 65px;
            height: 28px;
            line-height: 28px;
            text-align: center;

            color: white;
            background-color: #005faf;
            border-top-left-radius: 5px;
            border-bottom-left-radius: 5px;
        }

        .text_field {
            width: 278px;
            height: 28px;
            border-top-right-radius: 5px;
            border-bottom-right-radius: 5px;
            border-color: #eeeeee;
            /*border: 0;*/
        }
    </style>
</head>
<body>
<br><br><br><br><br><br><br><br><br>


<div class="login_frame">
    <div class="login_title">
        <span>销售管理系统</span>
    </div>
    <img class="image_logo" src="/static/images/login_logo.jpg">
    <form method="post" action="/mainPage">
        <p><label class="label_input">用户名</label><input type="text" name="username" maxlength="10" placeholder="请输入用户名" class="text_field"/></p>
        <p><label class="label_input">密码</label><input type="password" name="password" maxlength="10" placeholder="请输入密码" class="text_field"/></p>
        <div>
            <p><input type="submit" class="btn_login" value="登录" /></p>
        </div>
    </form>
    <#if errorMsg?? >${errorMsg}</#if>
    <#if msg?? >${msg}</#if>
</div>
</body>
</html>
<#--
<!DOCTYPE html>
<head>
    <title>全国销售管理系统——登录</title>
    <style type="text/css">
        body {
            background-image: url("static/images/login_bg.jpg");
            height:100%;
            width:100%;
            overflow: hidden;
            background-repeat: no-repeat;
        }
        .login_frame {
            width: 400px;
            height: 300px;
            padding: 13px;

            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -200px;

            background: -webkit-linear-gradient(top,#2976d6,white);
            background-color: rgba(255, 255, 255,1);

            border-radius: 10px;
            text-align: center;
        }

        form p > * {
            display: inline-block;
            vertical-align: middle;
        }

        .login_title {
            text-align: center;
            font-size: 30px;
            font-weight: 800;
        }
        .btn_login {
            font-size: 14px;
            font-family: 宋体;

            width: 120px;
            height: 28px;
            line-height: 28px;
            text-align: center;

            color: white;
            background-color: #005faf;
            border-radius: 6px;
            border: 0;
        }

        .image_logo {
            margin-top: 22px;
            width: 110px;
            height: 50px;
        }

        .label_input {
            font-size: 14px;
            font-family: 宋体;

            width: 65px;
            height: 28px;
            line-height: 28px;
            text-align: center;

            color: white;
            background-color: #005faf;
            border-top-left-radius: 5px;
            border-bottom-left-radius: 5px;
        }

        .text_field {
            width: 278px;
            height: 28px;
            border-top-right-radius: 5px;
            border-bottom-right-radius: 5px;
            border: 0;
        }
    </style>
</head>
<body>
<div class="login_frame">
    <div align="center" class="login_title">销售管理系统</div>
    <img class="image_logo" src="/static/images/login_logo.jpg">
    <form method="post" action="/mainPage">
        <p><label class="label_input">用户名</label><input type="text" name="username" maxlength="10" placeholder="请输入用户名" class="text_field"/></p>
        <p><label class="label_input">密码</label><input type="password" name="password" maxlength="10" placeholder="请输入密码" class="text_field"/></p>
        <div>
            <p><input type="submit" class="btn_login" value="登录" /></p>
        </div>
    </form>
    <#if errorMsg?? >${errorMsg}</#if>
    <#if msg?? >${msg}</#if>
</div>
</body>
</html>-->
