package ru.dgaribov.leetcode.google;

import java.util.*;

/**
 * @author David Garibov
 */
public class UniqueEmailAddresses {
    public static void main(String[] args) {
        UniqueEmailAddresses app = new UniqueEmailAddresses();
        System.out.println(app.numUniqueEmails(new String[]{"testemail@leetcode.com","testemail1@leetcode.com","testemail+david@lee.tcode.com"}));
    }
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();
        for (String email: emails) {
            uniqueEmails.add(compress(email));
        }

        return uniqueEmails.size();
    }

    private String compress(String email) {
        String domain = email.substring(email.indexOf("@"));
        String localName = email.substring(0, email.indexOf("@"));
        if (localName.indexOf("+") > 0) {
            localName = localName.substring(0, localName.indexOf("+"));
        }
        localName = localName.replaceAll("\\.", "");
        return localName + domain;
    }

}
