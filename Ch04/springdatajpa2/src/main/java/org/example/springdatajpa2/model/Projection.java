package org.example.springdatajpa2.model;

import org.springframework.beans.factory.annotation.Value;

public class Projection {
    public interface UserSummary{
        String getUsername();
        @Value("#{target.username} #{target.email}")
        String getInfo();

    }

    public static class UsernameOnly{
        private String username;
        public String email;
        public UsernameOnly(String username, String email) {
            this.email = email;
            this.username = username;
        }
        public String getInfo() {
            return username;
        }

        @Override
        public String toString() {
            return "UsernameOnly{" +
                    "username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
