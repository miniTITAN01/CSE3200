package com.example.lab2part1_needgrading.model

class AllQuestions {
    private val questions = listOf(
        QuestionModel(1, "The main protein ingredient traditionally used in Pho is beef.", listOf("False", "True"), 1),
        QuestionModel(2, "Cumin is essential for an authentic Pho broth flavor.", listOf("False", "True"), 0),
        QuestionModel(3, "Rice Noodles are typically used in Pho.", listOf("False", "True"), 1),
        QuestionModel(4, "The broth for Pho is typically boiled rapidly for 30 minutes.", listOf("False", "True"), 0),
        QuestionModel(5, "Basil is a common garnish added to Pho.", listOf("False", "True"), 1),
        QuestionModel(6, "Ketchup is commonly served with Pho.", listOf("False", "True"), 0),
        QuestionModel(7, "Lime is traditionally served on the side as wedges with Pho.", listOf("False", "True"), 1),
        QuestionModel(8, "Yellow Onion is typically charred and added to Pho broth for flavor.", listOf("False", "True"), 1),
        QuestionModel(9, "The spice packet used in Pho contains cloves, star anise, cinnamon, cardamom, and coriander seeds is known as Pho Spice Packet.", listOf("False", "True"), 1),
        QuestionModel(10, "Ribeye is often used as a topping for Pho, which cooks directly in the hot broth when served.", listOf("False", "True"), 0)
    )

    val size: Int
        get() = questions.size

    fun getQuestionByIndex(index: Int): QuestionModel? = questions.getOrNull(index)
}
