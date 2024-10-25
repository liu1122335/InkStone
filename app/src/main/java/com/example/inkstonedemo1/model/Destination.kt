package com.example.inkstonedemo1.model

import com.example.inkstonedemo1.R

interface Destination {
    val route : String
    val icon : Int
    val tabName : String
}

//主界面板块地址
object KnowledgeDestination : Destination{
    override val route: String = "Knowledge"
    override val icon: Int = R.drawable.ic_knowledge
    override val tabName: String = "知识库"
}

object IdentifyDestination : Destination{
    override val route: String = "Identify"
    override val icon: Int = R.drawable.ic_identify
    override val tabName: String = "识别"
}

object UserDestination : Destination{
    override val route: String = "User"
    override val icon: Int = R.drawable.ic_user
    override val tabName: String = "我的"
}

object EditUserDestination : Destination{
    override val route: String = "EditUser"
    override val icon: Int = R.drawable.ic_user
    override val tabName: String = "用户编辑"
}

object AllAppDestination : Destination{
    override val route: String = "AllApp"
    override val icon: Int = R.drawable.ic_all_ink_stone_list
    override val tabName: String = "全部"
}

//详细信息板块地址
object DetailInformationDestination : Destination{
    override val route: String = "DetailInformation"
    override val icon: Int = R.drawable.ic_collected
    override val tabName: String = "详细信息"
}

object ARShowDestination : Destination{
    override val route: String = "ARShow"
    override val icon: Int = R.drawable.ic_ar
    override val tabName: String = "AR展示"
}

object MainInformationDestination : Destination {
    override val route: String = "MainInformation"
    override val icon: Int = R.drawable.ic_collected
    override val tabName: String = "主详细"
}

object ImageShowDestination : Destination{
    override val route: String = "ImageShow"
    override val icon: Int = R.drawable.ic_image
    override val tabName: String = "图片展示"
}

//知识板块地址
object HistoryKnowledgeDestination : Destination{
    override val route: String = "HistoryKnowledge"
    override val icon: Int = R.drawable.ic_history
    override val tabName: String = "历史知识"
}

object MainHistoryKnowledgeDestination : Destination{
    override val route: String = "MainHistoryKnowledge"
    override val icon: Int =  R.drawable.ic_history
    override val tabName: String = "历史知识主界面"
}
object HistoryDetailDestination : Destination{
    override val route: String = "HistoryDetail"
    override val icon: Int = R.drawable.ic_history
    override val tabName: String = "历史"

}
object AppreciateKnowledgeDestination : Destination{
    override val route: String = "AppreciateKnowledge"
    override val icon: Int = R.drawable.ic_appreciate
    override val tabName: String = "工艺"
}

object CraftsmanKnowledgeDestination : Destination{
    override val route: String = "CraftsmanKnowledge"
    override val icon: Int = R.drawable.ic_craftsman
    override val tabName: String = "名匠"
}

object AllInkStoneListDestination : Destination{
    override val route: String = "AllInkStoneList"
    override val icon: Int = R.drawable.ic_all_ink_stone_list
    override val tabName: String = "名砚"
}

object MainAllInkStoneListDestination : Destination{
    override val route: String = "MainAllInkStoneList"
    override val icon: Int = R.drawable.ic_all_ink_stone_list
    override val tabName: String = "名砚主界面"
}
//趣玩板块地址
object FunnyDestination : Destination{
    override val route: String = "Funny"
    override val icon: Int = R.drawable.ic_funny
    override val tabName: String = "探索"
}
object VisitTilesDestination : Destination{
    override val route: String = "VisitTiles"
    override val icon: Int = R.drawable.ic_visit
    override val tabName: String = "砚坑探访"
}
object MainVisitDestination : Destination{
    override val route: String = "MainVisit"
    override val icon: Int = R.drawable.ic_visit
    override val tabName: String = "探访主界面"
}
object VRShowDestination : Destination{
    override val route: String = "VRShow"
    override val icon: Int = R.drawable.ic_vr
    override val tabName: String = "VR展示"

}
object WritingDestination : Destination{
    override val route: String = "Writing"
    override val icon: Int = R.drawable.ic_writing
    override val tabName: String = "临池而书"
}
object CollectedShowDestination : Destination{
    override val route: String = "CollectedShow"
    override val icon: Int = R.drawable.ic_collected
    override val tabName: String = "收藏展示"
}
object WritingShowDestination : Destination{
    override val route: String = "WritingShow"
    override val icon: Int = R.drawable.ic_writing
    override val tabName: String = "墨宝展示"
}

object DetailWritingShowDestination : Destination{
    override val route: String = "DetailWritingShow"
    override val icon: Int = R.drawable.ic_writing
    override val tabName: String = "墨宝详细展示"
}

val userDestinations = listOf(
    CollectedShowDestination,
    WritingShowDestination
)

val MainMenuDestinations = listOf(
    KnowledgeDestination,
    FunnyDestination,
    UserDestination
)