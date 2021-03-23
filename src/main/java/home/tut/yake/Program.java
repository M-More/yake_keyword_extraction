package home.tut.yake;

import java.util.List;

import home.tut.yake.Yake.KeywordExtractorOutput;

public class Program {

	public static void main(String[] args) throws Exception {
		testYake(args);
//		testYakeWithVN(args);
//		testVNCoreNLP(args);
	}

	public static void testYake(String[] args) {
		String text = "Sources tell us that Google is acquiring Kaggle, a platform that hosts data science and machine learning " +
				"competitions. Details about the transaction remain somewhat vague, but given that Google is hosting its Cloud " +
				"Next conference in San Francisco this week, the official announcement could come as early as tomorrow. " +
				"Reached by phone, Kaggle co-founder CEO Anthony Goldbloom declined to deny that the acquisition is happening. " +
				"Google itself declined 'to comment on rumors'. Kaggle, which has about half a million data scientists on its platform, " +
				"was founded by Goldbloom  and Ben Hamner in 2010. " +
				"The service got an early start and even though it has a few competitors like DrivenData, TopCoder and HackerRank, " +
				"it has managed to stay well ahead of them by focusing on its specific niche. " +
				"The service is basically the de facto home for running data science and machine learning competitions. " +
				"With Kaggle, Google is buying one of the largest and most active communities for data scientists - and with that, " +
				"it will get increased mindshare in this community, too (though it already has plenty of that thanks to Tensorflow " +
				"and other projects). Kaggle has a bit of a history with Google, too, but that's pretty recent. Earlier this month, " +
				"Google and Kaggle teamed up to host a $100,000 machine learning competition around classifying YouTube videos. " +
				"That competition had some deep integrations with the Google Cloud Platform, too. Our understanding is that Google " +
				"will keep the service running - likely under its current name. While the acquisition is probably more about " +
				"Kaggle's community than technology, Kaggle did build some interesting tools for hosting its competition " +
				"and 'kernels', too. On Kaggle, kernels are basically the source code for analyzing data sets and developers can " +
				"share this code on the platform (the company previously called them 'scripts'). " +
				"Like similar competition-centric sites, Kaggle also runs a job board, too. It's unclear what Google will do with " +
				"that part of the service. According to Crunchbase, Kaggle raised $12.5 million (though PitchBook says it's $12.75) " +
				"since its   launch in 2010. Investors in Kaggle include Index Ventures, SV Angel, Max Levchin, Naval Ravikant, " +
				"Google chief economist Hal Varian, Khosla Ventures and Yuri Milner ";

		String text2="新华社美国安克雷奇3月18日消息，当地时间3月18日，中共中央政治局委员、中央外事工作委员会办公室主任杨洁篪、国务委员兼外长王毅在安克雷奇同美国国务卿布林肯、总统国家安全事务助理沙利文举行中美高层战略对话。在美方发表开场白后，王毅表示，中方是应美方邀请而来。安克雷奇位处中美两国首都航线中间位置，是中美交往的“加油站”，也是中美两国相向而行的“交汇点”。过去几年，由于中国的正当权益受到无理打压，中美关系遭遇前所未有的严重困难。这种局面损害了两国人民利益，损害了世界稳定与发展，不应再继续下去了。中方过去、现在、将来都绝不会接受美国的无端指责，同时我们要求美方彻底放弃干涉中国内政的霸道行径。美国的这个老毛病要改一改了！王毅说，尤其要指出的是，3月17日，美国在香港问题上再次升级对中国的所谓制裁。这种粗暴干涉中国内政的做法激起中国人民强烈愤慨，我们当然坚决反对。美方在中方即将动身赴美参加中美高层战略对话前夕出台这一举措，这不是正常的待客之道。如果美方想以此增强所谓对华优势的话，那完全是打错了算盘，恰恰暴露了内心的虚弱和无力。这种做法丝毫不会影响中方的正当立场，也丝毫不会动摇中国人民维护主权和尊严的坚定意志。王毅指出，美方刚才在开场白中还声称一些国家认为中国胁迫他们。美方要搞清楚这是来自他们的说法，还是美方自己的主观臆断。如果美方仅仅因为一些国家是美国的盟友就偏听偏信，甚至袒护他们的错误言行，那么国际关系就很难正常发展。到底谁在胁迫谁，世界人民自有公断，历史也会作出公正结论。王毅强调，习近平主席和拜登总统除夕通话非常重要，双方达成的共识为中美关系重回正轨指明了方向。国际社会高度关注我们在这里的对话，关注双方能否各自真正显示出诚意和善意，关注我们能否从这里向全世界发出积极和正面的信号。如美方愿意，我们可以同美方一道，在相互尊重的基础上交换意见，共同把这份责任承担起来，把我们应当做的工作做好。";

		// ------ Simple Run --------
		Yake.KeywordExtractor kw_extractor1 = new Yake.KeywordExtractor();
		List<KeywordExtractorOutput>keywords1 = kw_extractor1.extract_keywords_output(text2);
		for (KeywordExtractorOutput kw : keywords1) {
			System.out.println(kw);
		}
		// ------ Run with config --------
//		String language = "vi";
//		int max_ngram_size = 3;
//		double deduplication_thresold = 0.9;
//		DedupAlg deduplication_algo = DedupAlg.jaro;
//		int windowSize = 1;
//		int numOfKeywords = 20;
//		Yake.KeywordExtractor kw_extractor2 = new Yake.KeywordExtractor(language, max_ngram_size, deduplication_thresold, deduplication_algo, windowSize, numOfKeywords, null);
//		List<KeywordExtractorOutput>keywords2 = kw_extractor2.extract_keywords(text);
//		for (KeywordExtractorOutput kw : keywords2) {
//			System.out.println(kw);
//		}
	}
	public static void testYakeWithVN(String[] args) {
		String text = "Bóng đá Đông Nam Á suốt hai thập niên qua, dù có những cuộc đấu nội bộ tại AFF Cup và SEA Games, nhưng loanh quanh cũng chỉ có vài cái tên Thái Lan, Việt Nam, Malaysia và Singapore. Hữu ích thì cũng có hữu ích, danh vị thì cũng quyến rũ, nhưng giá trị của các sân chơi mang tính kèn cựa nhau ấy không lớn. Kỳ thực, Thái Lan đã chán phải bơi trong cái ao làng ấy bởi sự thống trị của họ với bảy HC vàng SEA Games liên tiếp và ba chức vô địch AFF Cup cũng chẳng giúp cho đội bóng của Kiatisuk có nổi một chiến thắng tại vòng loại cuối cùng World Cup 2018, kết thúc cuộc phiêu lưu với vỏn vẹn hai điểm kiếm được khi cuộc chơi gần tàn. Thậm chí, họ còn thua Iraq cả lượt đi lẫn lượt về dù đây là đối thủ mà họ từng cầm hòa hai trận khi nằm chung bảng F với Việt Nam.";

		// ------ Simple Run --------
		Yake.KeywordExtractor kw_extractor1 = new Yake.KeywordExtractor("vi");
		List<KeywordExtractorOutput>keywords1 = kw_extractor1.extract_keywords_output(text);
		for (KeywordExtractorOutput kw : keywords1) {
			System.out.println(kw);
		}
		// ------ Run with config --------
//		String language = "vi";
//		int max_ngram_size = 3;
//		double deduplication_thresold = 0.9;
//		DedupAlg deduplication_algo = DedupAlg.jaro;
//		int windowSize = 1;
//		int numOfKeywords = 20;
//		Yake.KeywordExtractor kw_extractor2 = new Yake.KeywordExtractor(language, max_ngram_size, deduplication_thresold, deduplication_algo, windowSize, numOfKeywords, null);
//		List<KeywordExtractorOutput>keywords2 = kw_extractor2.extract_keywords(text);
//		for (KeywordExtractorOutput kw : keywords2) {
//			System.out.println(kw);
//		}
	}
}
