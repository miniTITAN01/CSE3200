package com.example.lab2part1_needgrading.model

class AllQuestions {
    private val questions = listOf(
        QuestionModel(1, "What is the main protein ingredient traditionally used in Pho?", listOf("Chicken", "Beef", "Tofu", "Pork"), 1),
        QuestionModel(2, "Which spice is essential for an authentic Pho broth flavor?", listOf("Cumin", "Coriander Seeds", "Cardamom", "Star Anise"), 3),
        QuestionModel(3, "What type of noodles are typically used in Pho?", listOf("Udon Noodles", "Rice Noodles", "Egg Noodles", "Soba Noodles"), 1),
        QuestionModel(4, "How is the broth for Pho typically prepared?", listOf("Boiled rapidly for 30 minutes", "Simmered for several hours", "Served cold", "Microwaved in a bowl"), 1),
        QuestionModel(5, "What fresh herb is commonly added as a garnish to Pho?", listOf("Basil", "Cilantro", "Dill", "Mint"), 0),
        QuestionModel(6, "Which of these condiments is commonly served with Pho?", listOf("Ketchup", "Hoisin Sauce", "Mustard", "Mayonnaise"), 1),
        QuestionModel(7, "What is the traditional way to serve lime with Pho?", listOf("Squeeze into the broth", "Served on the side as wedges", "Mixed into the noodles", "Grated zest on top"), 1),
        QuestionModel(8, "What type of onion is typically charred and added to Pho broth for flavor?", listOf("Shallots", "Red Onion", "Green Onion", "Yellow Onion"), 3),
        QuestionModel(9, "What is the name of the spice packet commonly used in Pho that contains cloves, star anise, cinnamon, cardamom, and coriander seeds?", listOf("Curry Powder", "Pho Spice Packet", "Garam Masala", "Bouquet Garni"), 1),
        QuestionModel(10, "What cut of beef is often used as a topping for Pho, which cooks directly in the hot broth when served?", listOf("Ribeye", "Brisket", "Filet Mignon", "Eye of Round"), 3)
        // Add more questions here if needed
    )

    val size: Int
        get() = questions.size

    fun getQuestionByIndex(index: Int): QuestionModel? = questions.getOrNull(index)


    // Implement more functionality as needed...
}
