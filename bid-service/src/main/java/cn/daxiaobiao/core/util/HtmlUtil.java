package cn.daxiaobiao.core.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Created by cheng on 2016/2/23.
 */
public class HtmlUtil {

    // 只有纯文本可以通过
    public static String getPureText(String html) {
        if (html == null){
            return null;
        }
        return Jsoup.clean(html, Whitelist.none());
    }

    public static void main(String[] args){
        String html = "<h1 xmlns:x=\"http://www.w3.org/2001/XMLSchema" +
                "\" xmlns:d=\"http://schemas.microsoft.com/sharepoint" +
                "/dsp\" xmlns:asp=\"http://schemas.microsoft.com/ASPN" +
                "ET/20\" xmlns:__designer=\"http://schemas.microsoft.com/WebParts/v2/DataView/designer\" xmlns:sharepoint=\"Microsoft.SharePoint.WebControls\" xmlns:ddwrt2=\"urn:frontpage:internal\"><a target=\"_self\" href=\"javascript:__doPostBack('ctl00$ContentPlaceHolderMain$SPWebPartManager$g_785aec8c_f98c_4273_afeb_8a29a02bcfe9','__connect={g_b548493c_e05f_47bf_a7df_763169855856#g_7b2bd4cd_72f1_4c93_a499_1fec703a492f*@_x9879__x76ee__x540d__x79f0__x000=#@bid_id=12675};')\">沈阳工业大学软件学院2014共建设备采购(1)公开招标招标公告(LNZC2015-0440)</a></h1> <div class=\"subinfo\" xmlns:x=\"http://www.w3.org/2001/XMLSchema\" xmlns:d=\"http://schemas.microsoft.com/sharepoint/dsp\" xmlns:asp=\"http://schemas.microsoft.com/ASPNET/20\" xmlns:__designer=\"http://schemas.microsoft.com/WebParts/v2/DataView/designer\" xmlns:sharepoint=\"Microsoft.SharePoint.WebControls\" xmlns:ddwrt2=\"urn:frontpage:internal\"> 2015年12月14日 </div> <div class=\"ExternalClassFD2A6573BA704B37928BF4FBF3EB26CE\"> <p style=\"text-align:justify;\">&nbsp;&nbsp;&nbsp;&nbsp; 辽宁省政府采购中心受沈阳工业大学委托，对沈阳工业大学软件学院2014共建设备采购(1)(编号:LNZC2015-0440)项目进行国内公开招标，现欢迎国内合格的投标人参加本次政府采购活动。 </p> <p style=\"text-align:left;\"> <strong>一、采购内容</strong></p> <div> <table width=\"100%\" class=\"ms-rteTable-default\" cellspacing=\"0\"> <tbody> <tr class=\"ms-rteTableEvenRow-default\"> <td class=\"ms-rteTableEvenCol-default\" style=\"width:10%;\">包号</td> <td class=\"ms-rteTableOddCol-default\" style=\"width:60%;\">分包产品名称</td> </tr> <tr class=\"ms-rteTableOddRow-default\"> <td class=\"ms-rteTableEvenCol-default\">1</td> <td class=\"ms-rteTableOddCol-default\">嵌入式教学实验平台</td> </tr> </tbody> </table> </div> <p style=\"text-align:left;\">本项目采购内容分为1个合同包。投标人对所投包的采购内容必须全投，否则其投标无效。</p> <p style=\"text-align:left;\"><span style=\"color:#444444;\"> <strong>二、合格投标人的资格条件</strong><br>&nbsp;&nbsp;&nbsp;&nbsp;<br>1.符合《中华人民共和国政府采购法》第二十二条规定应当具备的条件<br>2.应自觉抵制政府采购领域商业贿赂行为。<br>3.本项目不允许联合体投标。<br>4.合格投标人还要满足的其它资格条件:<br>(1)参加政府采购活动前3年内在经营活动中没有重大违法记录的声明<br>(2)投标人所投产品(品目号1-2台式计算机)属于节能产品政府采购清单中强制采购产品的,需提供投标产品在最新一期“节能产品政府采购清单”中当前页的打印件。（复印件） &nbsp;&nbsp; </span></p> <p style=\"text-align:left;\"> <strong>三、政府采购供应商入库须知 </strong> &nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp; 参加辽宁省省本级政府采购活动的供应商为进入辽宁省政府采购供应商库的，请详阅辽宁政府采购网（网址http://www.ccgp-liaoning.gov.cn）“首页-重要通知”中公布的“辽宁省政府采购供应商入库须知”，及时办理入库登记手续。</p> <p style=\"text-align:left;\"> <strong>四、获取招标文件、报名登记的时间及方式&nbsp;</strong><br>&nbsp;&nbsp;&nbsp;&nbsp; 即日起至2016年01月04日，下载采购文件，并进行报名登记详见辽宁省政府集中采购网（网址http://www.lnzc.gov.cn）“首页-重要通知”中公布的“参加辽宁省政府采购中心组织实施的政府采购项目的供应商下载采购文件、报名登记注意事项”。 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p> <p style=\"text-align:left;\"> <strong>五、递交投标文件截止及开标时间，递交投标文件及开标地点 </strong> <br>&nbsp;&nbsp;&nbsp;&nbsp; 递交投标文件截止及开标时间：2016-01-05 13:30（北京时间）&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp; 递交投标文件及开标地点：辽宁省政府采购中心五楼开标会议室，届时请投标人的法定代表人或其授权代表人按时参加公开开标大会。&nbsp;&nbsp;&nbsp;&nbsp; </p> <p style=\"text-align:left;\"> <strong>六、采购单位、采购代理机构的名称、地址和联系方式 </strong> <br> 采购单位：沈阳工业大学 <br> 地 址：沈阳经济技术开发区沈辽西路111号 <br> 联系人：​唐岩 <br> 联系电话：​024-25496068 <br> 集中采购机构：辽宁省政府采购中心 <br> 地址：沈阳市和平区太原北街二号（市府大路西塔岗西走100米路南砖红色楼）综合楼A座<font style=\"color:#444444;\">509</font><span style=\"color:#444444;\">室</span> <br> 项目联系人：关欣 、周璐<br>联系电话：024-23447772、23447750&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>传真：024-2344<span style=\"color:#444444;\">77</span><span style=\"color:#444444;\">73</span><span style=\"color:#444444;\">&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp; <br>投标保证金咨询电话：024-23447726 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>合同事宜咨询电话：024-23447778/23447750 <br></p> <p style=\"text-align:right;\">辽宁省政府采购中心</p> <p style=\"text-align:right;\">2015年12月14日</p> </div> <div class=\"ms-clear\"></div>";
        String pureText = getPureText(html);
        System.out.println(pureText);
    }
}
