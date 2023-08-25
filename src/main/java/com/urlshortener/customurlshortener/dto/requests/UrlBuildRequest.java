package com.urlshortener.customurlshortener.dto.requests;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class UrlBuildRequest {

    private String actualUrlLink;
    private String urlReplacementLink;
    private String userId;
    private String description;

    public static void main(String[] args) {
        int [] numbers = {2,4,6,8,10,12,14,15,16,17,18};

        reverse(numbers);
    }

    private static void reverse(int[] numbers) {
        int first= 0;
        int last= 0;
        int lastIndex = numbers.length-1;
        for (int i=0; i<numbers.length/2; i++) {
            first = numbers[i];
            last = numbers[lastIndex];
            numbers[lastIndex] = first;
            numbers[i] = last;
            lastIndex--;
        }
        System.out.println(Arrays.toString(numbers));
    }
}
