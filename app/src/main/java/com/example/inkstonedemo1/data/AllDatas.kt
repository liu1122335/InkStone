package com.example.inkstonedemo1.data

import androidx.compose.ui.graphics.Color
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.knowledge.AppreciateKnowledge
import com.example.inkstonedemo1.model.knowledge.HistoryKnowledge
import com.example.inkstonedemo1.model.Tile

val allColors = listOf(
    Color(0xFFD6D5B7),
    Color(0xFFD1BA74),
    Color(0xFFE6CEAC),
    Color(0xFFC7EDCC),
    Color(0xFFd2d97a),
    Color(0xFFf0d695),
    Color(0xFFf8b37f),
    Color(0xFFf7cfba),
    Color(0xFFf8c387),
    Color(0xFFf7e8aa)
)

val allPatterns = listOf(
    R.drawable.bg_main_pattern_1,
    R.drawable.bg_main_pattern_2,
    R.drawable.bg_main_pattern_3,
    R.drawable.bg_main_pattern_1,
    R.drawable.bg_main_pattern_2,
    R.drawable.bg_main_pattern_3,
    R.drawable.bg_main_pattern_1,
    R.drawable.bg_main_pattern_2,
    R.drawable.bg_main_pattern_3,
    R.drawable.bg_main_pattern_1,
)

val allInkStoneClasses = listOf(
    "却砚","易水砚","歙砚","洮砚","端砚"
)

val allTiles = listOf(
    Tile(
        name = "cover",
        introduce = "nothing",
        cover = R.drawable.visit_first_page_cover,
        vrPanoramaImage = R.drawable.test
    ),
    Tile(
        name = "砚坑村口",
        introduce = "首先我们来到了紫云谷砚坑村的村口，砚坑村属于高要区金渡镇，处在烂柯山深处。" +
                "如今，通往村里的山路已变水泥路，但并不平坦，九转十八弯，脚下是悬崖峭壁，山顶云雾缭绕，沿途风景迷人。",
        cover = R.drawable.visit_tile_cover_1,
        vrPanoramaImage = R.drawable.vr_image_1
    ),
    Tile(
        name = "麻子坑朝天岩坑口",
        introduce = "行过延绵不已的险峻山路，终于到达端溪南峰朝天岩。这也是一处知名的坑口，因洞口朝天，故名，且历史比麻子坑还早。" +
                "登高一望，原来此处已算接近峰顶，脚下另一侧山体陡峭如削，不由心里一惊。《端石拟》中称其“与水岩一气相通，故产石，似水岩之上层，较他山之石为最贵。" +
                "但色紫而干，亦有白中带紫者，质皆坚实，不能滑腻，久则拒墨”。",
        cover = R.drawable.visit_tile_cover_2,
        vrPanoramaImage = R.drawable.vr_image_2
    ),
    Tile(
        name = "坑仔岩坑口",
        introduce = "再上行，即是坑仔岩坑口，这也是上世纪70年代所开，一株老榕树倒伏着已然枯死——不知是地下掏空还是石块压住之故，而坑仔岩的洞口被水泥墙封了一大半，" +
                "上面留空，藤萝牵络其间，透过间隙朝里看，黑咕隆咚，惟觉凉气袭人，下面则杂草丛生，流水潺潺而出。",
        cover = R.drawable.visit_tile_cover_3,
        vrPanoramaImage = R.drawable.vr_image_3
    ),
    Tile(
        name = "老坑矿口",
        introduce = "从山上另一小路下来，便见到了一栋黄色小楼，旁边便是老坑的矿洞，如今老坑已经封坑了。" +
                "老坑始采于唐（或非今址），其后历代均有节制性开采，尤以明万历二十八年、清代光绪“张坑”（张之洞所开）声誉最隆。",
        cover = R.drawable.visit_tile_cover_4,
        vrPanoramaImage = R.drawable.vr_image_4
    ),
    Tile(
        name = "老坑坑口",
        introduce = "老坑洞位于端溪以东，邻近溪水江处。老坑砚石泥质结构，致密，块状构造。矿物成分：主要由云母类（水云母）粘土为主组成，" +
                "还有赤铁矿、石英、绿泥石、碳酸盐类，微量矿物有：电气石、金红石、黄铁矿等。",
        cover = R.drawable.visit_tile_cover_5,
        vrPanoramaImage = R.drawable.vr_image_5,
    ),
    Tile(
        name = "七星岩叮咚井坑口",
        introduce = "最后还有白端坑口——原来就在著名的七星岩景区，其中又以七岩排列榜首的玉屏岩所产为佳，白端石细润如玉，多用于研磨朱砂、化妆品与作装饰之用，" +
                "玉屏岩高不过数十米，果如其名，石皆如玉，而其中出产白端最佳的叮咚井在半山，登山见砚坑仍在，是一个宽约一米、长数米、深约十米的狭坑。",
        cover = R.drawable.visit_tile_cover_6,
        vrPanoramaImage = R.drawable.vr_image_6
    )
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

val allAppreciateKnowledge = listOf(
    AppreciateKnowledge(
        coverImage = R.drawable.appreciate_knowledge_cover_1,
        title = "材质",
        content = "常见的砚台材质包括：\n" +
                "1. 端砚：产于广东肇庆，石质细腻、滋润，发墨快且不损笔毫，是中国四大名砚之一。\n" +
                "2. 歙砚：产于安徽歙县，石质坚韧、纹理细密，具有“涩不留笔，滑不拒墨”的特点。\n" +
                "3. 洮砚：产于甘肃洮州，砚石色泽碧绿，质地细腻，为砚中上品。\n" +
                "4. 澄泥砚：以澄泥为原料，经特殊工艺烧制而成，质地细腻，色泽温润。\n" +
                "5. 玉石砚：用玉石制成，质地温润，具有较高的观赏价值。\n" +
                "6. 陶瓷砚：用陶瓷材料制作，造型多样，色彩丰富。\n" +
                "7. 紫砂砚：由紫砂泥制成，具有良好的透气性和吸水性。\n" +
                "8. 木砚：以木材为原料，常见的有檀木砚、楠木砚等。\n" +
                "9. 金属砚：如铜砚、铁砚等，质地坚硬，耐磨。\n"
    ),
    AppreciateKnowledge(
        coverImage = R.drawable.appreciate_knowledge_cover_2,
        title = "样式",
        content = "砚台的造型多种多样，主要分为七大类，包括古典式、自然式、大冠式、玉堂式、砚坯、套砚以及随形砚。\n" +
                "\n" +
                "1. 古典式：这是历代已成形制的砚式，如圭式、凤字式、琴式、日月式、抄手式等等。这些砚式都有其独特的特点和历史背景。\n" +
                "2. 自然式：也被称为“随形砚”。这种砚式是根据砚石的形状和花纹进行因材而雕，每块砚台都是独一无二的。其中，端砚中对“石眼”的处理是一种特别的技艺。\n" +
                "3. 大冠式、玉堂式、砚坯和套砚等：这些砚式也有其独特的特点和用途。\n" +
                "4. 随形砚：是一种特别的砚式，它不拘泥于固定的形状，而是根据砚材的自然形态进行雕刻，力求展现出砚材的天然美感和独特气质。这种砚式需要制砚者具备高超的技艺和丰富的想象力，因此每一件随形砚都是独一无二的。\n" +
                "\n" +
                "除了以上七大类，砚台还有很多其他的造型，如长方形、圆形、椭圆形、八角形、菱形等。这些造型的砚台既有实用的功能，也有观赏的价值。同时，不同的造型也反映了不同历史时期和地域的文化特色和艺术风格。\n" +
                "\n" +
                "总的来说，砚台的造型丰富多样，每一种造型都有其独特的美感和文化内涵。无论是古典式还是自然式，无论是大冠式还是玉堂式，都体现了制砚者的匠心独运和深厚文化底蕴。"
    ),
    AppreciateKnowledge(
        coverImage = R.drawable.appreciate_knowledge_cover_3,
        title = "雕刻",
        content = "砚台雕刻是一门古老而精湛的手工艺，其相关知识包括以下几个方面：\n" +
                "\n" +
                "雕刻技法：砚台雕刻的技法多种多样，包括浮雕、透雕、镂空雕等。其中，浮雕是在平面上雕出隆起的物体，形成立体感和层次感；透雕则是在浮雕的基础上进行镂空雕，形成强烈的真实和整体感。这些技法需要雕刻师具备高超的技艺和丰富的想象力，才能将砚石雕刻成具有艺术价值的作品。\n" +
                "\n" +
                "图案设计：砚台雕刻的图案设计也是一门艺术。图案内容广泛，包括山水、人物、龙凤、花鸟、鱼虫等，寓意着吉祥、美好和幸福。图案的布局和构图需要考虑到整体的美感和协调性，以及砚台的实用功能。\n" +
                "\n" +
                "总之，砚台雕刻是一门综合性很强的手工艺，需要雕刻师具备丰富的技艺和文化素养。通过精湛的雕刻技法和巧妙的设计构思，才能制作出既具有实用价值又具有艺术价值的砚台作品。\n"
    ),
    AppreciateKnowledge(
        coverImage = R.drawable.appreciate_knowledge_cover_4,
        title = "铭文",
        content = "砚台上的铭文，也被称为砚铭或研铭，是一种特殊的文字艺术。它通常镌刻在砚台的背面或侧面，有时也出现在正面。砚铭的内容主要是根据砚台的特点，如形象、纹理等，展开的人生体会和对生命的颖悟。这些铭文不仅是对砚台品质的鉴赏，更是体现了作者独特的人生哲学和艺术追求。\n" +
                "\n" +
                "砚铭的布局有一定的规矩，但无定式。一般来说，如果砚台有砚名，它通常被刻在右侧上方，而铭文则刻在左侧。如果没有砚名，铭文可以刻在右侧。收藏章则通常刻在左侧下方。对于刻在砚台正面的铭文，它们通常位于上方。\n" +
                "\n" +
                "在砚铭的雕刻过程中，艺术家们会遵循一些原则。例如，他们可能会将文字视为一种雕工，用以补充砚石的瑕疵处。然而，他们也会注意避免文字过大，以免掩盖砚石的天然美感。这是因为砚石的天然美、雕刻的工艺和铭文的文化内涵是相辅相成的，任何一方都不应过于突出，以免破坏整体的和谐。\n"

    )
)
