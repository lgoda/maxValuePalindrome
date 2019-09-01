package com.ing.mwchapter.palindrome;

import java.util.Optional;

public interface PalindromeService {

    Optional<String> highestValuePalindrome(String number, int maxAllowedChanges);

}
