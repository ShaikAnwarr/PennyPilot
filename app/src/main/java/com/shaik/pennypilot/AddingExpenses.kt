package com.shaik.pennypilot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
// import androidx.navigation.NavController // Uncomment if using NavController
import com.codewithfk.expensetracker.android.R
import com.shaik.pennypilot.ui.theme.LightMaroon
import com.shaik.pennypilot.widgets.ExpenseTextView

@Composable
fun AddingExpense(
    isIncome: Boolean,
    // navController: NavController? = null // Uncomment if using NavController
) {
    val menuExpanded = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, card, topBar) = createRefs()

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
                    .padding(top = 80.dp, start = 16.dp, end = 16.dp) // Adjusted top padding
                    .constrainAs(nameRow) {
                        top.linkTo(topBar.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            // navController?.popBackStack() // Uncomment if using NavController
                        }
                )
                ExpenseTextView(
                    text = "Add ${if (isIncome) "Income" else "Expense"}",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.Center)
                )
                Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Image(
                        painter = painterResource(id = R.drawable.dots_menu),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                menuExpanded.value = true
                            }
                    )
                    DropdownMenu(
                        expanded = menuExpanded.value,
                        onDismissRequest = { menuExpanded.value = false }
                    ) {
                        DropdownMenuItem(
                            text = { ExpenseTextView(text = "Settings") },
                            onClick = {
                                menuExpanded.value = false
                            }
                        )
                    }
                }
            }

            // Positioned Card
            DataForm(
                modifier = Modifier
                    .padding(horizontal = 32.dp) // Adjust padding as needed
                    .fillMaxWidth() // Increase width to fill available space
                    .constrainAs(card) {
                        top.linkTo(nameRow.bottom, margin = 32.dp) // Move the card higher
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        // You can remove or adjust the bottom constraint
                    }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataForm(modifier: Modifier) {
    // State for input fields
    val name = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(16.dp, RoundedCornerShape(16.dp)) // Slightly rounded corners
            .background(Color.Black, RoundedCornerShape(16.dp)) // Same rounding as button
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Name Field
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { ExpenseTextView("Name", color = Color.Gray) }, // Placeholder in gray
            modifier = Modifier.fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black), // Input text color
            shape = RoundedCornerShape(16.dp), // Similar to button
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = LightMaroon, // Similar to button color
                focusedBorderColor = LightMaroon // Same as button when focused
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Amount Field
        OutlinedTextField(
            value = amount.value,
            onValueChange = { amount.value = it },
            label = { ExpenseTextView("Amount", color = Color.Gray) }, // Placeholder in gray
            modifier = Modifier.fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black), // Input text color
            shape = RoundedCornerShape(16.dp), // Similar to button
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = LightMaroon, // Similar to button color
                focusedBorderColor = LightMaroon // Same as button when focused
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Date Field
        OutlinedTextField(
            value = date.value,
            onValueChange = { date.value = it },
            label = { ExpenseTextView("Date", color = Color.Gray) }, // Placeholder in gray
            modifier = Modifier.fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black), // Input text color
            shape = RoundedCornerShape(16.dp), // Similar to button
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor =LightMaroon, // Similar to button color
                focusedBorderColor = LightMaroon // Same as button when focused
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { ExpenseTextView("Add Invoice", color = Color.Gray) }, // Placeholder in gray
            modifier = Modifier.fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black), // Input text color
            shape = RoundedCornerShape(16.dp), // Similar to button
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = LightMaroon, // Similar to button color
                focusedBorderColor = LightMaroon // Same as button when focused
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Button to Add Expense
        Button(
            onClick = { /* Handle save button click */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = LightMaroon, // Similar to button color
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            ExpenseTextView(
                text = "Add Expense",
                fontSize = 14.sp,
                color = Color.Black,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddingExpensePreview() {
    AddingExpense(isIncome = false) // Passing default parameter for preview
}
