package ui.screen.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import business.data_state.DeliveryState
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.components.BasicScreenUI
import ui.screen.main.home.view_model.HomeViewModel
import ui.theme.BorderColor
import ui.theme.DefaultButtonTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinInject()
) {

    BasicScreenUI(
        showTopBar = false,
    ) {

        val deliveryState: DeliveryState by homeViewModel.deliveryState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp)
                .background(Color.LightGray)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                itemsIndexed(deliveryState.product) {index, value ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .border(1.dp, BorderColor, MaterialTheme.shapes.medium)
                                .clickable {
                                    homeViewModel.updateProductToState(index, "BBB")
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(value)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
                    .height(60.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        0.dp
                    ),
                    shape = MaterialTheme.shapes.extraLarge,
                    border = null,
                    colors = DefaultButtonTheme(),
                    contentPadding = ButtonDefaults.ContentPadding,
                    onClick = {
                        homeViewModel.addProductToState("AAA")
                        println(deliveryState)
                    }
                ) {
                    Text(
                        text = stringResource(Res.string.sign_up),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
            }
        }
    }
}
