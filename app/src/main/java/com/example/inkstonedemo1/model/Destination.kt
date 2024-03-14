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

object DetailInformationDestination : Destination{
    override val route: String = "DetailInformation"
    override val icon: Int = R.drawable.ic_collected
    override val tabName: String = "详细信息"
}

object HistoryDestination : Destination{
    override val route: String = "History"
    override val icon: Int = R.drawable.ic_history
    override val tabName: String = "历史"
}

object UserDestination : Destination{
    override val route: String = "User"
    override val icon: Int = R.drawable.ic_user
    override val tabName: String = "用户"

}

object ARShowDestination : Destination{
    override val route: String = "ARShow"
    override val icon: Int = R.drawable.ic_ar
    override val tabName: String = "AR"
}

object IdentifyDestination : Destination{
    override val route: String = "Identify"
    override val icon: Int = R.drawable.ic_identify
    override val tabName: String = "识别"
}

object MainInformationDestination : Destination {
    override val route: String = "MainInformation"
    override val icon: Int = R.drawable.ic_collected
    override val tabName: String = "主详细"
}

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

object ProducedKnowledgeDestination : Destination{
    override val route: String = "ProducedKnowledge"
    override val icon: Int = R.drawable.ic_produced
    override val tabName: String = "工艺知识"


}

val allDestinations = listOf(
    MainShowDestination,
    KnowledgeDestination,
    DetailInformationDestination,
    HistoryDestination,
    UserDestination,
    ARShowDestination,
    IdentifyDestination,
    MainInformationDestination
)

val knowledgeDestinations = listOf(
    HistoryKnowledgeDestination,
    AppreciateKnowledgeDestination,
    ProducedKnowledgeDestination,
)
