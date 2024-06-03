package ui.screen.main.profile.show_users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.components.BasicScreenUI
import ui.components.Spacer_32dp
import ui.components.Spacer_8dp
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.lifecycle.viewmodel.compose.viewModel
import database.DbEngine
import database.entity.UserInfoModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.koinInject
import ui.screen.main.profile.show_users.view_model.ShowUsersViewModel
import ui.theme.BorderColor

@Composable
fun ProfileShowUsersScreen(
    db: DbEngine = koinInject(),
    backOnTopBar: () -> Unit,
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val showUsersViewModel: ShowUsersViewModel = viewModel { ShowUsersViewModel(db) }

    BasicScreenUI(
        toolbarTitle = "Show Users",
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
            modifier = Modifier
                .fillMaxWidth()
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

