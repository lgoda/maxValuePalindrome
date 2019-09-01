package com.ing.mwchapter.palindrome;

import java.util.Optional;

public class PalindromeServiceImpl implements PalindromeService {

    private final static char MAX_NUMBER_DIGIT = '9';

    @Override
    public Optional<String> highestValuePalindrome(final String number, final int maxAllowedChanges) {

        final char[] palindrome = number.toCharArray();

        final int numberOfChanges = transformToPalindrome(palindrome);

        final int remainingChanges = maxAllowedChanges - numberOfChanges;

        // If remainingChanges is negative we have used
        // more than the allowed changes
        if (remainingChanges < 0) {
            return Optional.empty();
        }

        //Generate the maximum Palindrome given the remaining available changes
        return maxPalindrome(number, palindrome, remainingChanges);
    }


    private Optional<String> maxPalindrome(final String original, final char[] palindrome, int remainingChanges) {
        // We assume that the string we receive in input is already a palindrome without performing any check in regards

        int left = 0;
        int right = original.length() - 1;


        while (left <= right) {
            // At mid character, if maxAllowedChanges>0 then change
            // it to 9
            if (left == right) {
                if (remainingChanges > 0) {
                    palindrome[left] = MAX_NUMBER_DIGIT;
                }
            }

            // If character at lth (same as rth) is
            // less than 9
            if (palindrome[left] < MAX_NUMBER_DIGIT) {
                /* If none of them is changed in the
            previous loop then subtract 2 from remainingChanges
            and convert both to 9 */
                if (remainingChanges >= 2 && palindrome[left] == original.charAt(left)
                        && palindrome[right] == original.charAt(right)) {
                    remainingChanges -= 2;
                    palindrome[left] = palindrome[right] = MAX_NUMBER_DIGIT;
                } /* If one of them is changed in the previous
                loop then subtract 1 from K (1 more is
                subtracted already) and make them 9 */ else if (remainingChanges >= 1 && (palindrome[left] != original.charAt(left)
                        || palindrome[right] != original.charAt(right))) {
                    remainingChanges--;
                    palindrome[left] = palindrome[right] = MAX_NUMBER_DIGIT;
                }
            }
            left++;
            right--;
        }

        StringBuilder highestPalindrome = new StringBuilder();

        for (int i = 0; i < palindrome.length; i++) {
            highestPalindrome.append(palindrome[i]);
        }

        return Optional.of(highestPalindrome.toString());
    }

    private int transformToPalindrome(char[] number) {
        final int size = number.length;

        int left = 0;
        int right = size - 1;
        int numberOfChanges = 0;

        while (left < right) {
            // Replace left and right character with
            // the maximum of both
            if (number[left] != number[right]) {
                number[left] = number[right] = (char) Math.max(number[left],
                        number[right]);
                numberOfChanges++;
            }
            left++;
            right--;
        }
        return numberOfChanges;
    }


    public static void main(String[] args) {
        String s = "11";
        PalindromeService ps = new PalindromeServiceImpl();
        ps.highestValuePalindrome(s, 5);
    }
}
