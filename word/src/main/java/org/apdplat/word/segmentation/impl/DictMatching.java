package org.apdplat.word.segmentation.impl;

import java.util.ArrayList;
import java.util.List;

import org.apdplat.word.recognition.RecognitionTool;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

public class DictMatching  extends AbstractSegmentation{

    @Override
    public SegmentationAlgorithm getSegmentationAlgorithm() {
        return SegmentationAlgorithm.MaximumMatching;
    }
    @Override
    public List<Word> segImpl(String text) {
        List<Word> result = new ArrayList<>();
        //文本长度
        final int textLen=text.length();
        //从未分词的文本中截取的长度
        int len=getInterceptLength();
        //剩下未分词的文本的索引
        int start=0;
        //只要有词未切分完就一直继续
        while(start<textLen){
            if(len>textLen-start){
                //如果未分词的文本的长度小于截取的长度
                //则缩短截取的长度
                len=textLen-start;
            }
            //用长为len的字符串查词典，并做特殊情况识别
            boolean c=getDictionary().contains(text, start, len);
            boolean r=RecognitionTool.recog(text, start, len);
            while(! c && ! r){
                //如果长度为一且在词典中未找到匹配
                //则按长度为一切分
                if(len==1){
                    break;
                }
                //如果查不到，则长度减一后继续
                len--;
            }
            if(c){
            	addWord(result, text, start, len);
            }
            //从待分词文本中向后移动索引，滑过已经分词的文本
            start+=len;
            //每一次成功切词后都要重置截取长度
            len=getInterceptLength();
        }
        return result;
    }    
    
    
	static String a="      <p>　　相关基金套利交易性价比不高</p>"+
			"   <p>　　⊙记者 赵明超 ○编辑 张亦文</p>"+
			"   <p>　　随着2月19日一世班禅三只新股的上市，最近获批上市的50只新股，除了暂缓发行的两只以外，48只新股均已成功上市。由于大部分新股上市后均遭到爆炒，使得参与打新的基金获益匪浅。统计数据显示，198只公募基金累计打新362次，参与基金总收益逾54亿元。</p>"+
			"   <p>　　1月17日，纽威股份(603699,股吧)上市流通，作为IPO重启后首家上市的公司，上市首日涨幅高达43.49%，拉开了新股狂欢的序幕。随着2月19日岭南园林(002717,股吧)、登云股份(002715,股吧)和东易日盛的上市，在获批的50家新股中，除了暂缓发行的奥赛康(300361,股吧)和宏良股份(002720,股吧)，此前密集上市告一段落。</p>"+
			"   <p>　　暂停一年多之后重启的IPO，受到了资金的爆炒，不少新股上市首日涨幅就超过40%，这也给相关获配公募基金带来不菲的浮盈。根据2012年5月实行的新规定，基金获配股份在新股上市首日就可抛售，这给参与基金带来很大的操作余地。好买基金统计数据显示，截至2月19日，48只新股自上市以来平均涨幅约为103.47%，以此计算，如果基金选择继续持有，参与基金的总收益约有54.27亿元。</p>"+
			"   <p>　　通过分析此次参与打新的基金，可以发现呈现几个特点。首先是混合型基金的打新倾向较强，在至少打中4只新股的17只基金中，混合型基金占据7席，领先于其他各类型基金，主要是往年打新的主力军债券型基金，在此次打新中被禁止参与，而混合型基金不受股票型60%底仓的限制，可用更大比例的资产用于打新，使得混合型基金有较强的打新意愿。</p>"+
			"   <p>　　在众多打新基金中，国联安安心成长多次打中新股，吸引了众多投资者积极申购，使得规模迅速膨胀，摊薄了基金收益。数据显示，该基金净值自2013年年12月26日至2014年1月10日，净值波动幅度始终为零，明显在空仓等待打新。其所打中的新宝股份(002705,股吧)、炬华科技(300360,股吧)和楚天科技(300358,股吧)于1月21日同时上市，涨幅分别为45.24%、18.67%和45.20%，该基金在当日可获利约4683.46万元。按其2013年末的规模7.43亿来计算，当日净值涨幅应为6.30%左右。而在21日，该基金的收益增幅仅为1.71%。</p>"+
			"   <p>　　此轮打新基金的第二个特点是保本型基金表现突出。自14年1月17日至2月19日，打中新股的保本型基金平均收益率为3.87%，好于不少专职的打新基金。保本型基金表现突出，这是由于保本型基金的最高赎回费率一般为1.5%～2%，而股票型或混合型基金的这一比例低于1%，在一定程度上抑制了新增申购量，使得打新收益没有被稀释。另外，债券资产在保本型基金的配置中占比较大，而1月份中旬以来债券市场的反弹，使保本型基金获利不少。</p>"+
			"   <p>　　那么，投资者是否可以借道打新基金参与套利呢？好买基金表示，从投资者的角度看，虽然获配新股可以给相关基金带来不菲的收益，但打新基金获资金追捧后收益可能被稀释，净值增长幅度可能有限；而投资者申购赎回基金成本较大，所以这种套利交易性价比并不高。</p>";

    public static void main(String[] args){
//    	 WordConfTools.set("dic.path", "classpath:dic.txt");
//         DictionaryFactory.reload();//更改词典路径之后，重新加载词典
//        String text = "他十分惊讶地说：“啊，原来是您，杨尚川！能见到您真是太好了，我有个Nutch问题想向您请教呢！”";
//        if(args !=null && args.length == 1){
//            text = args[0];
//        }
        DictMatching m = new DictMatching();
        System.out.println("-----------");
        String t=a.replaceAll("<.*?>", "");
        System.out.println(m.seg(t).toString());

    }
}
