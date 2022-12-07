/*
 * Copyright 2022 Joel Kanyi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kanyideveloper.home.presentation.home.mymeal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kanyideveloper.compose_ui.theme.MainOrange
import com.kanyideveloper.compose_ui.theme.MyLightOrange
import com.kanyideveloper.domain.model.MealCategory
import com.kanyideveloper.home.presentation.home.HomeNavigator
import com.kanyideveloper.home.presentation.home.composables.MealItem
import com.kanyideveloper.mealtime.core.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MyMealScreen(
    navigator: HomeNavigator
) {
    var showRandomMeal by remember {
        mutableStateOf(false)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Categories",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        item(span = { GridItemSpan(2) }) {
            val mealCategories = listOf(
                MealCategory(
                    "Food",
                    R.drawable.ic_food
                ),
                MealCategory(
                    "Breakfast",
                    R.drawable.ic_breakfast
                ),
                MealCategory(
                    "Drinks",
                    R.drawable.ic_drinks
                ),
                MealCategory(
                    "Fruits",
                    R.drawable.ic_fruit
                ),
                MealCategory(
                    "Fast Food",
                    R.drawable.ic_pizza_thin
                )
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(mealCategories) { meal ->
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MyLightOrange),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = MainOrange,
                                painter = painterResource(id = meal.icon),

                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = meal.name,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item(span = { GridItemSpan(2) }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(200.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = 0.dp
            ) {
                Box(Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.randomize_meals),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "What to cook for lunch?",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Button(
                            onClick = {
                                showRandomMeal = true
                            }
                        ) {
                            Text(
                                text = "Get a Random Meal",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }

        if (showRandomMeal) {
            item(span = { GridItemSpan(2) }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(180.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = 0.dp
                ) {
                    Box(Modifier.fillMaxSize()) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.meal_banner),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                        Card(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .clickable {
                                    showRandomMeal = false
                                },
                            shape = RoundedCornerShape(8.dp),
                            elevation = 0.dp,
                            backgroundColor = Color.Red.copy(alpha = 0.8f)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 3.dp, horizontal = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Default.Close,
                                    tint = Color.White,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    modifier = Modifier.padding(vertical = 3.dp),
                                    text = "Dismiss",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomEnd)
                                .background(Color.Black.copy(alpha = 0.6f))
                                .padding(5.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Rice Chicken with Chapati",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(vertical = 3.dp, horizontal = 3.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        painter = painterResource(id = R.drawable.ic_clock),
                                        tint = MainOrange,
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        modifier = Modifier.padding(vertical = 3.dp),
                                        text = "3 Mins",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item(span = { GridItemSpan(2) }) {
            Text(
                modifier = Modifier.padding(vertical = 3.dp),
                text = "Meals",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        items(10) {
            MealItem(
                modifier = Modifier.clickable {
                    navigator.openMealDetails()
                }
            )
        }
    }
}