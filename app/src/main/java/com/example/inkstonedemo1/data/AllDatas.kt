package com.example.inkstonedemo1.data

import androidx.compose.ui.graphics.Color
import com.example.compose.md_theme_dark_onSecondaryContainer
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.compose.md_theme_dark_tertiaryContainer
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.HistoryKnowledge

val allColors = listOf(
    Color(0xFF2F4F4F),
    Color(0xFF15486D),
    Color(0xFF006D5B),
    Color(0xFF86897E),
    Color(0xFF9A572D),
    Color(0xFF2C4C23),
    Color(0xFFFC907C),
    Color(0xFF222325),
    Color(0xFF05BBAA),
    Color(0xFF223553),
    Color(0xFF67284C),
    Color(0xFF241384),
    Color(0xFFAF2C02),
    Color(0xFF591D2A),
    Color(0xFF7B0119),
    Color(0xFF3A3436),
    Color(0xFFD9421A),
    Color(0xFFA5D1F6)
)

val allPatterns = listOf(
    R.drawable.pattern_1,
    R.drawable.pattern_2,
    R.drawable.pattern_3
)

val allInkStoneImages = listOf(
    R.drawable.inkstone_1,
    R.drawable.inkstone_2,
    R.drawable.inkstone_3
)

val allInkStoneClasses = listOf(
    "却砚","易水砚","歙砚","洮砚","端砚"
)

val allHistoryKnowledge = listOf(
    HistoryKnowledge(
        title = "蒙于史前：砚台的萌芽",
        content = "砚的起源被认为最迟应在母系氏族时期的仰韶文化时期或更早。" +
                "一般认为，砚台是从研磨谷物的研磨器演变过来，并在“适用”于研墨的层面上逐步加以改进而成为研墨(颜料)的器具。" +
                "现存在中国历史博物馆的“双格石研磨盘”是1958年在陕西宝鸡出土。观此研，研型为椭圆，较对称、厚重，但石质粗糙，尺寸、比例几与今人用的实用砚相符。" +
                "其结构、功能相仿，亦有区别。专家认为，这是中国最原始的石砚，也是最早的绘画砚之一。",
        relevancyImage = listOf( R.drawable.history_knowledge_relevancy_1 ),
        relevancyName = listOf("双格石研磨盘")
    ),
    HistoryKnowledge(
        title = "兴于秦汉：砚台美化初露",
        content = "汉代砚质地以石为主，此外还出现了陶砚、瓦当砚、漆砚、铜砚等。" +
                "砚的造型上已初步显示了美化的趋势，形状大多为圆形、三足，足上有的刻花，有的作兽足，造型古朴，也有龟形砚。纹饰也多受同期其他艺术形式的影响。" +
                "汉代处于青铜时代末期，汉砚造型具有“青铜化”的特征，特别是三足砚的造型。" +
                "砚台发展到了汉代以后逐渐开始有了艺术元素，取法多取自然界中有力量而且神秘传说的灵兽造型作为砚台艺术的主要表现方式，" +
                "如龙、凤、虎、熊、龟等灵兽作为砚台的底足和砚盖，汉代凿刻工艺古拙简朴，写意大方。出土的文物来看，两汉研(砚)台已基本完成了研向砚的转变。" +
                "秦汉时期先后出现秦篆汉隶等文字，在此背景下研磨器便成为文房重器。研磨器的制作工艺也各有不同，此研磨器磨板用漆处理，研棒上方有朱红图腾，以代表使用者社会等级。",
        relevancyImage = listOf(R.drawable.history_knowledge_relevancy_2_1,R.drawable.history_knowledge_relevancy_2_2),
        relevancyName = listOf("东汉·卧虎盖三足石砚","平板砚")
    ),
    HistoryKnowledge(
        title = "盛于隋唐：端砚歙砚问世",
        content = "唐代石砚已经开始讲究石材。隋唐时期，因科举制度的诞生和完善，天下文人对砚台的需求量大大增多，" +
                "为了适应供小于求的砚台市场，很多地区就地取材，使得石质砚材有突出的发展。" +
                "山东青州的红丝石，广东高要县的端石，江西婺源县的歙石和甘肃县的洮河石，已被用来制砚，红丝砚、端砚、歙砚、洮砚等优质砚材纷纷问世。" +
                "同时，澄泥砚在唐代也开始制作，并且盛于唐，澄泥砚也成为皇室贵族和文人的专属物品。" +
                "唐朝制砚工艺也有很大的进步，砚的造型由单纯的圆形三足式，发展出了圈足、多足、辟雍以及极具时代特征的箕形砚等。" +
                "前期以圆砚为多，中唐以后箕形砚开始占主导地位。" +
                "箕形砚是在前朝器形的影响下演变出新的砚式，砚面较深，蓄墨量大。" +
                "双足平底的稳定性，平滑宽敞的砚堂，推研舒畅、有容易护，适用的器形与时代风尚完美和合，使箕形砚成砚史上的典范与丰碑。",
        relevancyImage = listOf(R.drawable.history_knowledge_relevancy_3_1,R.drawable.history_knowledge_relevancy_3_2),
        relevancyName = listOf("唐·澄泥砚","唐·箕形砚")
    ),
    HistoryKnowledge(
        title = "卓于宋元：实用与欣赏并重",
        content = "宋代文化学术高度发达，文人学士们对文具的实用和审美要求也相对提高。书写之余，鉴赏和收藏名砚成为文人的一大乐事，咏砚、赞砚，铭砚的诗文层出不穷。" +
                "宋代士人更重石砚，尤其推崇端砚、歙砚。除了注重石质外，还对砚的造型十分讲究，趋于多样化。宋砚开始从单一的文房用品逐渐发展为欣赏与使用相结合的艺术品了。" +
                "宋砚款式非常丰富，不下百种。但最有特色、存世最多、最能代表宋砚流行的还数抄手砚。" +
                "所谓抄手，即可用手抄砚底截取而得名，在箕形砚的基础上演化而来。抄手砚虽少纹饰，但线条简洁洗练，每一边缘都刚劲挺拔。它的端方四直和内敛精雅的风尚正体现了宋代文人士大夫的“理”性情怀。" +
                "太史砚为理学思想的产物，始于北宋，盛于明代，是宋明时期官用砚式典型之一。",
        relevancyImage = listOf(R.drawable.history_knowledge_relevancy_4_1,R.drawable.history_knowledge_relevancy_4_2),
        relevancyName = listOf("宋·罗纹抄手砚","宋·太史砚")
    ),
    HistoryKnowledge(
        title = "弘扬于明：实用性转为艺术性",
        content = "从明代开始，砚的制作和工艺装饰发生了极大的变化，它的功能由实用为主变成了以艺术为主，进而成为一种收藏品，开创了追求风韵文人砚雕的先河。" +
                "砚台不再仅是挥毫泼墨时的工具，更是置于案头的一件玩物。明代的砚台造型端庄厚重，纹饰不甚繁丽，大件制作居多。" +
                "文人在砚上镌诗、题铭之风在明代大为盛行，因此砚台的艺术价值逐渐超越了其使用价值，收藏砚台也成了达官贵人附庸风雅的利器。",
        relevancyImage = listOf(R.drawable.history_knowledge_relevancy_5_1,R.drawable.history_knowledge_relevancy_5_2),
        relevancyName = listOf("明·鱼化龙歙砚","明·鸠献蟠桃端砚")
    ),
    HistoryKnowledge(
        title = "精繁于清：雕琢工艺走向巅峰",
        content = "清砚被视为中国砚史的巅峰之作。清代砚材取用、造型、纹饰、雕琢工艺、题铭诸方面超越前代达到砚史高峰。" +
                "不仅端、歙、松花、澄泥、红丝、洮河等名砚各擅其长，还出现了水晶、漆砂、翡翠、象牙、玻璃等名贵材质；" +
                "砚的造型仿古、仿动植物、几何形、随形等，在清砚中各占一席。纹样题材也更加广博，雕琢技法以阴、阳线刻与浅浮雕为主，以局部的镂空雕，展现出生动、精致的风格。" +
                "文人题铭砚在清代发展到极致，题铭内容涉及砚的制作者或拥有者、砚的来源和开采、材质与形制的描写和赞颂，表达文人的认知、感悟、志向等思想情感。" +
                "题铭书体则以行、隶、篆、楷、草为主，旁及金文、石鼓文等。此时，文人雅士参与制砚、刻铭已成为一种风尚。",
        relevancyImage = listOf(R.drawable.history_knowledge_relevancy_6_1,R.drawable.history_knowledge_relevancy_6_2,R.drawable.history_knowledge_relevancy_6_3),
        relevancyName = listOf("晩清·荷趣端砚","清·端石圭璧砚","清·松花石砚")
    )

)