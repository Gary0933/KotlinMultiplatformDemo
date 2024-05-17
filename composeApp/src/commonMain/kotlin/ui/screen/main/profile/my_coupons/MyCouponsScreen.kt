package ui.screen.main.profile.my_coupons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import ui.components.BasicScreenUI
import ui.components.Spacer_32dp
import ui.components.Spacer_8dp
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.offer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.components.noRippleClickable
import ui.theme.BorderColor
import ui.theme.grey_050

@Composable
fun ProfileMyCouponsScreen(
    backOnTopBar: () -> Unit,
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    BasicScreenUI(
        toolbarTitle = "My Coupons",
        backOnTopBarOnClick = backOnTopBar,
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {

            Spacer_32dp()

            Text("Best offer for you", style = MaterialTheme.typography.titleLarge)
            Spacer_8dp()

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                itemsIndexed (couponsList) {index, value ->
                    Coupon(
                        couponContent = value,
                        onExecuteCopyCode = {
                            clipboardManager.setText(AnnotatedString(value.code))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Coupon(
    couponContent: CouponContent,
    onExecuteCopyCode: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth().height(180.dp)
                .border(1.dp, BorderColor, MaterialTheme.shapes.medium)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(couponContent.title, style = MaterialTheme.typography.titleLarge)
                Text(couponContent.description, style = MaterialTheme.typography.bodyMedium)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.offer),
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        "Get ${couponContent.offPercent}% OFF",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                    .background(grey_050).noRippleClickable {
                        onExecuteCopyCode()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "COPY CODE",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

data class CouponContent (
    val title: String,
    val description: String,
    val code: String,
    val offPercent: Int
)

val couponsList: List<CouponContent> = listOf(
    CouponContent(title = "WELCOME24",description = "Add items worth $2 more to unlock",code = "SDSD23SD", offPercent = 50),
    CouponContent(title = "BLACKFRIDAY24",description = "Add items worth $1 more to unlock",code = "S2323SD", offPercent = 75),
    CouponContent(title = "HOLYDAY24",description = "Add items worth $15 more to unlock",code = "SFER23SD", offPercent = 15),
    CouponContent(title = "WELCOME24",description = "Add items worth $2 more to unlock",code = "SDSD23SD", offPercent = 50),
    CouponContent(title = "BLACKFRIDAY24",description = "Add items worth $1 more to unlock",code = "S2323SD", offPercent = 75),
    CouponContent(title = "HOLYDAY24",description = "Add items worth $15 more to unlock",code = "SFER23SD", offPercent = 15),
)
