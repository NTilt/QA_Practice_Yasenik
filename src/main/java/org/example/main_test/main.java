package org.example.main_test;

public class main {
    public static void main(String[] args) {
        System.out.println(luckyTicket("234432"));
    }
    public static boolean luckyTicket(String ticket) {
        int[] ans = new int[ticket.length()];
        for(int i =0; i < ticket.length(); i++) {
            ans[i] = Character.getNumericValue(ticket.charAt(i));
        }
        return ans[0] + ans[1] + ans[2] == ans[3] + ans[4] + ans[5];
    }
}
