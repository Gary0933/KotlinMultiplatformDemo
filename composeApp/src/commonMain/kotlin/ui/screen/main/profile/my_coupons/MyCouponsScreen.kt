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
import androidx.lifecycle.viewmodel.compose.viewModel
import database.entity.UserInfoModel
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.offer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.components.noRippleClickable
import ui.screen.login.register.view_model.RegisterViewModel
import ui.screen.main.profile.my_coupons.view_model.ShowUsersViewModel
import ui.theme.BorderColor
import ui.theme.grey_050

@Composable
fun ProfileMyCouponsScreen(
    backOnTopBar: () -> Unit,
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val showUsersViewModel: ShowUsersViewModel = viewModel { ShowUsersViewModel() }

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
                itemsIndexed (showUsersViewModel.getUsersData()) {index, value ->
                    UserData(
                        userInfoModel = value,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun UserData(
    userInfoModel: UserInfoModel,
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
                Text(userInfoModel.UserName, style = MaterialTheme.typography.titleLarge)
                Text(userInfoModel.UserEmail, style = MaterialTheme.typography.titleLarge)
                Text(userInfoModel.UserPassword, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

