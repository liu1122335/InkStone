
package com.example.inkstonedemo1.view.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inkstonedemo1.R
import com.google.android.filament.Engine
import com.google.ar.core.Anchor
import com.google.ar.core.Config
import com.google.ar.core.Frame
import com.google.ar.core.Plane
import com.google.ar.core.TrackingFailureReason
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.createAnchorOrNull
import io.github.sceneview.ar.arcore.getUpdatedPlanes
import io.github.sceneview.ar.arcore.isValid
import io.github.sceneview.ar.getDescription
import io.github.sceneview.ar.node.AnchorNode
import io.github.sceneview.ar.rememberARCameraNode
import io.github.sceneview.loaders.MaterialLoader
import io.github.sceneview.loaders.ModelLoader
import io.github.sceneview.model.ModelInstance
import io.github.sceneview.node.CubeNode
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberCollisionSystem
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMaterialLoader
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes
import io.github.sceneview.rememberOnGestureListener
import io.github.sceneview.rememberView

private var kModelFile : String = "3Dmodels/fish.glb"
private const val kMaxModelInstances = 10

@Composable
fun ARShowScreen(
    modifier : Modifier = Modifier,
    arFilePath : String = "3Dmodels/fish.glb",
    navController: NavController
){
    Box (
        modifier = modifier
    ){
        kModelFile = arFilePath

        val engine = rememberEngine()
        val modelLoader = rememberModelLoader(engine = engine)
        val materialLoader = rememberMaterialLoader(engine = engine)
        val cameraNode = rememberARCameraNode(engine = engine)
        val childNodes = rememberNodes()
        val view = rememberView(engine = engine)
        val collisionSystem = rememberCollisionSystem(view = view)

        var planeRenderer by remember { mutableStateOf(true) }

        val modelInstances = remember { mutableListOf<ModelInstance>() }
        var trackingFailureReason by remember {
            mutableStateOf<TrackingFailureReason?>(null)
        }
        var frame by remember { mutableStateOf<Frame?>(null) }

        ARScene(
            modifier = Modifier.fillMaxSize(),
            childNodes = childNodes,
            engine = engine,
            view = view,
            modelLoader = modelLoader,
            collisionSystem = collisionSystem,
            sessionConfiguration = { session,config ->
                config.depthMode =
                    when(session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)){
                        true -> Config.DepthMode.AUTOMATIC
                        false -> Config.DepthMode.DISABLED
                    }
                config.instantPlacementMode = Config.InstantPlacementMode.LOCAL_Y_UP
                config.lightEstimationMode =
                    Config.LightEstimationMode.ENVIRONMENTAL_HDR
            },
            cameraNode = cameraNode,
            planeRenderer = planeRenderer,
            onTrackingFailureChanged = {
                trackingFailureReason  = it
            },
            onSessionUpdated = { _, updatedFrame ->
                frame = updatedFrame

                if (childNodes.isEmpty()){
                    updatedFrame.getUpdatedPlanes()
                        .firstOrNull { it.type == Plane.Type.HORIZONTAL_DOWNWARD_FACING }
                        ?.let { it.createAnchorOrNull(it.centerPose) }?.let { anchor ->
                            childNodes += createAnchorNode(
                                engine = engine,
                                modelLoader = modelLoader,
                                materialLoader = materialLoader,
                                modelInstances = modelInstances,
                                anchor = anchor
                            )
                        }
                }
            },
            onGestureListener = rememberOnGestureListener(
                onSingleTapConfirmed = { motionEvent, node ->
                    if (node == null){
                        val hitResult = frame?.hitTest(motionEvent.x,motionEvent.y)
                        hitResult?.firstOrNull {
                            it.isValid (
                                depthPoint = false,
                                point = false
                            )
                        }?.createAnchorOrNull()
                            ?.let { anchor ->
                                planeRenderer = false
                                childNodes += createAnchorNode(
                                    engine = engine,
                                    modelLoader = modelLoader,
                                    materialLoader = materialLoader,
                                    modelInstances = modelInstances,
                                    anchor = anchor
                                )
                            }
                    }
                }
            )
        )
        Text(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            color = Color.White,
            text = trackingFailureReason?.let {
                it.getDescription(LocalContext.current)
            }?: if (childNodes.isEmpty()){
                stringResource(id = R.string.point_your_phone_down)
            }else{
                stringResource(id = R.string.tap_anywhere_to_add_model)
            }
        )
    }
    BackHandler {
        navController.popBackStack()
    }
}

fun createAnchorNode(
    engine: Engine,
    modelLoader: ModelLoader,
    materialLoader: MaterialLoader,
    modelInstances: MutableList<ModelInstance>,
    anchor: Anchor
): AnchorNode {
    val anchorNode = AnchorNode(engine = engine, anchor = anchor)
    val modelNode = ModelNode(
        modelInstance = modelInstances.apply {
            if (isEmpty()){
                this += modelLoader.createInstancedModel(kModelFile, kMaxModelInstances)
            }
        }.removeLast(),
        scaleToUnits = 0.5f
    ).apply {
        isEditable = true
    }
    val boundingBoxMode = CubeNode(
        engine = engine,
        size = modelNode.size,
        center = modelNode.center,
        materialInstance = materialLoader.createColorInstance(Color.White.copy(alpha = 0.5f))
    ).apply {
        isVisible = false
    }
    modelNode.addChildNode(boundingBoxMode)
    anchorNode.addChildNode(modelNode)

    listOf(modelNode,anchorNode).forEach {
        it.onEditingChanged = { editingTransforms ->
            boundingBoxMode.isVisible = editingTransforms.isNotEmpty()
        }
    }
    return anchorNode
}

