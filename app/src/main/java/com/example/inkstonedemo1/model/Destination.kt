package com.example.inkstonedemo1.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.inkstonedemo1.R

interface Destination {
    val route : String
    val icon : Int
    val tabName : String
}

//主界面板块地址
object MainShowDestination : Destination{
    override val route: String ="MainShow"
    override val icon: Int = R.drawable.ic_home
    override val tabName: String = "主页"
}

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
    override val tabName: String = "用户"

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
    override val tabName: String = "详细历史"

}
object AppreciateKnowledgeDestination : Destination{
    override val route: String = "AppreciateKnowledge"
    override val icon: Int = R.drawable.ic_appreciate
    override val tabName: String = "鉴赏知识"
}

//趣玩板块地址
object FunnyDestination : Destination{
    override val route: String = "Funny"
    override val icon: Int = R.drawable.ic_funny
    override val tabName: String = "趣玩"
}
object MainFunnyDestination : Destination{
    override val route: String = "MainFunny"
    override val icon: Int = R.drawable.ic_funny
    override val tabName: String = "趣玩主界面"
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



val knowledgeDestinations = listOf(
    HistoryKnowledgeDestination,
    AppreciateKnowledgeDestination,
)
