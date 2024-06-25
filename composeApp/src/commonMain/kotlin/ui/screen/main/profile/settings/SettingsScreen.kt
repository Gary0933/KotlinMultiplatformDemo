package ui.screen.main.profile.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.arrow_right
import kotlinmultiplatformdemo.composeapp.generated.resources.exit
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import storage.dataStorage
import ui.components.BasicScreenUI
import ui.components.Spacer_32dp
import ui.components.Spacer_8dp
import ui.components.noRippleClickable
import ui.screen.main.profile.settings.view_model.SettingViewModel
import ui.theme.BorderColor

@Composable
fun ProfileSettingsScreen(
    settingViewModel: SettingViewModel = koinInject(),
    backOnTopBar: () -> Unit,
    backToLogin: () -> Unit
) {
    BasicScreenUI(
        toolbarTitle = "Settings",
        backOnTopBarOnClick = backOnTopBar,
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            Spacer_32dp()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .noRippleClickable {
                        dataStorage.putBoolean("loginSuccess", false)
                        settingViewModel.logout()
                        backToLogin()
                    },
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(Res.drawable.exit),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp),
                )
                Spacer_8dp()
                Text(
                    "Logout",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
                Icon(
                    painter = painterResource(Res.drawable.arrow_right),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp),
                )
            }
            HorizontalDivider(color = BorderColor)
        }
    }
}