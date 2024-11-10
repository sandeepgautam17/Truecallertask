package com.task.truecaller

import com.task.truecaller.utils.find15thCharacter
import com.task.truecaller.utils.findEvery15thCharacter
import com.task.truecaller.utils.requestWordCounter
import org.junit.Test

import org.junit.Assert.*

class StringExtensionsTest {

    // Test case for find15thCharacter extension function
    @Test
    fun `test find15thCharacter when string has 15 or more characters`() {
        val input = "This is a test string"
        val result = input.find15thCharacter()

        // 15th character should be ' '
        assertEquals(" ", result)
    }

    @Test
    fun `test find15thCharacter when string has less than 15 characters`() {
        val input = "Short text"
        val result = input.find15thCharacter()

        // No 15th character should return null
        assertNull(result)
    }

    // Test case for findEvery15thCharacter extension function
    @Test
    fun `test findEvery15thCharacter with enough characters`() {
        val input = "This is a test string that will have multiple 15th characters"
        val result = input.findEvery15thCharacter()

        // Every 15th character from the string should be returned as "  l e r"
        assertEquals("  l e r", result)
    }

    @Test
    fun `test findEvery15thCharacter with no enough characters`() {
        val input = "Short text"
        val result = input.findEvery15thCharacter()

        // No 15th characters should return an empty string
        assertEquals("", result)
    }

    // Test case for requestWordCounter extension function
    @Test
    fun `test requestWordCounter with normal input`() {
        val input = "This is a test. This is another test."
        val result = input.requestWordCounter()

        // Expected output for word count
        val expected = "this : 2 \nis : 2 \na : 1 \ntest. : 2 \nanother : 1 \n"
        assertEquals(expected, result)
    }

    @Test
    fun `test requestWordCounter with empty string`() {
        val input = ""
        val result = input.requestWordCounter()

        // Empty string should return an empty string
        assertEquals("", result)
    }

    @Test
    fun `test requestWordCounter with single word input`() {
        val input = "hello"
        val result = input.requestWordCounter()

        // Single word should return the word and count as 1
        val expected = "hello : 1 \n"
        assertEquals(expected, result)
    }

    @Test
    fun `test requestWordCounter with input having multiple spaces`() {
        val input = "hello   hello   world.  testing counter counter."
        val result = input.requestWordCounter()

        // Multiple spaces should still work correctly, counting words as "hello" and "world"
        val expected = "hello : 2 \nworld. : 1 \ntesting : 1 \ncounter : 1 \ncounter. : 1 \n"
        assertEquals(expected, result)
    }
}
