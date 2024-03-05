package com.example.inkstonedemo1.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

interface Destination {
    val route : String
}

object MainShowDestination : Destination{
    override val route: String ="MainShow"
}

object KnowledgeDestination : Destination{
    override val route: String = "Knowledge"
}

object DetailInformationDestination : Destination{
    override val route: String = "DetailInformation"
}

object HistoryDestination : Destination{
    override val route: String = "History"

}

object UserDestination : Destination{
    override val route: String = "User"

}

object ARShowDestination : Destination{
    override val route: String = "ARShow"
}

object IdentifyDestination : Destination{
    override val route: String = "Identify"
}

object MainInformationDestination : Destination {
    override val route: String = "MainInformation"
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