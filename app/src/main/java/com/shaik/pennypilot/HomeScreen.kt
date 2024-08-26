package com.shaik.pennypilot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.codewithfk.expensetracker.android.R
import com.shaik.pennypilot.ui.theme.LightMaroon
import com.shaik.pennypilot.widgets.ExpenseTextView

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black

    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, list, card, topBar, dots) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_topbar),
                contentDescription = null,
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Column {
                    ExpenseTextView(text = "Good Morning,", fontSize = 16.sp, color = Color.White)
                    ExpenseTextView(
                        text = "Shaik Anwar",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterEnd)
                )
            }

            CardItem(
                modifier = Modifier
                    .constrainAs(card) {
                        top.linkTo(nameRow.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            TransactionList(modifier = Modifier.fillMaxWidth().constrainAs(list){
                top.linkTo(card.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            })
        }
    }
}

@Composable
fun CardItem(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(LightMaroon)
            .padding(16.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            Column(modifier = Modifier.align(Alignment.CenterStart)){
                ExpenseTextView(
                    text = "Total Balance",
                    fontSize = 16.sp,
                    color = Color.White
                )
                ExpenseTextView(
                    text = "₹ 12,000",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.dots_menu),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Align columns to the opposite ends
        ) {
            CardRowItem(
                modifier = Modifier,
                title = "Income",
                amount = "₹ 20,000",
                image = R.drawable.ic_income
            )
            CardRowItem(
                modifier = Modifier,
                title = "Expenses",
                amount = "₹ 8,000",
                image = R.drawable.ic_expense
            )
        }
    }
}
@Composable
fun TransactionList(modifier: Modifier){
    Column(modifier = modifier.padding(horizontal=16.dp)) {
        Box(modifier = Modifier.fillMaxWidth()){
            ExpenseTextView(text = "Recent Transactions",color = Color.White, fontSize = 20.sp)
            ExpenseTextView(text = "See All", fontSize = 16.sp, color = Color.White, modifier = Modifier.align(Alignment.CenterEnd))

        }
        Spacer(modifier = Modifier.size(8.dp))
        TransactionItem(
            title = "Starbucks",
            amount = "-₹ 1000",
            icon = R.drawable.ic_starbucks,
            date = "19/08/2023",
            color = Color.Red

        )
        Spacer(modifier = Modifier.size(8.dp))
        TransactionItem(
            title = "Medicine",
            amount = "-₹ 1500",
            icon = R.drawable.ic_upwork,
            date = "16/08/2023",
            color = Color.Red

        )
        Spacer(modifier = Modifier.size(8.dp))
        TransactionItem(
            title = "EazyPay",
            amount = "+₹ 1000",
            icon = R.drawable.eazypay,
            date = "15/08/2023",
            color = Color.Green

        )
        Spacer(modifier = Modifier.size(8.dp))
        TransactionItem(
            title = "Netflix",
            amount = "-₹ 500",
            icon = R.drawable.ic_netflix,
            date = "11/08/2023",
            color = Color.Red

        )
        Spacer(modifier = Modifier.size(8.dp))
        TransactionItem(
            title = "Paypal",
            amount = "-₹ 4100",
            icon = R.drawable.ic_paypal,
            date = "9/08/2023",
            color = Color.Red

        )
        Spacer(modifier = Modifier.size(8.dp))
        TransactionItem(
            title = "Penny Pay",
            amount = "+₹ 900",
            icon = R.drawable.pennypayicon,
            date = "1/08/2023",
            color = Color.Green

        )
    }

}

@Composable
fun CardRowItem(modifier: Modifier, title: String, amount: String, image: Int) {
    Column(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(8.dp))
            ExpenseTextView(
                text = title,
                fontSize = 16.sp,
                color = Color.White
            )
        }
        ExpenseTextView(
            text = amount,
            fontSize = 16.sp,
            color = Color.White
        )
    }

}
@Composable
fun TransactionItem(title: String, amount: String, icon: Int, date: String, color: Color){
    Box(modifier=Modifier.fillMaxWidth().padding(vertical = 8.dp)){
        Row{
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier=Modifier.size(8.dp))
            Column{
                ExpenseTextView(
                        text = title,
                        fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                ExpenseTextView(
                    text = date,
                    fontSize = 12.sp,
                )

            }
        }
        ExpenseTextView(
            text =amount,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterEnd),
            color = color,
            fontWeight = FontWeight.SemiBold
        )

    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}
