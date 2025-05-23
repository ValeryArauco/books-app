package com.ucb.ucbtest.planes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ucb.domain.Plan
import com.ucb.ucbtest.R
import kotlinx.coroutines.launch
@Composable
@Preview
fun PlanesUI(){
    val plans = listOf(
        Plan(
            name = "Plan FLEX 5",
            oldPrice = "$270",
            currentPrice = "$199",
            dataAmount = "5GB",
            features = listOf(
                "Llamadas y SMS ilimitados",
                "Hotspot: Comparte tus datos",
                "Redes Sociales ilimitadas incluidas",
                "Arma tu plan con más apps ilimitadas",
                "CO2 Negativo"
            )
        ),
        Plan(
            name = "Plan FLEX 8",
            oldPrice = "$370",
            currentPrice = "$299",
            dataAmount = "8GB",
            features = listOf(
                "Llamadas y SMS ilimitados",
                "Hotspot: Comparte tus datos",
                "Redes Sociales ilimitadas incluidas",
                "Arma tu plan con más apps ilimitadas",
                "CO2 Negativo"
            )
        ),
        Plan(
            name = "Plan FLEX 10",
            oldPrice = "$470",
            currentPrice = "$399",
            dataAmount = "10GB",
            features = listOf(
                "Llamadas y SMS ilimitados",
                "Hotspot: Comparte tus datos",
                "Redes Sociales ilimitadas incluidas",
                "Arma tu plan con más apps ilimitadas",
                "CO2 Negativo"
            )
        )
    )
    var currentPlanIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background).padding(20.dp),
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "Nuestros planes móviles",
            color = Color(0xFFF08080),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp).align(Alignment.CenterHorizontally),
        )

        Text(
            text = "La mejor cobertura, increíbles beneficios y un compromiso con el planeta.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        // Plan Cards with Navigation
        Box(modifier = Modifier.fillMaxWidth()) {
            // Plan Card Content
            this@Column.AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                    PlanCard(
                        plan = plans[currentPlanIndex],
                        onWhatsAppClick = { /*openWhatsApp()*/ }
                    )
            }

            IconButton(
                onClick = {
                    coroutineScope.launch {
                        currentPlanIndex = if (currentPlanIndex > 0) {
                            currentPlanIndex - 1
                        } else {
                            plans.size - 1 // Go to last plan when at first
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset((-8).dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF08080).copy(alpha = 0.8f))
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous Plan",
                    tint = Color.White
                )
            }

            // Right Navigation Arrow (always visible)
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        currentPlanIndex = if (currentPlanIndex < plans.size - 1) {
                            currentPlanIndex + 1
                        } else {
                            0 // Go to first plan when at last
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF08080).copy(alpha = 0.8f))
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next Plan",
                    tint = Color.White
                )
            }
        }
    }
}
    @Composable
    fun PlanCard(plan: Plan, onWhatsAppClick: () -> Unit) {
        val context = LocalContext.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)


        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = plan.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF08080),
                    modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
                )

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(bottom = 10.dp).align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Antes",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = plan.oldPrice,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Gray,
                        modifier = Modifier.padding(5.dp, 0.dp),
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                    )
                    Text(
                        text = "/mes",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 2.dp, start = 2.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(bottom = 10.dp).align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Ahora",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = plan.currentPrice,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black,
                        modifier = Modifier.padding(5.dp, 0.dp)
                    )
                    Text(
                        text = "/mes",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 2.dp, start = 2.dp)
                    )
                }

                Text(
                    text = plan.dataAmount,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 20.dp)
                )


                plan.features.forEach { feature ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color(0xFFF08080),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = feature,
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_whatsapp),
                            contentDescription = "WhatsApp",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp).align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                    )
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_facebook),
                            contentDescription = "Facebook",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp).align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                    )
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_messenger),
                            contentDescription = "Messenger",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp).align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_x),
                            contentDescription = "X",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp).align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_instagram),
                            contentDescription = "Instagram",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp).align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_snapchat),
                            contentDescription = "Snapchat",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp).align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_telegram),
                            contentDescription = "Telegram",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp).align(Alignment.Center)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Want This Plan Button
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { /* Handle plan selection */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF08080)),
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                    ) {
                        Text(
                            text = "Quiero este plan",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // WhatsApp Button
                    FloatingActionButton(
                        onClick = onWhatsAppClick,
                        containerColor = Color(0xFF25D366),
                        contentColor = Color.White,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_whatsapp2),
                            contentDescription = "WhatsApp",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
        }
    }

