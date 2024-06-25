package ui.screen.main.profile.show_users

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import database.entity.UserInfoModel
import org.koin.compose.koinInject
import ui.components.noRippleClickable
import ui.screen.main.profile.show_users.view_model.ShowUsersViewModel
import ui.theme.BorderColor
import ui.theme.grey_050

@Composable
fun ProfileShowUsersScreen(
    showUsersViewModel: ShowUsersViewModel = koinInject(),
    backOnTopBar: () -> Unit,
) {
    // 获取用户实时的数据
    val userListState: List<UserInfoModel> by showUsersViewModel.userListState.collectAsState()

    LaunchedEffect(Unit) {// 第一次进入页面后会查询一次所有的user信息
        showUsersViewModel.getUsersData()
    }

    BasicScreenUI(
        toolbarTitle = "Show Users",
        backOnTopBarOnClick = backOnTopBar,
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {

            Spacer_32dp()

            Text("Please for reference to below data", style = MaterialTheme.typography.titleLarge)
            Spacer_8dp()

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                itemsIndexed (
                    userListState
                ) {index, value ->
                    UserData(
                        showUsersViewModel,
                        userInfoModel = value,
                    )
                }
            }
        }
    }
}

@Composable
fun UserData(
    showUsersViewModel: ShowUsersViewModel,
    userInfoModel: UserInfoModel,
) {

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
                .border(1.dp, BorderColor, MaterialTheme.shapes.medium)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "User Id : ${userInfoModel.Id}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "User Name : ${userInfoModel.UserName}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "User Email : ${userInfoModel.UserEmail}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "User Password : ${userInfoModel.UserPassword}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "User Create Date : ${userInfoModel.CreateDate}",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(grey_050)
                    .noRippleClickable {
                        showUsersViewModel.deleteUsersDataById(userInfoModel.Id)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "DELETE USER",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

