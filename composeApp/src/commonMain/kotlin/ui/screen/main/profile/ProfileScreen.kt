package ui.screen.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.arrow_right
import kotlinmultiplatformdemo.composeapp.generated.resources.coupon
import kotlinmultiplatformdemo.composeapp.generated.resources.location2
import kotlinmultiplatformdemo.composeapp.generated.resources.order
import kotlinmultiplatformdemo.composeapp.generated.resources.payment
import kotlinmultiplatformdemo.composeapp.generated.resources.profile2
import kotlinmultiplatformdemo.composeapp.generated.resources.setting2
import kotlinmultiplatformdemo.composeapp.generated.resources.wallet
import kotlinmultiplatformdemo.composeapp.generated.resources.warning
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.components.CircleImage
import ui.components.Spacer_12dp
import ui.components.Spacer_16dp
import ui.components.Spacer_32dp
import ui.components.Spacer_8dp
import ui.components.noRippleClickable

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ProfileScreen(
    navigateToMyOrders: () -> Unit,
    navigateToShowUsers: () -> Unit,
    navigateToSettings: () -> Unit
) {
    Scaffold {
        Box(
            modifier = Modifier.padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer_16dp()

                Text("Profile", style = MaterialTheme.typography.titleLarge)

                Spacer_16dp()

                CircleImage(
                    image = "",
                    modifier = Modifier.size(120.dp)
                )

                Spacer_16dp()

                Text(
                    text = "Gary",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer_32dp()

                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp), // 设置卡片的布局和边距
                    elevation = CardDefaults.cardElevation(8.dp), // 设置阴影大小
                    shape = RoundedCornerShape(8.dp), // 设置圆角
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer_12dp()

                        ProfileItemBox(title = "Edit profile", image = Res.drawable.profile2) {

                        }
                        ProfileItemBox(title = "Settings", image = Res.drawable.setting2) {
                            navigateToSettings() // 跳转到settings页面
                        }
                        ProfileItemBox(title = "Show Users", image = Res.drawable.coupon) {
                            navigateToShowUsers()
                        }
                        ProfileItemBox(title = "Manage Address", image = Res.drawable.location2) {

                        }
                        ProfileItemBox(title = "Payment Methods", image = Res.drawable.payment) {

                        }
                        ProfileItemBox(title = "My Orders", image = Res.drawable.order) {
                            navigateToMyOrders()
                        }
                        ProfileItemBox(title = "My Wallet", image = Res.drawable.wallet) {

                        }
                        ProfileItemBox(title = "Help Center", image = Res.drawable.warning, isLastItem = true) {

                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ProfileItemBox(
    title: String,
    image: DrawableResource,
    isLastItem: Boolean = false,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            .noRippleClickable { onClick() }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(image),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(35.dp)
                )
                Spacer_8dp()
                Text(title, style = MaterialTheme.typography.bodyLarge)
            }

            Icon(
                painterResource(Res.drawable.arrow_right),
                null,
                tint = MaterialTheme.colorScheme.primary.copy(alpha = .7f),
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer_12dp()
        if (!isLastItem) {
            HorizontalDivider()
            Spacer_12dp()
        }
    }
}