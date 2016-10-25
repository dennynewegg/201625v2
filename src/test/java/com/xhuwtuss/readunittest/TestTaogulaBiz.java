package com.xhuwtuss.readunittest;

import android.util.Log;

import com.dy45.reader.Biz.TaogulaBiz;
import com.dy45.reader.entity.ArticleDTO;

import org.junit.Test;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestTaogulaBiz {
    @Test
    public void addition_isCorrect() throws Exception {

        String html = "\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">\t<head>\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\t\t<title>股市大家谈_淘优质股_成长股价值投资_寻找低估值的优质好股票  </title>\t<script type=\"text/javascript\" src=\"http://cbjs.baidu.com/js/m.js\"></script>\t<meta name=\"keywords\" content=\"股市大家谈,优质股,股票投资论坛,价值投资网,股票投资分析,股票投资策略\" />\t<meta name=\"description\" content=\"淘优质股,价值投资是一种常见的投资方式,专门寻找价格低估的证券.长线是金价值投资论坛,是就价值投资的理念,方法和操作策略等进行展开和讨论!长线是金,坚持价值投资理念!探索价值投资之道!向巴菲特,格雷厄姆,林奇等价值投资界大师著作学习与交流，淘股啦股票网taogula.com \" />\t<meta name=\"generator\" content=\"Discuz! X3.2\" />\t<meta name=\"author\" content=\"Discuz! Team and Comsenz UI Team\" />\t<meta name=\"copyright\" content=\"2001-2013 Comsenz Inc.\" />\t<meta name=\"MSSmartTagsPreventParsing\" content=\"True\" />\t<meta http-equiv=\"MSThemeCompatible\" content=\"Yes\" />\t<base href=\"http://www.taogula.com/\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"data/cache/style_1_common.css?m1O\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"data/cache/style_1_forum_forumdisplay.css?m1O\" /><script type=\"text/javascript\">var STYLEID = '1', STATICURL = 'static/', IMGDIR = 'static/image/common', VERHASH = 'm1O', charset = 'gbk', discuz_uid = '0', cookiepre = 'W0zF_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'login|newthread', creditnotice = '1|金币|,2|银币|,3|福币|', defaultstyle = '', REPORTURL = 'aHR0cDovL3d3dy50YW9ndWxhLmNvbS9mb3J1bS00Ny0xLmh0bWw=', SITEURL = 'http://www.taogula.com/', JSPATH = 'data/cache/', CSSPATH = 'data/cache/style_', DYNAMICURL = '';</script>\t<script src=\"data/cache/common.js?m1O\" type=\"text/javascript\"></script>\t\t<meta name=\"application-name\" content=\"淘股啦股票网\" />\n" +
                "<meta name=\"msapplication-tooltip\" content=\"淘股啦股票网\" />\n" +
                "<meta name=\"msapplication-task\" content=\"name=淘股啦股票网;action-uri=http://www.taogula.com/portal.php;icon-uri=http://www.taogula.com/static/image/common/portal.ico\" /><meta name=\"msapplication-task\" content=\"name=淘股啦股票网;action-uri=http://www.taogula.com/forum.php;icon-uri=http://www.taogula.com/static/image/common/bbs.ico\" />\n" +
                "<link rel=\"archives\" title=\"淘股啦股票网\" href=\"http://www.taogula.com/archiver/\" />\n" +
                "<link rel=\"alternate\" type=\"application/rss+xml\" title=\"淘股啦股票网 - 淘股啦股票网:股票论坛_个股点评_股票研究_投资策略\" href=\"http://www.taogula.com/forum.php?mod=rss&fid=47&amp;auth=0\" />\n" +
                "<script src=\"data/cache/forum.js?m1O\" type=\"text/javascript\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body id=\"nv_forum\" class=\"pg_forumdisplay\" onkeydown=\"if(event.keyCode==27) return false;\">\n" +
                "<div id=\"append_parent\"></div><div id=\"ajaxwaitid\"></div>\n" +
                "<div id=\"toptb\" class=\"cl\">\n" +
                "<div class=\"wp\">\n" +
                "<div class=\"z\"><a href=\"http://www.taogula.com/fuli.html\" title=\"复利有多吓人，算过才知道\" target=\"_blank\" >复利计算器</a><a href=\"/forum-168-1.html\" target=\"_blank\"  style=\"color: blue\">网站事务</a><a href=\"http://www.taogula.com/thread-87251-1-1.html\" title=\"淘股啦股票网VIP用户\" target=\"_blank\"  style=\"color: red\">588股票群</a><script type=\"text/javascript\">var _speedMark = new Date();</script></div>\n" +
                "<div class=\"y\">\n" +
                "<a id=\"switchblind\" href=\"javascript:;\" onclick=\"toggleBlind(this)\" title=\"开启辅助访问\" class=\"switchblind\">开启辅助访问</a>\n" +
                "<a href=\"javascript:;\" id=\"switchwidth\" onclick=\"widthauto(this)\" title=\"切换到宽版\" class=\"switchwidth\">切换到宽版</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"qmenu_menu\" class=\"p_pop blk\" style=\"display: none;\">\n" +
                "<div class=\"ptm pbw hm\">\n" +
                "请 <a href=\"javascript:;\" class=\"xi2\" onclick=\"lsSubmit()\"><strong>登录</strong></a> 后使用快捷导航<br />没有帐号？<a href=\"member.php?mod=taogula51816\" class=\"xi2 xw1\">立即注册</a>\n" +
                "</div>\n" +
                "</div><div id=\"hd\">\n" +
                "<div class=\"wp\">\n" +
                "<div class=\"hdc cl\"><h2><a href=\"http://www.taogula.com/\" title=\"淘股啦股票网\"><img src=\"static/image/common/logo.png\" alt=\"淘股啦股票网\" border=\"0\" /></a></h2><script src=\"data/cache/logging.js?m1O\" type=\"text/javascript\"></script>\n" +
                "<form method=\"post\" autocomplete=\"off\" id=\"lsform\" action=\"member.php?mod=logging&amp;action=login&amp;loginsubmit=yes&amp;infloat=yes&amp;lssubmit=yes\" onsubmit=\"pwmd5('ls_password');return lsSubmit();\">\n" +
                "<div class=\"fastlg cl\">\n" +
                "<span id=\"return_ls\" style=\"display:none\"></span>\n" +
                "<div class=\"y pns\">\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<td>\n" +
                "<span class=\"ftid\">\n" +
                "<select name=\"fastloginfield\" id=\"ls_fastloginfield\" width=\"40\" tabindex=\"900\">\n" +
                "<option value=\"username\">用户名</option>\n" +
                "<option value=\"email\">Email</option>\n" +
                "</select>\n" +
                "</span>\n" +
                "<script type=\"text/javascript\">simulateSelect('ls_fastloginfield')</script>\n" +
                "</td>\n" +
                "<td><input type=\"text\" name=\"username\" id=\"ls_username\" autocomplete=\"off\" class=\"px vm\" tabindex=\"901\" /></td>\n" +
                "<td class=\"fastlg_l\"><label for=\"ls_cookietime\"><input type=\"checkbox\" name=\"cookietime\" id=\"ls_cookietime\" class=\"pc\" value=\"2592000\" tabindex=\"903\" />自动登录</label></td>\n" +
                "<td>&nbsp;<a href=\"javascript:;\" onclick=\"showWindow('login', 'member.php?mod=logging&action=login&viewlostpw=1')\">找回密码</a></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td><label for=\"ls_password\" class=\"z psw_w\">密码</label></td>\n" +
                "<td><input type=\"password\" name=\"password\" id=\"ls_password\" class=\"px vm\" autocomplete=\"off\" tabindex=\"902\" /></td>\n" +
                "<td class=\"fastlg_l\"><button type=\"submit\" class=\"pn vm\" tabindex=\"904\" style=\"width: 75px;\"><em>登录</em></button></td>\n" +
                "<td>&nbsp;<a href=\"member.php?mod=taogula51816\" class=\"xi2 xw1\">立即注册</a></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<input type=\"hidden\" name=\"quickforward\" value=\"yes\" />\n" +
                "<input type=\"hidden\" name=\"handlekey\" value=\"ls\" />\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"fastlg_fm y\" style=\"margin-right: 10px; padding-right: 10px\">\n" +
                "<p><a href=\"http://www.taogula.com/connect.php?mod=login&op=init&referer=forum.php%3Fmod%3Dforumdisplay%26fid%3D47%26page%3D1&statfrom=login_simple\"><img src=\"static/image/common/qq_login.gif\" class=\"vm\" alt=\"QQ登录\" /></a></p>\n" +
                "<p class=\"hm xg1\" style=\"padding-top: 2px;\">只需一步，快速开始</p>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"fastlg_fm y\" style=\"margin-right: 10px; padding-right: 10px\">\n" +
                "<p><a href=\"plugin.php?id=wechat:login\"><img src=\"source/plugin/wechat/image/wechat_login.png\" class=\"vm\" /></a></p>\n" +
                "<p class=\"hm xg1\" style=\"padding-top: 2px;\">扫一扫，访问微社区</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</form>\n" +
                "\n" +
                "<script src=\"data/cache/md5.js?m1O\" type=\"text/javascript\" reload=\"1\"></script>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"nv\">\n" +
                "<a href=\"javascript:;\" id=\"qmenu\" onmouseover=\"delayShow(this, function () {showMenu({'ctrlid':'qmenu','pos':'34!','ctrlclass':'a','duration':2});showForummenu(47);})\">快捷导航</a>\n" +
                "<ul><li class=\"a\" id=\"mn_N8e54\" ><a href=\"http://www.taogula.com/\" hidefocus=\"true\"  >首页</a></li><li id=\"mn_N8ded\" ><a href=\"/forum-47-1.html\" hidefocus=\"true\" title=\"个股交流，行情复盘\"  >谈股论道<span>个股交流，行情复盘</span></a></li><li id=\"mn_N940b\" ><a href=\"http://www.taogula.com/forum-180-1.html\" hidefocus=\"true\" title=\"阅读名家观点，开阔投资视野\"  >盘前资讯<span>阅读名家观点，开阔投资视野</span></a></li><li id=\"mn_Nf208\" ><a href=\"/forum-122-1.html\" hidefocus=\"true\" title=\"炒股知识学习\"  >淘股学堂<span>炒股知识学习</span></a></li></ul>\n" +
                "</div>\n" +
                "<div class=\"p_pop h_pop\" id=\"mn_userapp_menu\" style=\"display: none\"></div><div id=\"mu\" class=\"cl\">\n" +
                "</div><div id=\"scbar\" class=\"cl\">\n" +
                "<form id=\"scbar_form\" method=\"post\" autocomplete=\"off\" onsubmit=\"searchFocus($('scbar_txt'))\" action=\"search.php?searchsubmit=yes\" target=\"_blank\">\n" +
                "<input type=\"hidden\" name=\"mod\" id=\"scbar_mod\" value=\"search\" />\n" +
                "<input type=\"hidden\" name=\"formhash\" value=\"fc46ccce\" />\n" +
                "<input type=\"hidden\" name=\"srchtype\" value=\"title\" />\n" +
                "<input type=\"hidden\" name=\"srhfid\" value=\"47\" />\n" +
                "<input type=\"hidden\" name=\"srhlocality\" value=\"forum::forumdisplay\" />\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<td class=\"scbar_icon_td\"></td>\n" +
                "<td class=\"scbar_txt_td\"><input type=\"text\" name=\"srchtxt\" id=\"scbar_txt\" value=\"请输入搜索内容\" autocomplete=\"off\" x-webkit-speech speech /></td>\n" +
                "<td class=\"scbar_type_td\"><a href=\"javascript:;\" id=\"scbar_type\" class=\"xg1\" onclick=\"showMenu(this.id)\" hidefocus=\"true\">搜索</a></td>\n" +
                "<td class=\"scbar_btn_td\"><button type=\"submit\" name=\"searchsubmit\" id=\"scbar_btn\" sc=\"1\" class=\"pn pnc\" value=\"true\"><strong class=\"xi2\">搜索</strong></button></td>\n" +
                "<td class=\"scbar_hot_td\">\n" +
                "<div id=\"scbar_hot\">\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</form>\n" +
                "</div>\n" +
                "<ul id=\"scbar_type_menu\" class=\"p_pop\" style=\"display: none;\"><li><a href=\"javascript:;\" rel=\"curforum\" fid=\"47\" >本版</a></li><li><a href=\"javascript:;\" rel=\"forum\" class=\"curtype\">帖子</a></li><li><a href=\"javascript:;\" rel=\"user\">用户</a></li></ul>\n" +
                "<script type=\"text/javascript\">\n" +
                "initSearchmenu('scbar', '');\n" +
                "</script>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<script>var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"//hm.baidu.com/hm.js?36c848bcf00b267b56c1a4a53081912f\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();</script>\n" +
                "<div id=\"wp\" class=\"wp\">\n" +
                "<style id=\"diy_style\" type=\"text/css\"></style>\n" +
                "<!--[diy=diynavtop]--><div id=\"diynavtop\" class=\"area\"><div id=\"frame6ByZBU\" class=\"cl_frame_bm frame move-span cl frame-1\"><div id=\"frame6ByZBU_left\" class=\"column frame-1-c\"><div id=\"frame6ByZBU_left_temp\" class=\"move-span temp\"></div><div id=\"portal_block_2848\" class=\"cl_block_bm block move-span\"><div id=\"portal_block_2848_content\" class=\"dxb_bc\"><div class=\"portal_block_summary\"><script type=\"text/javascript\">BAIDU_CLB_fillSlot(\"1111433\");</script></div></div></div></div></div></div><!--[/diy]-->\n" +
                "<div id=\"pt\" class=\"bm cl\">\n" +
                "<div class=\"z\">\n" +
                "<a href=\"./\" class=\"nvhm\" title=\"首页\">淘股啦股票网</a><em>&raquo;</em><a href=\"http://www.taogula.com/forum.php\">淘股啦股票网</a> <em>&rsaquo;</em> <a href=\"http://www.taogula.com/forum.php?gid=1\"></a><em>&rsaquo;</em> <a href=\"http://www.taogula.com/forum-47-1.html\">股市论谈</a></div>\n" +
                "</div><div class=\"wp\">\n" +
                "<!--[diy=diy1]--><div id=\"diy1\" class=\"area\"></div><!--[/diy]-->\n" +
                "</div>\n" +
                "<div class=\"boardnav\">\n" +
                "<div id=\"ct\" class=\"wp cl\">\n" +
                "\n" +
                "<div class=\"mn\">\n" +
                "<div class=\"bm bml pbn\">\n" +
                "<div class=\"bm_h cl\">\n" +
                "<span class=\"y\">\n" +
                "<a href=\"http://www.taogula.com/home.php?mod=spacecp&amp;ac=favorite&amp;type=forum&amp;id=47&amp;handlekey=favoriteforum&amp;formhash=fc46ccce\" id=\"a_favorite\" class=\"fa_fav\" onclick=\"showWindow(this.id, this.href, 'get', 0);\">收藏本版 <strong class=\"xi1\" id=\"number_favorite\" >(<span id=\"number_favorite_num\">28</span>)</strong></a>\n" +
                "<span class=\"pipe\">|</span><a href=\"http://www.taogula.com/forum.php?mod=rss&amp;fid=47&amp;auth=0\" class=\"fa_rss\" target=\"_blank\" title=\"RSS\">订阅</a>\n" +
                "</span>\n" +
                "<h1 class=\"xs2\">\n" +
                "<a href=\"http://www.taogula.com/forum-47-1.html\">股市论谈</a>\n" +
                "<span class=\"xs1 xw0 i\">今日: <strong class=\"xi1\">0</strong><span class=\"pipe\">|</span>主题: <strong class=\"xi1\">23745</strong><span class=\"pipe\">|</span>排名: <strong class=\"xi1\" title=\"上次排名:1\">1</strong><b class=\"ico_increase\">&nbsp;</b></span></h1>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"drag\">\n" +
                "<!--[diy=diy4]--><div id=\"diy4\" class=\"area\"></div><!--[/diy]-->\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"pgt\" class=\"bm bw0 pgs cl\">\n" +
                "<span id=\"fd_page_top\"><div class=\"pg\"><strong>1</strong><a href=\"http://www.taogula.com/forum-47-2.html\">2</a><a href=\"http://www.taogula.com/forum-47-3.html\">3</a><a href=\"http://www.taogula.com/forum-47-4.html\">4</a><a href=\"http://www.taogula.com/forum-47-5.html\">5</a><a href=\"http://www.taogula.com/forum-47-6.html\">6</a><a href=\"http://www.taogula.com/forum-47-7.html\">7</a><a href=\"http://www.taogula.com/forum-47-8.html\">8</a><a href=\"http://www.taogula.com/forum-47-9.html\">9</a><a href=\"http://www.taogula.com/forum-47-10.html\">10</a><a href=\"http://www.taogula.com/forum-47-792.html\" class=\"last\">... 792</a><label><input type=\"text\" name=\"custompage\" class=\"px\" size=\"2\" title=\"输入页码，按回车快速跳转\" value=\"1\" onkeydown=\"if(event.keyCode==13) {window.location='forum.php?mod=forumdisplay&fid=47&amp;page='+this.value;; doane(event);}\" /><span title=\"共 792 页\"> / 792 页</span></label><a href=\"http://www.taogula.com/forum-47-2.html\" class=\"nxt\">下一页</a></div></span>\n" +
                "<span class=\"pgb y\"  ><a href=\"http://www.taogula.com/forum.php\">返&nbsp;回</a></span>\n" +
                "<a href=\"javascript:;\" id=\"newspecial\" onmouseover=\"$('newspecial').id = 'newspecialtmp';this.id = 'newspecial';showMenu({'ctrlid':this.id})\" onclick=\"showWindow('newthread', 'forum.php?mod=post&action=newthread&fid=47')\" title=\"发新帖\"><img src=\"static/image/common/pn_post.png\" alt=\"发新帖\" /></a></div>\n" +
                "<div id=\"threadlist\" class=\"tl bm bmw\">\n" +
                "<div class=\"th\">\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<th colspan=\"2\">\n" +
                "<div class=\"tf\">\n" +
                "<span id=\"atarget\" onclick=\"setatarget(-1)\" class=\"y atarget_1\" title=\"在新窗口中打开帖子\">新窗</span>\n" +
                "<a id=\"filter_special\" href=\"javascript:;\" class=\"showmenu xi2\" onclick=\"showMenu(this.id)\">全部主题</a>&nbsp;\t\t\t\t\t\t\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=lastpost&amp;orderby=lastpost\" class=\"xi2\">最新</a>&nbsp;\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=heat&amp;orderby=heats\" class=\"xi2\">热门</a>&nbsp;\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=hot\" class=\"xi2\">热帖</a>&nbsp;\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=digest&amp;digest=1\" class=\"xi2\">精华</a>&nbsp;\n" +
                "<a id=\"filter_dateline\" href=\"javascript:;\" class=\"showmenu xi2\" onclick=\"showMenu(this.id)\">更多</a>&nbsp;\n" +
                "<span id=\"clearstickthread\" style=\"display: none;\">\n" +
                "<span class=\"pipe\">|</span>\n" +
                "<a href=\"javascript:;\" onclick=\"clearStickThread()\" class=\"xi2\" title=\"显示置顶\">显示置顶</a>\n" +
                "</span>\n" +
                "</div>\n" +
                "</th>\n" +
                "<td class=\"by\">作者</td>\n" +
                "<td class=\"num\">回复/查看</td>\n" +
                "<td class=\"by\">最后发表</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "<div class=\"bm_c\">\n" +
                "<script type=\"text/javascript\">var lasttime = 1477010998;var listcolspan= '5';</script>\n" +
                "<div id=\"forumnew\" style=\"display:none\"></div>\n" +
                "<form method=\"post\" autocomplete=\"off\" name=\"moderate\" id=\"moderate\" action=\"forum.php?mod=topicadmin&amp;action=moderate&amp;fid=47&amp;infloat=yes&amp;nopost=yes\">\n" +
                "<input type=\"hidden\" name=\"formhash\" value=\"fc46ccce\" />\n" +
                "<input type=\"hidden\" name=\"listextra\" value=\"page%3D1\" />\n" +
                "<table summary=\"forum_47\" cellspacing=\"0\" cellpadding=\"0\" id=\"threadlisttableid\">\n" +
                "<tbody id=\"stickthread_87172\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87172-1-1.html\" title=\"全局置顶主题 - 新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/pin_3.gif\" alt=\"全局置顶\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87172\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87172';CONTENT_ID='stickthread_87172';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                "<a href=\"javascript:void(0);\" onclick=\"hideStickThread('87172')\" class=\"showhide y\" title=\"隐藏置顶帖\">隐藏置顶帖</a></em>\n" +
                " <a href=\"http://www.taogula.com/thread-87172-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">高手复盘手记：如何通过集合竞价看龙头和领涨股</a>\n" +
                "<img src=\"static/image/filetype/image_s.gif\" alt=\"attach_img\" title=\"图片附件\" align=\"absmiddle\" />\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-39103.html\" c=\"1\">淘股哥</a></cite>\n" +
                "<em><span>2016-5-28 15:19</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87172-1-1.html\" class=\"xi2\">1</a><em>1220</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CD%F5%D7%D3%B5%C4%D0%C4%BF%D5.html\" c=\"1\">王子的心空</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87172&goto=lastpost#lastpost\">2016-7-4 17:51</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"separatorline\">\n" +
                "<tr class=\"ts\">\n" +
                "<td>&nbsp;</td>\n" +
                "<th><a href=\"javascript:;\" onclick=\"checkForumnew_btn('47')\" title=\"查看更新\" class=\"forumrefresh\">版块主题</a></th><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<script type=\"text/javascript\">hideStickThread();</script>\n" +
                "<tbody id=\"normalthread_87444\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87444-1-1.html\" title=\"有新回复 - 新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_new.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"new\">\n" +
                "<a href=\"javascript:;\" id=\"content_87444\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87444';CONTENT_ID='normalthread_87444';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87444-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">10.20涨停板复盘：填权概念股成仅存热点 节奏踏对紧抓未来主线</a>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=redirect&amp;tid=87444&amp;goto=lastpost#lastpost\" class=\"xi1\">New</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-20 19:37</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87444-1-1.html\" class=\"xi2\">0</a><em>58</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87444&goto=lastpost#lastpost\">2016-10-20 19:37</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_85516\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-85516-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_85516\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='85516';CONTENT_ID='normalthread_85516';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-85516-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">炒股多年，在淘股啦开个实盘贴见证成长之路！(APP)</a>\n" +
                "<img src=\"static/image/filetype/image_s.gif\" alt=\"attach_img\" title=\"图片附件\" align=\"absmiddle\" />\n" +
                "<img src=\"static/image/common/recommend_2.gif\" align=\"absmiddle\" alt=\"recommend\" title=\"评价指数 20\" />\n" +
                "<img src=\"static/image/common/hot_3.gif\" align=\"absmiddle\" alt=\"heatlevel\" title=\"热度: 220\" />\n" +
                "<span class=\"tps\">&nbsp;...<a href=\"http://www.taogula.com/thread-85516-2-1.html\">2</a><a href=\"http://www.taogula.com/thread-85516-3-1.html\">3</a><a href=\"http://www.taogula.com/thread-85516-4-1.html\">4</a><a href=\"http://www.taogula.com/thread-85516-5-1.html\">5</a><a href=\"http://www.taogula.com/thread-85516-6-1.html\">6</a>..<a href=\"http://www.taogula.com/thread-85516-17-1.html\">17</a></span>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-37569.html\" c=\"1\">等神</a></cite>\n" +
                "<em><span>2015-5-30 09:32</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-85516-1-1.html\" class=\"xi2\">186</a><em>22310</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%BE%C3%CE%A5%C1%C9.html\" c=\"1\">久违辽</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/thread-85516-1-1.html\">2016-10-20 11:36</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87442\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87442-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87442\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87442';CONTENT_ID='normalthread_87442';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87442-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">10.19涨停板复盘：填权混改概念风起，抓住主线方能赚钱</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-19 19:20</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87442-1-1.html\" class=\"xi2\">0</a><em>81</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87442&goto=lastpost#lastpost\">2016-10-19 19:20</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87440\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87440-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87440\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87440';CONTENT_ID='normalthread_87440';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87440-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">10.18涨停板复盘：填权混改概念风起，股市内外充满了快活的空气</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-18 20:55</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87440-1-1.html\" class=\"xi2\">0</a><em>125</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87440&goto=lastpost#lastpost\">2016-10-18 20:55</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87438\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87438-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87438\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87438';CONTENT_ID='normalthread_87438';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87438-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">10.17复盘：B股跪了蓝瘦香菇 次新股轮动上涨</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-17 21:13</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87438-1-1.html\" class=\"xi2\">0</a><em>116</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87438&goto=lastpost#lastpost\">2016-10-17 21:13</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87436\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87436-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87436\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87436';CONTENT_ID='normalthread_87436';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87436-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">10.16：股市寻找Ten bagger 次新是牛股集中营</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-16 20:30</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87436-1-1.html\" class=\"xi2\">0</a><em>144</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87436&goto=lastpost#lastpost\">2016-10-16 20:30</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87435\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87435-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87435\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87435';CONTENT_ID='normalthread_87435';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87435-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">10.14复盘：高送转填权掀起一波行情 国企改革概念股有想象空间</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-14 21:00</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87435-1-1.html\" class=\"xi2\">0</a><em>203</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87435&goto=lastpost#lastpost\">2016-10-14 21:00</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87432\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87432-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87432\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87432';CONTENT_ID='normalthread_87432';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87432-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">10.12复盘：债转股龙头温州邦庄股强势连板 这个板块或可期待主升浪行情</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-12 20:56</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87432-1-1.html\" class=\"xi2\">0</a><em>254</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87432&goto=lastpost#lastpost\">2016-10-12 20:56</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87428\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87428-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87428\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87428';CONTENT_ID='normalthread_87428';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87428-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">天云说：楼市限购一夜冬 卖房炒股的机会来了吗？</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-9 18:10</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87428-1-1.html\" class=\"xi2\">0</a><em>242</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87428&goto=lastpost#lastpost\">2016-10-9 18:10</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87427\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87427-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87427\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87427';CONTENT_ID='normalthread_87427';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87427-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">股权转让龙头股五天四涨停，最全潜力股名单看这里</a>\n" +
                "<img src=\"static/image/filetype/image_s.gif\" alt=\"attach_img\" title=\"图片附件\" align=\"absmiddle\" />\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-10-9 17:59</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87427-1-1.html\" class=\"xi2\">0</a><em>285</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87427&goto=lastpost#lastpost\">2016-10-9 17:59</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87426\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87426-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87426\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87426';CONTENT_ID='normalthread_87426';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87426-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">国庆后A股怎么走？看这里就知道了</a>\n" +
                "<img src=\"static/image/filetype/image_s.gif\" alt=\"attach_img\" title=\"图片附件\" align=\"absmiddle\" />\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-8630.html\" c=\"1\">炒股老兵</a></cite>\n" +
                "<em><span>2016-10-7 21:18</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87426-1-1.html\" class=\"xi2\">0</a><em>152</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%B3%B4%B9%C9%C0%CF%B1%F8.html\" c=\"1\">炒股老兵</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87426&goto=lastpost#lastpost\">2016-10-7 21:18</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87391\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87391-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87391\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87391';CONTENT_ID='normalthread_87391';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87391-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.8涨停板复盘：重组概念开板后第二春 趋势是好朋友</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-8 18:29</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87391-1-1.html\" class=\"xi2\">2</a><em>825</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-linux3d.html\" c=\"1\">linux3d</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87391&goto=lastpost#lastpost\">2016-10-6 15:32</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87424\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87424-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87424\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87424';CONTENT_ID='normalthread_87424';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87424-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.30涨停板复盘：国庆持股过节胜算竟是这样的结果！</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-30 21:00</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87424-1-1.html\" class=\"xi2\">0</a><em>228</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87424&goto=lastpost#lastpost\">2016-9-30 21:00</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87422\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87422-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87422\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87422';CONTENT_ID='normalthread_87422';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87422-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.29涨停板复盘：股转变更概念火爆 龙头泸天化4天3板</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-29 20:42</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87422-1-1.html\" class=\"xi2\">0</a><em>200</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87422&goto=lastpost#lastpost\">2016-9-29 20:42</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87420\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87420-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87420\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87420';CONTENT_ID='normalthread_87420';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87420-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.28涨停板复盘：股市地量出现 股权变更SDR概念吸金</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-28 20:47</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87420-1-1.html\" class=\"xi2\">0</a><em>240</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87420&goto=lastpost#lastpost\">2016-9-28 20:47</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87418\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87418-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87418\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87418';CONTENT_ID='normalthread_87418';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87418-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.27复盘：股市缩量反弹，趋势依旧不明朗</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-27 20:33</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87418-1-1.html\" class=\"xi2\">0</a><em>232</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87418&goto=lastpost#lastpost\">2016-9-27 20:33</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87416\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87416-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87416\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87416';CONTENT_ID='normalthread_87416';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87416-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.26复盘：次新股“去泡沫化”  股指重新进入&quot;2&quot;时代</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-26 20:43</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87416-1-1.html\" class=\"xi2\">0</a><em>228</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87416&goto=lastpost#lastpost\">2016-9-26 20:43</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87415\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87415-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87415\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87415';CONTENT_ID='normalthread_87415';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87415-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.25：股市中长线看正是选股正当时！</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-25 20:25</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87415-1-1.html\" class=\"xi2\">0</a><em>214</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87415&goto=lastpost#lastpost\">2016-9-25 20:25</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87412\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87412-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87412\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87412';CONTENT_ID='normalthread_87412';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87412-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.23涨停板复盘：重组概念低位股补涨 新次新股阴跌趋势</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-23 20:30</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87412-1-1.html\" class=\"xi2\">0</a><em>291</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87412&goto=lastpost#lastpost\">2016-9-23 20:30</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87410\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87410-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87410\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87410';CONTENT_ID='normalthread_87410';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87410-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.22复盘：新妖股王四川双马诞生  举牌增持借壳概念是热点</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-22 20:48</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87410-1-1.html\" class=\"xi2\">0</a><em>293</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87410&goto=lastpost#lastpost\">2016-9-22 20:48</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87408\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87408-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87408\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87408';CONTENT_ID='normalthread_87408';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87408-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.21复盘：美联储加息决议公布在即 银河绍兴路赵老哥再出手</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-21 20:08</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87408-1-1.html\" class=\"xi2\">0</a><em>227</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87408&goto=lastpost#lastpost\">2016-9-21 20:08</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87406\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87406-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87406\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87406';CONTENT_ID='normalthread_87406';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87406-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.20复盘：A股振幅创14年新低 个股低吸为主</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-20 20:51</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87406-1-1.html\" class=\"xi2\">0</a><em>234</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87406&goto=lastpost#lastpost\">2016-9-20 20:51</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87404\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87404-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87404\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87404';CONTENT_ID='normalthread_87404';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87404-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.19复盘：核电板块崛起，温州帮庄股横行无忌</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-19 20:45</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87404-1-1.html\" class=\"xi2\">0</a><em>206</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87404&goto=lastpost#lastpost\">2016-9-19 20:45</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87402\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87402-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87402\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87402';CONTENT_ID='normalthread_87402';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87402-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.18：炒股不忘初心继续前进 股灾是小概率事件</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-18 20:38</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87402-1-1.html\" class=\"xi2\">2</a><em>200</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%B3%A3%CA%A4%BD%AB%BE%FC.html\" c=\"1\">常胜将军</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87402&goto=lastpost#lastpost\">2016-9-19 14:06</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87401\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87401-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87401\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87401';CONTENT_ID='normalthread_87401';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87401-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.17:股市中秋节后能否给人惊喜？</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-17 22:07</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87401-1-1.html\" class=\"xi2\">0</a><em>166</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87401&goto=lastpost#lastpost\">2016-9-17 22:07</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87400\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87400-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87400\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87400';CONTENT_ID='normalthread_87400';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87400-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.14复盘：除了股市还有亲人！月是故乡明中秋节快乐</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-14 18:17</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87400-1-1.html\" class=\"xi2\">0</a><em>202</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87400&goto=lastpost#lastpost\">2016-9-14 18:17</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87398\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87398-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87398\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87398';CONTENT_ID='normalthread_87398';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87398-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.13复盘：石墨烯板块强势吸金 行情反弹个股精彩</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-13 20:28</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87398-1-1.html\" class=\"xi2\">0</a><em>199</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87398&goto=lastpost#lastpost\">2016-9-13 20:28</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87396\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87396-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87396\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87396';CONTENT_ID='normalthread_87396';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87396-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.12复盘：大跌之后怎么办？重仓被套又咋办？</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-12 18:18</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87396-1-1.html\" class=\"xi2\">1</a><em>216</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%B3%A3%CA%A4%BD%AB%BE%FC.html\" c=\"1\">常胜将军</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87396&goto=lastpost#lastpost\">2016-9-13 11:03</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87393\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87393-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87393\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87393';CONTENT_ID='normalthread_87393';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87393-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.9复盘：重组复牌股第二春走强　庄股走势不得不服！</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-9 18:07</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87393-1-1.html\" class=\"xi2\">3</a><em>212</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-3331457694.html\" c=\"1\">3331457694</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87393&goto=lastpost#lastpost\">2016-9-12 11:35</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "<tbody id=\"normalthread_87394\">\n" +
                "<tr>\n" +
                "<td class=\"icn\">\n" +
                "<a href=\"http://www.taogula.com/thread-87394-1-1.html\" title=\"新窗口打开\" target=\"_blank\">\n" +
                "<img src=\"static/image/common/folder_common.gif\" />\n" +
                "</a>\n" +
                "</td>\n" +
                "<th class=\"common\">\n" +
                "<a href=\"javascript:;\" id=\"content_87394\" class=\"showcontent y\" title=\"更多操作\" onclick=\"CONTENT_TID='87394';CONTENT_ID='normalthread_87394';showMenu({'ctrlid':this.id,'menuid':'content_menu'})\"></a>\n" +
                " <a href=\"http://www.taogula.com/thread-87394-1-1.html\" onclick=\"atarget(this)\" class=\"s xst\">9.11:贫困地区IPO不用排队 炒股涨涨跌跌很正常</a>\n" +
                "</th>\n" +
                "<td class=\"by\">\n" +
                "<cite>\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" c=\"1\">天云</a></cite>\n" +
                "<em><span>2016-9-11 20:44</span></em>\n" +
                "</td>\n" +
                "<td class=\"num\"><a href=\"http://www.taogula.com/thread-87394-1-1.html\" class=\"xi2\">0</a><em>176</em></td>\n" +
                "<td class=\"by\">\n" +
                "<cite><a href=\"http://www.taogula.com/space-username-%CC%EC%D4%C6.html\" c=\"1\">天云</a></cite>\n" +
                "<em><a href=\"http://www.taogula.com/forum.php?mod=redirect&tid=87394&goto=lastpost#lastpost\">2016-9-11 20:44</a></em>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table><!-- end of table \"forum_G[fid]\" branch 1/3 -->\n" +
                "</form>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"filter_special_menu\" class=\"p_pop\" style=\"display:none\" change=\"location.href='forum.php?mod=forumdisplay&fid=47&filter='+$('filter_special').value\">\n" +
                "<ul>\n" +
                "<li><a href=\"http://www.taogula.com/forum-47-1.html\">全部主题</a></li>\n" +
                "<li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=specialtype&amp;specialtype=poll\">投票</a></li><li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=specialtype&amp;specialtype=reward\">悬赏</a></li><li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=specialtype&amp;specialtype=debate\">辩论</a></li></ul>\n" +
                "</div>\n" +
                "<div id=\"filter_reward_menu\" class=\"p_pop\" style=\"display:none\" change=\"forum.php?mod=forumdisplay&amp;fid=47&amp;filter=specialtype&amp;specialtype=reward&amp;rewardtype='+$('filter_reward').value\">\n" +
                "<ul>\n" +
                "<li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=specialtype&amp;specialtype=reward\">全部悬赏</a></li>\n" +
                "<li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=specialtype&amp;specialtype=reward&amp;rewardtype=1\">进行中</a></li></ul>\n" +
                "</div>\n" +
                "<div id=\"filter_dateline_menu\" class=\"p_pop\" style=\"display:none\">\n" +
                "<ul class=\"pop_moremenu\">\n" +
                "<li>排序: \n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=author&amp;orderby=dateline\" >发帖时间</a><span class=\"pipe\">|</span>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=reply&amp;orderby=replies\" >回复/查看</a><span class=\"pipe\">|</span>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=reply&amp;orderby=views\" >查看</a>\n" +
                "</li>\n" +
                "<li>时间: \n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;orderby=lastpost&amp;filter=dateline\" class=\"xw1\">全部时间</a><span class=\"pipe\">|</span>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;orderby=lastpost&amp;filter=dateline&amp;dateline=86400\" >一天</a><span class=\"pipe\">|</span>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;orderby=lastpost&amp;filter=dateline&amp;dateline=172800\" >两天</a><span class=\"pipe\">|</span>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;orderby=lastpost&amp;filter=dateline&amp;dateline=604800\" >一周</a><span class=\"pipe\">|</span>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;orderby=lastpost&amp;filter=dateline&amp;dateline=2592000\" >一个月</a><span class=\"pipe\">|</span>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;orderby=lastpost&amp;filter=dateline&amp;dateline=7948800\" >三个月</a>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div id=\"filter_orderby_menu\" class=\"p_pop\" style=\"display:none\">\n" +
                "<ul>\n" +
                "<li><a href=\"http://www.taogula.com/forum-47-1.html\">默认排序</a></li>\n" +
                "<li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=author&amp;orderby=dateline\">发帖时间</a></li>\n" +
                "<li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=reply&amp;orderby=replies\">回复/查看</a></li>\n" +
                "<li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=reply&amp;orderby=views\">查看</a></li>\n" +
                "<li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=lastpost&amp;orderby=lastpost\">最后发表</a></li>\n" +
                "<li><a href=\"http://www.taogula.com/forum.php?mod=forumdisplay&amp;fid=47&amp;filter=heat&amp;orderby=heats\">热门</a></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<a class=\"bm_h\" href=\"javascript:;\" rel=\"forum.php?mod=forumdisplay&fid=47&page=2\" curpage=\"1\" id=\"autopbn\" totalpage=\"792\" picstyle=\"0\" forumdefstyle=\"\">下一页 &raquo;</a>\n" +
                "<script src=\"data/cache/autoloadpage.js?m1O\" type=\"text/javascript\"></script>\n" +
                "<div class=\"bm bw0 pgs cl\">\n" +
                "<span id=\"fd_page_bottom\"><div class=\"pg\"><strong>1</strong><a href=\"http://www.taogula.com/forum-47-2.html\">2</a><a href=\"http://www.taogula.com/forum-47-3.html\">3</a><a href=\"http://www.taogula.com/forum-47-4.html\">4</a><a href=\"http://www.taogula.com/forum-47-5.html\">5</a><a href=\"http://www.taogula.com/forum-47-6.html\">6</a><a href=\"http://www.taogula.com/forum-47-7.html\">7</a><a href=\"http://www.taogula.com/forum-47-8.html\">8</a><a href=\"http://www.taogula.com/forum-47-9.html\">9</a><a href=\"http://www.taogula.com/forum-47-10.html\">10</a><a href=\"http://www.taogula.com/forum-47-792.html\" class=\"last\">... 792</a><label><input type=\"text\" name=\"custompage\" class=\"px\" size=\"2\" title=\"输入页码，按回车快速跳转\" value=\"1\" onkeydown=\"if(event.keyCode==13) {window.location='forum.php?mod=forumdisplay&fid=47&amp;page='+this.value;; doane(event);}\" /><span title=\"共 792 页\"> / 792 页</span></label><a href=\"http://www.taogula.com/forum-47-2.html\" class=\"nxt\">下一页</a></div></span>\n" +
                "<span  class=\"pgb y\"><a href=\"http://www.taogula.com/forum.php\">返&nbsp;回</a></span>\n" +
                "<a href=\"javascript:;\" id=\"newspecialtmp\" onmouseover=\"$('newspecial').id = 'newspecialtmp';this.id = 'newspecial';showMenu({'ctrlid':this.id})\" onclick=\"showWindow('newthread', 'forum.php?mod=post&action=newthread&fid=47')\" title=\"发新帖\"><img src=\"static/image/common/pn_post.png\" alt=\"发新帖\" /></a></div>\n" +
                "<!--[diy=diyfastposttop]--><div id=\"diyfastposttop\" class=\"area\"></div><!--[/diy]-->\n" +
                "<script type=\"text/javascript\">\n" +
                "var postminchars = parseInt('0');\n" +
                "var postmaxchars = parseInt('100000');\n" +
                "var disablepostctrl = parseInt('0');\n" +
                "var fid = parseInt('47');\n" +
                "</script>\n" +
                "<div id=\"f_pst\" class=\"bm\">\n" +
                "<div class=\"bm_h\">\n" +
                "<h2>快速发帖</h2>\n" +
                "</div>\n" +
                "<div class=\"bm_c\">\n" +
                "<form method=\"post\" autocomplete=\"off\" id=\"fastpostform\" action=\"forum.php?mod=post&amp;action=newthread&amp;fid=47&amp;topicsubmit=yes&amp;infloat=yes&amp;handlekey=fastnewpost\" onSubmit=\"return fastpostvalidate(this)\">\n" +
                "\n" +
                "<div id=\"fastpostreturn\" style=\"margin:-5px 0 5px\"></div>\n" +
                "\n" +
                "<div class=\"pbt cl\">\n" +
                "<input type=\"text\" id=\"subject\" name=\"subject\" class=\"px\" value=\"\" onkeyup=\"strLenCalc(this, 'checklen', 80);\" tabindex=\"11\" style=\"width: 25em\" />\n" +
                "<span>还可输入 <strong id=\"checklen\">80</strong> 个字符</span>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"cl\">\n" +
                "<div id=\"fastposteditor\">\n" +
                "<div class=\"tedt\">\n" +
                "<div class=\"bar\">\n" +
                "<span class=\"y\">\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=post&amp;action=newthread&amp;fid=47\" onclick=\"switchAdvanceMode(this.href);doane(event);\">高级模式</a>\n" +
                "</span><script src=\"data/cache/seditor.js?m1O\" type=\"text/javascript\"></script>\n" +
                "<div class=\"fpd\">\n" +
                "<a href=\"javascript:;\" title=\"文字加粗\" class=\"fbld\">B</a>\n" +
                "<a href=\"javascript:;\" title=\"设置文字颜色\" class=\"fclr\" id=\"fastpostforecolor\">Color</a>\n" +
                "<a id=\"fastpostimg\" href=\"javascript:;\" title=\"图片\" class=\"fmg\">Image</a>\n" +
                "<a id=\"fastposturl\" href=\"javascript:;\" title=\"添加链接\" class=\"flnk\">Link</a>\n" +
                "<a id=\"fastpostquote\" href=\"javascript:;\" title=\"引用\" class=\"fqt\">Quote</a>\n" +
                "<a id=\"fastpostcode\" href=\"javascript:;\" title=\"代码\" class=\"fcd\">Code</a>\n" +
                "<a href=\"javascript:;\" class=\"fsml\" id=\"fastpostsml\">Smilies</a>\n" +
                "</div></div>\n" +
                "<div class=\"area\">\n" +
                "<div class=\"pt hm\">\n" +
                "您需要登录后才可以发帖 <a href=\"member.php?mod=logging&amp;action=login\" onclick=\"showWindow('login', this.href)\" class=\"xi2\">登录</a> | <a href=\"member.php?mod=taogula51816\" class=\"xi2\">立即注册</a>\n" +
                "\n" +
                "\n" +
                "<a href=\"http://www.taogula.com/connect.php?mod=login&op=init&referer=forum.php%3Fmod%3Dforumdisplay%26fid%3D47%26page%3D1&statfrom=login\" target=\"_top\" rel=\"nofollow\"><img src=\"static/image/common/qq_login.gif\" class=\"vm\" /></a>\n" +
                "\n" +
                "\n" +
                "<a href=\"plugin.php?id=wechat:login\"><img src=\"source/plugin/wechat/image/wechat_login.png\" class=\"vm\" /></a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id=\"seccheck_fastpost\">\n" +
                "</div>\n" +
                "\n" +
                "<input type=\"hidden\" name=\"formhash\" value=\"fc46ccce\" />\n" +
                "<input type=\"hidden\" name=\"usesig\" value=\"\" />\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<p class=\"ptm pnpost\">\n" +
                "<a href=\"http://www.taogula.com/home.php?mod=spacecp&amp;ac=credit&amp;op=rule&amp;fid=47\" class=\"y\" target=\"_blank\">本版积分规则</a>\n" +
                "<button type=\"submit\" onmouseover=\"checkpostrule('seccheck_fastpost', 'ac=newthread');this.onmouseover=null\" name=\"topicsubmit\" id=\"fastpostsubmit\" value=\"topicsubmit\" tabindex=\"13\" class=\"pn pnc\"><strong>发表帖子</strong></button>\n" +
                "</p>\n" +
                "</form>\n" +
                "</div>\n" +
                "</div>\n" +
                "<!--[diy=diyforumdisplaybottom]--><div id=\"diyforumdisplaybottom\" class=\"area\"></div><!--[/diy]-->\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">document.onkeyup = function(e){keyPageScroll(e, 0, 1, 'forum.php?mod=forumdisplay&fid=47&filter=&orderby=lastpost&', 1);}</script>\n" +
                "<script type=\"text/javascript\">checkForumnew_handle = setTimeout(function () {checkForumnew(47, lasttime);}, checkForumtimeout);</script>\n" +
                "<div class=\"wp mtn\">\n" +
                "<!--[diy=diy3]--><div id=\"diy3\" class=\"area\"></div><!--[/diy]-->\n" +
                "</div>\n" +
                "<script>fixed_top_nv();</script>\t</div>\n" +
                "\n" +
                "\n" +
                "<script type=\"text/javascript\">var cookieLogin = Ajax(\"TEXT\");cookieLogin.get(\"connect.php?mod=check&op=cookie\", function() {});</script>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"wechat_float_qrcode\" class=\"p_pop xg1\" style=\"display:none;text-align:center;float:left;position:fixed;top:220px;z-index:100;margin-left: 2px;width:110px\">\n" +
                "<p class=\"cl\"><img class=\"y\" style=\"cursor:pointer\" onclick=\"display('wechat_float_qrcode');setcookie('wechatfqrc', 1, 86400)\" src=\"static/image/common/ad_close.gif\"></p>\n" +
                "<img src=\"plugin.php?id=wechat:qrcode&fid=47&amp;access=yes\" width=\"98\" />\n" +
                "<p>精彩复盘 高手技巧 </p>\n" +
                "</div>\n" +
                "<script>\n" +
                "function wechat_qrcode(type) {\n" +
                "if(type && $('wechat_float_qrcode').style.display == 'none') {\n" +
                "return;\n" +
                "}\n" +
                "var qrleft = parseInt($('ft').clientWidth + parseInt(fetchOffset($('ft'))['left']));\n" +
                "$('wechat_float_qrcode').style.display = '';\n" +
                "if(qrleft + $('wechat_float_qrcode').clientWidth > document.documentElement.clientWidth) {\n" +
                "$('wechat_float_qrcode').style.cssFloat = 'right';\n" +
                "$('wechat_float_qrcode').style.left = 'auto';\n" +
                "$('wechat_float_qrcode').style.right = 0;\n" +
                "} else {\n" +
                "$('wechat_float_qrcode').style.cssFloat = 'left';\n" +
                "$('wechat_float_qrcode').style.left = (qrleft) + 'px';\n" +
                "$('wechat_float_qrcode').style.right = 'auto';\n" +
                "}\n" +
                "}\n" +
                "_attachEvent(window, 'scroll', function () { wechat_qrcode(1); })\n" +
                "_attachEvent(window, 'load', function() { wechat_qrcode(0); }, document);\n" +
                "</script>\n" +
                "\n" +
                "<div id=\"ft\" class=\"wp cl\">\n" +
                "<div id=\"flk\" class=\"y\">\n" +
                "<p>\n" +
                "<a href=\"http://wpa.qq.com/msgrd?V=3&amp;Uin=1833701710&amp;Site=淘股啦股票网&amp;Menu=yes&amp;from=discuz\" target=\"_blank\" title=\"QQ\"><img src=\"static/image/common/site_qq.jpg\" alt=\"QQ\" /></a><span class=\"pipe\">|</span><a href=\"/about.html\" target=\"_blank\" >关于我们</a><span class=\"pipe\">|</span><a href=\"http://m.taogula.com\" >手机版</a><span class=\"pipe\">|</span><a href=\"http://www.taogula.com/archiver/\" >Archiver</a><span class=\"pipe\">|</span><a href=\"http://www.taogula.com/sitemap.php\" target=\"_blank\" >网站地图</a><span class=\"pipe\">|</span><strong><a href=\"http://www.taogula.com\" target=\"_blank\">淘股啦股票网</a></strong>\n" +
                "( <a href=\"http://www.miitbeian.gov.cn/\" target=\"_blank\">粤ICP备11106596号</a> )&nbsp;&nbsp;<span id=\"tcss\"></span><script type=\"text/javascript\" src=\"http://tcss.qq.com/ping.js?v=1VERHASH\" charset=\"utf-8\"></script><script type=\"text/javascript\" reload=\"1\">pgvMain({\"discuzParams\":{\"r2\":\"8440165\",\"ui\":0,\"rt\":\"forum\",\"md\":\"forumdisplay\",\"fi\":\"47\",\"pn\":1,\"qq\":\"000\"},\"extraParams\":\"\"});</script><script>\n" +
                "var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"//hm.baidu.com/hm.js?36c848bcf00b267b56c1a4a53081912f\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();\n" +
                "</script></p>\n" +
                "<p class=\"xs0\">\n" +
                "GMT+8, 2016-10-21 08:49<span id=\"debuginfo\">\n" +
                ", Processed in 0.044428 second(s), 15 queries\n" +
                ", Gzip On.\n" +
                "</span>\n" +
                "</p>\n" +
                "</div>\n" +
                "<div id=\"frt\">\n" +
                "<p>Powered by <strong><a href=\"http://www.discuz.net\" target=\"_blank\" rel=\"nofollow\">Discuz!</a></strong> <em>X3.2</em></p>\n" +
                "<p class=\"xs0\">&copy; 2001-2013 <a href=\"http://www.comsenz.com\" target=\"_blank\" rel=\"nofollow\">Comsenz Inc.</a></p>\n" +
                "</div></div>\n" +
                "<div id=\"scrolltop\">\n" +
                "<span hidefocus=\"true\"><a title=\"返回顶部\" onclick=\"window.scrollTo('0','0')\" class=\"scrolltopa\" ><b>返回顶部</b></a></span>\n" +
                "<span>\n" +
                "<a href=\"http://www.taogula.com/forum.php\" hidefocus=\"true\" class=\"returnboard\" title=\"返回版块\"><b>返回版块</b></a>\n" +
                "</span>\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">_attachEvent(window, 'scroll', function () { showTopLink(); });checkBlind();</script>\n" +
                "\t\t\t<div id=\"discuz_tips\" style=\"display:none;\"></div>\n" +
                "\t\t\t<script type=\"text/javascript\">\n" +
                "\t\t\t\tvar tipsinfo = '8440165|X3.2|0.6||0||0|7|1477010998|226fda262da24541e0a5e8e5b598c9a1|2';\n" +
                "\t\t\t</script>\n" +
                "\t\t\t<script src=\"http://discuz.gtimg.cn/cloud/scripts/discuz_tips.js?v=1\" type=\"text/javascript\" charset=\"UTF-8\"></script></body>\n" +
                "</html>\n";
        List<ArticleDTO> articleDTOs =  TaogulaBiz.parseHtml(html);
        if(articleDTOs.size()>0){
            for (ArticleDTO article : articleDTOs) {
                Log.i("Unit Test", article.getUrl());
            }
        }

    }

    @Test
    public void TestParseArticle(){
        String html = "\n" +
                "\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">\t<head>\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\t\t<title>10.20涨停板复盘：填权概念股成仅存热点 节奏踏对紧抓未来主线-淘股啦股票网  </title>\t<script type=\"text/javascript\" src=\"http://cbjs.baidu.com/js/m.js\"></script><link href=\"http://www.taogula.com/thread-87444-1-1.html\" rel=\"canonical\" />\t<meta name=\"keywords\" content=\"10.20涨停板复盘：填权概念股成仅存热点 节奏踏对紧抓未来主线\" />\t<meta name=\"description\" content=\" 10.20涨停板复盘：填权概念股成仅存热点 节奏踏对紧抓未来主线 \" />\t<meta name=\"generator\" content=\"Discuz! X3.2\" />\t<meta name=\"author\" content=\"Discuz! Team and Comsenz UI Team\" />\t<meta name=\"copyright\" content=\"2001-2013 Comsenz Inc.\" />\t<meta name=\"MSSmartTagsPreventParsing\" content=\"True\" />\t<meta http-equiv=\"MSThemeCompatible\" content=\"Yes\" />\t<base href=\"http://www.taogula.com/\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"data/cache/style_1_common.css?m1O\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"data/cache/style_1_forum_viewthread.css?m1O\" /><script type=\"text/javascript\">var STYLEID = '1', STATICURL = 'static/', IMGDIR = 'static/image/common', VERHASH = 'm1O', charset = 'gbk', discuz_uid = '0', cookiepre = 'W0zF_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'login|newthread', creditnotice = '1|金币|,2|银币|,3|福币|', defaultstyle = '', REPORTURL = 'aHR0cDovL3d3dy50YW9ndWxhLmNvbS90aHJlYWQtODc0NDQtMS0xLmh0bWw=', SITEURL = 'http://www.taogula.com/', JSPATH = 'data/cache/', CSSPATH = 'data/cache/style_', DYNAMICURL = '';</script>\t<script src=\"data/cache/common.js?m1O\" type=\"text/javascript\"></script>\t\t<meta name=\"application-name\" content=\"淘股啦股票网\" />\n" +
                "<meta name=\"msapplication-tooltip\" content=\"淘股啦股票网\" />\n" +
                "<meta name=\"msapplication-task\" content=\"name=淘股啦股票网;action-uri=http://www.taogula.com/portal.php;icon-uri=http://www.taogula.com/static/image/common/portal.ico\" /><meta name=\"msapplication-task\" content=\"name=淘股啦股票网;action-uri=http://www.taogula.com/forum.php;icon-uri=http://www.taogula.com/static/image/common/bbs.ico\" />\n" +
                "<link rel=\"archives\" title=\"淘股啦股票网\" href=\"http://www.taogula.com/archiver/\" />\n" +
                "<script src=\"data/cache/forum.js?m1O\" type=\"text/javascript\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body id=\"nv_forum\" class=\"pg_viewthread\" onkeydown=\"if(event.keyCode==27) return false;\">\n" +
                "<div id=\"append_parent\"></div><div id=\"ajaxwaitid\"></div>\n" +
                "<div id=\"toptb\" class=\"cl\">\n" +
                "<div class=\"wp\">\n" +
                "<div class=\"z\"><a href=\"http://www.taogula.com/fuli.html\" title=\"复利有多吓人，算过才知道\" target=\"_blank\" >复利计算器</a><a href=\"/forum-168-1.html\" target=\"_blank\"  style=\"color: blue\">网站事务</a><a href=\"http://www.taogula.com/thread-87251-1-1.html\" title=\"淘股啦股票网VIP用户\" target=\"_blank\"  style=\"color: red\">588股票群</a><script type=\"text/javascript\">var _speedMark = new Date();</script></div>\n" +
                "<div class=\"y\">\n" +
                "<a id=\"switchblind\" href=\"javascript:;\" onclick=\"toggleBlind(this)\" title=\"开启辅助访问\" class=\"switchblind\">开启辅助访问</a>\n" +
                "<a href=\"javascript:;\" id=\"switchwidth\" onclick=\"widthauto(this)\" title=\"切换到宽版\" class=\"switchwidth\">切换到宽版</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"qmenu_menu\" class=\"p_pop blk\" style=\"display: none;\">\n" +
                "<div class=\"ptm pbw hm\">\n" +
                "请 <a href=\"javascript:;\" class=\"xi2\" onclick=\"lsSubmit()\"><strong>登录</strong></a> 后使用快捷导航<br />没有帐号？<a href=\"member.php?mod=taogula51816\" class=\"xi2 xw1\">立即注册</a>\n" +
                "</div>\n" +
                "</div><div id=\"hd\">\n" +
                "<div class=\"wp\">\n" +
                "<div class=\"hdc cl\"><h2><a href=\"http://www.taogula.com/\" title=\"淘股啦股票网\"><img src=\"static/image/common/logo.png\" alt=\"淘股啦股票网\" border=\"0\" /></a></h2><script src=\"data/cache/logging.js?m1O\" type=\"text/javascript\"></script>\n" +
                "<form method=\"post\" autocomplete=\"off\" id=\"lsform\" action=\"member.php?mod=logging&amp;action=login&amp;loginsubmit=yes&amp;infloat=yes&amp;lssubmit=yes\" onsubmit=\"pwmd5('ls_password');return lsSubmit();\">\n" +
                "<div class=\"fastlg cl\">\n" +
                "<span id=\"return_ls\" style=\"display:none\"></span>\n" +
                "<div class=\"y pns\">\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<td>\n" +
                "<span class=\"ftid\">\n" +
                "<select name=\"fastloginfield\" id=\"ls_fastloginfield\" width=\"40\" tabindex=\"900\">\n" +
                "<option value=\"username\">用户名</option>\n" +
                "<option value=\"email\">Email</option>\n" +
                "</select>\n" +
                "</span>\n" +
                "<script type=\"text/javascript\">simulateSelect('ls_fastloginfield')</script>\n" +
                "</td>\n" +
                "<td><input type=\"text\" name=\"username\" id=\"ls_username\" autocomplete=\"off\" class=\"px vm\" tabindex=\"901\" /></td>\n" +
                "<td class=\"fastlg_l\"><label for=\"ls_cookietime\"><input type=\"checkbox\" name=\"cookietime\" id=\"ls_cookietime\" class=\"pc\" value=\"2592000\" tabindex=\"903\" />自动登录</label></td>\n" +
                "<td>&nbsp;<a href=\"javascript:;\" onclick=\"showWindow('login', 'member.php?mod=logging&action=login&viewlostpw=1')\">找回密码</a></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td><label for=\"ls_password\" class=\"z psw_w\">密码</label></td>\n" +
                "<td><input type=\"password\" name=\"password\" id=\"ls_password\" class=\"px vm\" autocomplete=\"off\" tabindex=\"902\" /></td>\n" +
                "<td class=\"fastlg_l\"><button type=\"submit\" class=\"pn vm\" tabindex=\"904\" style=\"width: 75px;\"><em>登录</em></button></td>\n" +
                "<td>&nbsp;<a href=\"member.php?mod=taogula51816\" class=\"xi2 xw1\">立即注册</a></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<input type=\"hidden\" name=\"quickforward\" value=\"yes\" />\n" +
                "<input type=\"hidden\" name=\"handlekey\" value=\"ls\" />\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"fastlg_fm y\" style=\"margin-right: 10px; padding-right: 10px\">\n" +
                "<p><a href=\"http://www.taogula.com/connect.php?mod=login&op=init&referer=forum.php%3Fmod%3Dviewthread%26tid%3D87444%26extra%3Dpage%253D1%26page%3D1&statfrom=login_simple\"><img src=\"static/image/common/qq_login.gif\" class=\"vm\" alt=\"QQ登录\" /></a></p>\n" +
                "<p class=\"hm xg1\" style=\"padding-top: 2px;\">只需一步，快速开始</p>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"fastlg_fm y\" style=\"margin-right: 10px; padding-right: 10px\">\n" +
                "<p><a href=\"plugin.php?id=wechat:login\"><img src=\"source/plugin/wechat/image/wechat_login.png\" class=\"vm\" /></a></p>\n" +
                "<p class=\"hm xg1\" style=\"padding-top: 2px;\">扫一扫，访问微社区</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</form>\n" +
                "\n" +
                "<script src=\"data/cache/md5.js?m1O\" type=\"text/javascript\" reload=\"1\"></script>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"nv\">\n" +
                "<a href=\"javascript:;\" id=\"qmenu\" onmouseover=\"delayShow(this, function () {showMenu({'ctrlid':'qmenu','pos':'34!','ctrlclass':'a','duration':2});showForummenu(47);})\">快捷导航</a>\n" +
                "<ul><li class=\"a\" id=\"mn_N8e54\" ><a href=\"http://www.taogula.com/\" hidefocus=\"true\"  >首页</a></li><li id=\"mn_N8ded\" ><a href=\"/forum-47-1.html\" hidefocus=\"true\" title=\"个股交流，行情复盘\"  >谈股论道<span>个股交流，行情复盘</span></a></li><li id=\"mn_N940b\" ><a href=\"http://www.taogula.com/forum-180-1.html\" hidefocus=\"true\" title=\"阅读名家观点，开阔投资视野\"  >盘前资讯<span>阅读名家观点，开阔投资视野</span></a></li><li id=\"mn_Nf208\" ><a href=\"/forum-122-1.html\" hidefocus=\"true\" title=\"炒股知识学习\"  >淘股学堂<span>炒股知识学习</span></a></li></ul>\n" +
                "</div>\n" +
                "<div class=\"p_pop h_pop\" id=\"mn_userapp_menu\" style=\"display: none\"></div><div id=\"mu\" class=\"cl\">\n" +
                "</div><div id=\"scbar\" class=\"cl\">\n" +
                "<form id=\"scbar_form\" method=\"post\" autocomplete=\"off\" onsubmit=\"searchFocus($('scbar_txt'))\" action=\"search.php?searchsubmit=yes\" target=\"_blank\">\n" +
                "<input type=\"hidden\" name=\"mod\" id=\"scbar_mod\" value=\"search\" />\n" +
                "<input type=\"hidden\" name=\"formhash\" value=\"fc46ccce\" />\n" +
                "<input type=\"hidden\" name=\"srchtype\" value=\"title\" />\n" +
                "<input type=\"hidden\" name=\"srhfid\" value=\"47\" />\n" +
                "<input type=\"hidden\" name=\"srhlocality\" value=\"forum::viewthread\" />\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<td class=\"scbar_icon_td\"></td>\n" +
                "<td class=\"scbar_txt_td\"><input type=\"text\" name=\"srchtxt\" id=\"scbar_txt\" value=\"请输入搜索内容\" autocomplete=\"off\" x-webkit-speech speech /></td>\n" +
                "<td class=\"scbar_type_td\"><a href=\"javascript:;\" id=\"scbar_type\" class=\"xg1\" onclick=\"showMenu(this.id)\" hidefocus=\"true\">搜索</a></td>\n" +
                "<td class=\"scbar_btn_td\"><button type=\"submit\" name=\"searchsubmit\" id=\"scbar_btn\" sc=\"1\" class=\"pn pnc\" value=\"true\"><strong class=\"xi2\">搜索</strong></button></td>\n" +
                "<td class=\"scbar_hot_td\">\n" +
                "<div id=\"scbar_hot\">\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</form>\n" +
                "</div>\n" +
                "<ul id=\"scbar_type_menu\" class=\"p_pop\" style=\"display: none;\"><li><a href=\"javascript:;\" rel=\"curforum\" fid=\"47\" >本版</a></li><li><a href=\"javascript:;\" rel=\"forum\" class=\"curtype\">帖子</a></li><li><a href=\"javascript:;\" rel=\"user\">用户</a></li></ul>\n" +
                "<script type=\"text/javascript\">\n" +
                "initSearchmenu('scbar', '');\n" +
                "</script>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<script>var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"//hm.baidu.com/hm.js?36c848bcf00b267b56c1a4a53081912f\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();</script>\n" +
                "<div id=\"wp\" class=\"wp\">\n" +
                "<script type=\"text/javascript\">var fid = parseInt('47'), tid = parseInt('87444');</script>\n" +
                "\n" +
                "<script src=\"data/cache/forum_viewthread.js?m1O\" type=\"text/javascript\"></script>\n" +
                "<script type=\"text/javascript\">zoomstatus = parseInt(1);var imagemaxwidth = '600';var aimgcount = new Array();</script>\n" +
                "\n" +
                "<style id=\"diy_style\" type=\"text/css\"></style>\n" +
                "<!--[diy=diynavtop]--><div id=\"diynavtop\" class=\"area\"><div id=\"frameRI79K8\" class=\"cl_frame_bm frame move-span cl frame-1\"><div id=\"frameRI79K8_left\" class=\"column frame-1-c\"><div id=\"frameRI79K8_left_temp\" class=\"move-span temp\"></div><div id=\"portal_block_2849\" class=\"cl_block_bm block move-span\"><div id=\"portal_block_2849_content\" class=\"dxb_bc\"><div class=\"portal_block_summary\"><script type=\"text/javascript\">BAIDU_CLB_fillSlot(\"1111433\");</script></div></div></div></div></div></div><!--[/diy]-->\n" +
                "<div id=\"pt\" class=\"bm cl\">\n" +
                "<div class=\"z\">\n" +
                "<a href=\"./\" class=\"nvhm\" title=\"首页\">淘股啦股票网</a><em>&raquo;</em><a href=\"http://www.taogula.com/forum.php\">淘股啦股票网</a> <em>&rsaquo;</em> <a href=\"http://www.taogula.com/forum.php?gid=1\"></a> <em>&rsaquo;</em> <a href=\"http://www.taogula.com/forum-47-1.html\">股市论谈</a> <em>&rsaquo;</em> <a href=\"http://www.taogula.com/thread-87444-1-1.html\">10.20涨停板复盘：填权概念股成仅存热点 节奏踏对紧抓未 ...</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<style id=\"diy_style\" type=\"text/css\"></style>\n" +
                "<div class=\"wp\">\n" +
                "<!--[diy=diy1]--><div id=\"diy1\" class=\"area\"></div><!--[/diy]-->\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"ct\" class=\"wp cl\">\n" +
                "<div id=\"pgt\" class=\"pgs mbm cl \">\n" +
                "<div class=\"pgt\"></div>\n" +
                "<span class=\"y pgb\"><a href=\"http://www.taogula.com/forum-47-1.html\">返回列表</a></span>\n" +
                "<a id=\"newspecial\" onmouseover=\"$('newspecial').id = 'newspecialtmp';this.id = 'newspecial';showMenu({'ctrlid':this.id})\" onclick=\"showWindow('newthread', 'forum.php?mod=post&action=newthread&fid=47')\" href=\"javascript:;\" title=\"发新帖\"><img src=\"static/image/common/pn_post.png\" alt=\"发新帖\" /></a></div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"postlist\" class=\"pl bm\">\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<td class=\"plc ptm pbn vwthd\">\n" +
                "<div class=\"y\">\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=viewthread&amp;action=printable&amp;tid=87444\" title=\"打印\" target=\"_blank\"><img src=\"static/image/common/print.png\" alt=\"打印\" class=\"vm\" /></a>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=redirect&amp;goto=nextoldset&amp;tid=87444\" title=\"上一主题\"><img src=\"static/image/common/thread-prev.png\" alt=\"上一主题\" class=\"vm\" /></a>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=redirect&amp;goto=nextnewset&amp;tid=87444\" title=\"下一主题\"><img src=\"static/image/common/thread-next.png\" alt=\"下一主题\" class=\"vm\" /></a>\n" +
                "</div>\n" +
                "<h1 class=\"ts\">\n" +
                "<span id=\"thread_subject\">10.20涨停板复盘：填权概念股成仅存热点 节奏踏对紧抓未来主线</span>\n" +
                "</h1>\n" +
                "<span class=\"xg1\">\n" +
                "<a href=\"http://www.taogula.com/thread-87444-1-1.html\" onclick=\"return copyThreadUrl(this, '淘股啦股票网')\" >[复制链接]</a>\n" +
                "</span>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\" class=\"ad\">\n" +
                "<tr>\n" +
                "<td class=\"pls\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table><div id=\"post_139183\" ><table id=\"pid139183\" class=\"plhin\" summary=\"pid139183\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<td class=\"plc\" style=\"width:100%\">\n" +
                "<div class=\"pi\" style=\"height:48px\">\n" +
                "<div id=\"fj\" class=\"y\">\n" +
                "<label class=\"z\">电梯直达</label>\n" +
                "<input type=\"text\" class=\"px p_fre z\" size=\"2\" onkeyup=\"$('fj_btn').href='forum.php?mod=redirect&ptid=87444&authorid=0&postno='+this.value\" onkeydown=\"if(event.keyCode==13) {window.location=$('fj_btn').href;return false;}\" title=\"跳转到指定楼层\" />\n" +
                "<a href=\"javascript:;\" id=\"fj_btn\" class=\"z\" title=\"跳转到指定楼层\"><img src=\"static/image/common/fj_btn.png\" alt=\"跳转到指定楼层\" class=\"vm\" /></a>\n" +
                "</div>\n" +
                "<strong>\n" +
                "<a href=\"http://www.taogula.com/thread-87444-1-1.html\"   id=\"postnum139183\" onclick=\"setCopy(this.href, '帖子地址复制成功');return false;\">\n" +
                "楼主</a>\n" +
                "</strong>\n" +
                "<div class=\"pti\">\n" +
                "<div class=\"pdbt\">\n" +
                "</div>\n" +
                "<div class=\"authi\">\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" target=\"_blank\" class=\"xi2 z\" style=\"padding-right:10px;\"><img src=\"http://www.taogula.com/uc_server/avatar.php?uid=4717&size=small\" /></a>\n" +
                "<img class=\"authicn vm\" id=\"authicon139183\" src=\"static/image/common/online_supermod.gif\" />\n" +
                "<a href=\"http://www.taogula.com/space-uid-4717.html\" target=\"_blank\" class=\"xi2\">天云</a>\n" +
                "<em id=\"authorposton139183\">发表于 2016-10-20 19:37</em>\n" +
                "<span class=\"pipe\">|</span>\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=viewthread&amp;tid=87444&amp;page=1&amp;authorid=4717\" rel=\"nofollow\">只看该作者</a>\n" +
                "<span class=\"none\"><img src=\"static/image/common/arw_r.gif\" class=\"vm\" alt=\"回帖奖励\" /></span>\n" +
                "<span class=\"pipe show\">|</span><a href=\"http://www.taogula.com/forum.php?mod=viewthread&amp;tid=87444&amp;extra=page%3D1&amp;ordertype=1\"  class=\"show\">倒序浏览</a>\n" +
                "<span class=\"pipe show\">|</span><a href=\"javascript:;\" onclick=\"readmode($('thread_subject').innerHTML, 139183);\" class=\"show\">阅读模式</a>\n" +
                "<div style=\"float:right;position:relative;top:-6px;\"><div class=\"bdsharebuttonbox\"><a href=\"#\" class=\"bds_more\" data-cmd=\"more\"></a><a href=\"#\" class=\"bds_tsina\" data-cmd=\"tsina\" title=\"分享到新浪微博\"></a><a href=\"#\" class=\"bds_tsohu\" data-cmd=\"tsohu\" title=\"分享到搜狐微博\"></a><a href=\"#\" class=\"bds_douban\" data-cmd=\"douban\" title=\"分享到豆瓣网\"></a><a href=\"#\" class=\"bds_bdysc\" data-cmd=\"bdysc\" title=\"分享到百度云收藏\"></a></div>\n" +
                "<script>window._bd_share_config={\"common\":{\"bdSnsKey\":{\"tsina\":\"3262180292\"},\"bdText\":\"\",\"bdMini\":\"2\",\"bdMiniList\":false,\"bdPic\":\"\",\"bdStyle\":\"1\",\"bdSize\":\"24\"},\"share\":{},\"image\":{\"viewList\":[\"tsina\",\"tsohu\",\"douban\",\"bdysc\"],\"viewText\":\"分享到：\",\"viewSize\":\"24\"},\"selectShare\":{\"bdContainerClass\":null,\"bdSelectMiniList\":[\"tsina\",\"tsohu\",\"douban\",\"bdysc\"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script></div></div>\n" +
                "</div>\n" +
                "</div><div class=\"pct\"><style type=\"text/css\">.pcb{margin-right:0}</style><div class=\"pcb\">\n" +
                " \n" +
                "<div class=\"t_fsz\">\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\"><tr><td class=\"t_f\" id=\"postmessage_139183\">\n" +
                "年线争夺战依然激烈，还是缩量。跌5%以上的个股开始增多了，一些前期<a href=\"http://www.taogula.com/\" target=\"_blank\" class=\"relatedlink\">强势股</a>出现在了跌幅榜上前列，部分主力在出货把收益落袋为安。次新股今天高位跳水，大多数是新开板不久的，需要注意风险。<br />\n" +
                "<br />\n" +
                "溢多利不负众望的一字板开盘，代替名家汇成为填权新龙头，龙二煌上煌贴身跟随，今天又有资金启动的通裕重工这个低价填权。一部分带填权特征的老次新股，今日集体走强，比如可立克002782，银宝山新002786，蓝海华腾300484，华自科技300490，亚股份300512，高澜股份300499。<br />\n" +
                "<br />\n" +
                "今天仅存的强势热点仅存填权，填权之后估计就是开始炒作高送转预期了，节奏踏对主线抓住了，估计是赚得盆满钵满，下波重点布线超跌未曾送过股的次新股。<br />\n" +
                "<br />\n" +
                "今日最强板块：尾盘名家汇跳水，一盆冷水从头淋下，明天填权板块不排除会进入休整期，但是相信还会继续有高点，别慌！<br />\n" +
                "<br />\n" +
                "高送转—填权：溢多利、金桥信息、煌上煌、四通新材、通裕重工、康拓红外、可立克、财信发展、皖新传媒<br />\n" +
                "<br />\n" +
                "OLED：濮阳惠成、星星科技、彩虹股份<br />\n" +
                "<br />\n" +
                "智慧党建：金桥信息、飞利信<br />\n" +
                "<br />\n" +
                "东音股份：前天推荐高送转预期个股，后面陆续淘一些有预期的个股和大家分享，加入到自选股中观察，走势破位，不达预期的及时删除淘汰，留强汰弱。<br />\n" +
                "<br />\n" +
                "年底需紧随高送转填权的行情主线，其他一些题材股就如烟火般灿烂，来去匆匆，并不太适合大多数人玩。</td></tr></table>\n" +
                "\n" +
                "<div class=\"ptg mbm mtn\">\n" +
                "<a title=\"数据库应用\" href=\"misc.php?mod=tag&amp;id=12968\" target=\"_blank\">数据库应用</a>, <a title=\"次新股\" href=\"misc.php?mod=tag&amp;id=12502\" target=\"_blank\">次新股</a>, <a title=\"彩虹股份\" href=\"misc.php?mod=tag&amp;id=12969\" target=\"_blank\">彩虹股份</a>, <a title=\"煌上煌\" href=\"misc.php?mod=tag&amp;id=12871\" target=\"_blank\">煌上煌</a>, <a title=\"皖新传媒\" href=\"misc.php?mod=tag&amp;id=12970\" target=\"_blank\">皖新传媒</a></div>\n" +
                "\n" +
                "</div>\n" +
                "<div id=\"comment_139183\" class=\"cm\">\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"post_rate_div_139183\"></div>\n" +
                "<div id=\"hm_t_101538\" class=\"wp\"></div></div>\n" +
                "</div>\n" +
                "\n" +
                "</td></tr>\n" +
                "<tr><td class=\"plc plm\">\n" +
                "<div id=\"p_btn\" class=\"mtw mbm hm cl\">\n" +
                "<div class=\"tshare cl\">\n" +
                "<b>分享到:&nbsp;</b>\n" +
                "\n" +
                "\n" +
                "<a href=\"http://www.taogula.com/home.php?mod=spacecp&ac=plugin&id=qqconnect:spacecp&pluginop=share&sh_type=4&thread_id=87444\" id=\"k_share_to_qq\" title=\"QQ好友和群\" target=\"_blank\"><i><img src=\"static/image/common/qq_share.png\" alt=\"QQ好友和群\" />QQ好友和群</i></a>\n" +
                "<a href=\"javascript:void(0);\" id=\"k_qqshare\" onclick=\"postToQzone();\" title=\"QQ空间\"><i><img src=\"static/image/common/qzone.gif\" alt=\"QQ空间\" />QQ空间</i></a>\n" +
                "<a href=\"javascript:void(0)\" onclick=\"postToWb();\" id=\"k_weiboshare\" title=\"腾讯微博\"><i><img src=\"static/image/common/weibo.png\" alt=\"腾讯微博\" />腾讯微博</i></a>\n" +
                "<a href=\"javascript:void(0);\" onclick=\"postToPengyou();\" id=\"k_pengyoushare\" title=\"腾讯朋友\"><i><img src=\"static/image/common/pengyou.png\" alt=\"腾讯朋友\" />腾讯朋友</i></a>\n" +
                "<script type=\"text/javascript\">\n" +
                "function postToWb(){\n" +
                "var _t = encodeURI(document.title);\n" +
                "var _url = encodeURIComponent(document.location);\n" +
                "var _appkey = encodeURI(\"801101930\");\n" +
                "var _pic = \"\";\n" +
                "var _site = encodeURIComponent(\"淘股啦股票网\");\n" +
                "var _from = 'discuz';\n" +
                "var _u = 'http://v.t.qq.com/share/share.php?url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic+'&title='+_t+'&from='+_from;\n" +
                "window.open( _u,'', 'width=700, height=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' );\n" +
                "}\n" +
                "function postToPengyou(){\n" +
                "var _url = encodeURIComponent(document.location.href);\n" +
                "var _site = encodeURIComponent(\"淘股啦股票网\");\n" +
                "var _title = encodeURIComponent(\"10.20涨停板复盘：填权概念股成仅存热点 节奏踏对紧抓未来主线\");\n" +
                "var _pics = \"\";\n" +
                "var _from = 'discuz';\n" +
                "var _u = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?to=pengyou&url='+_url+'&site='+_site+'&title='+_title+'&pics='+_pics+'&from='+_from;\n" +
                "window.open(_u);\n" +
                "}\n" +
                "</script>\n" +
                "\t\n" +
                "\n" +
                "\n" +
                "<a href=\"javascript:;\" onclick=\"showWindow('wechat_share', 'plugin.php?id=wechat:qrcode&threadqr=87444&access=yes&share=yes')\"><i><img src=\"source/plugin/wechat/image/share.png\" alt=\"微信\" />微信</i></a>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "<a href=\"http://www.taogula.com/home.php?mod=spacecp&amp;ac=favorite&amp;type=thread&amp;id=87444&amp;formhash=fc46ccce\" id=\"k_favorite\" onclick=\"showWindow(this.id, this.href, 'get', 0);\" onmouseover=\"this.title = $('favoritenumber').innerHTML + ' 人收藏'\" title=\"收藏本帖\"><i><img src=\"static/image/common/fav.gif\" alt=\"收藏\" />收藏<span id=\"favoritenumber\" style=\"display:none\">0</span></i></a>\n" +
                "<a id=\"recommend_add\" href=\"forum.php?mod=misc&amp;action=recommend&amp;do=add&amp;tid=87444&amp;hash=fc46ccce\"  onclick=\"showWindow('login', this.href)\" onmouseover=\"this.title = $('recommendv_add').innerHTML + ' 人点赞'\" title=\"顶一下\"><i><img src=\"static/image/common/rec_add.gif\" alt=\"点赞\" />点赞<span id=\"recommendv_add\" style=\"display:none\">0</span></i></a>\n" +
                "</div>\n" +
                "<div class=\"mtw mbw\">\n" +
                "<h3 class=\"pbm mbm bbda\">相关帖子</h3>\n" +
                "<ul class=\"xl xl2 cl\"><li>&#8226; <a href=\"http://www.taogula.com/thread-87324-1-1.html\" title=\"7.29 市场核心人气股点评－－如何筛选强势股？\" target=\"_blank\">7.29 市场核心人气股点评－－如何筛选强势股？</a></li>\n" +
                "<li>&#8226; <a href=\"http://www.taogula.com/thread-87365-1-1.html\" title=\"8.25涨停板复盘：高送转低位填权次新股受捧，半导体国产替代前景广阔\" target=\"_blank\">8.25涨停板复盘：高送转低位填权次新股受捧，半导体国产替代前景广阔</a></li>\n" +
                "<li>&#8226; <a href=\"http://www.taogula.com/thread-87377-1-1.html\" title=\"9.1复盘：举牌概念成败皆此股，耐心等多头的方向信号\" target=\"_blank\">9.1复盘：举牌概念成败皆此股，耐心等多头的方向信号</a></li>\n" +
                "<li>&#8226; <a href=\"http://www.taogula.com/thread-87400-1-1.html\" title=\"9.14复盘：除了股市还有亲人！月是故乡明中秋节快乐\" target=\"_blank\">9.14复盘：除了股市还有亲人！月是故乡明中秋节快乐</a></li>\n" +
                "<li>&#8226; <a href=\"http://www.taogula.com/thread-87432-1-1.html\" title=\"10.12复盘：债转股龙头温州邦庄股强势连板 这个板块或可期待主升浪行情\" target=\"_blank\">10.12复盘：债转股龙头温州邦庄股强势连板 这个板块或可期待主升浪行情</a></li>\n" +
                "<li>&#8226; <a href=\"http://www.taogula.com/thread-87436-1-1.html\" title=\"10.16：股市寻找Ten bagger 次新是牛股集中营\" target=\"_blank\">10.16：股市寻找Ten bagger 次新是牛股集中营</a></li>\n" +
                "<li>&#8226; <a href=\"http://www.taogula.com/thread-87438-1-1.html\" title=\"10.17复盘：B股跪了蓝瘦香菇 次新股轮动上涨\" target=\"_blank\">10.17复盘：B股跪了蓝瘦香菇 次新股轮动上涨</a></li>\n" +
                "<li>&#8226; <a href=\"http://www.taogula.com/thread-87440-1-1.html\" title=\"10.18涨停板复盘：填权混改概念风起，股市内外充满了快活的空气\" target=\"_blank\">10.18涨停板复盘：填权混改概念风起，股市内外充满了快活的空气</a></li>\n" +
                "<li>&#8226; <a href=\"http://www.taogula.com/thread-87442-1-1.html\" title=\"10.19涨停板复盘：填权混改概念风起，抓住主线方能赚钱\" target=\"_blank\">10.19涨停板复盘：填权混改概念风起，抓住主线方能赚钱</a></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr id=\"_postposition139183\"></tr>\n" +
                "<tr>\n" +
                "<td class=\"plc\" style=\"overflow:visible;--> width:100%\">\n" +
                "<div class=\"po hin\">\n" +
                "<div class=\"pob cl\">\n" +
                "<em>\n" +
                "<a class=\"fastre\" href=\"forum.php?mod=post&amp;action=reply&amp;fid=47&amp;tid=87444&amp;reppost=139183&amp;extra=page%3D1&amp;page=1\" onclick=\"showWindow('reply', this.href)\">回复</a>\n" +
                "</em>\n" +
                "\n" +
                "<p>\n" +
                "<a href=\"javascript:;\" onclick=\"showWindow('miscreport139183', 'misc.php?mod=report&rtype=post&rid=139183&tid=87444&fid=47', 'get', -1);return false;\">举报</a>\n" +
                "</p>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"ad\">\n" +
                "<td class=\"pls\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div><div id=\"postlistreply\" class=\"pl\"><div id=\"post_new\" class=\"viewthread_table\" style=\"display: none\"></div></div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<form method=\"post\" autocomplete=\"off\" name=\"modactions\" id=\"modactions\">\n" +
                "<input type=\"hidden\" name=\"formhash\" value=\"fc46ccce\" />\n" +
                "<input type=\"hidden\" name=\"optgroup\" />\n" +
                "<input type=\"hidden\" name=\"operation\" />\n" +
                "<input type=\"hidden\" name=\"listextra\" value=\"page%3D1\" />\n" +
                "<input type=\"hidden\" name=\"page\" value=\"1\" />\n" +
                "</form>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"pgs mtm mbm cl\">\n" +
                "<span class=\"pgb y\"><a href=\"http://www.taogula.com/forum-47-1.html\">返回列表</a></span>\n" +
                "<a id=\"newspecialtmp\" onmouseover=\"$('newspecial').id = 'newspecialtmp';this.id = 'newspecial';showMenu({'ctrlid':this.id})\" onclick=\"showWindow('newthread', 'forum.php?mod=post&action=newthread&fid=47')\" href=\"javascript:;\" title=\"发新帖\"><img src=\"static/image/common/pn_post.png\" alt=\"发新帖\" /></a>\n" +
                "</div>\n" +
                "\n" +
                "<!--[diy=diyfastposttop]--><div id=\"diyfastposttop\" class=\"area\"></div><!--[/diy]-->\n" +
                "<script type=\"text/javascript\">\n" +
                "var postminchars = parseInt('0');\n" +
                "var postmaxchars = parseInt('100000');\n" +
                "var disablepostctrl = parseInt('0');\n" +
                "</script>\n" +
                "\n" +
                "<div id=\"f_pst\" class=\"pl bm bmw\">\n" +
                "<form method=\"post\" autocomplete=\"off\" id=\"fastpostform\" action=\"forum.php?mod=post&amp;action=reply&amp;fid=47&amp;tid=87444&amp;extra=page%3D1&amp;replysubmit=yes&amp;infloat=yes&amp;handlekey=fastpost\" onSubmit=\"return fastpostvalidate(this)\">\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<td class=\"pls\">\n" +
                "</td>\n" +
                "<td class=\"plc\">\n" +
                "\n" +
                "<span id=\"fastpostreturn\"></span>\n" +
                "\n" +
                "\n" +
                "<div class=\"cl\">\n" +
                "<div id=\"fastposteditor\">\n" +
                "<div class=\"tedt mtn\">\n" +
                "<div class=\"bar\">\n" +
                "<span class=\"y\">\n" +
                "<a href=\"http://www.taogula.com/forum.php?mod=post&amp;action=reply&amp;fid=47&amp;tid=87444\" onclick=\"return switchAdvanceMode(this.href)\">高级模式</a>\n" +
                "</span><script src=\"data/cache/seditor.js?m1O\" type=\"text/javascript\"></script>\n" +
                "<div class=\"fpd\">\n" +
                "<a href=\"javascript:;\" title=\"文字加粗\" class=\"fbld\">B</a>\n" +
                "<a href=\"javascript:;\" title=\"设置文字颜色\" class=\"fclr\" id=\"fastpostforecolor\">Color</a>\n" +
                "<a id=\"fastpostimg\" href=\"javascript:;\" title=\"图片\" class=\"fmg\">Image</a>\n" +
                "<a id=\"fastposturl\" href=\"javascript:;\" title=\"添加链接\" class=\"flnk\">Link</a>\n" +
                "<a id=\"fastpostquote\" href=\"javascript:;\" title=\"引用\" class=\"fqt\">Quote</a>\n" +
                "<a id=\"fastpostcode\" href=\"javascript:;\" title=\"代码\" class=\"fcd\">Code</a>\n" +
                "<a href=\"javascript:;\" class=\"fsml\" id=\"fastpostsml\">Smilies</a>\n" +
                "</div></div>\n" +
                "<div class=\"area\">\n" +
                "<div class=\"pt hm\">\n" +
                "您需要登录后才可以回帖 <a href=\"member.php?mod=logging&amp;action=login\" onclick=\"showWindow('login', this.href)\" class=\"xi2\">登录</a> | <a href=\"member.php?mod=taogula51816\" class=\"xi2\">立即注册</a>\n" +
                "\n" +
                "\n" +
                "<a href=\"http://www.taogula.com/connect.php?mod=login&op=init&referer=forum.php%3Fmod%3Dviewthread%26tid%3D87444%26extra%3Dpage%253D1%26page%3D1&statfrom=login\" target=\"_top\" rel=\"nofollow\"><img src=\"static/image/common/qq_login.gif\" class=\"vm\" /></a>\n" +
                "\n" +
                "\n" +
                "<a href=\"plugin.php?id=wechat:login\"><img src=\"source/plugin/wechat/image/wechat_login.png\" class=\"vm\" /></a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id=\"seccheck_fastpost\">\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<input type=\"hidden\" name=\"formhash\" value=\"fc46ccce\" />\n" +
                "<input type=\"hidden\" name=\"usesig\" value=\"\" />\n" +
                "<input type=\"hidden\" name=\"subject\" value=\"  \" />\n" +
                "<p class=\"ptm pnpost\">\n" +
                "<a href=\"http://www.taogula.com/home.php?mod=spacecp&amp;ac=credit&amp;op=rule&amp;fid=47\" class=\"y\" target=\"_blank\">本版积分规则</a>\n" +
                "<button type=\"button\" onclick=\"showWindow('login', 'member.php?mod=logging&action=login&guestmessage=yes')\" onmouseover=\"checkpostrule('seccheck_fastpost', 'ac=reply');this.onmouseover=null\" name=\"replysubmit\" id=\"fastpostsubmit\" class=\"pn pnc vm\" value=\"replysubmit\" tabindex=\"5\"><strong>发表回复</strong></button>\n" +
                "<label for=\"fastpostrefresh\"><input id=\"fastpostrefresh\" type=\"checkbox\" class=\"pc\" />回帖后跳转到最后一页</label>\n" +
                "<script type=\"text/javascript\">if(getcookie('fastpostrefresh') == 1) {$('fastpostrefresh').checked=true;}</script>\n" +
                "</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</form>\n" +
                "</div>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "var connect_qzone_share_url = 'http://www.taogula.com/home.php?mod=spacecp&ac=plugin&id=qqconnect:spacecp&pluginop=share&sh_type=1&thread_id=87444';\n" +
                "var connect_weibo_share_url = 'http://www.taogula.com/home.php?mod=spacecp&ac=plugin&id=qqconnect:spacecp&pluginop=share&sh_type=2&thread_id=87444';\n" +
                "var connect_thread_info = {\n" +
                "thread_url: 'http://www.taogula.com/thread-87444-1-1.html',\n" +
                "thread_id: '87444',\n" +
                "post_id: '139183',\n" +
                "forum_id: '47',\n" +
                "author_id: '4717',\n" +
                "author: '天云'\n" +
                "};\n" +
                "\n" +
                "connect_autoshare = '0';\n" +
                "connect_isbind = '';\n" +
                "if(connect_autoshare == 1 && connect_isbind) {\n" +
                "_attachEvent(window, 'load', function(){\n" +
                "connect_share(connect_weibo_share_url, connect_openid);\n" +
                "});\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "new lazyload();\n" +
                "</script>\n" +
                "<script type=\"text/javascript\">document.onkeyup = function(e){keyPageScroll(e, 0, 0, 'forum.php?mod=viewthread&tid=87444', 1);}</script>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"wp mtn\">\n" +
                "<!--[diy=diy3]--><div id=\"diy3\" class=\"area\"></div><!--[/diy]-->\n" +
                "</div>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "function succeedhandle_followmod(url, msg, values) {\n" +
                "var fObj = $('followmod_'+values['fuid']);\n" +
                "if(values['type'] == 'add') {\n" +
                "fObj.innerHTML = '不收听';\n" +
                "fObj.href = 'home.php?mod=spacecp&ac=follow&op=del&fuid='+values['fuid'];\n" +
                "} else if(values['type'] == 'del') {\n" +
                "fObj.innerHTML = '收听TA';\n" +
                "fObj.href = 'home.php?mod=spacecp&ac=follow&op=add&hash=fc46ccce&fuid='+values['fuid'];\n" +
                "}\n" +
                "}\n" +
                "fixed_avatar([139183], 1);\n" +
                "</script>\t</div>\n" +
                "\n" +
                "\n" +
                "<script type=\"text/javascript\">var cookieLogin = Ajax(\"TEXT\");cookieLogin.get(\"connect.php?mod=check&op=cookie\", function() {});</script>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"wechat_float_qrcode\" class=\"p_pop xg1\" style=\"display:none;text-align:center;float:left;position:fixed;top:220px;z-index:100;margin-left: 2px;width:110px\">\n" +
                "<p class=\"cl\"><img class=\"y\" style=\"cursor:pointer\" onclick=\"display('wechat_float_qrcode');setcookie('wechatfqrc', 1, 86400)\" src=\"static/image/common/ad_close.gif\"></p>\n" +
                "<img src=\"plugin.php?id=wechat:qrcode&tid=87444&qrsize=2&amp;access=yes\" width=\"98\" />\n" +
                "<p>精彩复盘 高手技巧 </p>\n" +
                "</div>\n" +
                "<script>\n" +
                "function wechat_qrcode(type) {\n" +
                "if(type && $('wechat_float_qrcode').style.display == 'none') {\n" +
                "return;\n" +
                "}\n" +
                "var qrleft = parseInt($('ft').clientWidth + parseInt(fetchOffset($('ft'))['left']));\n" +
                "$('wechat_float_qrcode').style.display = '';\n" +
                "if(qrleft + $('wechat_float_qrcode').clientWidth > document.documentElement.clientWidth) {\n" +
                "$('wechat_float_qrcode').style.cssFloat = 'right';\n" +
                "$('wechat_float_qrcode').style.left = 'auto';\n" +
                "$('wechat_float_qrcode').style.right = 0;\n" +
                "} else {\n" +
                "$('wechat_float_qrcode').style.cssFloat = 'left';\n" +
                "$('wechat_float_qrcode').style.left = (qrleft) + 'px';\n" +
                "$('wechat_float_qrcode').style.right = 'auto';\n" +
                "}\n" +
                "}\n" +
                "_attachEvent(window, 'scroll', function () { wechat_qrcode(1); })\n" +
                "_attachEvent(window, 'load', function() { wechat_qrcode(0); }, document);\n" +
                "</script>\n" +
                "\n" +
                "<div id=\"ft\" class=\"wp cl\">\n" +
                "<div id=\"flk\" class=\"y\">\n" +
                "<p>\n" +
                "<a href=\"http://wpa.qq.com/msgrd?V=3&amp;Uin=1833701710&amp;Site=淘股啦股票网&amp;Menu=yes&amp;from=discuz\" target=\"_blank\" title=\"QQ\"><img src=\"static/image/common/site_qq.jpg\" alt=\"QQ\" /></a><span class=\"pipe\">|</span><a href=\"/about.html\" target=\"_blank\" >关于我们</a><span class=\"pipe\">|</span><a href=\"http://m.taogula.com\" >手机版</a><span class=\"pipe\">|</span><a href=\"http://www.taogula.com/archiver/\" >Archiver</a><span class=\"pipe\">|</span><a href=\"http://www.taogula.com/sitemap.php\" target=\"_blank\" >网站地图</a><span class=\"pipe\">|</span><strong><a href=\"http://www.taogula.com\" target=\"_blank\">淘股啦股票网</a></strong>\n" +
                "( <a href=\"http://www.miitbeian.gov.cn/\" target=\"_blank\">粤ICP备11106596号</a> )&nbsp;&nbsp;<span id=\"tcss\"></span><script type=\"text/javascript\" src=\"http://tcss.qq.com/ping.js?v=1VERHASH\" charset=\"utf-8\"></script><script type=\"text/javascript\" reload=\"1\">pgvMain({\"discuzParams\":{\"r2\":\"8440165\",\"ui\":0,\"rt\":\"forum\",\"md\":\"viewthread\",\"fi\":\"47\",\"ti\":\"87444\",\"pn\":1,\"qq\":\"000\"},\"extraParams\":\"\"});</script><script>\n" +
                "var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"//hm.baidu.com/hm.js?36c848bcf00b267b56c1a4a53081912f\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();\n" +
                "</script></p>\n" +
                "<p class=\"xs0\">\n" +
                "GMT+8, 2016-10-21 11:13<span id=\"debuginfo\">\n" +
                ", Processed in 0.049114 second(s), 24 queries\n" +
                ", Gzip On.\n" +
                "</span>\n" +
                "</p>\n" +
                "</div>\n" +
                "<div id=\"frt\">\n" +
                "<p>Powered by <strong><a href=\"http://www.discuz.net\" target=\"_blank\" rel=\"nofollow\">Discuz!</a></strong> <em>X3.2</em></p>\n" +
                "<p class=\"xs0\">&copy; 2001-2013 <a href=\"http://www.comsenz.com\" target=\"_blank\" rel=\"nofollow\">Comsenz Inc.</a></p>\n" +
                "</div></div>\n" +
                "<script src=\"home.php?mod=misc&ac=sendmail&rand=1477019614\" type=\"text/javascript\"></script>\n" +
                "<div id=\"scrolltop\">\n" +
                "<span><a href=\"http://www.taogula.com/forum.php?mod=post&amp;action=reply&amp;fid=47&amp;tid=87444&amp;extra=page%3D1&amp;page=1\" onclick=\"showWindow('reply', this.href)\" class=\"replyfast\" title=\"快速回复\"><b>快速回复</b></a></span>\n" +
                "<span hidefocus=\"true\"><a title=\"返回顶部\" onclick=\"window.scrollTo('0','0')\" class=\"scrolltopa\" ><b>返回顶部</b></a></span>\n" +
                "<span>\n" +
                "<a href=\"http://www.taogula.com/forum-47-1.html\" hidefocus=\"true\" class=\"returnlist\" title=\"返回列表\"><b>返回列表</b></a>\n" +
                "</span>\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">_attachEvent(window, 'scroll', function () { showTopLink(); });checkBlind();</script>\n" +
                "\t\t\t<div id=\"discuz_tips\" style=\"display:none;\"></div>\n" +
                "\t\t\t<script type=\"text/javascript\">\n" +
                "\t\t\t\tvar tipsinfo = '8440165|X3.2|0.6||0||0|7|1477019614|886251add0a09343b9ed320459256372|2';\n" +
                "\t\t\t</script>\n" +
                "\t\t\t<script src=\"http://discuz.gtimg.cn/cloud/scripts/discuz_tips.js?v=1\" type=\"text/javascript\" charset=\"UTF-8\"></script></body>\n" +
                "</html>\n";
        String str = TaogulaBiz.parseArticleContent(html);

        Log.i("UnitTest",str);

    }


}