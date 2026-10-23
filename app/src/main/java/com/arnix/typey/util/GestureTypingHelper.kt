package com.arnix.typey.util

typealias PathPoint = com.arnix.typey.lib.util.GestureTypingHelper.PathPoint
typealias KeyBoundingBox = com.arnix.typey.lib.util.GestureTypingHelper.KeyBoundingBox

/**
 * Compatibility object delegating to [com.arnix.typey.lib.util.GestureTypingHelper]
 * for full backward compatibility across the app module and unit tests.
 */
object GestureTypingHelper {

    typealias PathPoint = com.arnix.typey.lib.util.GestureTypingHelper.PathPoint
    typealias KeyBoundingBox = com.arnix.typey.lib.util.GestureTypingHelper.KeyBoundingBox

    val DEFAULT_VOCABULARY = com.arnix.typey.lib.util.GestureTypingHelper.DEFAULT_VOCABULARY

    fun registerWord(word: String) {
        com.arnix.typey.lib.util.GestureTypingHelper.registerWord(word)
    }

    fun addVocabulary(words: List<String>) {
        com.arnix.typey.lib.util.GestureTypingHelper.addVocabulary(words)
    }

    fun resolveTrajectoryToChars(
        points: List<PathPoint>,
        keys: List<KeyBoundingBox>
    ): String {
        return com.arnix.typey.lib.util.GestureTypingHelper.resolveTrajectoryToChars(points, keys)
    }

    fun matchGesture(
        rawChars: String,
        candidateVocabulary: List<String> = DEFAULT_VOCABULARY,
        maxResults: Int = 3
    ): List<String> {
        return com.arnix.typey.lib.util.GestureTypingHelper.matchGesture(rawChars, candidateVocabulary, maxResults)
    }

    fun compressConsecutiveDuplicates(word: String): String {
        return com.arnix.typey.lib.util.GestureTypingHelper.compressConsecutiveDuplicates(word)
    }

    fun clearDynamicVocabulary() {
        com.arnix.typey.lib.util.GestureTypingHelper.clearDynamicVocabulary()
    }
}
